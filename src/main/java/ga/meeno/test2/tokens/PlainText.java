package ga.meeno.test2.tokens;

public class PlainText extends Token {

  public PlainText(int origin, int end) {
    super(origin, end);
  }

  @Override
  protected String wrap(String representation) {
    return representation;
  }
}
