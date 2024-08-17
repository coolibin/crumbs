package version

import $.FunSuite

import scala.annotation.tailrec

class CompareVersionNumbers extends FunSuite {

  /**
   * -1: version1 < version2
   * 0: version1 == version2
   * 1: version1 > version2
   *
   * Example:
   * 0.9 < 1.0.3.4 < 1.1.0 < 2.0 < 2.1
   */
  def compareVersionNumbers(version1: String, version2: String): Int = {

    @tailrec
    def recursively(ver1: List[Int], ver2: List[Int]): Int = {
      if (ver1.isEmpty && ver2.isEmpty) {
        0
      } else if (ver1.isEmpty && ver2.nonEmpty) {
        -1
      } else if (ver1.nonEmpty && ver2.isEmpty) {
        1
      } else if (ver1.head > ver2.head) {
        1
      } else if (ver1.head < ver2.head) {
        - 1
      } else {
        recursively(ver1.tail, ver2.tail)
      }
    }
    recursively(version1.split("\\.").map(_.toInt).toList, version2.split("\\.").map(_.toInt).toList)
  }

  test("1")(compareVersionNumbers("0.9", "1.0.3.4") shouldBe -1)
  test("2")(compareVersionNumbers("1.1.1", "1.1") shouldBe 1)
  test("3")(compareVersionNumbers("2.3.4", "2.3.4") shouldBe 0)
  test("4")(compareVersionNumbers("0.9", "2.1") shouldBe -1)

  def compareVersionNumbers2(version1: String, version2: String): Int = {

    @tailrec
    def recursively(ver1: List[Int], ver2: List[Int]): Int = {
      (ver1, ver2) match {
        case (Nil, Nil) => 0
        case (Nil, _ :: _)  if ver2.exists(_ != 0 ) => -1
        case (Nil, _ :: _)  => 0
        case (_ :: _, Nil)  if ver1.exists(_ != 0 ) => 1
        case (_ :: _, Nil)  => 0
        case (v1 :: _, v2 :: _) if v1 > v2 => 1
        case (v1 :: _, v2 :: _) if v1 < v2 => -1
        case (_ :: t1, _ :: t2) => recursively(t1, t2)
      }
    }

    recursively(version1.split("\\.").map(_.toInt).toList, version2.split("\\.").map(_.toInt).toList)
  }

  test("2.1")(compareVersionNumbers2("0.9", "1.0.3.4") shouldBe -1)
  test("2.2")(compareVersionNumbers2("1.1.1", "1.1") shouldBe 1)
  test("2.3")(compareVersionNumbers2("2.3.4", "2.3.4") shouldBe 0)
  test("2.4")(compareVersionNumbers2("0.9", "2.1") shouldBe -1)
  test("2.5")(compareVersionNumbers2("0", "0.1") shouldBe -1)
  test("2.6")(compareVersionNumbers2("2.1", "2.01") shouldBe 0)
  test("2.7")(compareVersionNumbers2("2.1", "2.01.0") shouldBe 0)


}
