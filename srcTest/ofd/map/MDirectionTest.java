package ofd.map;

import static ofd.map.MDirection.EAST;
import static ofd.map.MDirection.NORTH;
import static ofd.map.MDirection.SOUTH;
import static ofd.map.MDirection.WEST;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MDirectionTest {

  @Test
  public void testNext() {
    assertEquals(EAST, NORTH.next());
    assertEquals(SOUTH, EAST.next());
    assertEquals(WEST, SOUTH.next());
    assertEquals(NORTH, WEST.next());
  }
  
  @Test
  public void testPrevious() {
    assertEquals(WEST, NORTH.previous());
    assertEquals(NORTH, EAST.previous());
    assertEquals(EAST, SOUTH.previous());
    assertEquals(SOUTH, WEST.previous());
  }

}
