import scala.io.Source
import scala.jdk.CollectionConverters._
import org.knowm.xchart.{CategoryChart, CategoryChartBuilder, SwingWrapper}
import org.knowm.xchart.VectorGraphicsEncoder
import org.knowm.xchart.VectorGraphicsEncoder.VectorGraphicsFormat

object Main extends App {
  val filename = "Inventory_v2.csv"
  val lines = Source.fromFile(filename).getLines().drop(1).toList

  val productPN = lines.map(_.split(",")(0))
  val quantity = lines.map(_.split(",")(3).toInt)

  val chart: CategoryChart = new CategoryChartBuilder()
    .width(1000).height(600)
    .title("Cantidades por producto")
    .xAxisTitle("Producto")
    .yAxisTitle("Cantidades")
    .build()

  chart.addSeries("Cantidades", productPN.asJava, quantity.map(_.asInstanceOf[Number]).asJava)
  new SwingWrapper(chart).displayChart()
  Thread.sleep(20000)

  // Guardar como SVG
  VectorGraphicsEncoder.saveVectorGraphic(chart, "grafica_inventario", VectorGraphicsFormat.SVG)

  println("¡Gráfica guardada como grafica_inventario.pdf!")
}
