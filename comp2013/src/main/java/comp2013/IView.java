package comp2013;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;

public interface IView {
    /**
     * Sets the controller to use.
     * @param controller Controller to use.
     */
    void setController(SnakeController controller);
    /**
     * Gets the current url of the song playing
     * @return URL of the currently playing song.
     */
    String getMusic();

    /**
     * Set the currently playing music
     * @param url Location of new file to play ".mp3"
     */
    void setMusic(String url);

    /**
     * Plays the currently loaded song.
     */
    void playMusic();

    /**
     * Overloaded function to play music from a specified timestamp.
     * @param timeStamp
     */
    void playMusic(int timeStamp);

    /**
     * Stops the music from playing
     * @return Timestamp of the song that was playing
     */
    int stopMusic();

    /**
     * Draw the snake again from its new coordinates.
     */
    void refreshSnake();

    /**
     * Build the snake at the beginning of the game.
     * @param length Length of the snake to build.
     */
    void buildSnake(int length);

    /**
     * Sets the background image of the game.
     * @param imageView ImageView that holds the image.
     */
    public void setBackgroundImage(ImageView imageView);

    /**
     * Draws the current score of the game.
     */
    public void drawScore();

    /**
     * Swaps the snake-head image depending on the direction of travel
     */
    void changeHeadDirection();
}
