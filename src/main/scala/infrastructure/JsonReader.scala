package infrastructure

import java.time.LocalDateTime

import domain.{ConsumptionItem, ConsumptionList}
import spray.json.{DefaultJsonProtocol, DeserializationException, JsString, JsValue, RootJsonFormat}

/**
  * Created by david.lush on 08/05/2017.
  */
trait ConsumptionJsonReader extends DefaultJsonProtocol {

  implicit object DateJsonFormat extends RootJsonFormat[LocalDateTime] {

//    private val parserISO : DateTimeFormatter = ISODateTimeFormat.dateTimeNoMillis();

    override def write(obj: LocalDateTime) = JsString(obj.toString)

    override def read(json: JsValue) : LocalDateTime = {
      json match {
        case JsString(s) =>
          import java.time.format.DateTimeFormatter
          val formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ssz")
          LocalDateTime.parse(s.replace("T",""), formatter)
        case _ => throw DeserializationException("cannot deserialize ...")
      }
    }
  }


  implicit val consumptionItemFormat = jsonFormat3(ConsumptionItem)
  implicit val consumptionItemSeqFormat = jsonFormat1(ConsumptionList)

}
