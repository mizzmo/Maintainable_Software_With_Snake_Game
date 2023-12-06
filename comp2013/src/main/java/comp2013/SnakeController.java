package comp2013;

public class SnakeController implements IController{
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
