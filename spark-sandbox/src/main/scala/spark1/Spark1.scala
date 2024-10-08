package spark1

import org.apache.spark.sql.types._
import org.apache.spark.sql.{DataFrame, SparkSession}

object Spark1 extends App {

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
    .schema(carsSchema)
    .option("multiline","true")
    .option("mode", "failFast")
    .load(this.getClass.getResource("/data/cars.json").getPath)

  private val carsDF2: DataFrame = spark.read
    .format("json")
    .schema(carsSchema)
    .option("multiline","true")
    .option("mode", "failFast")
    .option("path", this.getClass.getResource("/data/cars.json").getPath)
    .load()

  carsDF.show()
}
