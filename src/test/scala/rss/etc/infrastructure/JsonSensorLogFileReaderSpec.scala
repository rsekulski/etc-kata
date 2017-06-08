package rss.etc.infrastructure

import java.nio.file.Paths

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Sink
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpec}


class JsonSensorLogFileReaderSpec extends WordSpec with ScalaFutures with MustMatchers with BeforeAndAfterAll {

  implicit val system = ActorSystem("test-system")
  implicit val materializer = ActorMaterializer()

  override def afterAll(): Unit = {
    system.terminate()
  }

  "JsonSensorLogFileReader" should {
    "provide stream of SensorLogs from selected file" in {
      val path = Paths.get("src/test/resources/test_consumption_data.json")

      val sensorLogs = JsonSensorLogFileReader.readFile(path).runWith(Sink.seq).futureValue

      sensorLogs.length mustEqual 2
    }
  }
}
