package ga.meeno.test2.tokens;

import ga.meeno.test2.Parsable;

public abstract class Token {
  private final int origin;
  private final int end;

  public Token(int origin, int end) {
    this.origin = origin;
    this.end = end;
  }

  public String getWrappedRepresentation(Parsable parsable) {
    return wrap(parsable.getBetween(origin, end));
  }

  protected abstract String wrap(String representation);

  final String wrapInTag(String stringToWrap, String opening, String closing) {
    return opening + stringToWrap + closing;
  }

  public int getOrigin() {
    return origin;
  }

  public int getEnd() {
    return end;
  }
}
