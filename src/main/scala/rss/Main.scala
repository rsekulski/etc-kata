package rss

import java.nio.file.Paths

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import rss.etc.domain.service.SensorConsumptionReportCreator
import rss.etc.infrastructure.JsonSensorLogFileReader


object Main extends App with JsonSensorLogFileReader with SensorConsumptionReportCreator {

  implicit val system = ActorSystem("system")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  val path = Paths.get("src/main/resources/consumption_data.json")
  val sensorId = "b08c6195-8cd9-43ab-b94d-e0b887dd73d2"

  readFile(path)
    .via(createConsumptionReportForSensor(sensorId))
    .runForeach(println)
    .map(_ => system.terminate())
}
