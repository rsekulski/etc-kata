name := "extract"

version := "1.0"

scalaVersion := "2.12.1"

scalacOptions ++= Seq(
  "-feature",
  "language:implicitConversions"
)
libraryDependencies ++= {

  val akkaVersion = "2.5.2"
  val streamJsonVersion = "3.3.0"
  val squantsVersion = "1.3.0"
  val circeVersion = "0.7.0"
  val scalatestVersion = "3.0.1"

  Seq(
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "de.knutwalker" %% "akka-stream-json" % streamJsonVersion,
    "de.knutwalker" %% "akka-stream-circe" % streamJsonVersion,
    "org.typelevel"  %% "squants" % squantsVersion,
    "io.circe" %% "circe-core" % circeVersion,
    "io.circe" %% "circe-generic" % circeVersion,
    "io.circe" %% "circe-parser" % circeVersion,
    "org.scalatest" %% "scalatest" % scalatestVersion % Test
  )
}