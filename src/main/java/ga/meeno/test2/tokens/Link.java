package ga.meeno.test2.tokens;

public class Link extends Token {

  public Link(int origin, int end) {
    super(origin, end);
  }

  @Override
  protected String wrap(String representation) {
    return wrapInTag(representation, "<a href=\"" + representation + "\">", "</a>");
  }
}
