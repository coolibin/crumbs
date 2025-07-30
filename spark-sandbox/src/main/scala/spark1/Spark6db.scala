package spark1

import org.apache.spark.sql.SparkSession

object Spark6db extends App {

  private val spark: SparkSession = SparkSession.builder()
    .appName("MyApplication")
    .config("spark.master", "local")
    .getOrCreate()

  def readTable(tableName: String) = spark.read
    .format("jdbc")
    .option("driver", "")



}
