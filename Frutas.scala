import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn

object UserData {
  def main(args: Array[String]): Unit = {
    val dataMutable = ArrayBuffer()

    print("Ingresa una nueva fruta: ")
    val nuevaFruta = StdIn.readLine()

    frutasMutable += nuevaFruta
    println("Frutas actualizadas: " + frutasMutable.mkString(", "))

    val ultimaFruta = frutasMutable.remove(frutasMutable.length - 1)
    println("Última fruta eliminada: " + ultimaFruta)
    println("Frutas finales: " + frutasMutable.mkString(", "))
  }
}