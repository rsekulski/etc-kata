import domain.ConsumptionItem
import infrastructure.ConsumptionJsonReader
import spray.json._

/**
  * Created by david.lush on 08/05/2017.
  */
class main extends App with ConsumptionJsonReader {

  val inputs = scala.io.Source.fromFile("src/main/resources/consumption_data.json")
    .mkString
    .parseJson
    .convertTo[Seq[ConsumptionItem]]



}
