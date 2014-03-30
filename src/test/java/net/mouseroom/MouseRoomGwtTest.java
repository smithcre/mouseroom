package net.mouseroom;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * GWT JUnit tests must extend GWTTestCase.
 */
public class MouseRoomGwtTest extends GWTTestCase {

  /**
   * Must refer to a valid module that sources this class.
   */
  public String getModuleName() {
    return "net.mouseroom.MouseRoom";
  }

  /**
   * Add as many tests as you like.
   */
  public void testSimple() {
    assertTrue(true);
  }

}
