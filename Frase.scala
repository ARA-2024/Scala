import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn

object Ejemplo {
  def main(args: Array[String]): Unit = {
    val userData: ArrayBuffer[String] = ArrayBuffer.empty[String]
    print("Inserta una frase: ")
    val sentence = StdIn.readLine()
    print("Inserta una letra: ")
    val char1 = StdIn.readLine()
    userData +=sentence
    userData +=char1
    val counter = 0
    for (i <- 0 until sentence.length) {
       if (i == char1) {
          counter= counter+1
        }
    }
    println(s"El número de veces que aparece la letra elegida en la frase introducida es: $counter")

  }
}


