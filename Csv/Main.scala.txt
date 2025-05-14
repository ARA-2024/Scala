import com.mongodb.client.{MongoClients, MongoCollection, MongoDatabase}
import org.bson.Document
import scala.io.StdIn

object MongoDBCRUD {
  private val client = MongoClients.create("mongodb://localhost:27017")
  private val database: MongoDatabase = client.getDatabase("biblioteca")
  private val collection: MongoCollection[Document] = database.getCollection("libros")

  case class Libro(titulo: String, isbn: String, autor: String) //Es un tipo especial de clase en Scala diseñada para modelar datos inmutables.

  val doc1 = new Document()
      .append("titulo", "titulo1")
      .append("isbn", "123")
      .append("autor", "autor")

private def definirLibro(): Libro = {
    val titulo = firstBooks(0)
    val isbn = firstBooks(1)
    val autor = firstBooks(2)
    Libro(titulo, isbn, autor)
  }

  val firstBooks = Array("libro1", "isbn1", "Autor1","libro2", "isbn2", "Autor2","libro3", "isbn3", "Autor3","libro4", "isbn4", "Autor4","libro5", 
  "isbn5", "Autor5","libro6", "isbn6", "Autor6","libro7", "isbn7", "Autor7","libro8", "isbn8", "Autor8","libro9", "isbn9", "Autor9","libro10", "isbn10", "Autor10")
  var counter: Int = 0
// Insertar 10 libros en MongoDB
  for (i <- 0 until firstBooks.length) {
       doc1 = new Document("titulo", firstBooks(i))
      .append("isbn",firstBooks(i+1))
      .append("autor",firstBooks(i+2))
      collection.insertOne(doc)
      println("Documento insertado")
      counter=counter+2
   }

  def main(args: Array[String]): Unit = {
    var salir = false
    while (!salir) {
      mostrarMenu()
      StdIn.readLine() match {
        case "1" => crearLibro()
        case "2" => listarLibros()
        case "3" => buscarPorISBN()
        case "4" => actualizarLibro()
        case "5" => eliminarLibro()
        case "6" => salir = true
        case _ => println("Opción inválida")
      }
    }
    client.close()
  }

  private def mostrarMenu(): Unit = {
    println("\n=== MENU CRUD LIBROS ===")
    println("1. Crear libro")
    println("2. Listar todos los libros")
    println("3. Buscar libro por ISBN")
    println("4. Actualizar libro")
    println("5. Eliminar libro")
    println("6. Salir")
    print("Seleccione opcion: ")
  }

  private def crearLibro(): Unit = {
    val libro = solicitarDatosLibro()
    val doc = new Document()
      .append("titulo", libro.titulo)
      .append("isbn", libro.isbn)
      .append("autor", libro.autor)
    
    collection.insertOne(doc)
    println("\nLibro creado exitosamente!")
  }

  private def listarLibros(): Unit = {
    println("\n=== LISTA DE LIBROS ===")
    val resultados = collection.find().iterator()
    while (resultados.hasNext) {
      mostrarLibro(resultados.next())
    }
  }

  private def buscarPorISBN(): Unit = {
    print("Ingrese ISBN a buscar: ")
    val isbn = StdIn.readLine()
    val filtro = new Document("isbn", isbn)
    val libro = collection.find(filtro).first()
    
    if (libro != null) {
      println("\nLibro encontrado:")
      mostrarLibro(libro)
    } else {
      println("\nLibro no encontrado")
    }
  }

  private def actualizarLibro(): Unit = {
    print("Ingrese ISBN del libro a actualizar: ")
    val isbn = StdIn.readLine()
    val filtro = new Document("isbn", isbn)
    val libroExistente = collection.find(filtro).first()
    
    if (libroExistente != null) {
      val nuevosDatos = solicitarDatosLibro()
      val actualizacion = new Document("$set", new Document()
        .append("titulo", nuevosDatos.titulo)
        .append("autor", nuevosDatos.autor)
        .append("isbn", nuevosDatos.isbn))
      
      collection.updateOne(filtro, actualizacion)
      println("\nLibro actualizado correctamente")
    } else {
      println("\nLibro no encontrado")
    }
  }

  private def eliminarLibro(): Unit = {
    print("Ingrese ISBN del libro a eliminar: ")
    val isbn = StdIn.readLine()
    val filtro = new Document("isbn", isbn)
    val resultado = collection.deleteOne(filtro)
    
    if (resultado.getDeletedCount > 0) {
      println("\nLibro eliminado correctamente")
    } else {
      println("\nLibro no encontrado")
    }
  }

  private def solicitarDatosLibro(): Libro = {
    print("Titulo: ")
    val titulo = StdIn.readLine()
    print("ISBN: ")
    val isbn = StdIn.readLine()
    print("Autor: ")
    val autor = StdIn.readLine()
    
    Libro(titulo, isbn, autor)
  }

  private def mostrarLibro(doc: Document): Unit = {
    println(s"""
      Titulo: ${doc.getString("titulo")}
      ISBN: ${doc.getString("isbn")}
      Autor: ${doc.getString("autor")}
      ---------------------------""")
  }
}

