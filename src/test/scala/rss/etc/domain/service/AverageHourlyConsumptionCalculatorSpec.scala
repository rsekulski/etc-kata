package rss.etc.domain.service

import java.time.LocalDateTime

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpec}
import rss.etc.domain.model.{AverageConsumptionInInterval, SensorLog}
import squants.energy.{KilowattHours, WattHours}

import scala.collection.immutable.Seq

class AverageHourlyConsumptionCalculatorSpec extends WordSpec with Matchers with ScalaFutures with BeforeAndAfterAll {

  implicit val system = ActorSystem("test-system")
  implicit val materializer = ActorMaterializer()

  override def afterAll(): Unit = {
    system.terminate()
  }

  "AverageHourlyConsumptionCalculator" should {
    "compute average hourly consumption between provided hours" in {
      val from = LocalDateTime.of(2017, 2, 10, 10, 0)
      val to = from.plusHours(4)

      val logsWithinInterval = Seq(
        SensorLog("sensorId", from, WattHours(10)),
        SensorLog("sensorId", from.plusHours(1), WattHours(5)),
        SensorLog("sensorId", to, WattHours(5))
      )
      val averageConsumption =
        logsWithinInterval.foldLeft(KilowattHours(0))((energy, log) => energy + log.consumption) / logsWithinInterval.length

      val logsOutsideOfInterval = Seq(
        SensorLog("sensorId", from.minusHours(1), WattHours(8)),
        SensorLog("sensorId", to.plusHours(3), WattHours(3))
      )

      val result = Source(logsWithinInterval ++ logsOutsideOfInterval).via(
        AverageHourlyConsumptionCalculator.calculateAverageHourlyConsumptionBetween(from.toLocalTime, to.toLocalTime)
      ).runWith(Sink.head).futureValue

      result shouldEqual AverageConsumptionInInterval(from.toLocalTime, to.toLocalTime, averageConsumption)
    }
  }
}
