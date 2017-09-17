package ga.meeno.test1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ROTDecoderHashmapTest {
  @Test
  public void testCorrectness() {
    String encoded = "Ceboyrz fbyivat vf 35 creprag bs gur wbo";
    String decoded = "Problem solving is 80 percent of the job";

    ROTDecoder decoder = new ROTDecoderHashMap();
    assertEquals(decoder.decode(encoded), decoded);
  }
}
