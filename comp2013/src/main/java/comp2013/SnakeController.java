package comp2013;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;



public class SnakeController implements IController{
    @FXML
    public Label scoreLabel;
    @FXML
    private ImageView imageView;
    SnakeModel m_Model;
    SnakeView m_View;

    public SnakeObject m_Snake;
    // Holds an instance of the controller that is used in View to set the Controller
    // This is because JavaFX creates its own instance of view before the controller
    //      can be initialised, so in order to give it a controller, it needs to be
    //      done like this.
    public static SnakeController m_Instance;
    public SnakeController(){

        m_Instance = this;
        this.m_Model = SnakeModel.getInstance();
        m_Model.setController(this);

        // Create a new snake.
        m_Snake = new SnakeObject();
        // Tells the model that the snake is alive.
        m_Model.setAlive(1);
    }

    /**
     * Sets the view of the controller.
     * @param view SnakeView to be used in the controller.
     */
    // I have had to do this as FXML creates its own instance of Controller,
    //  before the view is created, so I have to set it after the fact from within view.
    public void setView(SnakeView view){ m_View = view; }
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
    public boolean getOutOfBounds() {
        return false;
    }


     // Move the snake in a specified direction
    @Override
    public void moveSnake(int direction) {

    }

    @FXML
    public void initialize() {
        Image background = new Image(getClass().getResourceAsStream("/images/UI-Background.png"));
        imageView.setImage(background);

    }


}
