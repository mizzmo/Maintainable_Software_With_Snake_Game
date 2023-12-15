package comp2013.View;

import comp2013.Controller.SnakeController;
import comp2013.Model.SnakeModel;
import javafx.scene.canvas.Canvas;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class SnakeFoodTest extends TestCase {
    /**
     * Test to see if the image intersection method works
     */
    @Test
    public void testAreImagesIntersecting() {
        // Set the image coordinates and dimenstions to intersect
        boolean areIntersect = SnakeFood.imageIntersect(50, 50, 50,50,50,50,50,50);
        // Check that it recognises the images are intersecting
        assertTrue(areIntersect);
        // Set to images that arent intersecting
        areIntersect = SnakeFood.imageIntersect(150, 150, 50,50,50,50,50,50);
        // Check that it recognises the images are intersecting
        assertFalse(areIntersect);
    }
}