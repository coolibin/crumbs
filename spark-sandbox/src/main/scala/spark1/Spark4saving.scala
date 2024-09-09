package spark1

import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

object Spark4saving extends App {
  private val spark: SparkSession = SparkSession.builder()
    .appName("MyApplication")
    .config("spark.master", "local")
    .getOrCreate()


  private val carsDF: DataFrame = spark.read
    .format("json")
    .option("inferSchema", "true")
    .option("multiline", "true")
    .option("mode", "failFast")
    .load(this.getClass.getResource("/data/cars.json").getPath)

  carsDF.write
    .format("csv")
    .mode(SaveMode.Overwrite)
    .option("header", "true")
    .option("sep", "\t")
    .save("tmp/cars.csv")

  // to database
  carsDF.write
    .format("jdbc")
    .option("url", "jdbc:mysql://localhost:3306/test")
    .option("dbtable", "cars")
    .option("user", "root")
    .option("password", "root")
    .save()

}
