object _1_JsonDecoding extends App {

  import io.circe._
  import io.circe.parser._

  val jsonString: String = """{"foo":"bar","baz":123, "extraField":"extraValue","list of stuff":[ 4, 5, 6 ]}"""
  /* note that the jsonString includes an extra field that is missing in a case class */

  {
    /* Decoding an object using a parse command */
    val json = parse(jsonString).getOrElse(throw new Exception("Could not parse JSON"))

    val fooBaz = json.as[FooBaz].right.get
    val foo: Option[String] = fooBaz.foo
    println(s"Foo is equal to $foo") // Foo is equal to Some(bar)
  }

  {
    /* Decoding an object using decode command */
    val either: Either[Error, FooBaz] = decode[FooBaz](jsonString)
    val foo: Option[String] = either.map(_.foo).right.get
    println(s"Foo is equal to $foo") // Foo is equal to Some(bar)
  }
}
