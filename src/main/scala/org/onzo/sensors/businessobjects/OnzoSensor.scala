package org.onzo.sensors.businessobjects

//import java.time.Instant
//Instant.parse ("2015-08-20T08:26:21.000Z").toString

/**
  * Created by david.lush on 18/05/2017.
  */
case class OnzoSensor(sensor_id: String, timestamp: String, consumption_Wh: Long)
