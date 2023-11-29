package comp2013;

public class SnakeModel implements IModel {
    /**
     * Initialise the game to a specified size, and store references to the view and controller.
     *
     * @param width      Width, in pixels.
     * @param height     Height, in pixels.
     * @param view       the view to use.
     * @param controller the controller to use.
     */
    @Override
    public void initialise(int width, int height, IView view, IController controller) {

    }

    /**
     * Get the score of the game.
     *
     * @return Value of the current score.
     */
    @Override
    public int getScore() {
        return 0;
    }

    /**
     * Set the score of the game
     *
     * @param score Value of the current score.
     */
    @Override
    public void setScore(int score) {

    }

    /**
     * Get the current length of the snake in play.
     *
     * @return Length of the snake.
     */
    @Override
    public int getLength() {
        return 0;
    }

    /**
     * Set the length of the snake to a specific value.
     *
     * @param length
     */
    @Override
    public void setLength(int length) {

    }

    /**
     * Adds one to the length of the snake.
     */
    @Override
    public void incrementLength() {

    }

    /**
     * Gets the Height of the screen in pixels.
     *
     * @return Height of the screen in pixels.
     */
    @Override
    public int getHeight() {
        return 0;
    }

    /**
     * Gets the Width of the screen in pixels.
     *
     * @return Width of the screen in pixels.
     */
    @Override
    public int getWidth() {
        return 0;
    }

    /**
     * Set the height of the screen
     *
     * @param height Height of the screen in Pixels.
     */
    @Override
    public void setHeight(int height) {

    }

    /**
     * Set the width of the screen in Pixels
     *
     * @param width Width of the screen in pixels
     */
    @Override
    public void setWidth(int width) {

    }

    /**
     * Check if the game is over or not.
     *
     * @return true: Game has finished, False: Game is in progress.
     */
    @Override
    public boolean hasFinished() {
        return false;
    }

    /**
     * Set the game to be finished
     *
     * @param finished stores if the game is over.
     */
    @Override
    public void setFinished(boolean finished) {

    }

    /**
     * Restart the game from the beginning.
     */
    @Override
    public void restart() {

    }
}
