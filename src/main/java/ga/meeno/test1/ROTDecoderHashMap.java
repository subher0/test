package ga.meeno.test1;

import java.util.HashMap;

public class ROTDecoderHashMap extends HashMap<Character, Character> implements ROTDecoder {
  ROTDecoderHashMap() {
    fillMap(
        ROTDecoderConstants.UPPER_ORIGIN, ROTDecoderConstants.UPPER_END, ROTDecoderConstants.CHAR_OFFSET);
    fillMap(
        ROTDecoderConstants.LOWER_ORIGIN, ROTDecoderConstants.LOWER_END, ROTDecoderConstants.CHAR_OFFSET);
    fillMap(
        ROTDecoderConstants.NUMERIC_ORIGIN, ROTDecoderConstants.NUMERIC_END, ROTDecoderConstants.NUMERIC_OFFSET);
  }

  private void fillMap(int origin, int end, int offset) {
    int width = end - origin + 1;
    for (int i = origin; i <= end; i++) {
      if (i - offset >= origin)
        put((char)i, (char) (i - offset));
      else
        put((char)i, (char) (i + width - offset));
    }
  }

  public String decode(String encoded) {
    StringBuilder decoded = new StringBuilder();

    for (int i = 0; i < encoded.length(); i++) {
      Character character = get(encoded.charAt(i));
      if (character == null)
        character = encoded.charAt(i);
      decoded.append(character);
    }

    return decoded.toString();
  }
}
