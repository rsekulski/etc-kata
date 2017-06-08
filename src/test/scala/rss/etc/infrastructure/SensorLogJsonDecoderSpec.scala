package rss.etc.infrastructure

import java.time.LocalDateTime

import io.circe.parser.decode
import org.scalatest.{MustMatchers, WordSpec}
import rss.etc.domain.model.SensorLog
import squants.energy.WattHours


class SensorLogJsonDecoderSpec extends WordSpec with MustMatchers {


  "SensorLogJsonDecoder" should {
    "be able to decode SensorLog from JSON" in {

      val sensorId = "1a258617-2d1d-4dd8-9ad0-046ed2881b58"
      val timestamp = "2017-03-30T00:00:00Z"
      val consumption = 150
      val sensorLogJson = s"""{
                            |    "sensor_id": "$sensorId",
                            |    "timestamp": "$timestamp",
                            |    "consumption_Wh": $consumption
                            |  }""".stripMargin


      import rss.etc.infrastructure.SensorLogJsonDecoder.decodeSensorLog

      val result = decode[SensorLog](sensorLogJson)

      result mustEqual Right(SensorLog(
        sensorId,
        LocalDateTime.of(2017, 3, 30, 0, 0, 0),
        WattHours(consumption)
      ))
    }
  }

}
