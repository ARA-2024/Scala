import scala.io.Source
import scala.jdk.CollectionConverters._
import org.knowm.xchart.{CategoryChart, CategoryChartBuilder, SwingWrapper}
import org.knowm.xchart.VectorGraphicsEncoder
import org.knowm.xchart.VectorGraphicsEncoder.VectorGraphicsFormat

object GraficaSueldos extends App {
  val filename = "sueldos.csv"
  val lines = Source.fromFile(filename).getLines().drop(1).toList

  val ciudades = lines.map(_.split(",")(0))
  val sueldos = lines.map(_.split(",")(1).toInt)

  val chart: CategoryChart = new CategoryChartBuilder()
    .width(1000).height(600)
    .title("Sueldo por Ciudad")
    .xAxisTitle("Ciudad")
    .yAxisTitle("Sueldo")
    .build()

  chart.addSeries("Sueldo", ciudades.asJava, sueldos.map(_.asInstanceOf[Number]).asJava)
  new SwingWrapper(chart).displayChart()
  Thread.sleep(20000)

  // Guardar como PDF
  VectorGraphicsEncoder.saveVectorGraphic(chart, "grafica_sueldos", VectorGraphicsFormat.SVG)

  println("¡Gráfica guardada como grafica_sueldos.pdf!")
}
