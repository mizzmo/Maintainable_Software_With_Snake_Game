package comp2013.View;

import comp2013.Controller.SnakeController;
import comp2013.Model.SnakeModel;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Tests the SnakeWall Class, non GUI tests.
 */
public class SnakeWallTest extends TestCase {
    private SnakeWall M_Wall;
    private SnakeController M_Controller;
    private SnakeModel M_Model;

    /**
     * Tests that the wall is rotated every time rotateWall is called.
     */
    @Test
    public void testRotateWall() {
        // Create new controller and model
        M_Model = new SnakeModel(500,500);
        M_Controller = new SnakeController();
        // Make new wall
        M_Wall = new SnakeWall();
        // Rotate the wall so its vertical.
        M_Wall.rotateWall();
        // Check the dimenions are correct
        assertEquals(32, M_Wall.m_Width);
        assertEquals(128, M_Wall.m_Height);
        // Rotate the wall so its horizontal.
        M_Wall.rotateWall();
        // Check the dimenions are correct
        assertEquals(128, M_Wall.m_Width);
        assertEquals(32, M_Wall.m_Height);
    }
}