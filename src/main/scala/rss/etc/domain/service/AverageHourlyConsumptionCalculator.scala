package rss.etc.domain.service

import java.time.LocalTime

import akka.NotUsed
import akka.stream.scaladsl.Flow
import rss.etc.domain.model.{AverageConsumptionInInterval, SensorLog}
import squants.energy.{Energy, KilowattHours, WattHours}


trait AverageHourlyConsumptionCalculator {

  def calculateAverageHourlyConsumptionBetween(from: LocalTime, to: LocalTime): Flow[SensorLog, AverageConsumptionInInterval, NotUsed] = {
    filterLogsByHours(from, to)
      .via(computeAverageConsumption)
      .map(summary =>
        AverageConsumptionInInterval(from, to, summary.totalConsumption / summary.logsNo in KilowattHours)
      )
  }

  private def filterLogsByHours(from: LocalTime, to: LocalTime): Flow[SensorLog, SensorLog, NotUsed] =
    Flow[SensorLog].filter(log => logBetween(log, from, to))

  private def logBetween(sensorLog: SensorLog, start: LocalTime, end: LocalTime): Boolean = {
    val time = sensorLog.timestamp.toLocalTime
    time.compareTo(start) >= 0 && time.compareTo(end) <= 0
  }

  private def computeAverageConsumption: Flow[SensorLog, ConsumptionSummary, NotUsed] =
    Flow[SensorLog].fold[ConsumptionSummary](ConsumptionSummary(0,WattHours(0)))((summary, sensor) =>
      ConsumptionSummary(summary.logsNo +1, summary.totalConsumption + sensor.consumption)
    )


  case class ConsumptionSummary(logsNo: Int, totalConsumption: Energy)

}

object AverageHourlyConsumptionCalculator extends AverageHourlyConsumptionCalculator