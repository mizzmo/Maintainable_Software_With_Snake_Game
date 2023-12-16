package comp2013.Controller;

import javafx.scene.input.KeyCode;

/**
 *
 * The main brains of the program, used to sync actions
 * from the View and Model.
 * @author Toby Surtees
 */
public interface IController {
    /**
     * Checks if the snake has hit itself
     *
     * @return True if it has hit itself, False if not.
     */
    boolean checkSelfCollide();

    /**
     * Check if the snake is out of bounds.
     *
     * @return True if snake is out of bounds, False if not.
     */
    boolean checkOutOfBounds();

    /**
     * Move the snake in a specified direction
     * Only works if the snake is still alive.
     * If not, calls the function to end the game.
     */
    void moveSnake();

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

    /**
     * Handles what happens when the game ends.
     */
    void handleGameOver();

    /**
     * Restarts the game to its original state.
     */
    void restartGame();

}
