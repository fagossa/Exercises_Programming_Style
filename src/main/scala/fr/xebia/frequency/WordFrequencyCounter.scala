package fr.xebia.frequency

object WordFrequencyCounter {

  class WordFrequencyController(words: List[String], maxWords: Int) {
    def run: Seq[(String, Int)] = {
      val withoutSpecialChars = new SpecialCharsManager(words).removeSpecialChars

      val withoutStopWords = new StopWordManager(withoutSpecialChars).removeStopWords()

      // groups and sorts
      val frequencyManager = new WordFrequencyManager()
      val wordsInGroups = frequencyManager.groupWordsTogether(withoutStopWords)
      frequencyManager.sortWords(maxWords, wordsInGroups)
    }
  }

  class SpecialCharsManager(lines: List[String]) {
    def removeSpecialChars: List[String] = {
      lines.map(_.replaceAll("[,|;|.]", ""))
    }
  }

  class StopWordManager(words: List[String]) {

    def exclusions = scala.io.Source
      .fromInputStream(getClass.getResourceAsStream("/stop_words.txt")).getLines()
      .mkString.split(",").toList

    def removeStopWords(): List[String] = {
      words
        .flatMap(_.split(" "))
        .map(_.toLowerCase)
        .filter(!exclusions.contains(_))
        .filter(_.forall(_.isLetter))
        .filterNot(_.isEmpty)
    }
  }

  class WordFrequencyManager {
    def groupWordsTogether(words: List[String]): Map[String, Int] =
      words
        .groupBy(identity)
        .mapValues { t: List[String] => t.size }

    def sortWords(maxWords: Int, frequency: Map[String, Int]): Seq[(String, Int)] = {
      frequency.map { case (k, v) => (k, v) }.toSeq
        .sortWith { case ((word1, count1), (word2, count2)) => if (count1 == count2) word1.compareTo(word2) <= 0 else count1 > count2 }
        .take(maxWords)
    }
  }

  def main(args: Array[String]) {
    val toList: List[String] = scala.io.Source.fromFile(args(0)).getLines().toList
    new WordFrequencyController(toList, 25)
      .run
      .foreach { case (word, frequency) => println(s"$word  -  $frequency") }
  }

}
