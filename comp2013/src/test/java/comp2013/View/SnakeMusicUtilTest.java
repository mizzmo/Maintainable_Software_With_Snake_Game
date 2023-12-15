package comp2013.View;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Tests the SnakeMusicUtil Class.
 */
public class SnakeMusicUtilTest extends TestCase {
    /**
     * Test if getMedia returns the correct address.
     */
    @Test
    public void testGetMedia() {
        // Get the correct address
        String correctAddress =
                "file:/C:/Users/mizzm/OneDrive/Documents/comp2013_cw/" +
                        "comp2013/target/classes/sound/frogger.mp3";
        // Get the address from the table
        String getAddress = SnakeMusicUtil.getMedia("frogger");
        // Check they match
        assertEquals(correctAddress, getAddress);
    }
}