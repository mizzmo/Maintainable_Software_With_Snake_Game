package comp2013.Controller;

import comp2013.Model.SnakeBody;
import comp2013.Model.SnakeModel;
import comp2013.Model.SnakeObject;
import comp2013.View.SnakeView;
import javafx.scene.input.KeyCode;


public class SnakeController implements IController {
    public SnakeModel m_Model;
    public SnakeView m_View;
    // Stores constants for the key codes for WSAD and Arrow Keys.
    private static final int W = 87, S = 83, A = 65, D = 68;
    private static final int UP = 38, DOWN = 39, LEFT = 37, RIGHT = 40;

    private float M_SpeedMultiplierX, M_SpeedMultiplierY;
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
        m_Snake = new SnakeObject(m_Model.getWidth()/2, m_Model.getHeight()/2);

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
    public boolean checkSelfCollide() {
        // For every point in the body.
        for (SnakeBody bodyPoint1 : m_Snake.m_SnakeBody) {
            // For every other point in the body
            for (SnakeBody bodyPoint2 : m_Snake.m_SnakeBody) {
                // If the points are in the same place, and are not the same point.
                if (bodyPoint1.getY() == (bodyPoint2.getY()) && bodyPoint1.getX() == (bodyPoint2.getX()) && bodyPoint1 != bodyPoint2) {
                    m_Model.setAlive(0);
                    return true;
                }
            }
        }
        return false;
    }


    // Check if the snake is out of bounds.
    @Override
    public boolean checkOutOfBounds()
    {
        SnakeBody snakeHead = m_Snake.m_SnakeBody.getFirst();
        boolean xOut = (snakeHead.getX() < 0 || snakeHead.getX() > (m_Model.getWidth()));
        boolean yOut = (snakeHead.getY() < 0 || snakeHead.getY() > (m_Model.getHeight()));
        if (xOut || yOut)
        {
            // Set the snake to be dead if found to be out of bounds
            m_Model.setAlive(0);
            return true;
        }
        return false;
    }


     // Move the snake in a specified direction
     @Override
     public void moveSnake() {
         // Determines what to add or take from the direction of the snake.
         float speedMultiplierY = 0;
         float speedMultiplierX = 0;
         // Counts the amount of times move snake has been called.
         // Stores the previous coordinate.
         int prevX = 50, prevY = 50;
         // Changes the direction the snakes head is facing.
         m_View.changeHeadDirection();
         // Check that the snake is still alive.
         if(this.checkOutOfBounds()){
             // If out of bounds, end this loop and handle the gameover.
             this.handleGameOver();
             return;
         }
         if(this.checkSelfCollide()){
             // If out of hit itself, end this loop and handle the gameover.
             this.handleGameOver();
             return;
         }
         // If the snake is still alive.
         if(m_Model.getAlive() == 1)
         {
             if (m_Snake.getDirection() == SnakeObject.UP)
             {
                 speedMultiplierY =-1;
             } else if (m_Snake.getDirection() == SnakeObject.DOWN)
             {
                 speedMultiplierY = 1;
             } else if (m_Snake.getDirection() == SnakeObject.LEFT)
             {
                 speedMultiplierX = -1 ;
             } else if (m_Snake.getDirection() == SnakeObject.RIGHT)
             {
                 speedMultiplierX = 1;
             }
             // For every part in the body
             for (int i = m_Model.getLength() - 1; i > 0; i--) {
                 SnakeBody currentPart = m_Snake.m_SnakeBody.get(i);
                 SnakeBody previousPart = m_Snake.m_SnakeBody.get(i - 1);

                 // Update the current part's position to the position of the previous part.
                 currentPart.setX(previousPart.getX());
                 currentPart.setY(previousPart.getY());
             }

             // Update the head's position based on the direction.
             SnakeBody head = m_Snake.m_SnakeBody.getFirst();
             head.setX((int) (head.getX() + speedMultiplierX * m_Snake.m_SnakeSpeed));
             head.setY((int) (head.getY() + speedMultiplierY * m_Snake.m_SnakeSpeed));
         }
         // Set the direction to the current direction.
         m_Snake.setDirection(m_Snake.getDirection());
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
            case KeyCode.ESCAPE:
                    m_View.pauseGame();
                break;
            default:
                break;
        }
    }
    // Handles what happens when the game is over.
    @Override
    public void handleGameOver() {
        m_View.gameOverScreen();
    }

    @Override
    public void restartGame() {
        // Reset the model to default.
        m_Model.setScore(0);
        m_Model.setLength(m_Model.m_InitialLength);
        m_Model.setAlive(1);
        // Reset the snake.
        m_Snake = new SnakeObject(m_Model.getLength()/2, m_Model.getWidth()/2);
        // Restart the UI.
        m_View.restartGame();

    }


}
