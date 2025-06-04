import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn

object Letra {
  def main(args: Array[String]): Unit = {
    val char1: Char = 'a' 
    print("Inserta una letra: ")
    val char1 = StdIn.readLine()
    userData += char1
    if (char1 == "a") {
       println("La respuesta es: 7")
    } else if (char1 == "b") {  
       println("La respuesta es: 9")  
    } else if (char1 == "c") {  
       println("La respuesta es: 101")  
    } else {
       println("Se ha equivocado de letra.")
    }

  }
}