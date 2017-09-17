package ga.meeno.test2;

import ga.meeno.test2.tokens.Entity;
import ga.meeno.test2.tokens.Link;
import ga.meeno.test2.tokens.Token;
import ga.meeno.test2.tokens.TwitterUsername;

public class Main {
  public static void main(String... args) {
    Parsable parsable = new ParsableImpl("Obama visited Facebook headquarters: http://bit.ly/xyz @elversatile");

    Token token1 = new Entity(14, 22);
    Token token2 = new Entity(0, 5);
    Token token3 = new TwitterUsername(55, 67);
    Token token4 = new Link(37, 54);

    parsable.addToken(token1).addToken(token2).addToken(token3).addToken(token4);

    System.out.println(parsable.getProcessedString());
  }
}
