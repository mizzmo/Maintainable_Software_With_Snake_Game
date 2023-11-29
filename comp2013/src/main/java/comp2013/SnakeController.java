package comp2013;

public class SnakeController implements IController{
    /**
     * Checks if the snake is moving.
     *
     * @return True if it is moving, False if it is not.
     */
    @Override
    public boolean getSnakeMoving() {
        return false;
    }

    /**
     * Checks if the snake has hit itself
     *
     * @return True if it has hit itself, False if not.
     */
    @Override
    public boolean getSelfCollide() {
        return false;
    }

    /**
     * Check if the snake is out of bounds.
     *
     * @return True if snake is out of bounds, False if not.
     */
    @Override
    public boolean getOutOfBounds() {
        return false;
    }

    /**
     * Move the snake in a specified direction
     *
     * @param direction Direction to move the snake.
     */
    @Override
    public void moveSnake(int direction) {

    }
}
