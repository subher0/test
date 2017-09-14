package ga.meeno;

import ga.meeno.Main.ROTDecoder;
import ga.meeno.Main.ROTDecoderArray;
import ga.meeno.Main.ROTDecoderHashMap;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Test {
  public static void main(String... args) {
    Test test = new Test();
    ROTDecoder decoderHashMap = new ROTDecoderHashMap();
    test.test(decoderHashMap);
    ROTDecoder decoderArray = new ROTDecoderArray();
    test.test(decoderArray);
  }

  public void test(ROTDecoder decoder) {
    System.out.println("##############Testing " + decoder.getClass().getCanonicalName() + "######################");
    ClassLoader classLoader = getClass().getClassLoader();
    try (Scanner scanner = new Scanner(Paths.get(classLoader.getResource("test_cases.txt").getFile()))){
      while (scanner.hasNext()) {
        String encoded = scanner.nextLine();

        long begin = System.nanoTime();
        String decoded = decoder.decode(encoded);
        long end = System.nanoTime();

        System.out.println(decoded);
        System.out.println("Decoding took " + (end - begin) + " nanosecs");
        System.out.println("In average " + (end - begin) / encoded.length() + " nanosecs per character");
        System.out.println("-----------------------------------------------");
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    System.out.println("##################################################");
  }
}
