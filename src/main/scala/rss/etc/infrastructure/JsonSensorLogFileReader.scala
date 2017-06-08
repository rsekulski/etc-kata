package rss.etc.infrastructure

import java.nio.file.Path

import akka.stream.IOResult
import akka.stream.scaladsl.{FileIO, Source}
import de.knutwalker.akka.stream.support.CirceStreamSupport
import rss.etc.infrastructure.SensorLogJsonDecoder.decodeSensorLog
import rss.etc.domain.model.SensorLog

import scala.concurrent.Future


trait JsonSensorLogFileReader extends SensorLogFileReader {

  override def readFile(path: Path): Source[SensorLog, Future[IOResult]] =
    FileIO.fromPath(path).via(CirceStreamSupport.decode[List[SensorLog]]).mapConcat(identity)
}

object JsonSensorLogFileReader extends JsonSensorLogFileReader