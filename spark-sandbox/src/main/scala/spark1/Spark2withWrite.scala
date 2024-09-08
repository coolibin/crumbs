package spark1

import org.apache.spark.sql.types._
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

object Spark2withWrite extends App {

  private val spark: SparkSession = SparkSession.builder()
    .appName("MyApplication")
    .config("spark.master", "local")
    .getOrCreate()

  private val sc = spark.sparkContext
  sc.setLogLevel("WARN")

  private val carsSchema: StructType = StructType(Array(
    StructField("Name", StringType),
    StructField("Miles_per_Gallon", DoubleType),
    StructField("Cylinders", LongType),
    StructField("Displacement", DoubleType),
    StructField("Horsepower", LongType),
    StructField("Weight_in_lbs", LongType),
    StructField("Acceleration", DoubleType),
    StructField("Year", StringType),
    StructField("Origin", StringType)
  ))

  private val carsDF: DataFrame = spark.read
    .format("json")
    .options(Map("multiline" -> "true", "mode" -> "failFast", "inferSchema" -> "true"))
    .load(this.getClass.getResource("/data/cars.json").getPath)

  carsDF.write
    .format("json")
    .mode(SaveMode.Overwrite)
    .save("tmp/cars2.json")
}
