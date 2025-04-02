import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn

object Suma {
  def main(args: Array[String]): Unit = {
    var operation: String = "suma" 
    print("Escribe suma o resta: ")
    operation = StdIn.readLine()
       
    if (operation == "suma") {
       println("Voy a sumar.")
    } else {
       println("Voy a restar.")
    }
  }
}