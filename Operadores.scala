import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn

object Operadores {
  def main(args: Array[Int]): Unit = {
    val numbers: ArrayBuffer[Int] = ArrayBuffer.empty[Int]
    print("Inserta número 1: ")
    val number1 = StdIn.readLine()
    print("Inserta número 2: ")
    val number2 = StdIn.readLine()
    print("Inserta número 3: ")
    val number3 = StdIn.readLine()
    numbers+=number1
    numbers+=number2
    numbers+=number3
    if (number1 < number2) {
       nminor = number1  // Se ejecuta si la condición es true
    }
    if (number3 < nminor) {
       println(s"El número menor es: $number3")
    } else {
       println(s"El número menor es: $nminor")  // Se ejecuta si la condición del if es false
    }

  }
}