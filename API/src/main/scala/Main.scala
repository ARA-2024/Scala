import sttp.client3._
import sttp.client3.okhttp.OkHttpSyncBackend
import io.circe._
import io.circe.parser._
import io.circe.generic.auto._  // Importación clave para derivación automática

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
                println(s"UserID: ${post.userId}")
                println(s"ID: ${post.id}")
                println(s"Title: ${post.title}")
                println(s"Body: ${post.body}")
              
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
