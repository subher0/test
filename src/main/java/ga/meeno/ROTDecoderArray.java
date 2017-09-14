package ga.meeno;

import static ga.meeno.ROTDecoderConstants.*;

public class ROTDecoderArray implements ROTDecoder {

  private char[] lowers = new char[26];
  private char[] uppers = new char[26];
  private char[] numerics = new char[10];

  ROTDecoderArray() {
    fillArrays(5, 13);
  }

  private void fillArrays(int numericOffset, int letterOffset) {
    fillArray(lowers, letterOffset, LOWER_ORIGIN);
    fillArray(uppers, letterOffset, UPPER_ORIGIN);
    fillArray(numerics, numericOffset, NUMERIC_ORIGIN);
  }

  private void fillArray(char[] array, int offset, int origin) {
    int width = array.length;
    for (int i = 0; i < width; i++) {
      if (i - offset >= 0) {
        array[i] = (char) (origin + i - offset);
      } else {
        array[i] = (char) (origin + i + width - offset);
      }
    }
  }

  @Override
  public String decode(String encoded) {
    char[] chars = new char[encoded.length()];
    for (int i = 0; i < encoded.length(); i++) {
      char ch = encoded.charAt(i);

      if (ch < NUMERIC_ORIGIN || ch > LOWER_END) {
        chars[i] = ch;
      } else {
        if (ch >= LOWER_ORIGIN && ch <= LOWER_END) {
          chars[i] = lowers[ch - LOWER_ORIGIN];
        } else if (ch >= UPPER_ORIGIN && ch <= UPPER_END) {
          chars[i] = uppers[ch - UPPER_ORIGIN];
        } else if (ch >= NUMERIC_ORIGIN && ch <= NUMERIC_END) {
          chars[i] = numerics[ch - NUMERIC_ORIGIN];
        }
      }
    }
    return new String(chars);
  }
}
