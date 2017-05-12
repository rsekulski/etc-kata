import domain.{ConsumptionItem, ConsumptionList}
import infrastructure.ConsumptionJsonReader
import org.scalatest.{FlatSpec, Matchers}
import spray.json._

/**
  * Created by david.lush on 08/05/2017.
  */
class InputReaderTest extends FlatSpec with Matchers with ConsumptionJsonReader {

  "json read" should "serialise into objects properly" in {
    val input = scala.io.Source.fromFile("src/main/resources/consumption_data.json")
      .mkString
      .parseJson

    val objs = input
      .convertTo[Seq[ConsumptionItem]]

    val size = objs.size
    objs shouldBe a[Seq[ConsumptionItem]]
    size shouldEqual 72
  }

  "filter" should "filter items correctly" in {
    val inputs = scala.io.Source.fromFile("src/main/resources/consumption_data.json")
      .mkString
      .parseJson
      .convertTo[Seq[ConsumptionItem]]

    val id = "b08c6195-8cd9-43ab-b94d-e0b887dd73d2"
    val filtered = inputs.filter(_.sensor_id == id)
    filtered.size shouldEqual 24
  }

  "sum of sensor" should "work correctly" in {
    val inputs = scala.io.Source.fromFile("src/main/resources/consumption_data.json")
      .mkString
      .parseJson
      .convertTo[Seq[ConsumptionItem]]

    val id = "b08c6195-8cd9-43ab-b94d-e0b887dd73d2"
    val filtered = inputs.filter(_.sensor_id == id)
    val sumOfWhatts = filtered.map(_.consumption_Wh).sum / 1000

    filtered.size shouldEqual 24
    sumOfWhatts shouldEqual 10.713
  }

  "avg hourly consumption of sensor" should "work correctly" in {
    val inputs = scala.io.Source.fromFile("src/main/resources/consumption_data.json")
      .mkString
      .parseJson
      .convertTo[Seq[ConsumptionItem]]

    val id = "b08c6195-8cd9-43ab-b94d-e0b887dd73d2"
    val filtered = inputs.filter(_.sensor_id == id)
    val range = Seq(0,1,2,3,4,5,6,7)
    val listOfHours =
      filtered
      .groupBy(_.timestamp.getHour)

    val filteredByHour = listOfHours.filter{x =>
       range.contains(x._1)
    }

    val avg = filteredByHour
      .flatMap(_._2.map(_.consumption_Wh)).sum / filteredByHour.size / 1000

    avg shouldEqual 0.322875
  }
}
