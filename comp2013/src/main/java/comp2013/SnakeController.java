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
        // Sets initial direction to RIGHT
        m_Snake.setDirection(SnakeObject.RIGHT);
        m_Model.setLength(5);
    }
    /**
     * Sets the view of the controller.
     * @param view SnakeView to be used in the controller.
     */
    // I have had to do this as FXML creates its own instance of Controller,
    //  before the view is created, so I have to set it after the fact from within view.
    public void setView(SnakeView view){ this.m_View = view; }
    /**
     * Returns an instance of the Controller.
     * @return SnakeController Instance
     */
    public static SnakeController getInstance() {
        return m_Instance;
    }

    // Checks if the snake has hit itself
    @Override
    public void checkSelfCollide() {
        // For every point in the body.
        for (SnakeBody bodyPoint1 : m_Snake.m_SnakeBody) {
            // For every other point in the body
            for (SnakeBody bodyPoint2 : m_Snake.m_SnakeBody) {
                // If the points are in the same place, and are not the same point.
                if (bodyPoint1.getY() == (bodyPoint2.getY()) && bodyPoint1.getX() == (bodyPoint2.getX()) && bodyPoint1 != bodyPoint2) {
                    m_Model.setAlive(0);
                    System.out.println("Snake has hit itself");
                    this.handleGameOver();
                }
            }
        }
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
            System.out.println("Snake is out of bounds");
            this.handleGameOver();
        }
    }

     // Move the snake in a specified direction
    @Override
    public void moveSnake() {
        // Determines what to add or take from the direction of the snake.
        float speedMultiplierY = 0;
        float speedMultiplierX = 0;
        // Stores the previous coordinate.
        int prevX = 50, prevY = 50;
        // Changes the direction the snakes head is facing.
        m_View.changeHeadDirection();
        // Check that the snake is still alive.
        this.checkOutOfBounds();
        this.checkSelfCollide();
        // If the snake is still alive.
        if(m_Model.getAlive() == 1)
        {
            if (m_Snake.getDirection() == SnakeObject.UP)
            {
                speedMultiplierY = m_Snake.m_SnakeSpeed * -1;
            } else if (m_Snake.getDirection() == SnakeObject.DOWN)
            {
                speedMultiplierY = m_Snake.m_SnakeSpeed;
            } else if (m_Snake.getDirection() == SnakeObject.LEFT)
            {
                speedMultiplierX = m_Snake.m_SnakeSpeed * -1 ;
            } else if (m_Snake.getDirection() == SnakeObject.RIGHT)
            {
                speedMultiplierX = m_Snake.m_SnakeSpeed;
            }
            // For every part in the body
            for (int i = m_Model.getLength() - 1; i > 0; i--) {


                SnakeBody currentPart = m_Snake.m_SnakeBody.get(i);
                SnakeBody previousPart = m_Snake.m_SnakeBody.get(i - 1);

                // Set the current part's position to the position of the part in front of it.
                currentPart.setX(previousPart.getX());
                currentPart.setY(previousPart.getY());
            }
            // Update the head's position based on the direction.
            SnakeBody head = m_Snake.m_SnakeBody.getFirst();
            head.setX((int) (head.getX() + speedMultiplierX));
            head.setY((int) (head.getY() + speedMultiplierY));
            // Checks if the snake has hit itself before updating the image.
            this.checkOutOfBounds();
            this.checkSelfCollide();

        }
        // Otherwise the game is over, handle this.
        else{this.handleGameOver();}
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
            // If Up Arrow or W is pressed.
            case UP:
            case W:
                // Stops the snake turning in on itself.
                if (m_Snake.getDirection() != SnakeObject.DOWN)
                {
                    // Set the new direction.
                    m_Snake.setDirection(SnakeObject.UP);
                }
                break;
            // If Down Arrow or S is pressed.
            case DOWN:
            case S:
                if (m_Snake.getDirection() != SnakeObject.UP)
                {
                    m_Snake.setDirection(SnakeObject.DOWN);
                }
                break;
            // If Left Arrow or A is pressed.
            case LEFT:
            case A:
                if (m_Snake.getDirection() != SnakeObject.RIGHT)
                {
                    m_Snake.setDirection(SnakeObject.LEFT);
                }
                break;
            // If Right Arrow or D is pressed.
            case RIGHT:
            case D:
                if (m_Snake.getDirection() != SnakeObject.LEFT)
                {
                    m_Snake.setDirection(SnakeObject.RIGHT);
                }
                break;
            default:
                break;
        }
    }
    // Handles what happens when the game is over.
    @Override
    public void handleGameOver() {
        return;
    }


}
