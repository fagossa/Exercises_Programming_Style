package fr.xebia.frequency

import fr.xebia.frequency.WordFrequencyCounter._
import org.scalatest.{FunSpec, Matchers}

class WordFrequencyCounterSpec extends FunSpec with Matchers {

  val count = 25

  describe("Word frequency counter using hardcoded data") {
    it("should ignore special characters") {
      val words = List("hEllo worlD worLd , to ; 4 (")
      frequenciesOf(words, count) should be(List(("world", 2), ("hello", 1)))
    }

    it("should handle several lines") {
      val words = List(
        "White tigers live mostly in India",
        "Wild lions live mostly in Africa"
      )
      frequenciesOf(words, count) should be(List(
        ("live", 2), ("mostly", 2),
        ("africa", 1), ("india", 1), ("lions", 1), ("tigers", 1), ("white", 1), ("wild", 1)
      ))
    }

    it("should remove special characters") {
      val initialPhrases =
        List("White tigers; live,", "mostly in .India;")
      removeSpecialChars(initialPhrases) shouldBe List(
        "White tigers live", "mostly in India"
      )
    }

    it("should stop words") {
      val initialPhrases =
        List("a white tigers live across", "mostly in all India")
      removeStopWords(initialPhrases) shouldBe List(
        "white", "tigers", "live", "mostly", "india"
      )
    }

  }

  describe("Word frequency counter reading data from a file") {
    lazy val words: List[String] = scala.io.Source
      .fromInputStream(getClass.getResourceAsStream("/mobydick.txt")).getLines().toList

    it("should find the most frequent words") {
      frequenciesOf(words, count) should be(List(
        ("water", 6),
        ("whenever", 4),
        ("high", 3), ("land", 3), ("men", 3), ("nothing", 3), ("stand", 3), ("take", 3), ("upon", 3),
        ("city", 2), ("come", 2), ("crowds", 2), ("find", 2), ("here", 2), ("little", 2), ("more", 2), ("myself", 2), ("ocean", 2), ("one", 2), ("reveries", 2), ("ships", 2), ("streets", 2), ("thousands", 2), ("time", 2), ("up", 2)
      ))
    }

  }

}
