package comp2013.Model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the SnakeBody Class.
 */
public class SnakeBodyTest extends TestCase {

    private SnakeBody M_SnakeBody;
    @Before
    public void setUp() throws Exception {
        M_SnakeBody = new SnakeBody(0, 0);
    }

    /**
     * Test that the getter for X works correctly
     */
    @Test
    public void testGetX() {
        // Make a new snakebody
        M_SnakeBody = new SnakeBody(0, 0);
        // Get the x value
        int xValue = M_SnakeBody.getX();
        // Match to the expected output.
        assertEquals(0, xValue);
    }

    /**
     * Test that the getter for Y works correctly.
     */
    @Test
    public void testGetY() {
        // Make a new snakebody
        M_SnakeBody = new SnakeBody(0, 5);
        // Get the x value
        int yValue = M_SnakeBody.getY();
        // Match to the expected output.
        assertEquals(5, yValue);
    }

    /**
     * Test that the getter value for PrevX works correctly.
     */
    @Test
    public void testGetPrevX() {
        // Make a new snakebody
        M_SnakeBody = new SnakeBody(1, 1);
        // Set a new X value.
        M_SnakeBody.setX(5);
        // Get the previous X value
        int newXValue = M_SnakeBody.getPrevX();
        // Match to the expected output.
        assertEquals(1, newXValue);
    }
    /**
     * Test that the getter value for PrevY works correctly.
     */
    @Test
    public void testGetPrevY() {
        // Make a new snakebody
        M_SnakeBody = new SnakeBody(3, 3);
        // Set a new Y value.
        M_SnakeBody.setY(5);
        // Get the previous X value
        int newYValue = M_SnakeBody.getPrevY();
        // Match to the expected output.
        assertEquals(3, newYValue);
    }

    /**
     * Test that the setter for X works correctly
     */
    @Test
    public void testSetX() {
        // Create new object
        M_SnakeBody = new SnakeBody(0,0);
        // Set X to 5
        M_SnakeBody.setX(5);
        // Check that X = 5;
        assertEquals(5, M_SnakeBody.getX());
    }
    /**
     * Test that the setter for Y works correctly
     */
    @Test
    public void testSetY() {
        // Create new object
        M_SnakeBody = new SnakeBody(0,0);
        // Set Y to 6
        M_SnakeBody.setY(6);
        // Check that Y = 6;
        assertEquals(6, M_SnakeBody.getY());
    }


}