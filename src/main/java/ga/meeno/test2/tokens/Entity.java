package ga.meeno.test2.tokens;

public final class Entity extends Token {

  public Entity(int origin, int end) {
    super(origin, end);
  }

  @Override
  protected String wrap(String representation) {
    return wrapInTag(representation, "<strong>", "</strong>");
  }
}
