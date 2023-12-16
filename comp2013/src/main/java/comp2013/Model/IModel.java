package comp2013.Model;

/**
 * @author Toby Surtees
 * Holds useful information about the program.
 */
public interface IModel {
    /**
     * Gets whether the snake is still alive or not
     * @return Alive Status: 1 is alive, 0 is dead.
     */
    int getAlive();

    /**
     * Sets life status of the snake.
     * @param status Value for if the snake is alive or dead. 1 is alive, 0 is dead.
     */
    void setAlive(int status);
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
     * Sets the initial length of the snake to be referred back to
     * @param length Length of the snake.
     */
    void setInitialLength(int length);

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
     * Get the colour mode to use for the game
     * @return Int value for the colour mode.
     */
    int getColourMode();

    /**
     * Set the colour mode of the game.
     * @param colourMode Int to determine which colour mode to use.
     */
    void setColourMode(int colourMode);

    /**
     * Returns the wall mode currently in use
     * @return Int value of wall mode. 1 : In use. 0 : Not in use.
     */
    int getWallMode();

    /**
     * Set the wall mode to be used
     * @param wallMode Int value of the wall mode
     *                 1 : Use Walls, 0 : Dont use walls.
     */
    void setWallMode(int wallMode);


}
