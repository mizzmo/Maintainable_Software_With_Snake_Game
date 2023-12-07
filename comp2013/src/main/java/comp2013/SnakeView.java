package comp2013;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.util.List;

public class SnakeView extends Application implements IView {
    // Store references to the controller
    SnakeController m_Controller;

    public Canvas m_SnakeCanvas;

    private Image M_SnakeHeadImg;

    private Image M_SnakeBodyImg;

    private static SnakeView m_Instance;
    public SnakeView() {
        // Constructor gets the instance of controller.
        m_Controller = SnakeController.getInstance();
        // Set the controllers view to be this.
        m_Controller.setView(this);
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
    public void refreshSnake() {
        // Get the list of snake body parts
        List<SnakeBody> snakeBody = m_Controller.m_Snake.m_SnakeBody;
        GraphicsContext gc = m_SnakeCanvas.getGraphicsContext2D();

        // Clear the canvas by filling it with a transparent color
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        // Draw the head at its new coordinates and rotation.
        gc.drawImage(M_SnakeHeadImg, snakeBody.getFirst().getX(), snakeBody.getFirst().getY());
        // Draw the rest of the body.
        for(int i = 1; i < m_Controller.m_Model.getLength(); i++){
          // Draws the body at the new location
            gc.drawImage(M_SnakeBodyImg, snakeBody.get(i).getX(), snakeBody.get(i).getY());
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Set title of screen.
        primaryStage.setTitle("Snake!");
        // Set the icon of the window.
        Image icon = new Image(getClass().getResource("/images/snake-logo.png").toExternalForm());
        primaryStage.getIcons().add(icon);

        // Set the event handler for the window-closing event
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();});

        StackPane snakePane = new StackPane();
        Scene scene = new Scene(snakePane, m_Controller.m_Model.getWidth(), m_Controller.m_Model.getHeight());
        // Add an image view to the pane
        ImageView imageView = new ImageView();
        // Set the background of the image.
        this.setBackgroundImage(imageView);
        // Add the background to the pane.
        snakePane.getChildren().add(imageView);

        // Create a canvas that will be used to draw on the snake.
        m_SnakeCanvas = new Canvas(m_Controller.m_Model.getWidth(), m_Controller.m_Model.getHeight());
        snakePane.getChildren().add(m_SnakeCanvas);

        // Build the initial snake.
        this.buildSnake(m_Controller.m_Model.getLength());

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(30), event -> refreshSnake()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        scene.setOnKeyPressed(event -> m_Controller.handleKeyPress(event.getCode()));

        // Set the scene and show the page.
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @Override
    public void buildSnake(int length){
        // Get the center of the screen.
        int canvasCenterHorizontal = m_Controller.m_Model.getWidth() / 2;
        int canvasCenterVertical = m_Controller.m_Model.getHeight() / 2;
        // Stores reference to currently used snake part.
        List<SnakeBody> snakeBody;
        // Stores reference to currently used snake head.
        SnakeBody snakeHead;
        // Coordinate value to add onto the coordinates
        int horizontalAdd = 25;
        int verticalAdd = 25;
        // Get the image of the snake head.
        M_SnakeHeadImg = new Image(getClass().getResourceAsStream("/images/snake-head-right.png"));
        M_SnakeBodyImg = new Image(getClass().getResourceAsStream("/images/snake-body.png"));

        // Just build the head.
        GraphicsContext gc = m_SnakeCanvas.getGraphicsContext2D();
        gc.drawImage(M_SnakeHeadImg, canvasCenterHorizontal, canvasCenterVertical);

        snakeBody = m_Controller.m_Snake.m_SnakeBody;
        // Get the head of the snake.
        snakeHead = snakeBody.getFirst();
        //Set the cooridnates of the head.
        snakeHead.setX(canvasCenterHorizontal);
        snakeHead.setY(canvasCenterVertical);


        // If the snake has body parts draw them.
        // This means you can start the game with body parts.
        if(length > 0)
        {

            for(int i = 1; i < length; i++)
            {
                // Draws the body and takes away the last horizontal coordinate + a constant, creates a line of circles.
                gc.drawImage(M_SnakeBodyImg, canvasCenterHorizontal-horizontalAdd, canvasCenterVertical);
                // Update and add a new segment of the snake.
                m_Controller.addSegment(canvasCenterHorizontal-horizontalAdd, canvasCenterVertical, false);
                horizontalAdd += 25;
            }
        }
    }
    @Override
    public void setBackgroundImage(ImageView imageView){
        Image background = new Image(getClass().getResourceAsStream("/images/UI-Background.png"));
        imageView.setImage(background);
    }

    @Override
    public void changeHeadDirection(){
        // Finds out which way the snake is facing and sets the image accordingly.
        switch (m_Controller.m_Snake.getDirection()) {
            case 0: {
                M_SnakeHeadImg = new Image(getClass().getResourceAsStream("/images/snake-head-up.png"));
            }
            case 1: {
                M_SnakeHeadImg = new Image(getClass().getResourceAsStream("/images/snake-head-down.png"));
            }
            case 2: {
                M_SnakeHeadImg = new Image(getClass().getResourceAsStream("/images/snake-head-left.png"));
            }
            case 3: {
                M_SnakeHeadImg = new Image(getClass().getResourceAsStream("/images/snake-head-right.png"));
            }
        }
    }



}
