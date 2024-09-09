package spark1

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._

object Spark3csv extends App {

  private val spark: SparkSession = SparkSession.builder()
    .appName("MyApplication")
    .config("spark.master", "local")
    .getOrCreate()

  val stockSchema = StructType(Array(
    StructField("symbol", StringType),
    StructField("date", DateType),
    StructField("price", DoubleType)
  ))

  private val stocksDF = spark.read
    .schema(stockSchema)
    .option("header", "true")
    .option("dateFormat", "MMM dd yyyy")
    .option("sep", ",")
    .csv(this.getClass.getResource("/data/stocks.csv").getPath)

  stocksDF.show()

}
