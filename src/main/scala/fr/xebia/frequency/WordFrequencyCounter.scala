package fr.xebia.frequency

object WordFrequencyCounter {

  def frequenciesOf(words: List[String], maxWords : Int): Seq[(String, Int)] = {
    val triggerWith = removeStopWords _ andThen groupWordsTogether andThen sortWords(maxWords)
    triggerWith(words)
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

  def sortWords(maxWords: Int)(frequency: Map[String, Int]): Seq[(String, Int)] =
    frequency.map { case (k, v) => (k, v) }.toSeq
      .sortBy(t => t._2)
      .reverse
      .take(maxWords)
}
