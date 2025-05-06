import scala.collection.mutable.Set
import scala.collection.immutable.List
import scala.collection.mutable.Map


// Main method con el punto de entrada
object Main {
  def main(args: Array[String]): Unit = {
 
  println("ejemplo")
  //val set3 = scala.collection.mutable.Set(1, 2, 3, 4, 5)
  val set3 : scala.collection.mutable.Set[Int] = Set(2, 1, 4, 3, 5)
  set3 -= 1
  println(set3)
  
  // Nuevo mapa asociando cada edad con los nombres
  val personas = Map("Ana" -> 30, "Luis" -> 25, "Eva" -> 30, "Juan" -> 25)
  println(personas)
  val claves = personas.keys       
  val valores = personas.values.toList 
  println(s"Claves iniciales: ${claves}")  // Set(Ana, Luis, Eva, Juan) // Iterable[String]
  println(s"Valores iniciales: ${valores}")  // List(30, 25, 30, 25)  Iterable convertido a Lista
  var set1: Set[String] = Set(claves.toSeq: _*)  // (Eva, Ana, Luis, Juan)
  println(s"valores del Mapa: ${valores}")
  set1 -= "Luis"
  set1 -= "Juan"
  println(set1)
  var set2: Set[String] = Set(claves.toSeq: _*)  // (Eva, Ana, Luis, Juan)
  set2 -= "Eva"
  set2 -= "Ana"
  println(set2)
  val myMap: Map[Int, Set[String]] = Map.empty[Int, Set[String]]
  //myMap += 30 -> set1
  //myMap += 25 -> set2
  myMap += valores(0) -> set1
  myMap += valores(1) -> set2
  println(myMap)
  //val myMap = (keys zip values).toMap
  
  val invertedMap = personas.map(_.swap)
  println(invertedMap) 
  
// Intersección de claves y suma de valores
// Dado dos mapas Map[String, Int], busca las claves comunes y suma sus valores, creando un nuevo mapa con esas sumas.

  val m1 = Map("a" -> 1, "b" -> 2, "c" -> 3)
  val m2 = Map("b" -> 5, "c" -> 7, "d" -> 10)

  var combinado = m1 ++ m2
  println(s"Mapa combinado: ${combinado}")  // (a -> 1, b -> 5, c -> 7, d -> 10)

  combinado -= "a" 
  combinado -= "d" // (b -> 5, c -> 7)
  combinado.update("b", 7)  
  //combinado("b") = 7
  combinado("c") = 10
  println(s"Mapa combinado y transformado: ${combinado}")

  var claves1 = m1.keys
  println(s"claves1: ${claves1}")
  var claves2 = m2.keys
  println(s"claves2: ${claves2}")
  var claves3 = claves1 ++ claves2
  println(s"claves union: ${claves3}")   // (a, b, c, d)
  //var intersection = claves1 & claves2  // No funciona
  //val intersection = claves1 intersect claves2  // No funciona
  //val intersection = claves1 -- claves2  // No funciona
  //println(s"claves intersección: ${intersection}")

  val valores1 = m1.values 
  val valores2 = m2.values
  println(s"valores1: ${valores1}")  // (1, 2, 3)
  println(s"valores2: ${valores2}")  // (5, 7, 10)


  // Palabras en los dos textos que aparecen una sola vez
  val texto1 = List("sol", "luna", "estrella", "sol", "mar")
  val texto2 = List("mar", "sol", "luna", "luna", "cielo")

  var setTexto1 = texto1.toSet  // (sol, luna, estrella, mar)
  var setTexto2 = texto2.toSet  // (mar, sol, luna, cielo)
  val interseccion = setTexto1 & setTexto2
  // val interseccion: scala.collection.mutable.Set[String] = setTexto1 & setTexto2
  println(s"interseccion: ${interseccion}")   // (sol, luna, mar)
  val sol1= texto1.count(_ == "sol")  // 3
  println(sol1)
  //if (texto1.count(_ == "sol") > 0) {
    //interseccion.remove("sol")  
    //interseccion -= "sol"  
  //}
  println(interseccion)

  val diferencia = setTexto1 -- setTexto2
  println(s"diferencia: ${diferencia}")

  //val union = texto1 | texto2  // No funciona
  val union = texto1 ++ texto2 
  println(s"union: ${union}")  // (sol, luna, estrella, sol, mar, mar, sol, luna, luna, cielo)

  // Palabras únicas de dos listas
  val textoUnico1 = texto1.toSet
  println(textoUnico1)  // ("sol", "luna", "estrella", "mar")
  val textoUnico2 = texto2.toSet
  println(textoUnico2)  // ("mar", "sol", "luna", "cielo")
  val uniqueWords = (textoUnico1 ++ textoUnico2) -- (textoUnico1 & textoUnico2)
  println(s"Valores únicos de los dos textos: ${uniqueWords}")  // (cielo, estrella)

  // Mapa de frecuencias y filtrado de palabras que aparecen más de una vez
  val palabras = List("a", "b", "a", "c", "b", "a", "d")
  println(s"a: ${palabras.count(_ == "a")}")  
  println(s"b: ${palabras.count(_ == "b")}")    

  val a= palabras.count(_ == "a")  // 3
  val b= palabras.count(_ == "b")  // 2

  val keys = List("a", "b")
  val values = List(a,b)
  val miMapa = (keys zip values).toMap
  println(miMapa)    //  (a -> 3, b -> 2)
  

  }

}