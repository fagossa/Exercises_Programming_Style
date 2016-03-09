package fr.xebia.frequency

object WordFrequencyCounter {

  class WordFrequencyController(words: List[String], maxWords: Int) {

    lazy val stopWords = scala.io.Source
      .fromInputStream(getClass.getResourceAsStream("/stop_words.txt")).getLines()
      .mkString.split(",").toList

    def run: Seq[(String, Int)] = {
      words
        .map(_.replaceAll("[,|;|.]", ""))
        // remove stop words
        .flatMap(_.split(" "))
        .map(_.toLowerCase)
        .filter(!stopWords.contains(_))
        .filter(_.forall(_.isLetter))
        .filterNot(_.isEmpty)
        //groupWordsTogether
        .groupBy(identity)
        .mapValues { t: List[String] => t.size }
        //sortWords
        .map { case (k, v) => (k, v) }.toSeq
        .sortWith { case ((word1, count1), (word2, count2)) => if (count1 == count2) word1.compareTo(word2) <= 0 else count1 > count2 }
        .take(maxWords)
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
