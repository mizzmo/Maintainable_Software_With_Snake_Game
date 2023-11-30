package comp2013;

import javax.swing.*;
import java.awt.*;

public class SnakeView implements IView{
    // Create a new JFrame as a Class Variable
    public static JFrame m_JFrame = new JFrame();
    // Store references to the controller and model.
    IModel model = new SnakeModel();
    IController controller = new SnakeController();

    /**
     * Gets the current url of the song playing
     *
     * @return URL of the currently playing song.
     */
    @Override
    public String getMusic() {
        return null;
    }

    /**
     * Set the currently playing music
     *
     * @param url Location of new file to play ".mp3"
     */
    @Override
    public void setMusic(String url) {

    }

    /**
     * Plays the currently loaded song.
     */
    @Override
    public void playMusic() {

    }

    /**
     * Overloaded function to play music from a specified timestamp.
     *
     * @param timeStamp
     */
    @Override
    public void playMusic(int timeStamp) {

    }

    /**
     * Stops the music from playing
     *
     * @return Timestamp of the song that was playing
     */
    @Override
    public int stopMusic() {
        return 0;
    }

    /**
     * Load the current game frame.
     */
    @Override
    public void loadFrame() {

    }

    /**
     * Draws the body of the snake for a specified length.
     *
     * @param length Length of the snake in play.
     */
    @Override
    public void drawBody(int length) {

    }

    /**
     * Refreshes the screen.
     */
    @Override
    public void refreshDisplay() {

    }

    /**
     * Initialise the screen to play the game.
     *
     * @param height Height of the screen in Pixels.
     * @param width  Width of the screen in Pixels.
     */
    @Override
    public void initialiseDisplay(int height, int width) {
        m_JFrame = new JFrame("Snake!"); // Set the title and establish a new JFrame object.
        // Set the icon of the frame.
        m_JFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(MyFrame.class.getResource("/images/snake-logo.png")));
        //Set the size of the frame.
        model.setHeight(height);
        model.setWidth(width);

        m_JFrame.setVisible(true);
    }
}
