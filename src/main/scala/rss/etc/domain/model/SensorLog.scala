package rss.etc.domain.model

import java.time.LocalDateTime

import squants.energy.Energy


case class SensorLog(sensorId: String,
                     timestamp: LocalDateTime,
                     consumption: Energy )

