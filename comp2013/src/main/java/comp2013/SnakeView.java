package comp2013;

import javax.swing.*;
import java.awt.*;

public class SnakeView implements IView{
    // Create a new JFrame as a Class Variable
    public static JFrame m_JFrame = new JFrame();
    // Store references to the controller
    SnakeController controller;

    // Initialised Constructor
    SnakeView(SnakeController controller){
        this.controller = controller;
    }

    // Gets the current url of the song playing
    @Override
    public String getMusic() {
        return null;
    }
     // Set the currently playing music
    @Override
    public void setMusic(String url) {

    }

    // Plays the currently loaded song.
    @Override
    public void playMusic() {

    }


    // Overloaded function to play music from a specified timestamp.
    @Override
    public void playMusic(int timeStamp) {

    }

    // Stops the music from playing
    @Override
    public int stopMusic() {
        return 0;
    }


    // Load the current game frame.
    @Override
    public void loadFrame() {

    }

    //Draws the body of the snake for a specified length.
    @Override
    public void drawBody(int length) {

    }

     // Refreshes the screen.
    @Override
    public void refreshDisplay() {

    }


     // Initialise the screen to play the game.
    @Override
    public void initialiseDisplay(int height, int width) {
        m_JFrame = new JFrame("Snake!"); // Set the title and establish a new JFrame object.
        // Set the icon of the frame.
        m_JFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(MyFrame.class.getResource("/images/snake-logo.png")));
        m_JFrame.setSize(width, height);
        m_JFrame.setLocationRelativeTo(null);
        m_JFrame.setVisible(true);
    }

}
