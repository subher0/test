package ga.meeno.test2;

import ga.meeno.test2.tokens.Token;

public interface Parsable {
  String getBetween(int origin, int end);
  Parsable addToken(Token token);
  String getProcessedString();
}
