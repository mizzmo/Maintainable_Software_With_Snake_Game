package comp2013.View;

import comp2013.Controller.SnakeController;
import comp2013.Model.SnakeBody;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Random;

public class SnakeWall {
    private int M_X, M_Y, M_Width, M_Height;

    private SnakeController M_Controller = SnakeController.getInstance();

    SnakeWall(){
        // Get the width and height of the image.
        this.M_Width = 128;
        this.M_Height = 32;
        // Set a random location on the screen
        this.M_X = (int) (Math.random() * (M_Controller.m_Model.getWidth()*0.9 - M_Width));
        this.M_Y = (int) (Math.random() * (M_Controller.m_Model.getHeight()*0.9 - M_Height));
    }

    /**
     * Checks if the snake has hit the wall or not.
     */
    public boolean checkHitWall()	{
        // For every point in the body.
        for (SnakeBody snakePart : M_Controller.m_Snake.m_SnakeBody) {
            int snakeBodyX = snakePart.getX();
            int snakeBodyY = snakePart.getY();
            // Checks if the wall is intersecting with any part of the snake
            boolean isIntersecting = SnakeFood.areImagesIntersecting(snakeBodyX, snakeBodyY, 32, 32,
                    this.M_X, this.M_Y, this.M_Width, this.M_Height);
            // If intersecting, return true.
            if(isIntersecting){
                return true;
            }
        }
        return false;
    }

    /**
     * Draw the wall to the screen.
     * @param canvas Canvas to draw the wall onto.
     */
    public void drawWall(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setEffect(null);
        // Draw the clear rectangle
        gc.clearRect(0, 0, gc.getCanvas().getWidth(),
                gc.getCanvas().getHeight());
        // Add the effect back again
        if(M_Controller.m_Model.getColourMode() == 1) {
            gc.setEffect(M_Controller.m_View.M_ColorAdjust);
        }
        gc.setFill(Color.RED);
        gc.fillRect(this.M_X, this.M_X, this.M_Width, this.M_Height);
    };
}
