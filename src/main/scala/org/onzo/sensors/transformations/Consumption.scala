package org.onzo.sensors.transformations

import spray.json.DefaultJsonProtocol

case class Consumption(
  sensor_id: String,
  timestamp: String,
  consumptionWh: Int
)
object Consumption  extends DefaultJsonProtocol{
  implicit val comsumptionFormat = jsonFormat3(Consumption.apply)
}
