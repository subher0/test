package ga.meeno.test2.tokens;

public class TwitterUsername extends Token {

  public TwitterUsername(int origin, int end) {
    super(origin, end);
  }

  @Override
  protected String wrap(String representation) {
    return wrapInTag(representation, "<a href=\"http://twitter.com/" + representation + "\">", "</a>");
  }
}
