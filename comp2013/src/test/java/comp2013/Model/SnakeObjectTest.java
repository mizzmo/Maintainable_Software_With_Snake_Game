package comp2013.Model;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Tests the SnakeObject Class.
 */
public class SnakeObjectTest extends TestCase {
    private SnakeObject M_SnakeObject;

    /**
     * Test the getters and setters for Direction
     */
    @Test
    public void testGetDirection() {
        // Make a new object
        M_SnakeObject = new SnakeObject(0,0);
        // Set the direction
        M_SnakeObject.setDirection(3);
        // Check it matches
        assertEquals(3, M_SnakeObject.getDirection());

    }

    /**
     * Test that get Prev diection works correctly
     */
    @Test
    public void testGetPreviousDirection() {
        // Make a new object
        M_SnakeObject = new SnakeObject(0,0);
        // Set the direction
        M_SnakeObject.setDirection(3);
        // Set the direction to something new
        M_SnakeObject.setDirection(2);
        // Check it matches the previous direction
        assertEquals(3, M_SnakeObject.getPreviousDirection());
    }
}