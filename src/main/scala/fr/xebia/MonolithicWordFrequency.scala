package fr.xebia

class WordAndFreq(val word: String, var freq: Int = 1)

object MonolithicWordFrequency {
  def main(args: Array[String]) {
    var wordFreqs: Array[WordAndFreq] = Array()
    val stopWords = scala.io.Source
      .fromInputStream(this.getClass.getResourceAsStream("/stop_words.txt"))
      .mkString
      .split(",")
    val lines = scala.io.Source
      .fromFile(args(0))
      .getLines
    lines.foreach(line => {
      var startChar: Option[Int] = None
      var i = 0
      (line + " ").iterator.foreach(c => {
        if (startChar.isEmpty) {
          if (c.isLetterOrDigit) {
            // We found the start of a word
            startChar = Some(i)
          }
        } else {
          if (!c.isLetterOrDigit) {
            // We found the end of a word. Process it
            var found = false
            val word = line.substring(startChar.get, i).toLowerCase
            // Ignore stop words:
            if (!stopWords.contains(word)) {
              var pairIndex = 0
              import scala.util.control.Breaks._
              breakable {
                // Let’s see if it already exists
                for (pair <- wordFreqs) {
                  if (word == pair.word) {
                    pair.freq += 1
                    found = true
                    break
                  }
                  pairIndex += 1
                }
              }
              if (!found) {
                wordFreqs = wordFreqs :+ new WordAndFreq(word)
              } else if (!wordFreqs.isEmpty) {
                // We may need to reorder
                for (n <- (0 to pairIndex).reverse) {
                  if (wordFreqs(pairIndex).freq > wordFreqs(n).freq) {
                    // swap
                    val temp = wordFreqs(n)
                    wordFreqs(n) = wordFreqs(pairIndex)
                    wordFreqs(pairIndex) = temp
                    pairIndex = n
                  }
                }
              }
            }
            // Let’s reset
            startChar = None
          }
        }
        i += 1
      })
    })

    val index = wordFreqs.view.zipWithIndex
    for ((tf, i) <- index.takeWhile { case (a, b) => b < 25 }) {
      println(s"${tf.word}  -  ${tf.freq}")
    }
  }
}
