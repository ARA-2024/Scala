val datos = spark.sparkContext.parallelize(1 to 100)
val pares = datos.filter(_ % 2 == 0)

import spark.implicits._
val usuarios = Seq(
  ("Ana", 34, "Madrid"),
  ("Carlos", 28, "Barcelona"),
  ("Luisa", 41, "Valencia")
)
val df = usuarios.toDF("nombre", "edad", "ciudad")