package fr.xebia.frequency

import org.scalatest.{FunSpec, Matchers}

class WordFrequencyCounterSpec extends FunSpec with Matchers {

  val count = 25

  describe("Frequency counter tested integrally") {
    it("should ignore special characters") {
      val words = List("hEllo worlD worLd , to ; 4 (")
      WordFrequencyCounter.frequenciesOf(words, count) should be(List(("world", 2), ("hello", 1)))
    }

    it("should handle several lines") {
      val phrases = List(
        "White tigers live mostly in India",
        "Wild lions live mostly in Africa"
      )
      WordFrequencyCounter.frequenciesOf(phrases, count) should be(List(
        ("live", 2), ("mostly", 2),
        ("africa", 1), ("india", 1), ("lions", 1), ("tigers", 1), ("white", 1), ("wild", 1)
      ))
    }

    lazy val words: List[String] = scala.io.Source
      .fromInputStream(getClass.getResourceAsStream("/mobydick.txt")).getLines().toList

    it("should find the most frequent words") {
      WordFrequencyCounter.frequenciesOf(words, count) should be(List(
        ("water", 6),
        ("whenever", 4),
        ("high", 3), ("land", 3), ("men", 3), ("nothing", 3), ("stand", 3), ("take", 3), ("upon", 3),
        ("city", 2), ("come", 2), ("crowds", 2), ("find", 2), ("here", 2), ("little", 2), ("look", 2),
        ("more", 2), ("myself", 2), ("ocean", 2), ("one", 2), ("reveries", 2), ("ships", 2), ("streets", 2),
        ("thousands", 2), ("time", 2)
      ))
    }

  }


}
