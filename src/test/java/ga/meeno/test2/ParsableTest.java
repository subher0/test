package ga.meeno.test2;

import static org.junit.Assert.assertEquals;

import ga.meeno.test2.tokens.Entity;
import ga.meeno.test2.tokens.Link;
import ga.meeno.test2.tokens.Token;
import ga.meeno.test2.tokens.TwitterUsername;
import org.junit.Test;

public class ParsableTest {

  @Test
  public void test() {
    Parsable parsable = new ParsableImpl("Obama visited Facebook headquarters: http://bit.ly/xyz @elversatile");

    Token token1 = new Entity(14, 22);
    Token token2 = new Entity(0, 5);
    Token token3 = new TwitterUsername(55, 67);
    Token token4 = new Link(37, 54);

    parsable.addToken(token1).addToken(token2).addToken(token3).addToken(token4);

    assertEquals(parsable.getProcessedString(), "<strong>Obama</strong> visited <strong>Facebook</strong> headquarters: <a href=\"http://bit.ly/xyz\">http://bit.ly/xyz</a> <a href=\"http://twitter.com/@elversatile\">@elversatile</a>");
  }
}
