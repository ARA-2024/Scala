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

    var n1: Float = 1 // Mutable
    var n2: Float = 1 // Mutable
    print("Escribe número 1: ")
    n1 = StdIn.readLine.toFloat()
    print("Escribe número 2: ")
    n2 = StdIn.readLine.toFloat()
    
    if (operation == "suma") {
       println(n1 + n2)
    } else {
       println(n1 - n2)  // Se ejecuta si la condición del if es false
    }
  }
}
