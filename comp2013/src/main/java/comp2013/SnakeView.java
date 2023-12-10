package comp2013;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.util.Duration;
import java.util.List;

public class SnakeView extends Application implements IView {
    // Store references to the controller
    public SnakeController m_Controller;
    public SnakeMusic m_SnakeMusic;
    private StackPane M_SnakePane;
    public Canvas m_SnakeCanvas;
    public Canvas m_FoodCanvas;
    private Image M_SnakeHeadImg;
    private Image M_SnakeBodyImg;
    private SnakeFood M_SnakeFood;
    private Label M_ScoreLabel, M_CountDownLabel, M_GameOverLabel;
    private Timeline M_Timeline;
    private Button M_RestartButton, M_MenuReturnButton;
    private int M_TimerLength;
    // Used if the restart button has been pressed,
    // so the timeline doesnt call restart over and over.
    private static final int NO_RESTART = -1;

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


     // Refreshes the snake at its new location.
    @Override
    public void refreshSnake() {
        // Get the list of snake body parts
        List<SnakeBody> snakeBody = m_Controller.m_Snake.m_SnakeBody;
        // Tells the loop if it needs to add a new segment or not.
        boolean addSegment = false;
        GraphicsContext gc = m_SnakeCanvas.getGraphicsContext2D();
        // Clear the canvas by filling it with a transparent color
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        // If the food has been eaten, draw a new one to replace it.
        if(M_SnakeFood.eaten()){
            // Re-Roll the fruit.
            M_SnakeFood.newFruit();
            // Draw the new fruit.
            M_SnakeFood.drawFruit(m_FoodCanvas);
            // Update the score
            this.drawScore();
            // Add a new segment
            addSegment = true;

        }
        // Draw the head at its new coordinates and rotation.
        gc.drawImage(M_SnakeHeadImg, snakeBody.getFirst().getX(), snakeBody.getFirst().getY());
        // Draw the rest of the body.
        for (int i = 1; i < m_Controller.m_Model.getLength(); i++) {

            // Draws the body at the new location
            gc.drawImage(M_SnakeBodyImg, snakeBody.get(i).getX(), snakeBody.get(i).getY());
            if(i == m_Controller.m_Model.getLength()-1 && addSegment){
                m_Controller.addSegment(snakeBody.get(i).getX()+1, snakeBody.get(i).getY()+1, true);
                addSegment = false;
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Set title of screen.
        primaryStage.setTitle("Snake!");
        // Set the icon of the window.
        Image icon = SnakeImageUtil.getImage("snakeIcon");
        primaryStage.getIcons().add(icon);

        // Set the event handler for the window-closing event
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();});

        M_SnakePane = new StackPane();

        Scene scene = new Scene(M_SnakePane, m_Controller.m_Model.getWidth(),
                m_Controller.m_Model.getHeight());

        // Load the CSS file
        scene.getStylesheets().add(getClass().getResource
                ("/SnakeStyle.css").toExternalForm());

        // Add an image view to the pane
        ImageView imageView = new ImageView();

        // Set the background of the image.
        this.setBackgroundImage(imageView);
        // Add the background to the pane.
        M_SnakePane.getChildren().add(imageView);

        // Create a canvas that will be used to draw on the snake.
        m_SnakeCanvas = new Canvas(m_Controller.m_Model.getWidth(),
                m_Controller.m_Model.getHeight());
        // Create a seperate canvas for the food.
        m_FoodCanvas = new Canvas(m_Controller.m_Model.getWidth(),
                m_Controller.m_Model.getHeight());

        // Add both of the canvases to the screen
        M_SnakePane.getChildren().add(m_FoodCanvas);
        M_SnakePane.getChildren().add(m_SnakeCanvas);


        M_ScoreLabel = new Label("Score: 0");

        // Apply the CSS style to the Label
        M_ScoreLabel.getStyleClass().add("label-with-padding");

        // Set alignment of the label within the StackPane
        StackPane.setAlignment(M_ScoreLabel, javafx.geometry.Pos.TOP_CENTER);

        // Add the label to the StackPane
        M_SnakePane.getChildren().add(M_ScoreLabel);

        // Build the initial snake.
        this.buildSnake(m_Controller.m_Model.getLength());
        // Create a new food and draw it.
        M_SnakeFood = new SnakeFood();
        M_SnakeFood.drawFruit(m_FoodCanvas);

        M_Timeline = new Timeline(new KeyFrame(Duration.millis((double) 200),
            event -> {
            refreshSnake();
            m_Controller.moveSnake();
        }));

        M_Timeline.setCycleCount(Animation.INDEFINITE);
        M_Timeline.play();

        // Create a new SnakeMusic to be used to play music.
        m_SnakeMusic = new SnakeMusic(SnakeMusic.class.getResource("/sound/frogger.mp3").toString());
        // Play the music
        m_SnakeMusic.playMusic();
        // Sets the music to loop until it is told otherwise.
        m_SnakeMusic.setLooping(true);

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
        this.changeHeadDirection();
        M_SnakeBodyImg = SnakeImageUtil.getImage("snakeBody");

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
                m_Controller.addSegment(canvasCenterHorizontal-horizontalAdd,
                        canvasCenterVertical, false);
                horizontalAdd += 25;
            }
        }
    }
    @Override
    public void setBackgroundImage(ImageView imageView){
        Image background = new Image
                (getClass().getResourceAsStream("/images/UI-Background.png"));

        imageView.setImage(background);
    }

    @Override
    public void drawScore(){
        M_ScoreLabel.setText("Score: " + m_Controller.m_Model.getScore());
    }

    @Override
    public void changeHeadDirection(){
        // Finds out which way the snake is facing and sets the image accordingly.
        switch (this.m_Controller.m_Snake.getDirection()) {
            case SnakeObject.UP: {
                M_SnakeHeadImg = SnakeImageUtil.getImage("snakeHeadUp");
            }
            break;
            case SnakeObject.DOWN: {
                M_SnakeHeadImg = SnakeImageUtil.getImage("snakeHeadDown");
            }
            break;
            case SnakeObject.LEFT: {
                M_SnakeHeadImg = SnakeImageUtil.getImage("snakeHeadLeft");
            }
            break;
            case SnakeObject.RIGHT: {
                M_SnakeHeadImg = SnakeImageUtil.getImage("snakeHeadRight");
            }
            break;
            default:
                break;
        }
    }
    @Override
    public void gameOverScreen(){
        GraphicsContext gc;

        // Stop the timeline so the snake no longer moves.
        M_Timeline.stop();
        // Remove the food from the screen
        gc = m_FoodCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        // Remove the Snake from the screen.
        gc = m_SnakeCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        // Add a new label to show game over.
        M_GameOverLabel = new Label("Game Over!");
        M_GameOverLabel.getStyleClass().add("label-with-padding");
        M_GameOverLabel.getStyleClass().add("game-over-label");
        StackPane.setAlignment(M_GameOverLabel, javafx.geometry.Pos.TOP_CENTER);
        M_SnakePane.getChildren().add(M_GameOverLabel);
        M_GameOverLabel.setTranslateY(175);
        // Create a button that skips the countdown timer and
        // restarts the game.
        M_RestartButton = new Button("Restart");
        M_RestartButton.setOnAction(event -> {
            // Restart the game.
            m_Controller.restartGame();
        });
        // Create a button that returns to the main menu.
        M_MenuReturnButton = new Button("Main Menu");
        M_MenuReturnButton.setOnAction(event -> { return; });
        // Set the location of the buttons.
        StackPane.setAlignment(M_RestartButton, javafx.geometry.Pos.TOP_CENTER);
        StackPane.setAlignment(M_MenuReturnButton, javafx.geometry.Pos.TOP_CENTER);
        // Adding CSS to the buttons.
        M_RestartButton.getStyleClass().add("snake-button");
        M_MenuReturnButton.getStyleClass().add("snake-button");
        // Add new buttons to the screen.
        M_SnakePane.getChildren().addAll(M_RestartButton, M_MenuReturnButton);
        // Set the position of the buttons.
        M_RestartButton.setTranslateY(375);
        M_MenuReturnButton.setTranslateY(425);

        // Change the location of the label, move it to the middle of the screen.
        M_ScoreLabel.setTranslateY(275);
        // Update the text.
        M_ScoreLabel.setText("Final Score: " + m_Controller.m_Model.getScore());
        // Stop the music playing after its last loop.
        m_SnakeMusic.setLooping(false);

        M_CountDownLabel = new Label("Restart in: 5");
        M_CountDownLabel.getStyleClass().add("label-with-padding");
        // Set alignment of the label within the StackPane
        StackPane.setAlignment(M_CountDownLabel, javafx.geometry.Pos.TOP_CENTER);
        M_SnakePane.getChildren().add(M_CountDownLabel);
        M_CountDownLabel.setTranslateY(325);
        // Start the countdown timer.
        this.updateTimer();
    }

    private void updateTimer() {
        // Countdown to restart
        M_TimerLength = 5;
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            Platform.runLater(() -> {
                if(M_TimerLength > 0) {
                    M_TimerLength--;
                    M_CountDownLabel.setText("Restart in: " + M_TimerLength);
                }
                // Check if the countdown has reached zero
                else if (M_TimerLength == 0) {
                    // Stop the timeline before restarting the game
                    m_Controller.restartGame();
                    timeline.stop();
                }
            });
        }));
        timeline.setCycleCount(6);
        timeline.play();
    }

    public void restartGame(){
        // Set the timer to -1 so that the timeline loop doesnt do anything.
        M_TimerLength = -1;
        // Remove the game over labels from the screen.
        M_SnakePane.getChildren().remove(M_CountDownLabel);
        M_SnakePane.getChildren().remove(M_GameOverLabel);
        M_SnakePane.getChildren().remove(M_RestartButton);
        M_SnakePane.getChildren().remove(M_MenuReturnButton);
        // Put the score back to its place.
        M_ScoreLabel.setTranslateY(0);
        // Update its text back to default.
        M_ScoreLabel.setText("Score: " + m_Controller.m_Model.getScore());
        // Build the snake again.
        this.buildSnake(m_Controller.m_Model.getLength());
        // Create a new food and draw it.
        M_SnakeFood = new SnakeFood();
        M_SnakeFood.drawFruit(m_FoodCanvas);
        // Start the timeline again.
        M_Timeline.play();
        // Play the music again from the beginning.
        m_SnakeMusic.playMusic(0);
        // Play the music on a loop.
        m_SnakeMusic.setLooping(true);
    }






}
