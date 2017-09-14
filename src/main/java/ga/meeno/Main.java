package ga.meeno;

import static ga.meeno.Main.ROTDecoderConstants.*;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
  public static void main (String... args) {
    ROTDecoderHashMap decoder = new ROTDecoderHashMap();
    Scanner scanner = new Scanner(System.in);
    String encodedString = scanner.nextLine();

    long begin = System.nanoTime();
    String decoded = decoder.decode(encodedString);
    long end = System.nanoTime();

    System.out.println(decoded);
    System.out.println("Decoding took " + (end - begin) + " nanosecs");
  }

  public interface ROTDecoder {
    String decode(String encoded);
  }

  static class ROTDecoderConstants {
    static final int CHAR_OFFSET = 13;
    static final int NUMERIC_OFFSET = 5;
    static final int UPPER_ORIGIN = (int)'A';
    static final int UPPER_END = (int) 'Z';
    static final int LOWER_ORIGIN = (int) 'a';
    static final int LOWER_END = (int) 'z';
    static final int NUMERIC_ORIGIN = (int) '0';
    static final int NUMERIC_END = (int) '9';
  }

  public static class ROTDecoderHashMap extends HashMap<Character, Character> implements ROTDecoder {
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

  public static class ROTDecoderArray implements ROTDecoder {
    private char[] lowers = new char[26];
    private char[] uppers = new char[26];
    private char[] numerics = new char[10];
    private final int smallest = NUMERIC_ORIGIN;
    private final int largest = LOWER_END;

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
        if (i - offset >= 0)
          array[i] = (char) (origin + i - offset);
        else
          array[i] = (char) (origin + i + width - offset);
      }
    }

    @Override
    public String decode(String encoded) {
      char[] chars = new char[encoded.length()];
      for (int i = 0; i < encoded.length(); i++) {
        char ch = encoded.charAt(i);

        if (ch < smallest || ch > largest) {
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
}
