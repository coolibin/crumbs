package brokenpalindrome

import $.FunSuite

/**
 * Write a function isFuzzyPalindrome
 * that receives a string and a number of allowed errors
 * and would return true if the string is a broken palindrome
 * and false if it is not.
 *
 * A palindrome is a sequence of letters which reads the same backward as forward, like “1122332211” for example.
 * A broken palindrome is a string that could become a palindrome if the certain number of characters was replaced.
 * Example:
 * "11223322xx" is a broken palindrome with 2 errors.
 */


class BrokenPalindrome
  extends FunSuite {

  def isBrokenPalindrome(str: String, maxErrors: Int): Boolean = {
    val left = str.take(str.length/2)
    val right = str.takeRight(str.length/2).reverse
    left.zip(right).count{case (l,r) => l != r} <= maxErrors
  }

  test("""("abcdcba", 2)""") (isBrokenPalindrome("abcdcba", 2) shouldBe true)
  test("""("abcdxza", 2)""") (isBrokenPalindrome("abcdxza", 2) shouldBe true)
  test("""("abcdxyz", 2)""") (isBrokenPalindrome("abcdxyz", 2) shouldBe false)
}
