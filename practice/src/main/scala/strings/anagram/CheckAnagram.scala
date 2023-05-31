package strings.anagram

import $.FunSuite

class CheckAnagram extends FunSuite {

  def checkAnagram(sa: String, sb: String): Boolean = {
    // stressed - desserts
   sa.sorted == sb.sorted
  }
}
