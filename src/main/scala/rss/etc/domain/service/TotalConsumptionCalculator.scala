package rss.etc.domain.service

import akka.NotUsed
import akka.stream.scaladsl.Flow
import rss.etc.domain.model.{SensorLog, TotalDailyConsumption}
import squants.energy.KilowattHours

trait TotalConsumptionCalculator {

  def calculateTotalConsumption: Flow[SensorLog, TotalDailyConsumption, NotUsed] =
    Flow[SensorLog].fold(KilowattHours(0))((total, sensor) => total + sensor.consumption).map(TotalDailyConsumption)
}
