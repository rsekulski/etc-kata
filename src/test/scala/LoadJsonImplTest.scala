import org.onzo.sensors.businessobjects.OnzoSensor
import org.scalatest.FlatSpec

/**
  * Created by david.lush on 18/05/2017.
  */
class LoadJsonImplTest extends FlatSpec {


  val json:String = """{
                          "sensor_id": "1a258617-2d1d-4dd8-9ad0-046ed2881b58",
                          "timestamp": "2017-03-30T00:00:00Z",
                          "consumption_Wh": 150
                        }"""


  val jsonCSV:String = "7dd8f7f3-95e3-4b14-bd8c-1b9eb7056d7a,2017-03-30T00:00:00Z,162"

 /* it should "deserialize data into case class" in {
    import spray.json._
    import org.onzo.sensors.impl.LoadJsonImpl._
    println(json.parseJson.convertTo[OnzoSensor])
  }*/


  it should "return valid onzo sensor" in {
    import org.onzo.sensors.impl.LoadJsonImpl._

    val map = loadData(jsonFile)
    //println(lookUpSensorId(map, "1a258617-2d1d-4dd8-9ad0-046ed2881b58"))

    val onzo = lookUpSensorId(map, "1a258617-2d1d-4dd8-9ad0-046ed2881b58")
    assert(onzo != null)
  }
}
