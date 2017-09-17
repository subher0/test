package ga.meeno.test1;

public class ROTDecoderArray implements ROTDecoder {

  private char[] lowers = new char[26];
  private char[] uppers = new char[26];
  private char[] numerics = new char[10];

  ROTDecoderArray() {
    fillArrays(5, 13);
  }

  private void fillArrays(int numericOffset, int letterOffset) {
    fillArray(lowers, letterOffset, ROTDecoderConstants.LOWER_ORIGIN);
    fillArray(uppers, letterOffset, ROTDecoderConstants.UPPER_ORIGIN);
    fillArray(numerics, numericOffset, ROTDecoderConstants.NUMERIC_ORIGIN);
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

      if (ch < ROTDecoderConstants.NUMERIC_ORIGIN || ch > ROTDecoderConstants.LOWER_END) {
        chars[i] = ch;
      } else {
        if (ch >= ROTDecoderConstants.LOWER_ORIGIN && ch <= ROTDecoderConstants.LOWER_END) {
          chars[i] = lowers[ch - ROTDecoderConstants.LOWER_ORIGIN];
        } else if (ch >= ROTDecoderConstants.UPPER_ORIGIN && ch <= ROTDecoderConstants.UPPER_END) {
          chars[i] = uppers[ch - ROTDecoderConstants.UPPER_ORIGIN];
        } else if (ch >= ROTDecoderConstants.NUMERIC_ORIGIN && ch <= ROTDecoderConstants.NUMERIC_END) {
          chars[i] = numerics[ch - ROTDecoderConstants.NUMERIC_ORIGIN];
        }
      }
    }
    return new String(chars);
  }
}
