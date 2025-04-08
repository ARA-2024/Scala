import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn

object UserData {
  def main(args: Array[String]): Unit = {
    val dataMutable = ArrayBuffer()

    print("Introduce Nombre: ")
    val newName = StdIn.readLine()
    dataMutable += newName

    print("Introduce Apellido: ")
    val newLastname = StdIn.readLine()
    dataMutable += newLastname

    print("Introduce Email: ")
    val newEmail = StdIn.readLine()
    dataMutable += newEmail

    print("Introduce Dirección: ")
    val newAddress = StdIn.readLine()
    dataMutable += newAddress

    println("Datos usuario: " + dataMutable.mkString(", "))

  }
}