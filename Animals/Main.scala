import com.mongodb.client.{MongoClients, MongoCollection}
import org.bson.Document

object MongoExample {
  def main(args: Array[String]): Unit = {
    // Crear cliente y conectarse a la base de datos
    val mongoClient = MongoClients.create("mongodb://localhost:27017")
    val database = mongoClient.getDatabase("Zaragoza")
    val collection: MongoCollection[Document] = database.getCollection("animals")

    // Leer documentos
    val documentos = collection.find().iterator()
    println("\nAnimales en la base de datos:")
    while (documentos.hasNext) {
      val doc = documentos.next()
      println(s"- ${doc.getString("title")} ${doc.getString("especie")}")
    }

    // Insertar un documento
      val animal = new Document("title", "CHISPA")
      .append("especie", "Canina")
      collection.insertOne(animal)
      println("Documento insertado")

    // Leer documentos
    println("\nAnimales en la base de datos actualizada:")
    val documentos2 = collection.find().iterator()
    while (documentos2.hasNext) {
      val doc2 = documentos2.next()
      println(s"- ${doc2.getString("title")} ${doc2.getString("especie")}")
    }

    // Cerrar cliente
    mongoClient.close()
  }
}