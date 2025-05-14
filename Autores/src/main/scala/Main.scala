import scala.io.StdIn
import play.api.libs.json._
import com.mongodb.client.{MongoClients, MongoCollection}
import org.bson.Document
import upickle.default.{read => uread, write => uwrite, ReadWriter, macroRW} // Importamos macroRW para generar automáticamente el ReadWriter
import os.{read => _, _} // Excluimos el método read de os-lib
import scala.collection.mutable.ArrayBuffer
import play.api.libs.json.Json


// Definimos la clase AutorLibros
case class AutorLibros(autorId:Int, autor: String, libros: String)
// Definir que el formato del JSON sea el de la clase AutorLibros
implicit val autorLibrosFormat: OFormat[AutorLibros] = Json.format[AutorLibros]
// Creamos un objeto companion para AutorLibros y definimos un ReadWriter implícito
object AutorLibros {
  implicit val rw: ReadWriter[AutorLibros] = macroRW
}

object Main {
  def main(args: Array[String]): Unit = {
    // Solicitar datos al usuario
    print("Introduce Autor: ")
    var autor:String = StdIn.readLine()

    print("Introduce Título del libro: ")
    var libro:String = StdIn.readLine()

    // Crear cliente y conectarse a la base de datos
    val mongoClient = MongoClients.create("mongodb://localhost:27017")
    val database = mongoClient.getDatabase("autoresDB")
    val collection: MongoCollection[Document] = database.getCollection("autores")

    // Insertar un documento
    var autorDoc:Document = new Document("autor", autor)
      .append("libro", libro)
    collection.insertOne(autorDoc)
    println("Documento insertado")

    // Solicitar datos al usuario
    print("Introduce Autor: ")
    autor = StdIn.readLine()

    print("Introduce Título del libro: ")
    libro = StdIn.readLine()

    // Crear un objeto JSON con los datos proporcionados
    var json = Json.obj(
      "autor" -> autor,
      "libro" -> libro
    )

    // Mostrar el JSON en la consola
    println("JSON generado:")
    println(Json.prettyPrint(json))

    // Insertar un documento
    autorDoc = new Document("autor", autor)
      .append("libro", libro)
    collection.insertOne(autorDoc)
    println("Documento insertado")

    // Ruta del archivo JSON con un solo libro por autor (en la misma carpeta que esté el programa)
    val rutaArchivo = os.pwd / "autores1.json"

    // Leer el contenido del archivo JSON
    val contenidoJSON = os.read(rutaArchivo)

    println(contenidoJSON)

    // Convierte el string a JSON
    val jsValue = Json.parse(contenidoJSON)
    
    // Convierte el jsValue en un ArrayBuffer[AutorLibros]
    val autores: ArrayBuffer[AutorLibros] = jsValue.as[ArrayBuffer[AutorLibros]]
    
    // Imprime el array de objetos
    autores.foreach(autores => println(s"${autores.autorId} - ${autores.autor} - ${autores.libros}"))

    println(autores)
   
    // Crear un objeto JSON con los datos proporcionados
    var jsonNew1 = Json.obj(
      "autorId" -> autores(0).autorId,
      "autor" -> autores(0).autor,
      "libros" -> autores(0).libros
    )

    // Mostrar el JSON en la consola
    println("JSON generado:")
    println(Json.prettyPrint(jsonNew1))

    // Insertar un documento
    var autorDoc1 = new Document("autorId", autores(0).autorId)
      .append("autor", autores(0).autor)
      .append("libros", autores(0).libros)
    collection.insertOne(autorDoc1)
    println("Documento insertado")
    
    // Cerrar cliente
    mongoClient.close()

  }
}
