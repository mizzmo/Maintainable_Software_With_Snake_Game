package comp2013;

public interface IModel {
    /**
     * Initialise the game to a specified size, and store references to the view and controller.
     * @param width Width of screen in pixels.
     * @param height Height of screen in pixels.
     */
    void initialise(int width, int height);


    /**
     * Get the score of the game.
     * @return Value of the current score.
     */
    int getScore();

    /**
     * Set the score of the game
     * @param m_Score Value of the current score.
     */
    void setScore(int m_Score);

    /**
     * Get the current length of the snake in play.
     * @return Length of the snake.
     */
    int getLength();

    /**
     * Set the length of the snake to a specific value.
     * @param m_Length
     */
    void setLength(int m_Length);

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
     * @param m_Height Height of the screen in Pixels.
     */
    void setHeight(int m_Height);

    /**
     * Set the width of the screen in Pixels
     * @param m_Width Width of the screen in pixels
     */
    void setWidth(int m_Width);
    /**
     * Check if the game is over or not.
     * @return true: Game has finished, False: Game is in progress.
     */
    boolean hasFinished();

    /**
     * Set the game to be finished
     * @param m_Finished stores if the game is over.
     */
    void setFinished(boolean m_Finished);

    /**
     * Restart the game from the beginning.
     */
    void restart();


}
