//import scala.collection.immutable._
import scala.collection.immutable.List

// Main method con el punto de entrada
object Listas {
  def main(args: Array[String]): Unit = {

  // Elimina duplicados y cuenta ocurrencias
  val palabras = List("gato", "perro", "gato", "loro", "perro", "gato")
  println(s"Lista inicial:\n${palabras}")
  println("Lista sin palabras repetidas:")
  println(palabras.distinct)
  println("Número de veces en la lista original:")
  println(palabras.find(_ == "gato"))
  println(palabras.find(_ == "perro"))
  println(s"gato: ${palabras.count(_ == "gato")}")   
  println(s"perro: ${palabras.count(_ == "perro")}")   
  println(s"loro: ${palabras.count(_ == "loro")}")   

  // Encuentra el segundo mayor
  var numeros = List(5, 3, 9, 1, 9, 7)
  println(s"\nLista inicial:${numeros}")
  numeros = numeros.distinct
  println(s"Lista sin repetidos:${numeros}")
  numeros = numeros.sorted(Ordering.Int.reverse)
  println(s"Lista ordenada: ${numeros}")
  println(s"El segundo mayor de la lista es: ${numeros(1)}")

  // Filtra los que sean múltiplos de 3 y mayores que 10
  var x: Int = 0
  var lista = List(3, 6, 12, 18, 20, 21, 9)
  // lista = lista.filter((_ % 3 == 0)+(_>10))
  println(s"\nLista inicial:\n${lista}")
  lista = lista.filter(_ % 3 == 0)
  lista = lista.filter(_>10)
  println(s"Lista filtrada:\n${palabras}")
  //lista = lista.map(_*_)
  def double(a:Int):Int = { a*2 } 
  println(s"Lista transformada:\n${lista.map(double)}")
  //println(lista.map(double))

  }

}