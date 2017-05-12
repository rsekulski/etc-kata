package domain

import java.time.LocalDateTime

/**
  * Created by david.lush on 08/05/2017.
  */
case class ConsumptionItem(sensor_id: String,
                            timestamp: LocalDateTime,
                            consumption_Wh: Double)

case class ConsumptionList(items: Seq[ConsumptionItem])