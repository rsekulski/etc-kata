package rss.etc.infrastructure

import java.nio.file.Path

import akka.stream.IOResult
import akka.stream.scaladsl.Source
import rss.etc.domain.model.SensorLog

import scala.concurrent.Future

/**
  * Created by rsekulski on 06.06.2017.
  */
trait SensorLogFileReader {

  def readFile(path: Path): Source[SensorLog, Future[IOResult]]

}
