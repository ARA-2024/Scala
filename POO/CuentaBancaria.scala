// Clase CuentaBancaria   // Double: Número real (punto flotante de doble precisión)
class CuentaBancaria(private val _numeroCuenta: String, private var _saldo: Double) {

  // Get
  def numeroCuenta: String = _numeroCuenta
  def saldo: Double = _saldo

  // Set
  def saldo_=(nuevoSaldo: Double): Unit = {
    _saldo=nuevoSaldo
  }

  // Método público que devuelve información
  def mostrarInfo(): String = s"$numeroCuenta (${saldo} Euros)"

  // Método depositar
  def depositar(deposito: Double): Unit = {
    if (deposito >= 0) saldo = saldo + deposito
    else println("Error: El depósito no puede ser negativo")
    }

    // Método retirar
  def retirar(cantidad: Double): Unit = {
    if (cantidad >= 0 && (saldo-cantidad)>=0) saldo = saldo - cantidad
    else println("Error: El depósito no puede ser negativo")
    }

    // Método mostrarSaldo
  def mostrarSaldo(): String = s"El saldo actual es: ${saldo} Euros"

}

// Objeto principal con el punto de entrada
object MainApp {
  def main(args: Array[String]): Unit = {
    // Crear instancias de Cuentas bancarias
    val CuentaBancaria1 = new CuentaBancaria("001", 100.5)
    val CuentaBancaria2 = new CuentaBancaria("002", 500)

    // Usar métodos y propiedades
    println("=== Datos iniciales ===")
    println(CuentaBancaria1.mostrarInfo())
    println(CuentaBancaria2.mostrarInfo())

    // Modificar saldo usando setter
    CuentaBancaria2.saldo = 1500
    println("\n=== Después de actualizar saldo ===")
    println(CuentaBancaria2.mostrarInfo())

    // Hacer un depósito
    println("\n=== Hacemos un depósito ===")
    CuentaBancaria1.depositar(50)
    println(CuentaBancaria1.mostrarInfo())

    // Retirar dinero
    println("\n=== Retiramos dinero ===")
    CuentaBancaria2.retirar(50)
    println(CuentaBancaria2.mostrarInfo())

    // Mostrar saldo
    println("\n=== Saldo actual de la cuenta 1 ===")
    println(CuentaBancaria1.mostrarSaldo())
    println("\n=== Saldo actual de la cuenta 2 ===")
    println(CuentaBancaria2.mostrarSaldo())
    

  }
}
