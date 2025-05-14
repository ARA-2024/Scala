import sttp.client3._
import sttp.client3.okhttp.OkHttpSyncBackend
import io.circe._
import io.circe.parser._
import io.circe.generic.auto._  // Importación clave para derivación automática
import com.mongodb.client.{MongoClients, MongoCollection}
import org.bson.Document

case class Post(
  userId: Int,
  id: Int,
  title: String,
  body: String,
)

object Main {
  def main(args: Array[String]): Unit = {
    val backend = OkHttpSyncBackend()
    
    val request = basicRequest
      .get(uri"https://jsonplaceholder.typicode.com/posts/1")
      .header("User-Agent", "Scala-API-Example")
      .header("Accept", "application/vnd.github.v3+json")
      
    val response = request.send(backend)
    
     response.body match {
      case Right(json) =>
        parse(json) match {
          case Right(parsedJson) =>
            parsedJson.as[Post] match {
              case Right(post) =>
                // Corregido: post es accesible dentro de este bloque
                println("Datos")
                println(s"userId: ${post.userId}")
                println(s"id: ${post.id}")
                println(s"title: ${post.title}")
                println(s"body: ${post.body}")

                // Crear cliente y conectarse a la base de datos
                val mongoClient = MongoClients.create("mongodb://localhost:27017")
                val database = mongoClient.getDatabase("postsDB")
                 val collection: MongoCollection[Document] = database.getCollection("posts")

                // Insertar un documento
                val postm = new Document("userId", post.userId)
                 .append("id", post.id)
                  .append("title", post.title)
                  .append("body", post.body)
                collection.insertOne(postm)
                println("Documento insertado")

                // Leer documentos
                val documentos = collection.find().iterator()
                println("\nPost en la base de datos:")
                while (documentos.hasNext) {
                  val doc = documentos.next()
                  println(s"- ${doc.getInteger("userId")}, ${doc.getInteger("id")}, ${doc.getString("title")}, ${doc.getString("body")}")
                }

                // Cerrar cliente
                mongoClient.close()
              
              case Left(decodingError) =>  // Renombrado para evitar conflicto
                println(s"Error parseando JSON: $decodingError")
            }
          case Left(parsingError) =>
            println(s"Error en el formato JSON: $parsingError")
        }
      case Left(requestError) =>
        println(s"Error en la solicitud: $requestError")
    }
    
    backend.close()

  }
}
