package spark1

import org.apache.spark.sql.types._
import org.apache.spark.sql.{DataFrame, SparkSession}

object Spark1 extends App {

//  val logger: Logger = Logger.getLogger("My.Example.Code.Rules")
//  Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
//  Logger.getLogger("org.apache.spark.storage.BlockManager").setLevel(Level.ERROR)
//  logger.setLevel(Level.INFO)

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
    .load(this.getClass.getResource("/data/cars.json").getPath)

  carsDF.show()

}
