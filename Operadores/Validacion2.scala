import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn

object Validacion {
  def main(args: Array[String]): Unit = {
    val user: String = "anarubio"
    val password: String = "Impulso06"

    var user1: String = 'a'
    var password1: String = 'a'
    var counter: Int = 0

    print("Inserta tu usuario: ")
    user1 = StdIn.readLine()
    print("Inserta tu contraseña: ")
    password1 = StdIn.readLine()

    if (user1==user && password1 == password) {
       println("Validación correcta")
    } else {
       println("Sigue intentándolo.")
       counter=counter+1
       while (counter <= 3) {
          print("Inserta tu usuario: ")
          user1 = StdIn.readLine()
          print("Inserta tu contraseña: ")
          password1 = StdIn.readLine()
          if (user1==user && password1 == password) {
            println("Validación correcta")
          } else {
              counter = counter +1
              println("Sigue intentándolo.")
    }
    }
    }

    if (counter == 4) {
       println("Ya no tienes más intentos.")  
    }
  
}
}
