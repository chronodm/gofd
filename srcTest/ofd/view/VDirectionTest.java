package ofd.view;

import static ofd.view.VDirection.BACK;
import static ofd.view.VDirection.FWD;
import static ofd.view.VDirection.LEFT;
import static ofd.view.VDirection.RIGHT;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VDirectionTest {

  @Test
  public void testNext() {
    assertEquals(RIGHT, FWD.next());
    assertEquals(BACK, RIGHT.next());
    assertEquals(LEFT, BACK.next());
    assertEquals(FWD, LEFT.next());
  }
  
  @Test
  public void testPrevious() {
    assertEquals(LEFT, FWD.previous());
    assertEquals(FWD, RIGHT.previous());
    assertEquals(RIGHT, BACK.previous());
    assertEquals(BACK, LEFT.previous());
  }

}
