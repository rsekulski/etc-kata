package org.onzo.sensors.traits

import org.onzo.sensors.businessobjects.OnzoSensor

/**
  * Created by david.lush on 18/05/2017.
  */
trait LoadData {

  def loadData(path:String): Map[String, OnzoSensor]
}
