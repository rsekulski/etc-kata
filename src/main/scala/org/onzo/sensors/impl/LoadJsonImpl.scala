package org.onzo.sensors.impl

import java.io.InputStream

import org.onzo.sensors.businessobjects.OnzoSensor
import org.onzo.sensors.traits.{LoadData, OnzoSensorTransformation}
import spray.json.DefaultJsonProtocol

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.io.Source



/**
  * Created by david.lush on 18/05/2017.
  */
object LoadJsonImpl extends DefaultJsonProtocol with LoadData with OnzoSensorTransformation with App  {

  val jsonFile = "src/main/resources/consumption_data.csv"

  implicit val sensorFormat = jsonFormat3(OnzoSensor.apply)

  //: Seq[OnzoSensor]
  override def loadData(path: String): Map[String, OnzoSensor] = {
    val lines = scala.io.Source.fromFile( jsonFile ).getLines
    val header = lines.next()
    val map = new mutable.HashMap[String, OnzoSensor]()

    ///val json = Color("CadetBlue", 95, 158, 160).toJson
    ///val color = json.convertTo[Color]
    lines foreach {
      row =>
        val split = row.split(",")
        val sensor = OnzoSensor(split(0), split(1), split(2).toLong)
        map += sensor.sensor_id -> sensor
    }
    map toMap
  }

  override def lookUpSensorId( map: Map[String, OnzoSensor], id: String): OnzoSensor = {
    map.get(id).get
  }

  val map = loadData(jsonFile)
  println(lookUpSensorId(map, "1a258617-2d1d-4dd8-9ad0-046ed2881b58"))

}
