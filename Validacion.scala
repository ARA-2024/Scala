import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn

object Validacion {
  def main(args: Array[String]): Unit = {
    val user: String = "anarubio"
    val password: String = "Impulso06"

    var userData: ArrayBuffer[String] = ArrayBuffer.empty[String] 
    var counter: Int = 0

    while (counter <= 3) {
       counter = counter +1
       print("Inserta tu usuario: ")
       var user1 = StdIn.readLine()
       print("Inserta tu contraseña: ")
       var password1 = StdIn.readLine()
       userData += user1
       userData += password1
       if (user1==user && password1 == password) {
       println("Validación correcta")
       counter=3
    } else {
       if (counter <= 3) {
       println("Sigue intentándolo")
    }
    }
    }

    if (counter == 4) {
       println("Ya no tienes más intentos.")  
    }
  
}
}
