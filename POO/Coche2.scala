// Clase Coche
class Coche(private val _marca: String, private val _modelo: String, private var _velocidadActual: Int) {
  // Constructor secundario
  def this(marca: String, modelo:String) = this(marca, modelo, 0)

  // Get
  def marca: String = _marca
  def modelo: String = _modelo
  def velocidadActual: Int = _velocidadActual

  // Set con validación
  def velocidadActual_=(nuevaVelocidad: Int): Unit = {
    if (nuevaVelocidad >= 0) _velocidadActual = nuevaVelocidad
    else println("Error: La velocidad no puede ser negativa")
  }

  // Método público que devuelve información
  def mostrarInfo(): String = s"$marca $modelo (${velocidadActual} km/hora)"

  // Método acelerar
  def acelerar(): Unit = {
     _velocidadActual = _velocidadActual +10
    }

    // Método frenar
  def frenar(): Unit = {
    var velocidad: Int = 0 // Mutable
    velocidad = _velocidadActual -40
    if (velocidad >= 0) _velocidadActual = _velocidadActual -40
    else println("Error: La velocidad no puede ser negativa")
  }

    // Método mostrarVelocidad
  def mostrarVelocidad(): String = s"Velocidad actual: ${velocidadActual} km/hora"
}

// Objeto principal con el punto de entrada
object MainApp {
  def main(args: Array[String]): Unit = {
    // Crear instancias de Coche
    val coche1 = new Coche("SEAT", "Leon", 100)
    val coche2 = new Coche("Peugeot", "306")

    // Usar métodos y propiedades
    println("=== Datos iniciales ===")
    println(coche1.mostrarInfo())  
    println(coche2.mostrarInfo())  

    // Modificar velocidad usando setter
    coche2.velocidadActual = 20
    println("\n=== Después de actualizar la velocidad ===")
    println(coche2.mostrarInfo())

    // Intentar asignar velocidad inválida
    println("\n=== Intento de velocidad inválida ===")
    coche1.velocidadActual = -10  // Error: La velocidad no puede ser negativa

    // Acelerar
    println("\n=== Acelera el coche 1 ===")
    coche1.acelerar()
    println(coche1.mostrarInfo())
    println("\n=== Acelera el coche 2 ===")
    coche2.acelerar()
    println(coche2.mostrarInfo())

    // Frenar
    println("\n=== Frena el coche 1 ===")
    coche1.frenar()
    println(coche1.mostrarInfo())
    println("\n=== Frena el coche 2 ===")
    coche2.frenar()
    println(coche2.mostrarInfo())

    // Mostrar velocidad
    println("\n=== Velocidad actual del coche 1 ===")
    println(coche1.mostrarVelocidad())
    println("\n=== Velocidad actual del coche 2 ===")
    println(coche2.mostrarVelocidad())

  }
}
