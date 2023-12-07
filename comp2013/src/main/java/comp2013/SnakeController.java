package comp2013;

import javafx.fxml.FXML;
import javafx.scene.Camera;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


public class SnakeController implements IController {
    SnakeModel m_Model;
    SnakeView m_View;
    // Stores constants for the key codes for WSAD and Arrow Keys.
    private static final int W = 87, S = 83, A = 65, D = 68;
    private static final int UP = 38, DOWN = 39, LEFT = 37, RIGHT = 40;
    public SnakeObject m_Snake;
    // Holds an instance of the controller that is used in View to set the Controller
    // This is because JavaFX creates its own instance of view before the controller
    //      can be initialised, so in order to give it a controller, it needs to be
    //      done like this.
    public static SnakeController m_Instance;
    public SnakeController(){
        // Create an instance of controller.
        m_Instance = this;
        // Set the model.
        this.m_Model = SnakeModel.getInstance();
        m_Model.setController(this);

        // Create a new snake and pass it the values for the center of the screen.
        m_Snake = new SnakeObject(m_Model.getLength()/2, m_Model.getWidth()/2);
        // Tells the model that the snake is alive.
        m_Model.setAlive(1);
        m_Model.setLength(5);
    }
    /**
     * Sets the view of the controller.
     * @param view SnakeView to be used in the controller.
     */
    // I have had to do this as FXML creates its own instance of Controller,
    //  before the view is created, so I have to set it after the fact from within view.
    public void setView(SnakeView view){ this.m_View = SnakeView.getInstance(); }
    /**
     * Returns an instance of the Controller.
     * @return SnakeController Instance
     */
    public static SnakeController getInstance() {
        return m_Instance;
    }
    // Checks if the snake is moving.
    @Override
    public boolean getSnakeMoving() {
        return false;
    }


    // Checks if the snake has hit itself

    @Override
    public boolean getSelfCollide() {
        return false;
    }

    // Check if the snake is out of bounds.
    @Override
    public void checkOutOfBounds()
    {
        SnakeBody snakeHead = m_Snake.m_SnakeBody.getFirst();
        boolean xOut = (snakeHead.getX() <= 0 || snakeHead.getX() >= (m_Model.getWidth()));
        boolean yOut = (snakeHead.getY() <= 0 || snakeHead.getY() >= (m_Model.getHeight()));
        if (xOut || yOut)
        {
            // Set the snake to be dead if found to be out of bounds
            m_Model.setAlive(0);
        }
    }

     // Move the snake in a specified direction
    @Override
    public void moveSnake(int direction) {

    }

    // Add a new segment to the snake.
    @Override
    public void addSegment(int x, int y, boolean newSegment){
        this.m_Snake.m_SnakeBody.add(new SnakeBody(x, y));
        // If this is a new addition, add to the lengh
        // This is used after the snake is initialised to the starting length.
        if(newSegment) {
            this.m_Model.incrementLength();
        }
    }

    @Override
    public void handleKeyPress(KeyCode code) {
        // Decides what to do when each key is pressed.
        switch (code)
        {
            case KeyEvent.VK_UP:
                if (!down)
                {
                    up = true;
                    down = false;
                    left = false;
                    right = false;

                    newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, -90);
                }
                break;

            case KeyEvent.VK_DOWN:
                if (!up)
                {
                    up = false;
                    down = true;
                    left = false;
                    right = false;

                    newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, 90);
                }
                break;

            case KeyEvent.VK_LEFT:
                if (!right)
                {
                    up = false;
                    down = false;
                    left = true;
                    right = false;

                    newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, -180);

                }
                break;

            case KeyEvent.VK_RIGHT:
                if (!left)
                {
                    up = false;
                    down = false;
                    left = false;
                    right = true;

                    newImgSnakeHead = IMG_SNAKE_HEAD;
                }

            default:
                break;
        }
    }


}
