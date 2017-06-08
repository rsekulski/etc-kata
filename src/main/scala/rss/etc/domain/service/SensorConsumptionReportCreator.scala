package rss.etc.domain.service

import java.time.LocalTime

import akka.NotUsed
import akka.stream.FlowShape
import akka.stream.scaladsl.{Broadcast, Flow, GraphDSL, ZipN, ZipWith}
import rss.etc.domain.model.{AverageConsumptionInInterval, SensorLog, SensorReport, TotalDailyConsumption}

trait SensorConsumptionReportCreator extends AverageHourlyConsumptionCalculator with SensorIdFilter with TotalConsumptionCalculator {

  def createConsumptionReportForSensor(sensorId: String): Flow[SensorLog, SensorReport, NotUsed] =
    Flow.fromGraph(GraphDSL.create() {implicit builder =>
      import GraphDSL.Implicits._

      val broadcast = builder.add(Broadcast[SensorLog](4))
      val zipAvgConsumptionInIntervals = builder.add(ZipN[AverageConsumptionInInterval](3))
      val createReport = builder.add(ZipWith[TotalDailyConsumption, Seq[AverageConsumptionInInterval], SensorReport](SensorReport(sensorId, _, _)))
      val filter = builder.add(filterById(sensorId))
      val totalConsumption = builder.add(calculateTotalConsumption)
      val avgBetween0and7 = builder.add(calculateAverageHourlyConsumptionBetween(LocalTime.of(0,0), LocalTime.of(7,0)))
      val avgBetween8and15 = builder.add(calculateAverageHourlyConsumptionBetween(LocalTime.of(8,0), LocalTime.of(15,0)))
      val avgBetween16and23 = builder.add(calculateAverageHourlyConsumptionBetween(LocalTime.of(16,0), LocalTime.of(23,0)))

      filter ~> broadcast.in
      broadcast.out(0) ~> avgBetween0and7 ~> zipAvgConsumptionInIntervals
      broadcast.out(1) ~> avgBetween8and15 ~> zipAvgConsumptionInIntervals
      broadcast.out(2) ~> avgBetween16and23 ~> zipAvgConsumptionInIntervals
      broadcast.out(3) ~> totalConsumption ~> createReport.in0
      zipAvgConsumptionInIntervals ~> createReport.in1

      FlowShape(filter.in, createReport.out)
    })

}
