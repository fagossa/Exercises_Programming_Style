package fr.xebia.frequency

object WordFrequencyCounter {

  def removeSpecialChars(phrases: List[String]): List[String] =
    phrases.map(_.replaceAll("[,;.!]", ""))

  def splitPhrases(phrases: List[String]): List[String] =
    phrases.flatMap(_.split(" ")).map(_.toLowerCase).filter(_.forall(_.isLetter)).filterNot(_.isEmpty)

  def removeStopWords(words: List[String]): List[String] = {
    val exclusions = scala.io.Source
      .fromInputStream(getClass.getResourceAsStream("/stop_words.txt"))
      .getLines().mkString.split(",").toList
    words.filterNot(exclusions.contains(_))
  }

  def groupWordsTogether(words: List[String]): Map[String, Int] =
    words.groupBy(identity)
      .mapValues(_.size)

  def sortWords(maxWords: Int)(frequency: Map[String, Int]) : Seq[(String, Int)] =
    frequency.toSeq
      .sortWith{
        case ((w1, fr1),(w2, fr2)) if fr1 == fr2 => w1.compareTo(w2) < 0
        case ((w1, fr1),(w2, fr2)) => fr1 > fr2
      }
      .take(maxWords)


  def frequenciesOf(phrases: List[String], maxWords: Int): Seq[(String, Int)] = {
    val fun = removeSpecialChars _ andThen splitPhrases andThen removeStopWords andThen groupWordsTogether andThen sortWords(maxWords)
    fun(phrases)
  }

  def main(args: Array[String]) {
    WordFrequencyCounter.frequenciesOf(scala.io.Source.fromFile(args(0)).getLines().toList, 25)
      .foreach { case (word, frequency) => println(s"$word  -  $frequency") }
  }
}
