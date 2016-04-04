package fr.xebia.frequency

object WordFrequencyCounter {

  def frequenciesOf(phrases: List[String], maxWords: Int): Seq[(String, Int)] = {
    ???
  }

  def main(args: Array[String]) {
    WordFrequencyCounter.frequenciesOf(scala.io.Source.fromFile(args(0)).getLines().toList, 25)
      .foreach { case (word, frequency) => println(s"$word  -  $frequency") }
  }
}
