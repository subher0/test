package ga.meeno;

import java.util.HashMap;

import static ga.meeno.ROTDecoderConstants.*;

public class ROTDecoderHashMap extends HashMap<Character, Character> implements ROTDecoder {
  ROTDecoderHashMap() {
    fillMap(UPPER_ORIGIN, UPPER_END, CHAR_OFFSET);
    fillMap(LOWER_ORIGIN, LOWER_END, CHAR_OFFSET);
    fillMap(NUMERIC_ORIGIN, NUMERIC_END, NUMERIC_OFFSET);
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
