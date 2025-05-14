import com.mongodb.client.{MongoClients, MongoCollection}
import org.bson.Document

object MongoExample {
  def main(args: Array[String]): Unit = {
    // Crear cliente y conectarse a la base de datos
    val mongoClient = MongoClients.create("mongodb://localhost:27017")
    val database = mongoClient.getDatabase("personasDB")
    val collection: MongoCollection[Document] = database.getCollection("personas")

    // Insertar un documento
    val persona = new Document("nombre", "Juan")
      .append("apellidos", "Sánchez Ramírez")
    collection.insertOne(persona)
    println("Documento insertado")

    // Leer documentos
    val documentos = collection.find().iterator()
    println("\nPersonas en la base de datos:")
    while (documentos.hasNext) {
      val doc = documentos.next()
      println(s"- ${doc.getString("nombre")} ${doc.getString("apellidos")}")
    }

    // Cerrar cliente
    mongoClient.close()
  }
}
