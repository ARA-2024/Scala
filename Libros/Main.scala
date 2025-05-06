import scala.io.StdIn
import scala.io.Source
import com.mongodb.client.{MongoClients, MongoCollection}
import org.bson.Document
import upickle.default.{read => uread, write => uwrite, ReadWriter, macroRW} // Importamos macroRW para generar automáticamente el ReadWriter
import os.{read => _, _} // Excluimos el método read de os-lib

case class Autor(nombre: String, apellidos: String, n_libros: String)
case class Libro(nombreLibro: String, n_paginas: String)

object Main {
  def main(args: Array[String]): Unit = {
    // Ruta al archivo CSV 
    val ruta = "autores.csv"
    // Leer el archivo, saltando la cabecera
    val autores: List[Autor] = Source.fromFile(ruta)
      .getLines()
      .drop(1) // Saltar la cabecera
      .map { linea =>
        val Array(nombre, apellidos, n_libros) = linea.split(",", 3)
        Autor(nombre.trim, apellidos.trim, n_libros.trim)
      }
      .toList
    // Mostrar los datos leídos
    println(autores)
    //autores.foreach(println)
    
   // Ruta al archivo CSV 
    val ruta2 = "libros.csv"
    // Leer el archivo, saltando la cabecera
    val libros: List[Libro] = Source.fromFile(ruta)
      .getLines()
      .drop(1) // Saltar la cabecera
      .map { linea =>
        val Array(nombreLibro, n_paginas) = linea.split(",", 2)
        Libro(nombreLibro.trim, n_paginas.trim)
      }
      .toList
    // Mostrar los datos leídos
    libros.foreach(println)

    // Crear cliente y conectarse a la base de datos
    val mongoClient = MongoClients.create("mongodb://localhost:27017")
    val database = mongoClient.getDatabase("csvDB")
    val collection: MongoCollection[Document] = database.getCollection("autoresCSV")

    // Insertar un documento
    var autoresDoc:Document = new Document("nombre", autores(0).nombre)
      .append("apellidos", autores(0).apellidos)
      .append("n_libros", autores(0).n_libros)
    collection.insertOne(autoresDoc)
    println("Documento insertado")

    // Cerrar cliente
    mongoClient.close()



  }
}
