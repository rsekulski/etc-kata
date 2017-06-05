package org.onzo.sensors.transformations

import org.joda.time.DateTime

/**
  * Created by david.lush on 05/06/2017.
  */
object OnzoKata extends App{
  
  
  def getSample(listComsumption: List[Consumption], id: String): List[Consumption] = 
    listComsumption.filter(_.sensor_id == id) 
  
  
  def getTotalperDay(listComsumption: List[Consumption], day: String): Int = {
    listComsumption.filter(_.timestamp.startsWith(day)).map(_.consumptionWh).sum
  }

  // 2017-03-30T00
//  def getTotalperInterval(listComsumption: List[Consumption], start: String, end: String): Map[String, Int] = {
//
//    val tuples: Seq[(String, Int)] = listComsumption.map(c => (c.timestamp.substring(0, 13), c.consumptionWh))
//
//    val grouped: Map[String, Seq[(String, Int)]] = tuples.groupBy(_._1)
//
//    val mapPerHour = grouped.mapValues(l => l.map(_._2).sum)
//
//    //Array("2017-03-30", "00")
//    val endList: Array[String] = end.split("T")
//    val startList: Array[String] = start.split("T")
//
//
//
//
//
//
//
//
//
//  }
  
  
  



}


