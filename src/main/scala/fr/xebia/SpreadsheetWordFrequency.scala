package fr.xebia

import scala.io.Source

case class Column[T](var data: Seq[T] = Seq(), formula: Option[() => Seq[T]] = None)(implicit manifest: Manifest[T])

object SpreadsheetWordFrequency {
  def main(args: Array[String]) {
    // The columns. Each column is a data element and a formula.
    // The first 2 columns are the input data, so no formulas.
    val allWords = Column[String](
      data = """\w+""".r.findAllIn(Source.fromFile(args(0)).mkString).toList)
    val stopWords = Column[String](
      data = Source
        .fromInputStream(this.getClass.getResourceAsStream("/stop_words.txt"))
        .mkString
        .split(",")
    )
    println(allWords.data.map(d => s""""$d""""))
    println(stopWords.data.map(d => s""""$d""""))
    val nonStopWords = Column[String](
      formula = Some(() => allWords.data.map(word => if (stopWords.data.contains(word)) "" else word))
    )
    val uniqueWords = Column[String](
      formula = Some(() => nonStopWords.data.filter(!_.isEmpty).distinct)
    )
    val counts = Column[Int](
      formula = Some(() => uniqueWords.data.map(uniqueWord => allWords.data.count(word => uniqueWord.equals(word))))
    )
    val sortedData = Column[String](
      formula = Some(() => uniqueWords.data.zip(counts.data).map(_._1)) // TODO sort
    )

    // The entire spreadsheet
    val allColumns = Array(
      allWords,
      stopWords,
      nonStopWords,
      uniqueWords,
      counts,
      sortedData
    )

    // The active procedure over the columns of data.
    // Call this everytime the input data changes, or periodically.
    def update(): Unit = {
      for (column <- allColumns) {
        column.formula match {
          case Some(formula) => column.data = formula()
        }
      }
    }

    update()

    for ((word, frequency) <- sortedData.data.take(25)) {
      println(s"$word  -  $frequency")
    }
  }
}
