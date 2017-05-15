import org.joda.time.DateTime

import scala.collection.immutable

case class SensorReadHourly(sensor:String, timeStamp:DateTime, consumption_Wh: Int )

trait MeterReader {
  def loadFile(path:String): List[SensorReadHourly]
}

object MeterReaderImpl {
  def loadFile(path:String): List[SensorReadHourly]  =
    val file: immutable.Seq[String] = scala.io.Source.fromFile(path).getLines.toList.tail
    file.map{x =>
      val line = x.split(',')
      SensorReadHourly(line(0), new DateTime(line(1)), line(2).toInt)
    }
  }
}

object Consumption {

   def convertToKWh(wh: Int): Int ={
    wh / 1000
  }

  def getDataPerkWh(data: List[SensorReadHourly], id:String, from:DateTime, to:DateTime) ={
    val filtered = data.filter(x => from.compareTo(x.timeStamp) <= 0 && to.compareTo(x.timeStamp) <= 0)
    filtered.map(x => convertToKWh(x.consumption_Wh)).sum / filtered.length
  }




}
