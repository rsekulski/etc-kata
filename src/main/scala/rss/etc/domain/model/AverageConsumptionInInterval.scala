package rss.etc.domain.model

import java.time.LocalTime

import squants.energy.Energy

/**
  * Created by rsekulski on 08.06.2017.
  */
case class AverageConsumptionInInterval(from: LocalTime,
                                        to: LocalTime,
                                        averageConsumption: Energy)
