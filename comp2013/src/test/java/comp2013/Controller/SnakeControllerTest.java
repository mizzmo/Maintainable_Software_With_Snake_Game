package comp2013.Controller;

import comp2013.Model.SnakeBody;
import comp2013.Model.SnakeModel;
import comp2013.Model.SnakeObject;
import javafx.scene.input.KeyCode;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the SnakeController Class
 */
public class SnakeControllerTest extends TestCase {
    private SnakeController M_Controller;
    private SnakeModel M_Model;

    /**
     * Test that the selfCollide System works properly.
     */
    @Test
    public void testCheckSelfCollide() {
        // Make a new model
        M_Model = new SnakeModel(900, 600);
        // Make a new snake controller
        M_Controller = new SnakeController();
        // Set the model
        M_Controller.m_Model = M_Model;
        M_Model.setController(M_Controller);
        // Add a new body segment in the same location as the head
        M_Controller.addSegment(M_Model.getWidth()/2,M_Model.getHeight()/2, true);
        // Check if the snake has hit itself
        boolean collide = M_Controller.checkSelfCollide();
        // Check that the method identifies the snake has hit itself
        assertTrue(collide);
    }

    /**
     * Test to check that out of bounds method works correctly
     */
    @Test
    public void testCheckOutOfBounds() {
        // Make a new model
        M_Model = new SnakeModel(500, 500);
        // Make a new snake controller
        M_Controller = new SnakeController();
        // Set the model
        M_Controller.m_Model = M_Model;
        M_Model.setController(M_Controller);
        // Set up a snake head within the bounds
        SnakeBody snakeHead = new SnakeBody(50, 50);
        M_Controller.m_Snake.m_SnakeBody.addFirst(snakeHead);
        // Test when the snake head is within the bounds
        assertFalse(M_Controller.checkOutOfBounds());
        // Move the snake head out of bounds
        snakeHead.setX(-1);
        snakeHead.setY(50);
        // Test when the snake head is out of bounds
        assertTrue(M_Controller.checkOutOfBounds());
    }

    /**
     * Test to check that addSegment works correctly
     */
    @Test
    public void testAddSegment() {
        // Make a new model
        M_Model = new SnakeModel(500, 500);
        // Make a new snake controller
        M_Controller = new SnakeController();
        // Set the model
        M_Controller.m_Model = M_Model;
        M_Model.setController(M_Controller);
        // Get the length before addin
        int initialLen = M_Model.getLength();
        // Add a new segment
        M_Controller.addSegment(50,50, true);
        // Check the length increased by 1 after adding
        assertEquals(initialLen+1, M_Model.getLength());
        // Check the new segment is in the list of bodyparts
        int newX = M_Controller.m_Snake.m_SnakeBody.getLast().getX();
        // Check the X matches
        assertEquals(50, newX);

    }

    /**
     * Test to check that handleKeyPress works correctly
     */
    @Test
    public void testHandleKeyPress() {
        // Make a new model
        M_Model = new SnakeModel(500, 500);
        // Make a new snake controller
        M_Controller = new SnakeController();
        // Set the model
        M_Controller.m_Model = M_Model;
        M_Model.setController(M_Controller);
        // Simulate pressing the DOWN key
        M_Controller.handleKeyPress(KeyCode.DOWN);
        // Snake should be facing DOWN
        assertEquals(SnakeObject.DOWN, M_Controller.m_Snake.getDirection());
        // Simulate pressing the RIGHT key
        M_Controller.handleKeyPress(KeyCode.RIGHT);
        // Snake should be facing RIGHT
        assertEquals(SnakeObject.RIGHT, M_Controller.m_Snake.getDirection());
        // Simulate pressing random key, shouldnt do anything
        M_Controller.handleKeyPress(KeyCode.ENTER);
        // The direction should still be RIGHT
        assertEquals(SnakeObject.RIGHT, M_Controller.m_Snake.getDirection());
    }


}