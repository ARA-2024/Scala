import sttp.client3._
import java.nio.file.{Files, Paths}
import scala.io.Source
import scala.jdk.CollectionConverters._
import org.knowm.xchart.{CategoryChart, CategoryChartBuilder, SwingWrapper, BitmapEncoder}
import org.knowm.xchart.VectorGraphicsEncoder
import org.knowm.xchart.VectorGraphicsEncoder.VectorGraphicsFormat
import org.knowm.xchart.BitmapEncoder.BitmapFormat
import java.io.{FileInputStream, FileOutputStream}
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.util.Units
import org.apache.poi.ss.usermodel.Workbook
import com.mongodb.client.{MongoClients, MongoCollection, MongoDatabase}
import org.bson.Document
import scala.io.StdIn.readLine
import io.circe._, io.circe.parser._, io.circe.generic.auto._
import scala.collection.mutable.Map

object Downloader:
  def main(args: Array[String]): Unit =
    val url = "https://knuth.uca.es/moodle/pluginfile.php/12922/mod_folder/content/0/resuelto1.csv?forcedownload=1"
    val fileName = "resuelto1.csv"
    implicit val backend = HttpURLConnectionBackend()
    val request = basicRequest.get(uri"$url")
    val response = request.send()
    response.body match
      case Right(data) =>
        Files.write(Paths.get(fileName), data.getBytes)
        println(s"Archivo descargado correctamente como $fileName")
      case Left(error) =>
        println(s"Error al descargar el archivo: $error")

    val mDB = "workersDB"
    val mCollection = "workers"

    var salir = false
    while !salir do
      println(
        """
        |=== MENÚ PRINCIPAL ===
        |1. Crear gráfica en un documento Word desde el fichero csv
        |2. Escribir en MongoDB los datos del csv
        |3. Crear gráfica en un documento Word leyendo los datos de MongoDB
        |4. Salir
        |Seleccione opción:
        """.stripMargin)
      readLine() match
        case "1" => graficaDesdeCSV(fileName)
        case "2" => CSVMongoDB()
        case "3" => graficaMongoDB(mDB, mCollection)
        case "4" => salir = true
        case _   => println("Opción no válida.")
    
    println("Programa finalizado.")

    //var imgFile =" "

    def docGrafica(imgFile : String): Unit =
      // Crea un documento DOCX e inserta la imagen
      println(imgFile)
      val doc = new XWPFDocument()
      val docFile = imgFile.dropRight(3) + "docx"
      println(docFile)

      val out = new FileOutputStream(docFile)
      val paragraph = doc.createParagraph()
      val run = paragraph.createRun()

      val is = new FileInputStream(imgFile)
      run.addPicture(is, Workbook.PICTURE_TYPE_PNG, imgFile, Units.toEMU(500), Units.toEMU(300))
      is.close()
      doc.write(out)
      out.close()
      doc.close()
      println("¡Documento DOCX creado con la gráfica! Puedes abrirlo con WordPad o Word.")

    def graficaDesdeCSV(fileName : String): Unit =
      val lines = Source.fromFile(fileName).getLines().drop(1).toList
      val trabajador = lines.map(_.split(",")(0))
      val ntienda = lines.map(_.split(",")(5).toInt)

      val chart: CategoryChart = new CategoryChartBuilder()
        .width(1000).height(600)
        .title("Número de Tienda por Trabajador")
        .xAxisTitle("Trabajador")
        .yAxisTitle("Número de Tienda")
        .build()

      chart.addSeries("Tiendas", trabajador.asJava, ntienda.map(_.asInstanceOf[Number]).asJava)
      new SwingWrapper(chart).displayChart()
      Thread.sleep(200)

      // Guardar como SVG
      VectorGraphicsEncoder.saveVectorGraphic(chart, "grafica_tiendas", VectorGraphicsFormat.SVG)
      println("Gráfica guardada como grafica_tiendas.svg")

      // Exporta la gráfica como imagen PNG
      val imgFile = "grafica_tiendas.png"
      BitmapEncoder.saveBitmap(chart, "grafica_tiendas", BitmapFormat.PNG)
      // Crea un documento DOCX e inserta la imagen
      docGrafica(imgFile)
          
    def CSVMongoDB() : Unit =
      println("Se ha guardado el fichero csv en MongoDB.")

    def graficaMongoDB(mDB: String, mCollection : String): Unit =
      // Crear cliente y conectarse a la base de datos
      val mongoClient = MongoClients.create("mongodb://localhost:27017")
      val database = mongoClient.getDatabase(mDB)
      val collection: MongoCollection[Document] = database.getCollection(mCollection)

      // Leer documentos de MongoDB
      println("MongoDB.")
      val documentos = collection.find().iterator()
      println("\nTrabajadores en la base de datos:")
      var mapaMutable: Map[String, Int] = Map()
      while (documentos.hasNext) {
        val doc = documentos.next()
        println(s"- ${doc.getString("trabajador")} ${doc.getInteger("nalmacen")}")
        mapaMutable += (doc.getString("trabajador") -> doc.getInteger("nalmacen")) 
      }
      println(mapaMutable)

      val trabajadores = mapaMutable.keys.take(3).toList
      val almacenes = mapaMutable.values.take(3).toList
      println(trabajadores)
    
      // Cerrar cliente
      mongoClient.close()

      // Crea la gráfica
      val chart: CategoryChart = new CategoryChartBuilder()
        .width(1000).height(600)
        .title("Número de almacén por trabajador")
        .xAxisTitle("Trabajador")
        .yAxisTitle("Almacén")
        .build()

      chart.addSeries("Número de almacén", trabajadores.asJava, almacenes.map(_.asInstanceOf[Number]).asJava)
    
      // Exporta la gráfica como imagen PNG
      val imgFile = "grafica_almacenes.png"
      BitmapEncoder.saveBitmap(chart, "grafica_almacenes", BitmapFormat.PNG)
    
      // Crea un documento DOCX e inserta la imagen
      docGrafica(imgFile)

    

    