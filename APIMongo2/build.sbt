// build.sbt
name := "mongo-scala"
version := "0.1"
scalaVersion := "3.6.4"

libraryDependencies ++= Seq(
  "com.softwaremill.sttp.client3" %% "core" % "3.9.1",
  "com.softwaremill.sttp.client3" %% "okhttp-backend" % "3.9.1",
  "io.circe" %% "circe-core" % "0.14.6",
  "io.circe" %% "circe-generic" % "0.14.6",  // Necesario para derivación automática
  "io.circe" %% "circe-parser" % "0.14.6",
  "org.mongodb" % "mongodb-driver-sync" % "4.10.2" // Driver Java sync
)