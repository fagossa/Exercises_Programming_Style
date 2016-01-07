package fr.xebia.frequency

object WordFrequencyCounter {

  def frequenciesOf(words: List[String], maxWords: Int): Seq[(String, Int)] = {
    val triggerWith = removeSpecialChars _ andThen removeStopWords andThen groupWordsTogether andThen sortWords(maxWords)
    triggerWith(words)
  }

  def removeSpecialChars(lines: List[String]): List[String] = {
    lines.map(_.replaceAll("[,|;|.]", ""))
  }

  def removeStopWords(words: List[String]): List[String] = {
    val exclusions = scala.io.Source
      .fromInputStream(getClass.getResourceAsStream("/stop_words.txt")).getLines()
      .mkString.split(",").toList
    words
      .flatMap(_.split(" "))
      .map(_.toLowerCase)
      .filter(!exclusions.contains(_))
      .filter(_.forall(_.isLetter))
      .filterNot(_.isEmpty)
  }

  def groupWordsTogether(words: List[String]): Map[String, Int] =
    words
      .groupBy(identity)
      .mapValues { t: List[String] => t.size }

  def sortWords(maxWords: Int)(frequency: Map[String, Int]): Seq[(String, Int)] = {
    frequency.map { case (k, v) => (k, v) }.toSeq
      .sortWith { case ((word1, count1), (word2, count2)) => if (count1 == count2) word1.compareTo(word2) <= 0 else count1 > count2 }
      .take(maxWords)
  }
}
