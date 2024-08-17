package strings.justify

import scala.annotation.tailrec

// TODO unfinished
object Justify extends App {

  def justify(text: String, width: Int): String = {

    def recursively(remainedText: String, accumulator: List[String]): List[String] = {
      if (remainedText.length <= width) {
        remainedText :: accumulator
      } else {
        val candidate = remainedText.take(width)
        val endPos = candidate.length - candidate.reverse.indexOf(' ')
        val line = candidate.take(endPos)
        recursively(remainedText.drop(endPos), line :: accumulator)
      }
    }

    val linesReversed = recursively(text, List())

    @tailrec
    def justifyLineTailRec(line: String, iteration: Int): String = {
      val numberOfAdditionalSpaces = width - line.length
      if (numberOfAdditionalSpaces == 0) {
        line
      } else {
        val currentSplitter = List.fill(iteration)(" ").mkString
        val words = line.split(currentSplitter)
        if (numberOfAdditionalSpaces < words.length) {
          val toExtend = words.take(numberOfAdditionalSpaces).toList
          val newLine = toExtend.map(w => w + currentSplitter + " ").mkString +
            words.drop(numberOfAdditionalSpaces).map(w => currentSplitter + w).mkString
          newLine
        } else {
          val newLine = words.mkString(List.fill(iteration + 1)(" ").mkString)
          justifyLineTailRec(newLine, iteration + 1)
        }
      }
    }

    val linesUpdated = linesReversed.head :: linesReversed.tail.map(l => justifyLineTailRec(l.trim, 1))

    linesUpdated.reverse.mkString("\n")
  }

  println(justify("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", 30))

}
