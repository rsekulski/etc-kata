package rss.etc.infrastructure

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import io.circe.Decoder
import rss.etc.domain.model.SensorLog
import squants.energy.{Energy, WattHours}


trait SensorLogJsonDecoder {

  private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
  implicit val decodeLocalDateTime: Decoder[LocalDateTime] = Decoder.decodeString.map(s => LocalDateTime.parse(s, formatter))
  implicit val decodeWattHour: Decoder[Energy] =
    Decoder.decodeInt.map(WattHours(_))

  implicit val decodeSensorLog: Decoder[SensorLog] =
    Decoder.forProduct3("sensor_id", "timestamp", "consumption_Wh")(SensorLog.apply)
}

object SensorLogJsonDecoder extends SensorLogJsonDecoder
