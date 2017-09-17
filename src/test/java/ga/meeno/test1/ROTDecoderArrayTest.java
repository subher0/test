package ga.meeno.test1;

import org.junit.Test;

import static org.junit.Assert.*;

public class ROTDecoderArrayTest {

  @Test
  public void testCorrectness() {
    String encoded = "Ceboyrz fbyivat vf 35 creprag bs gur wbo";
    String decoded = "Problem solving is 80 percent of the job";

    ROTDecoder decoder = new ROTDecoderArray();
    assertEquals(decoder.decode(encoded), decoded);
  }
}
