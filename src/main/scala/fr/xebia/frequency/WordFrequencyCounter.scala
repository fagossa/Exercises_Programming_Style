package fr.xebia.frequency

// TODO: use only functions!

case class WordFrequencyCounter(exclusions: List[String], maxWords : Int) {

  def frequenciesOf(words: List[String]): Seq[(String, Int)] = {
    val onlyWords = isValidWord(exclusions)_
    val triggerWith = filterWith(onlyWords) _ andThen groupWordsTogether andThen sortWords
    triggerWith(words)
  }

  def filterWith(onlyWords: (String) => Boolean)(words: List[String]): List[String] =
    words
      .flatMap(_.split(" "))
      .map(_.toLowerCase)
      .filter(onlyWords)

  def isValidWord(exclusions: List[String])(word: String): Boolean =
    !word.isEmpty && !exclusions.contains(word) && word.forall(_.isLetter)

  def sortWords(frequency: Map[String, Int]): Seq[(String, Int)] =
    frequency.map { case (k, v) => (k, v) }.toSeq
      .sortBy(t => t._2)//(Ordering[Int].reverse)
      .reverse
      .take(maxWords)

  def groupWordsTogether(words: List[String]): Map[String, Int] =
    words
      .groupBy(identity)
      .mapValues { t: List[String] => t.size }

}
