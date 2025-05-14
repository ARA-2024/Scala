import sttp.client3._
import sttp.client3.okhttp.OkHttpSyncBackend
import io.circe._
import io.circe.parser._
import io.circe.generic.auto._  // Importación clave para derivación automática
import scala.collection.mutable.ArrayBuffer
import com.mongodb.client.{MongoClients, MongoCollection}
import org.bson.Document

case class Post(
  userId: Int,
  id: Int,
  title: Option[String],
  `body`: Option[String]
)

object Main {
  
  def mostrarPost(post: Post): Unit = {
    println(s"Datos del Post ID ${post.id}:")
    println(s"userId: ${post.userId}")
    println(s"id: ${post.id}")
    println(s"Title: ${post.title.getOrElse("Title No disponible")}")
    println(s"Body: ${post.body.getOrElse("Body No disponible")}")
  }

  def insertarPost(post: Post, collection:MongoCollection[Document]): Unit = { 
    val postm = new Document("userId", post.userId)  // Este elemento no tiene nulls
      .append("id", post.id)   // Este elemento no tiene nulls
      .append("title", post.title.orNull)
      .append("body", post.body.orNull)
    collection.insertOne(postm)
    }

  def main(args: Array[String]): Unit = {
    val backend = OkHttpSyncBackend()
    
    val request = basicRequest
      .get(uri"https://jsonplaceholder.typicode.com/posts")
      .header("User-Agent", "Scala-API")
           
    val response = request.send(backend)
    
     response.body match {
      case Right(json) =>
        parse(json) match {
          case Right(parsedJson) =>
            parsedJson.as[ArrayBuffer[Post]] match {
              case Right(posts) =>
                posts.foreach(mostrarPost)

                // Crear cliente y conectarse a la base de datos
                val mongoClient = MongoClients.create("mongodb://localhost:27017")
                val database = mongoClient.getDatabase("postsDB")
                val collection: MongoCollection[Document] = database.getCollection("posts")

                // Insertar los posts en MongoDB
                posts.foreach(insertarPost(_,collection))
            
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
