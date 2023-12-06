package comp2013;

public class SnakeController implements IController{
    SnakeModel m_Model;
    SnakeView m_View;
    // Holds an instance of the controller that is used in View to set the Controller
    // This is because JavaFX creates its own instance of view before the controller
    //      can be initialised, so in order to give it a controller, it needs to be
    //      done like this.
    public static SnakeController m_Instance;
    public SnakeController(){

        m_Instance = this;
        this.m_Model = SnakeModel.getInstance();
        m_Model.setController(this);
        this.m_View = SnakeView.getInstance();
        m_View.setController(this);

        System.out.printf("Im in the Controller!\n");
    }

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
}
