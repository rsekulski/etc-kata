import java.time.LocalDateTime

import domain.ConsumptionItem
import infrastructure.ConsumptionJsonReader
import org.scalatest.{FlatSpec, Matchers}
import spray.json._

/**
  * Created by david.lush on 08/05/2017.
  */
class JsonTests extends FlatSpec with Matchers with ConsumptionJsonReader {

  "localdatetime" should "serialise correctly" in {
   val ldt: String = "2017-03-30T00:00:00"
    val json = ldt.parseJson
    val c = 0
//      .convertTo[LocalDateTime]
//    println(json)
 }

  it should "serialize properly" in {
    val obj = ConsumptionItem("sensorId", LocalDateTime.MIN, 123.345)
    val obj2 = ConsumptionItem("sensorId", LocalDateTime.MIN, 123.345)
    val json = obj.toJson

    val items = Seq(obj, obj2)
    val jsonList = items.toJson

    jsonList.convertTo[Seq[ConsumptionItem]] shouldEqual items
    json.convertTo[ConsumptionItem] shouldEqual obj

    println(json)
    println(jsonList)
  }

}
