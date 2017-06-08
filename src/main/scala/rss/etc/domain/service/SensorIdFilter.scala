package rss.etc.domain.service

import akka.NotUsed
import akka.stream.scaladsl.Flow
import rss.etc.domain.model.SensorLog

trait SensorIdFilter {

  def filterById(sensorId: String): Flow[SensorLog, SensorLog, NotUsed] =
    Flow[SensorLog].filter(_.sensorId == sensorId)
}
