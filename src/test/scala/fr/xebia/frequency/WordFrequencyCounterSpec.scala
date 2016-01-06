package fr.xebia.frequency

import org.scalatest.{Matchers, FunSpec}

class WordFrequencyCounterSpec extends FunSpec with Matchers {

  val count = 25

  describe("Word frequency counter using hardcoded data")  {
pending
    it("should ignore special characters") {
      val words = List("hEllo worlD worLd , to ; 4 (")
      WordFrequencyCounter.frequenciesOf(words, count) should be(List(("world", 2), ("hello", 1)))
    }

    it("should handle several lines") {
      val words = List(
        "White tigers live mostly in India",
        "Wild lions live mostly in Africa"
      )
      WordFrequencyCounter.frequenciesOf(words, count) should be(List(
        ("mostly", 2), ("live", 2), ("white", 1), ("africa", 1), ("lions", 1), ("tigers", 1), ("india", 1), ("wild", 1)
      ))
    }

  }

  describe("Word frequency counter reading data from a file") {
    lazy val words: List[String] = scala.io.Source
      .fromInputStream(getClass.getResourceAsStream("/mobydick.txt")).getLines().toList

    it("should find the most frequent words") {
      WordFrequencyCounter.frequenciesOf(words, count) should be(List(
        ("water", 6),
        ("whenever", 4),
        ("upon", 3), ("take", 3), ("stand", 3), ("nothing", 3), ("men", 3), ("land", 3), ("high", 3),
        ("warehouses", 2), ("up", 2), ("time", 2), ("thousands", 2), ("streets", 2), ("ships", 2), ("reveries", 2), ("one", 2), ("ocean", 2), ("myself", 2), ("more", 2), ("little", 2), ("here", 2), ("find", 2), ("crowds", 2), ("come", 2)
      ))
    }

  }

}
