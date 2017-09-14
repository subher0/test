package ga.meeno;

import java.nio.file.Paths;
import java.util.Scanner;

public class Performance {

  public static void main(String... args) {
    Performance test = new Performance();
    ROTDecoder decoderHashMap = new ROTDecoderHashMap();
    test.test(decoderHashMap);
    ROTDecoder decoderArray = new ROTDecoderArray();
    test.test(decoderArray);
  }

  private void test(ROTDecoder decoder) {
    System.out.println("##############Testing " + decoder.getClass().getCanonicalName()
        + "######################");
    ClassLoader classLoader = getClass().getClassLoader();
    try (Scanner scanner = new Scanner(
        Paths.get(classLoader.getResource("test_cases.txt").getFile()))) {
      while (scanner.hasNext()) {
        String encoded = scanner.nextLine();

        long begin = System.nanoTime();
        String decoded = decoder.decode(encoded);
        long end = System.nanoTime();

        System.out.println("String length: " + decoded.length());
        System.out.println("Decoding took " + (end - begin) + " nanosecs");
        System.out
            .println("In average " + (end - begin) / encoded.length() + " nanosecs per character");
        System.out.println("-----------------------------------------------");
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    System.out.println("##################################################");
  }
}
