package comp2013;

import javafx.scene.input.KeyCode;

public interface IController {
    /**
     * Move the snake Left.
     */
    static final int LEFT = 0;
    /**
     * Move the snake Right.
     */
    static final int RIGHT = 1;
    /**
     * Move the snake Up.
     */
    static final int UP = 2;
    /**
     * Move the snake Down.
     */
    static final int DOWN = 3;

    /**
     * Checks if the snake is moving.
     * @return True if it is moving, False if it is not.
     */
    boolean getSnakeMoving();

    /**
     * Checks if the snake has hit itself
     * @return True if it has hit itself, False if not.
     */
    boolean getSelfCollide();

    /**
     * Check if the snake is out of bounds.
     * @return True if snake is out of bounds, False if not.
     */
    void checkOutOfBounds();

    /**
     * Move the snake in a specified direction
     * @param direction Direction to move the snake.
     */
    void moveSnake(int direction);

    /**
     * Adds a segment to the snake.
     * @param x X coordinate of the segment.
     * @param y Y coordinate of the segment
     * @param newSegment If true, increment length, else length stays the same.
     */
    void addSegment(int x, int y, boolean newSegment);

    /**
     * Decides what happens when a key is pressed.
     * @param code  The code for the key that was pressed.
     */
    void handleKeyPress(KeyCode code);
}
