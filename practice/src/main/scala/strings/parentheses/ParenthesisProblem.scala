package strings.parentheses

import $.FunSuite

import scala.annotation.tailrec

class ParenthesisProblem extends FunSuite {

  // ()()
  // (()())
  private def hasValidParenthesis(s: String): Boolean = {

    val OPEN = '('

    @tailrec
    def recursively(remained: String, level: Int = 0): Boolean = {
      if (remained.isEmpty)
        level == 0
      else {
        if (remained.head == OPEN)
          recursively(remained.tail, level + 1)
        else
          recursively(remained.tail, level - 1)
      }
    }

    if (s.head != OPEN) false
    else recursively(s)
  }

  test("1")(hasValidParenthesis("()()") shouldBe true)
  test("2")(hasValidParenthesis("(()())") shouldBe true)
  test("3")(hasValidParenthesis("(()))") shouldBe false)
  test("4")(hasValidParenthesis(")(") shouldBe false)


  /**
   * n = 1 ()
   * n = 2 [(()), ()()]
   * n = 3 [((())), ()()(), (()()), ()(()), (())()]
   *
   * () :: []
   * ()() :: (()) :: []
   * ()()() :: ((())) :: [(()()), (())(), ()(())]
   *
   */
  private def generateAllValidParentheses(n: Int): List[String] = {

    def genParensTailrec(nRemainingParens: Int, currentStrings: Set[String]): Set[String] = {
      if (nRemainingParens == 0) {
        currentStrings
      } else {
        val newStrings = for {
          string <- currentStrings
          index <- 0 until string.length
        } yield {
          val (before, after) = string.splitAt(index)
          s"$before()$after"
        }
        genParensTailrec(nRemainingParens - 1, newStrings)
      }
    }

    if (n == 0) Nil
    else genParensTailrec(n-1, Set("()")).toList
  }

  test("2.1")(generateAllValidParentheses(1) should contain theSameElementsAs (List("()")))
  test("2.2")(generateAllValidParentheses(2) should contain theSameElementsAs (List("(())", "()()")))
  test("2.3")(generateAllValidParentheses(3) should contain theSameElementsAs (List("((()))", "()()()", "(()())", "()(())", "(())()")))
  test("2.4")(generateAllValidParentheses(4) should contain theSameElementsAs (List("(()()())", "(()())()", "((()()))", "(()(()))",
    "((()))()", "(((())))", "()(()())", "()(())()", "()((()))", "()()(())", "()()()()", "(())()()", "(())(())", "((())())")))

}
