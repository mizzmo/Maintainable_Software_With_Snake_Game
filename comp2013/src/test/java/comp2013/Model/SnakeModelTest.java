package comp2013.Model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the SnakeModel Class
 */
public class SnakeModelTest extends TestCase {
    private SnakeModel M_Model;
    @Before
    public void setUp() throws Exception {
        M_Model = new SnakeModel(500, 500);
    }

    /**
     * Test the getter for alive works
     * Also checks the setter works at the same time.
     */
    @Test
    public void testGetAlive() {
        // Create new model
        M_Model = new SnakeModel(500,500);
        // Set to alive
        M_Model.setAlive(1);
        // Check if it matches
        assertEquals(1, M_Model.getAlive());
    }

    /**
     * Test to check if set initial length works.
     */
    @Test
    public void testSetInitialLength() {
        // Create new model
        M_Model = new SnakeModel(0,0);
        // Set initial length
        M_Model.setInitialLength(5);
        // Check if it matches
        assertEquals(5, M_Model.m_InitialLength);
    }

    /**
     * Test to see if get score works
     * Also checks if set score works.
     */
    @Test
    public void testGetScore() {
        // Create new model
        M_Model = new SnakeModel(0,0);
        // Set the score
        M_Model.setScore(600);
        // Check if it matches
        assertEquals(600, M_Model.getScore());
    }

    /**
     * Test to see if getter for length works
     * Also checks if the setter works
     */
    @Test
    public void testGetLength() {
        // Create new model
        M_Model = new SnakeModel(0,0);
        // Set the length
        M_Model.setLength(5);
        // check it matches
        assertEquals(5, M_Model.getLength());
    }

    /**
     * Check to see if the length increment method works.
     */
    @Test
    public void testIncrementLength() {
        // Create new model
        M_Model = new SnakeModel(0,0);
        // Set the length
        M_Model.setLength(5);
        // Increment the length
        M_Model.incrementLength();
        // Check that it matches
        assertEquals(6, M_Model.getLength());
    }

    /**
     * Checks if getHeight works
     * Also checks setter for height
     */
    @Test
    public void testGetHeight() {
        // Create new model
        M_Model = new SnakeModel(0,0);
        // Set the height
        M_Model.setHeight(300);
        //Check it matches
        assertEquals(300, M_Model.getHeight());
    }
    /**
     * Checks if getWidth works
     * Also checks setter for width
     */
    @Test
    public void testGetWidth() {
        // Create new model
        M_Model = new SnakeModel(0,0);
        // Set the width
        M_Model.setWidth(500);
        //Check it matches
        assertEquals(500, M_Model.getWidth());
    }

    /**
     * Check to see if setter and getter for colour mode
     * works correctly
     */
    @Test
    public void testGetColourMode() {
        // Make new model
        M_Model = new SnakeModel(0,0);
        // Set the colour mode
        M_Model.setColourMode(0);
        // Check it matches
        assertEquals(0, M_Model.getColourMode());
    }

    /**
     * Check getter and setter for wall mode work
     */
    @Test
    public void testGetWallMode() {
        // Make new model
        M_Model = new SnakeModel(0,0);
        // Set the colour mode
        M_Model.setWallMode(1);
        // Check it matches
        assertEquals(1, M_Model.getWallMode());
    }
}