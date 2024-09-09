package spark1

import org.apache.spark.sql.{DataFrame, SparkSession}

object Spark5Select extends App {

  private val spark: SparkSession = SparkSession.builder()
    .appName("MyApplication")
    .config("spark.master", "local")
    .getOrCreate()


  private val carsDF: DataFrame = spark.read
    .option("inferSchema", "true")
    .option("multiline", "true")
    .option("mode", "failFast")
    .json(this.getClass.getResource("/data/cars.json").getPath)

  val nameColumn = carsDF.col("Name")

  import org.apache.spark.sql.functions.{col, column, expr}
  import spark.implicits._

  carsDF.select(
    nameColumn,
    col("Acceleration"),
    column("Weight_in_lbs"),
    'Year,
    $"Horsepower",
    expr("Origin")
  ).show()

  carsDF.select("Name", "Year").show()

  // transformation
  private val weightInKgColumn = col("Weight_in_lbs") / 2.2

  carsDF.select(
    col("Name"),
    col("Weight_in_lbs"),
    weightInKgColumn.as("Weight_in_kg"),
    expr("Weight_in_lbs / 2.2").as("Weight_in_kg_2")
  ).show()

  // selectExpr
  private val carsDFWithSelectExpr = carsDF.selectExpr(
    "Name",
    "Weight_in_lbs",
    "Weight_in_lbs / 2.2 as Weight_in_kg"
  )
  carsDFWithSelectExpr.show()

  //DF processing
  // adding a column
  carsDF.withColumn("Weight_in_kg3", col("Weight_in_lbs") / 2.2)
    .show()

  // renaming a column
  private val carsDFWithColumnRenamed = carsDF.withColumnRenamed("Weight_in_lbs", "Weight in pounds")
  carsDFWithColumnRenamed.show()

  // careful with column names
  carsDFWithColumnRenamed.selectExpr("`Weight in pounds`").show()

  // remove a column
  carsDFWithColumnRenamed.drop("Cylinders", "Displacement").show()

  // Filtering

  carsDF.filter(col("Origin") === "USA").show()
  carsDF.where(col("Origin") === "USA").show()
  carsDF.filter(col("Origin") =!= "USA").show()

  private val americanCarsDF = carsDF.filter("Origin = 'USA'")
  americanCarsDF.show()

  // Chain filters
  carsDF.filter(col("Origin") === "USA" && col("Horsepower") > 150).show()
  carsDF.filter(col("Origin") === "USA" and col("Horsepower") > 150).show()
  carsDF.filter("Origin = 'USA' and Horsepower > 150").show()

  // Unions

  private val moreCarsDF = spark.read
    .option("inferSchema", "true")
    .option("multiline", "true")
    .json(this.getClass.getResource("/data/more_cars.json").getPath)

  private val allCarsDF = carsDF.union(moreCarsDF)


  // Distinct values
  allCarsDF.select("Origin").distinct().show()

}
