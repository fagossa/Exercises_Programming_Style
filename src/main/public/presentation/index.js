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

// Import image preloader util
import preloader from "spectacle/lib/utils/preloader";

// Import theme
import createTheme from "spectacle/lib/themes/default";

// Require CSS
require("normalize.css");
require("spectacle/lib/themes/default/index.css");


const images = {
  author_original: require("../assets/author_original.png"),
  basic: require("../assets/basic.jpg"),
  book_original: require("../assets/book-original.jpg"),
  book_programming: require("../assets/book-programming.jpg"),
  historical: require("../assets/historical.jpg"),
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
            <Heading>Historical</Heading>
            <Appear fid="1">
              <Heading textColor="primary">Good old times</Heading>
            </Appear>
            <Appear fid="2">
              <Heading textColor="primary">Go Forth</Heading>
            </Appear>
          </Slide>
          <Slide bgImage={images.basic.replace("/", "")} bgDarken={0.75}>
            <Heading>Basic Styles</Heading>
            <Appear fid="1">
              <Heading textColor="primary">Monolithic</Heading>
            </Appear>
          </Slide>
          <Slide bgImage={images.basic.replace("/", "")} bgDarken={0.75} notes="<ul><li>">
            <Heading>Basic Styles</Heading>
            <Heading textColor="primary">Monolithic</Heading>
            <Appear fid="2">
              <Heading textColor="primary">Cookbook</Heading>
            </Appear>
            <Appear fid="3">
              <Heading textColor="primary">Candy Factory</Heading>
            </Appear>
            <Appear fid="4">
              <Heading textColor="primary">Code Golf</Heading>
            </Appear>
          </Slide>
        </Deck>
      </Spectacle>
    );
  }
}
