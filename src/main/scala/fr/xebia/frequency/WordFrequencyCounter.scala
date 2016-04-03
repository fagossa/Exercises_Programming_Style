package fr.xebia.frequency

object WordFrequencyCounter {

  def frequenciesOf(phrases: List[String], maxWords: Int): Seq[(String, Int)] = {
    val triggerWith = removeSpecialChars _ andThen splitPhrases andThen removeStopWords andThen groupWordsTogether andThen sortWords(maxWords)
    triggerWith(phrases)
  }

  def removeSpecialChars(phrases: List[String]): List[String] =
    phrases
      .map(_.replaceAll("[,|;|.|!]", ""))

  def splitPhrases(phrases: List[String]): List[String] =
    phrases
      .flatMap(_.split(" "))
      .map(_.toLowerCase)
      .filter(_.forall(_.isLetter))
      .filterNot(_.isEmpty)

  def removeStopWords(phrases: List[String]): List[String] = {
    val exclusions = scala.io.Source
      .fromInputStream(getClass.getResourceAsStream("/stop_words.txt")).getLines()
      .mkString.split(",").toList
    phrases
      .filterNot(exclusions.contains(_))
  }

  def groupWordsTogether(phrases: List[String]): Map[String, Int] =
    phrases
      .groupBy(identity)
      .mapValues { t: List[String] => t.size }

  def sortWords(maxWords: Int)(frequency: Map[String, Int]): Seq[(String, Int)] = {
    frequency.map { case (k, v) => (k, v) }.toSeq
      .sortWith { case ((word1, count1), (word2, count2)) => if (count1 == count2) word1.compareTo(word2) <= 0 else count1 > count2 }
      .take(maxWords)
  }

  def main(args: Array[String]) {
    WordFrequencyCounter.frequenciesOf(scala.io.Source.fromFile(args(0)).getLines().toList, 25)
      .foreach { case (word, frequency) => println(s"$word  -  $frequency") }
  }

}
