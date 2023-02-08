import io.circe.Encoder
import io.circe.generic.AutoDerivation

/* Defining a case class that represents the JSON or YAML */
case class FooBaz(foo: Option[String], baz: Option[Int], `list of stuff`: List[Int])

/* Adding a default encoder for the case class */
object FooBaz extends AutoDerivation {
  implicit val enc: Encoder[FooBaz] = exportEncoder[FooBaz].instance.mapJson(_.deepDropNullValues)
}
