import scala.collection.mutable.Set
import scala.collection.immutable.List
import scala.collection.mutable.Map


// Main method con el punto de entrada
object Main {
  def main(args: Array[String]): Unit = {

  // Nuevo mapa asociando cada edad con los nombres
  val personas = Map("Ana" -> 30, "Luis" -> 25, "Eva" -> 30, "Juan" -> 25)
  println(personas)
  var claves = personas.keys       
  var valores = personas.values 
  println(s"Claves iniciales: ${claves}")  // Set (Ana, Luis, Eva, Juan)
  println(s"Valores iniciales: ${valores}")  // Iterable (30, 25, 30, 25)
  //var set1 = claves.takeRight(3)  //  Set(Juan, Ana, Luis)
  //var set2 = set1.toMutable()
  //var set1 = Set("Juan", "Ana", "Luis")
  //var set2 = set1.remove("Ana")
  //var set3 = Set("Ana")
  //var diferencia2 = set2 -- set3
  //println(s"set1: ${diferencia2}")
  //set1 = set1 -- set1("Ana")
  //set1 = set1 - "Ana"
  //set1 = set1 -- "Ana"
  //set1 -= "Ana"
  
  println(claves.getClass)
  //claves=claves.remove("Ana")
  val age1= personas("Ana")
  println(s"age1: ${age1}") 
  val age2= personas("Luis")
  println(s"age2: ${age2}") 
  // val age1 = personas.get("Ana")
  val set1= Set("Ana","Eva")
  val set2= Set("Luis","Juan")
  var mapaMutable = Map(age1 -> set1, age2 -> set2)
  println(s"Mapa transformado: ${mapaMutable}")

  //val set2 = claves.remove(0,2)
  //println(s"set2: ${set2}")
  //val key1 = valores(0)
  //println(s"key1: ${key1}")

  val invertedMap = personas.map(_.swap)
  println(s"Mapa invertido: ${invertedMap}\n")
  
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
  println(s"valores2: ${valores2}\n")  // (5, 7, 10)
  
  // Palabras en los dos textos que aparecen una sola vez
  val texto1 = List("sol", "luna", "estrella", "sol", "mar")
  val texto2 = List("mar", "sol", "luna", "luna", "cielo")
  val textoUnion = texto1 ++ texto2
  println(textoUnion)

  var setTexto1 = texto1.toSet  // (sol, luna, estrella, mar)
  var setTexto2 = texto2.toSet  // (mar, sol, luna, cielo)
  var interseccion = setTexto1 & setTexto2  
  println(s"texto1: ${texto1}")
  println(s"texto2: ${texto2}")
  println(s"interseccion: ${interseccion}")   // Set(sol, luna, mar)
  if (texto1.count(_ == "sol") > 1) {
    interseccion -= "sol"  
  } else if (texto1.count(_ == "luna") > 1) {
    interseccion -= "luna"
  } else if (texto1.count(_ == "mar") > 1) {
    interseccion -= "mar" 
  } else if (texto2.count(_ == "mar") > 1) {
    interseccion -= "mar" 
  } else if (texto2.count(_ == "sol") > 1) {
    interseccion -= "sol" 
  } else if (texto2.count(_ == "luna") > 1) {
    interseccion -= "luna" 
  } else {
    println(s"Palabras que aparecen una sola vez en los dos textos: ${interseccion}")
}
  println(s"Palabras que aparecen una sola vez en los dos textos: ${interseccion}")
  val diferencia = setTexto1 -- setTexto2
  println(s"diferencia: ${diferencia}")

  //val union = texto1 | texto2  // No funciona
  val union = texto1 ++ texto2 
  println(s"union: ${union}\n")  // (sol, luna, estrella, sol, mar, mar, sol, luna, luna, cielo)

  // Palabras únicas de dos listas
  val textoUnico1 = texto1.toSet
  println(textoUnico1)  // ("sol", "luna", "estrella", "mar")
  val textoUnico2 = texto2.toSet
  println(textoUnico2)  // ("mar", "sol", "luna", "cielo")
  val uniqueWords = (textoUnico1 ++ textoUnico2) -- (textoUnico1 & textoUnico2)
  println(s"Valores únicos de los dos textos: ${uniqueWords}\n")  // (cielo, estrella)
 
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