// Import React
import React from "react";

// Import Spectacle Core tags
import {
  Appear,
  BlockQuote,
  Cite,
  CodePane,
  Deck,
  Fill,
  Heading,
  Image,
  Layout,
  Link,
  ListItem,
  List,
  Markdown,
  Quote,
  Slide,
  Spectacle,
  Text
} from "spectacle";

import CodeSlide from "spectacle-code-slide"

// Import image preloader util
import preloader from "spectacle/lib/utils/preloader";

// Import theme
import createTheme from "spectacle/lib/themes/default";

// Require CSS
require("normalize.css");
require("spectacle/lib/themes/default/index.css");
require("../presentation/presentation.css");


const images = {
  adversity: require("../assets/adversity.jpg"),
  author_original: require("../assets/author_original.png"),
  basic: require("../assets/basic.jpg"),
  book_original: require("../assets/book-original.jpg"),
  book_programming: require("../assets/book-programming.jpg"),
  conclusion: require("../assets/conclusion.png"),
  concurrency: require("../assets/concurrency.gif"),
  data_first: require("../assets/data-first.jpg"),
  function_composition: require("../assets/function-composition.gif"),
  historical: require("../assets/historical.jpg"),
  interactivity: require("../assets/interactivity.gif"),
  meta: require("../assets/meta.gif"),
  object: require("../assets/object.gif"),
  trollface: require("../assets/trollface.png")
};

preloader(images);

const theme = createTheme({
  primary: "#9b2f8b"
});

export default class Presentation extends React.Component {
  render() {
    return (
      <Spectacle theme={theme}>
        <Deck transition={["zoom", "slide"]} transitionDuration={500}>
          <Slide bgColor="primary">
            <Heading size={1} fit caps lineHeight={1}>
              Exercices in
            </Heading>
            <Heading size={1} fit caps>
              Programming Style
            </Heading>
          </Slide>
          <Slide bgColor="black">
            <Heading size={1} fit caps lineHeight={1}>
              Why?
            </Heading>
          </Slide>
          <Slide>
            <Heading size={1} fit caps lineHeight={1}>
              We are tired
            </Heading>
            <Heading size={1} fit caps textColor="black">
              of programming flamewars
            </Heading>
            <Image src={images.trollface.replace("/", "")} height="293px" padding="20px" bgColor="white" />
          </Slide>
          <Slide bgColor="black">
            <Heading size={2} caps fit lineHeight={1} textColor="white">
              What?
            </Heading>
          </Slide>
          <Slide>
            <Image src={images.book_programming.replace("/", "")} />
          </Slide>
          <Slide bgColor="black">
            <Heading size={2} caps fit textColor="white">
              Inspired from
            </Heading>
            <Image src={images.book_original.replace("/", "")} height="250pt"/>
          </Slide>
          <Slide>
            <Heading size={1} caps>
              By Raymond Queneau
            </Heading>
          </Slide>
          <Slide bgColor="black">
            <Image src={images.author_original.replace("/", "")} height="300pt" />
            <Heading size={1}>
              Handsome guy
            </Heading>
          </Slide>
          <Slide>
            <Heading size={1} caps fit>
              One story
            </Heading>
            <Heading size={2} caps fit>
              99 styles
            </Heading>
          </Slide>
          <Slide bgColor="black">
            <Heading size={1} caps fit>
              What a style
            </Heading>
            <Heading size={1} caps fit>
              really is?
            </Heading>
          </Slide>
          <Slide>
            <Heading size={1} caps fit>
              Constraints!
            </Heading>
          </Slide>
          <Slide bgImage={images.historical.replace("/", "")} bgDarken={0.75} notes="<ul><li>Good old times: small amount of primary memory; no identifiers</li><li>Go Forth: data stack; heap; procedures</li></ul>">
            <Heading caps fit>Historical</Heading>
            <Appear fid="1">
              <Heading textColor="primary">Good old times</Heading>
            </Appear>
            <Appear fid="2">
              <Heading textColor="primary">Go Forth</Heading>
            </Appear>
          </Slide>
          <Slide bgImage={images.basic.replace("/", "")} bgDarken={0.75}>
            <Heading>Basic Styles</Heading>
            <Appear>
              <Heading textColor="primary">Monolithic</Heading>
            </Appear>
          </Slide>
          <CodeSlide transition={[]} lang="scala" code={require("raw!../../scala/fr/xebia/MonolithicWordFrequency.scala")} ranges={[
          { loc: [0, 72], title: "OMG!" },
          { loc: [2, 3], title: "a named Tuple2"},
          { loc: [2, 3], title: "mutable second term" },
          { loc: [5, 72], title: "one big main" },
          { loc: [6, 7], title: "final result" },
          { loc: [7, 14], title: "read files" },
          { loc: [14, 15], title: "first big loop (lines)" },
          { loc: [17, 18], title: "second loop (characters)" },
          { loc: [18, 23], title: "detect start of a word" },
          { loc: [24, 28], title: "detect end of a word" },
          { loc: [28, 30], title: "ignore stop words" },
          { loc: [30, 43], title: "search if word already exists" },
          { loc: [43, 45], title: "append new word" },
          { loc: [46, 57], title: "reorder word with new frequency" },
          { loc: [58, 60], title: "ready to process next word" },
          { loc: [62, 63], title: "next character" },
          { loc: [66, 69], title: "print results" }
          ]} />
          <Slide>
            <Heading size={1} caps fit>Constraints</Heading>
            <Appear>
              <Heading textColor="black" caps fit>No abstraction</Heading>
            </Appear>
            <Appear>
              <Heading textColor="black" caps fit>Long blocks of program text</Heading>
            </Appear>
            <Appear>
              <Heading textColor="black" caps fit>High Cyclomatic Complexity</Heading>
            </Appear>
            <Appear>
              <Heading textColor="#FF0066" caps fit>BAD BAD BAD</Heading>
            </Appear>
          </Slide>
          <Slide bgImage={images.basic.replace("/", "")} bgDarken={0.75} notes="<ul><li>Cookbook: no more gotos; procedures; global variables; iterative</li></ul>">
            <Heading caps fit>Basic Styles</Heading>
            <Heading textColor="primary">Monolithic</Heading>
            <Appear>
              <Heading textColor="primary">Cookbook</Heading>
            </Appear>
            <Appear>
              <Heading textColor="primary">Candy Factory</Heading>
            </Appear>
          </Slide>
          <Slide bgImage={images.basic.replace("/", "")} bgDarken={0.75} notes="<ul><li>Code Golf: as few lines of code as possible</li></ul>">
            <Heading caps fit>Basic Styles</Heading>
            <Heading textColor="primary">Monolithic</Heading>
            <Heading textColor="primary">Cookbook</Heading>
            <Heading textColor="primary">Candy Factory</Heading>
            <Appear>
              <Heading textColor="primary">Code Golf</Heading>
            </Appear>
          </Slide>
          <Slide bgImage={images.function_composition.replace("/", "")} bgDarken={0.75} notes="<ul>
            <li>Infinite Mirror: recursive</li>
            <li>Kick Your Teammate Forward: callbacks</li>
            <li>The One: higher abstraction; wrap/apply functions/unwrap; identity monad from Haskell</li>
          </ul>">
            <Heading caps fit>Function Composition</Heading>
            <Appear>
              <Heading textColor="primary">Infinite Mirror</Heading>
            </Appear>
            <Appear>
              <Heading textColor="primary">Kick Your Teammate Forward</Heading>
            </Appear>
            <Appear>
              <Heading textColor="primary">The One</Heading>
            </Appear>
          </Slide>
          <Slide bgImage={images.object.replace("/", "")} bgDarken={0.75} notes="<ul>
            <li>Things: OOP; encapsulation; failed with distributed systems like CORBA</li>
            <li>Letterbox: single dispatch, message interchange</li>
            <li>Closed Maps: objects with map with mixed data and functions, prototype style</li>
            <li>Abstract Things: interface/contract and implementations</li>
            <li>Hollywood: aka call me maybe; inversion of control with callbacks registered for some events; used in interrupts in hardware and GUI</li>
            <li>Bulletin Board: publish/subscribe events; never calls modules directly; requires infrastructure; lazilly coupled</li>
          </ul>">
            <Heading caps fit>Objects and Object Interaction</Heading>
            <Appear>
              <Heading textColor="primary" size={2}>Things</Heading>
            </Appear>
            <Appear>
              <Heading textColor="primary" size={2}>Letterbox</Heading>
            </Appear>
            <Appear>
              <Heading textColor="primary" size={2}>Closed Maps</Heading>
            </Appear>
            <Appear>
              <Heading textColor="primary" size={2}>Abstract Things</Heading>
            </Appear>
            <Appear>
              <Heading textColor="primary" size={2}>Hollywood</Heading>
            </Appear>
            <Appear>
              <Heading textColor="primary" size={2}>Bulletin Board</Heading>
            </Appear>
          </Slide>
          <Slide bgImage={images.meta.replace("/", "")} bgDarken={0.75} notes="<ul>
            <li>TODO</li>
          </ul>">
            <Heading caps fit>Reflection and Metaprogramming</Heading>
            <Appear>
              <Heading textColor="primary" size={2}>Introspective</Heading>
            </Appear>
            <Appear>
              <Heading textColor="primary" size={2}>Reflective</Heading>
            </Appear>
            <Appear>
              <Heading textColor="primary" size={2}>Asides</Heading>
            </Appear>
            <Appear>
              <Heading textColor="primary" size={2}>No Commitment</Heading>
            </Appear>
          </Slide>
          <Slide bgImage={images.adversity.replace("/", "")} bgDarken={0.75} notes="<ul>
            <li>TODO</li>
          </ul>">
            <Heading caps fit>Adversity</Heading>
            <Appear>
              <Heading textColor="primary" size={2}>Constructivist</Heading>
            </Appear>
            <Appear>
              <Heading textColor="primary" size={2}>Tantrum</Heading>
            </Appear>
            <Appear>
              <Heading textColor="primary" size={2}>Passive Aggressive</Heading>
            </Appear>
            <Appear>
              <Heading textColor="primary" size={2}>Declared Intentions</Heading>
            </Appear>
            <Appear>
              <Heading textColor="primary" size={2}>Quarantine</Heading>
            </Appear>
          </Slide>
          <Slide bgImage={images.data_first.replace("/", "")} bgDarken={0.75} notes="<ul>
            <li>TODO</li>
          </ul>">
            <Heading caps fit>Data First</Heading>
            <Appear>
              <Heading textColor="primary" size={2}>Persistent Tables</Heading>
            </Appear>
            <Appear>
              <Heading textColor="primary" size={2}>Spreadsheet</Heading>
            </Appear>
          </Slide>
          <CodeSlide transition={[]} lang="python" code={require("raw!../assets/tf-26.py")} ranges={[
            { loc: [0, 1], title: "Python FTW!" },
            { loc: [4, 5], title: "Python FTW!" },
            { loc: [23, 26], title: "columns sorted by dependency" },
            { loc: [5, 9] },
            { loc: [9, 13], title: "copy all words except stop ones" },
            { loc: [13, 15], title: "select distinct non stop words" },
            { loc: [15, 18], title: "count each unique from non stop words" },
            { loc: [18, 22], title: "sort by frequency" },
            { loc: [39, 42] },
            { loc: [42, 44] },
            { loc: [27, 37], title: "update formulas" },
            { loc: [45, 47], title: "prints out result" }
            ]} />
          <Slide>
            <Heading size={1} caps fit>Constraints</Heading>
            <Appear>
              <Heading textColor="black" caps fit>Like a spreadsheet</Heading>
            </Appear>
            <Appear>
              <Heading textColor="black" caps fit>Formulas are applied when data change</Heading>
            </Appear>
            <Appear>
              <Heading textColor="black" caps fit>Inherently declarative and reactive</Heading>
            </Appear>
            <Appear>
              <Heading textColor="black" caps fit>Custom dependency management</Heading>
            </Appear>
          </Slide>
          <Slide bgImage={images.data_first.replace("/", "")} bgDarken={0.75} notes="<ul>
            <li>TODO</li>
          </ul>">
            <Heading caps fit>Data First</Heading>
            <Heading textColor="primary" size={2}>Persistent Tables</Heading>
            <Heading textColor="primary" size={2}>Spreadsheet</Heading>
            <Appear>
              <Heading textColor="primary" size={2}>Lazy Rivers</Heading>
            </Appear>
          </Slide>
          <Slide bgImage={images.concurrency.replace("/", "")} bgDarken={0.75} notes="<ul>
            <li>TODO</li>
          </ul>">
            <Heading caps fit>Concurrency</Heading>
            <Appear>
              <Heading textColor="primary" size={2}>Free Agents</Heading>
            </Appear>
            <Appear>
              <Heading textColor="primary" size={2}>Dataspaces</Heading>
            </Appear>
            <Appear>
              <Heading textColor="primary" size={2}>Inverse Multiplexer</Heading>
            </Appear>
            <Appear>
              <Heading textColor="primary" size={2}>Double Inverse Multiplexer</Heading>
            </Appear>
          </Slide>
          <Slide bgImage={images.interactivity.replace("/", "")} bgDarken={0.75} notes="<ul>
            <li>TODO</li>
          </ul>">
            <Heading caps fit>Interactivity</Heading>
            <Appear>
              <Heading textColor="primary" size={2}>Trinity</Heading>
            </Appear>
            <Appear>
              <Heading textColor="primary" size={2}>Restful</Heading>
            </Appear>
          </Slide>
          <Slide bgImage={images.conclusion.replace("/", "")} />
        </Deck>
      </Spectacle>
    );
  }
}
