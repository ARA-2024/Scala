import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn

object Suma {
  def main(args: Array[String]): Unit = {
    val userOperation: ArrayBuffer[String] = ArrayBuffer.empty[String]
    print("Escribe suma o resta: ")
    val operation = StdIn.readLine()
    userOperation += operation
    
    if (operation == "suma") {
       println("Voy a sumar.")
    } else {
       println("Voy a restar.")
    }

  }
}