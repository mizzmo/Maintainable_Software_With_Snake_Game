package comp2013;

public interface IModel {
    /**
     * Initialise the game to a specified size, and store references to the view and controller.
     * @param width Width, in pixels.
     * @param height Height, in pixels.
     * @param view the view to use.
     * @param controller the controller to use.
     */
    void initialise( int width, int height, IView view, IController controller );
    /**
     * Get the score of the game.
     * @return Value of the current score.
     */
    int getScore();

    /**
     * Set the score of the game
     * @param score Value of the current score.
     */
    void setScore(int score);

    /**
     * Get the current length of the snake in play.
     * @return Length of the snake.
     */
    int getLength();

    /**
     * Set the length of the snake to a specific value.
     * @param length
     */
    void setLength(int length);

    /**
     * Adds one to the length of the snake.
     */
    void incrementLength();

    /**
     * Gets the Height of the screen in pixels.
     * @return Height of the screen in pixels.
     */
    int getHeight();
    /**
     * Gets the Width of the screen in pixels.
     * @return Width of the screen in pixels.
     */
    int getWidth();

    /**
     * Set the height of the screen
     * @param height Height of the screen in Pixels.
     */
    void setHeight(int height);

    /**
     * Set the width of the screen in Pixels
     * @param width Width of the screen in pixels
     */
    void setWidth(int width);
    /**
     * Check if the game is over or not.
     * @return true: Game has finished, False: Game is in progress.
     */
    boolean hasFinished();

    /**
     * Set the game to be finished
     * @param finished stores if the game is over.
     */
    void setFinished(boolean finished);

    /**
     * Restart the game from the beginning.
     */
    void restart();


}
