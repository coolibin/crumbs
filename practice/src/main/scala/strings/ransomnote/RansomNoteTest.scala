package strings.ransomnote

import $.FunSuite

class RansomNoteTest extends FunSuite {

  /**
   * Note: I have your daughter
   * Magazine:
   * I bought this really nice doll for my daughter. I often have discounts at Amazon. I suggest the item for your kids.
   */
  def ransomNoteByChars(note: String, magazine: String): Boolean = {

    def buildMap(s: String): Map[Char, Int] = {
      s.foldLeft(Map[Char, Int]()) {
        case (m, ch) => m + (ch -> (m.getOrElse(ch, 0) + 1))
      }
    }

    val noteChars = buildMap(note)
    val magazineChars = buildMap(magazine)
    noteChars.forall { case (k, v) => magazineChars.getOrElse(k, 0) >= v }
  }

  test("1.1") {
    ransomNoteByChars("I have your daughter", "I bought this really nice doll for my daughter. I often have discounts at Amazon. I suggest the item for your kids.") shouldBe true
  }
  test("1.2") {
    ransomNoteByChars("I have your dog", "I bought this really nice doll for my daughter") shouldBe false
  }

  def ransomNoteByWords(note: String, magazine: String): Boolean = {

    val noteWords = note.replace(".", "").split(" ").sorted.toList
    val magazineWords = magazine.replace(".", "").split(" ").sorted.toList

    def matching(nwords: List[String], mwords: List[String]): Boolean = {
      if (nwords.isEmpty) {
        true
      } else if (mwords.isEmpty) {
        false
      } else {
        if (nwords.head == mwords.head) {
          matching(nwords.tail, mwords.tail)
        } else {
          matching(nwords, mwords.tail)
        }
      }
    }

    matching(noteWords, magazineWords)
  }

  test("2.1") {
    ransomNoteByWords("I have your daughter", "I bought this really nice doll for my daughter. I often have discounts at Amazon. I suggest the item for your kids.") shouldBe true
  }
  test("2.2") {
    ransomNoteByWords("I have your dog", "I bought this really nice doll for my daughter. I often have discounts at Amazon. I suggest the item for your kids.") shouldBe false
  }
}
