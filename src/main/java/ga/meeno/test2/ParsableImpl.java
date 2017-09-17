package ga.meeno.test2;

import ga.meeno.test2.tokens.PlainText;
import ga.meeno.test2.tokens.Token;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import javafx.util.Pair;

public class ParsableImpl implements Parsable {
  private final String content;
  private final ArrayList<Token> tokens;

  public ParsableImpl(String content) {
    this.content = content;
    tokens = new ArrayList<>();
  }

  @Override
  public String getBetween(int origin, int end) {
    return content.substring(origin, end);
  }

  @Override
  public Parsable addToken(Token token) {
    tokens.add(token);
    return this;
  }

  @Override
  public String getProcessedString() {
    tokens.sort(Comparator.comparingInt(Token::getOrigin));
    LinkedList<Token> tokensWithPlainText = new LinkedList<>();
    for (int i = 0; i < tokens.size(); i++) {
      int origin;
      int end;
      if (i < tokens.size() - 1) {
        origin = tokens.get(i).getEnd();
        end = tokens.get(i + 1).getOrigin();
      } else {
        origin = tokens.get(i).getEnd();
        end = content.length();
      }

      Token plainText = new PlainText(origin, end);
      tokensWithPlainText.add(tokens.get(i));
      tokensWithPlainText.add(plainText);
    }

    StringBuilder processedString = new StringBuilder();
    for (Token token : tokensWithPlainText) {
      processedString.append(token.getWrappedRepresentation(this));
    }
    return processedString.toString();
  }
}
