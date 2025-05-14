import org.apache.spark.sql.{DataFrame, SparkSession}

// Función para leer un CSV
def leerCSV(ruta: String)(implicit spark: SparkSession): DataFrame = {
  spark.read
    .option("header", "true")
    .option("inferSchema", "true")
    .csv(ruta)
}

// Función para hacer un fetch a una API que devuelve JSON
def fetchJSON(url: String)(implicit spark: SparkSession): DataFrame = {
  import scala.io.Source
  import spark.implicits._
  val json = Source.fromURL(url).mkString
  val ds = Seq(json).toDS()
  spark.read.json(ds)
}

// Función para leer de MongoDB (requiere el conector en el classpath)
def leerMongo(uri: String, db: String, coleccion: String)(implicit spark: SparkSession): DataFrame = {
  spark.read
    .format("mongodb")
    .option("uri", uri)
    .option("database", db)
    .option("collection", coleccion)
    .load()
}