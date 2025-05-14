import com.mongodb.client.{MongoClients, MongoCollection}
import org.bson.Document

object MongoExample {
  def main(args: Array[String]): Unit = {
    // Crear cliente y conectarse a la base de datos
    val mongoClient = MongoClients.create("mongodb://localhost:27017")
    val database = mongoClient.getDatabase("Zaragoza")
    val collection: MongoCollection[Document] = database.getCollection("Animales")

    // Leer documentos
    val documentos = collection.find().iterator()
    println("\nAnimales en adopción en Zaragoza:")
    while (documentos.hasNext) {
      val doc = documentos.next()
      println(s"- ${doc.getString("title")} ${doc.getString("especie")}")
/*
    // Insertar un documento
    val persona = new Document("nombre", "Ana")
      .append("apellidos", "Gómez Pérez")
    collection.insertOne(persona)
    println("Documento insertado")
*/
        }

    // Cerrar cliente
    mongoClient.close()
  }
}