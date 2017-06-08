package rss.etc.domain.model


case class SensorReport(sensorId: String,
                       totalConsumption: TotalDailyConsumption,
                       averageConsumptionsInIntervals: Seq[AverageConsumptionInInterval])

