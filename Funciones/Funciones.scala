import scala.io.StdIn
import scala.collection.mutable.Set


// Main method con el punto de entrada
object Funciones {
  def main(args: Array[String]): Unit = {

  // Función pura para calcular el área de un rectángulo
  def area(b: Int, h: Int): Int = b * h
  print("Introduce base del rectángulo: ")
  var base:String = StdIn.readLine()
  print("Introduce altura del rectángulo: ")
  var height:String = StdIn.readLine()
  println(s"El área del rectángulo es: ${(area(base.toInt,height.toInt))}")

  // Función de orden superior que recibe una lista de enteros y calcula su cuadrado
  var set1 = Set(1, 2, 3, 4, 5)
  val cuadrado = set1.map(numero => numero * numero)
  println(s"Los cuadrados de la lista son: ${cuadrado}")   // (16, 1, 4, 9, 25)

  val cuadradoSet = (x: Set) => _.map(x => x * x)
  println(s"Los cuadrados de la lista son: ${cuadradoSet(set1)}")
  //var cuadrado: Set => Set = _.map(x => x * x)
  
  /*
  def nuevaLista(f: Set => Set, s: Set): Set = f(s)
  var cuadrado = set1.map(x => x * x)
  println(cuadrado(set1))
  
  //var cuadrado: Set => Set = _.map(x => x * x)
  //var set1 = Set(1, 2, 3, 4, 5)
  //println(cuadrado(set1))
  //println(nuevaLista(cuadrado, set1))
  */

  /*
  def nuevaLista(f: List => List, l: List): List = f(l)
  val cuadrado: List => List = _.map(x => x * x)
  //val listaTriple = lista.map(x => x * 3)
  val lista1 = List(1, 2, 3, 4, 5)
  println(nuevaLista(cuadrado, lista1))
  //def aplicar(f: Int => String, v: Int): String = f(v)
  //val aCadena: Int => String = _.toString
  */

  // Función que devuelve otra función que suma un número fijo a su argumento
  def suma(incremento: Int): Int => Int = (x: Int) => x +incremento
  val sumaSiete = suma(7)
  print("Introduce un número: ")
  var numero:String = StdIn.readLine()
  println(s"El valor sumaSiete de ese número es: ${(sumaSiete(numero.toInt))}")
  
  // Función que da la longitud de cada palabra de una lista
  // Dada una lista de palabras, usa map para obtener la longitud de cada palabra
  val nombres = List("Ana", "Luis", "Juan")
  val longitudes = nombres.map(_.length)           // Usando _
  println(s"La longitud de las palabras de la lista es: ${longitudes}")
  val longitudes2 = nombres.map(nombre => nombre.length) // Usando lambda
  println(s"La longitud de las palabras de la lista es: ${longitudes2}")
  def contarLetras(s: String) = s.length
  val longitudes3 = nombres.map(contarLetras)
  println(s"La longitud de las palabras de la lista es: ${longitudes3}")



  }

}
