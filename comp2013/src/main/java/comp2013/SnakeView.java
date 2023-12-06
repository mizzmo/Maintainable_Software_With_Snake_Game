package comp2013;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import javax.swing.*;
import java.awt.*;

public class SnakeView extends Application implements IView {
    // Store references to the controller
    SnakeController m_Controller;

    private static SnakeView m_Instance;
    public SnakeView() {
        m_Controller = SnakeController.getInstance();
    }

    public static SnakeView getInstance() {
        return m_Instance;
    }

    public void setController(SnakeController controller){
        this.m_Controller = controller;
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

    @Override
    public void start(Stage primaryStage) {
        if (m_Controller == null) {
            // Handle the case where the controller is not set.
            System.err.println("Controller is not set in SnakeView.");
            return;
        }
        primaryStage.setTitle("Snake!");
        // Set the icon of the window.
        Image icon = new Image(getClass().getResource("/images/snake-logo.png").toExternalForm());
        primaryStage.getIcons().add(icon);
        // Create a new Layout
        StackPane layout = new StackPane();
        Scene scene = new Scene(layout, m_Controller.m_Model.getWidth(), m_Controller.m_Model.getHeight());

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
