import org.joda.time.DateTime
import org.scalatest.{FlatSpecLike, Matchers}

class ConsumptionTest extends FlatSpecLike with Matchers{

  behavior of "read file"

  it should "read json" in {

    println(MeterReaderImpl.loadFile("src/main/resources/consumption_data.json"))
  }
  behavior of "read averag "
  it should "somthing " in {
    true shouldBe true

  }

  val id= "monkeys"
  val from = new DateTime(2017, 1,1,1,0)
  val Hour1Read = 4000

  val data = List(
    SensorReadHourly(id, from, Hour1Read),
    SensorReadHourly(id, from.plus(3), Hour1Read),
    SensorReadHourly(id, from.plus(10), 2)
  )

  it should "find the range reading for 1 hour if from and to are the same" in {
    Consumption.getDataPerkWh(data, id, from, from) shouldEqual(2)
  }
  it should "find the average with from and to" in {
    Consumption.getDataPerkWh(data, id, from, from.plus(3)) shouldEqual(2)
  }

}
