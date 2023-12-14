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

    public SnakeWall(){
        // Get the width and height of the image.
        this.M_Width = 128;
        this.M_Height = 32;
        // Set a random location on the screen
        this.M_X = (int) (Math.random() *
                (M_Controller.m_Model.getWidth()*0.7));
        this.M_Y = (int) (Math.random() *
                (M_Controller.m_Model.getHeight()*0.7));
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
            boolean isIntersecting = SnakeFood.areImagesIntersecting
                    (snakeBodyX, snakeBodyY, 32, 32,
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
    public void drawWall(Canvas canvas) {
        // If the wall doesnt overlap the snake.
        if (!checkHitWall()){
                // Generate a random number to determine which direction the wall will lay.
                int i = new Random().nextInt(2);
            // If the random number is 0, change the direction of the wall.
            if (i == 0) {
                rotateWall();
            }
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setEffect(null);
            // Draw the clear rectangle
            gc.clearRect(0, 0, gc.getCanvas().getWidth(),
                    gc.getCanvas().getHeight());
            // Add the effect back again
            if (M_Controller.m_Model.getColourMode() == 1) {
                gc.setEffect(M_Controller.m_View.m_ColorAdjust);
            }
            // Draw a 3D appearing rectangle on the screen.
            gc.setFill(Color.DARKRED);
            gc.fillRect(this.M_X, this.M_Y, this.M_Width + 4,
                    this.M_Height + 4);
            gc.setFill(Color.RED);
            gc.fillRect(this.M_X, this.M_Y, this.M_Width,
                    this.M_Height);
        }
        // If the wall does overlap the snake, move it somewhere else.
        else{ this.newWall(); }
    };

    /**
     * Changes the wall from a horizontal wall to a vertical wall.
     */
    private void rotateWall(){
        this.M_Width = 32;
        this.M_Height = 128;
    }

    /**
     * Changes the location of the wall to a new location.
     */
    public void newWall(){
        this.M_X = (int) (Math.random() *
                (M_Controller.m_Model.getWidth()*0.7));
        this.M_Y = (int) (Math.random() *
                (M_Controller.m_Model.getHeight()*0.7));
        // If this new wall is ontop of the snake, make a new wall.
        if (checkHitWall()) { this.newWall();}
    }
}
