package spark1

import org.apache.spark.sql.SparkSession

object Spark0 {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("MyApplication")
      .config("spark.master", "local")
      .getOrCreate()

    val firstDF = spark.read
      .format("json")
      .option("inferSchema", "true")
      .option("multiline","true")
      .load(this.getClass.getResource("/first.json").getPath)

    firstDF.show()
  }

}
