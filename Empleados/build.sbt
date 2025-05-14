name := "CsvDownloader"
version := "0.1"
scalaVersion := "3.6.4"

libraryDependencies ++= Seq(
  "org.mongodb" % "mongodb-driver-sync" % "5.1.0",
  "io.circe" %% "circe-core" % "0.14.7",
  "io.circe" %% "circe-generic" % "0.14.7",
  "io.circe" %% "circe-parser" % "0.14.7",
  "com.softwaremill.sttp.client3" %% "core" % "3.9.5",
  "org.knowm.xchart" % "xchart" % "3.8.1",
  "de.erichseifert.vectorgraphics2d" % "VectorGraphics2D" % "0.13",
  "org.apache.poi" % "poi-ooxml" % "5.2.5",
  "org.mongodb" % "mongodb-driver-sync" % "5.1.0"
)
