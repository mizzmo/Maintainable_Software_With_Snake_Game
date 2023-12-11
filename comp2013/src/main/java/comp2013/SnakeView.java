package comp2013;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.util.Duration;
import java.util.List;

public class SnakeView extends Application implements IView {
    // Store references to the controller
    public SnakeController m_Controller;
    private Stage M_PrimaryStage;
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
    private double M_MusicVolume = 0.2;

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
        this.M_PrimaryStage = primaryStage;
        // Set title of screen.
        M_PrimaryStage.setTitle("Snake!");
        // Set the icon of the window.
        Image icon = SnakeImageUtil.getImage("snakeIcon");
        M_PrimaryStage.getIcons().add(icon);

        // Set the event handler for the window-closing event
        M_PrimaryStage.setOnCloseRequest(event -> {
            Platform.exit();});

        this.setMenuScene();

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
    public void setBackgroundImage(ImageView imageView, String imageID){
        Image background = SnakeImageUtil.getImage(imageID);
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
        // Set what happens when button is clicked.
        M_RestartButton.setOnAction(event -> {
            // Restart the game.
            m_Controller.restartGame();
        });
        // Create a button that returns to the main menu.
        M_MenuReturnButton = new Button("Main Menu");
        // Set what happens when button is clicked.
        M_MenuReturnButton.setOnAction(event -> {
            // Stop the timeline
            M_Timeline.stop();
            // Go to the menu
            this.setMenuScene();
        });
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
    @Override
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
        // Play the music on a loop.
        m_SnakeMusic.setLooping(true);
        // Start the timeline again.
        M_Timeline.play();
    }

    private void setMenuScene(){
        // Stops music if there is any, and plays a new one.
        if(m_SnakeMusic != null) {
            System.out.printf("Here\n");
            m_SnakeMusic.stopMusic();
        }

        m_SnakeMusic = new SnakeMusic(SnakeMusicUtil.getMedia("retroFunk"));
        m_SnakeMusic.playMusic();

        // Set the volume
        m_SnakeMusic.setVolume(this.M_MusicVolume);
        // Set the music to loop
        m_SnakeMusic.setLooping(true);

        // Initialise the menu scene and stack pane.
        StackPane menuPane = new StackPane();
        Scene menuScene = new Scene(menuPane, m_Controller.m_Model.getWidth(),
                m_Controller.m_Model.getHeight());
        // Add the CSS to the scene.
        menuScene.getStylesheets().add(getClass().getResource
                ("/SnakeStyle.css").toExternalForm());

        // Add an image view to the pane
        ImageView imageView = new ImageView();
        // Set the background of the image.
        this.setBackgroundImage(imageView, "jungle-background");
        // Add the background to the pane.
        menuPane.getChildren().add(imageView);
        // Create a transparent VBox that goes over the top of the jungle
        // image so that it isnt so glaring.
        VBox darkBox = new VBox();
        // Set the box background to be transparent black.
        darkBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6);");
        menuPane.getChildren().add(darkBox);
        // New label for the title screen
        Label titleLabel = new Label("Snake!");
        titleLabel.getStyleClass().add("label-with-padding");
        titleLabel.getStyleClass().add("snake-title-label");
        // Set the position of the label
        StackPane.setAlignment(titleLabel, javafx.geometry.Pos.TOP_CENTER);
        menuPane.getChildren().add(titleLabel);
        titleLabel.setTranslateY(100);

        // Button to start the game.
        Button startButton = new Button("Start Game");
        startButton.setOnAction(event -> {
            // Set the new scene
            this.setGameScene();
            // Reset the game to default.
            m_Controller.restartGame();
        });
        // Add styling and set location
        startButton.getStyleClass().add("snake-button");
        startButton.getStyleClass().add("menu-button-size");
        StackPane.setAlignment(startButton, javafx.geometry.Pos.TOP_CENTER);
        // Add to pane
        menuPane.getChildren().add(startButton);
        startButton.setTranslateY(225);

        // Create a settings button to access the games settings.
        Button settingsButton = new Button("Settings");
        settingsButton.setOnAction(event -> {
            this.setSettingsScene();
        });
        // Add styling and set location
        settingsButton.getStyleClass().add("snake-button");
        settingsButton.getStyleClass().add("menu-button-size");
        StackPane.setAlignment(settingsButton, javafx.geometry.Pos.TOP_CENTER);
        // Add to pane
        menuPane.getChildren().add(settingsButton);
        settingsButton.setTranslateY(325);

        // Create a exit button that exits the game.
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> {
            Platform.exit();
        });
        // Add styling and set location
        exitButton.getStyleClass().add("snake-button");
        exitButton.getStyleClass().add("menu-button-size");
        StackPane.setAlignment(exitButton, javafx.geometry.Pos.TOP_CENTER);
        // Add to pane
        menuPane.getChildren().add(exitButton);
        exitButton.setTranslateY(425);

        // Set the scene and show the page.
        M_PrimaryStage.setScene(menuScene);
        M_PrimaryStage.show();
    }
    private void setGameScene(){
        // Stop any music that is already playing.
        if(m_SnakeMusic != null) {
            m_SnakeMusic.stopMusic();
        }

        M_SnakePane = new StackPane();

        Scene m_SnakeScene = new Scene(M_SnakePane, m_Controller.m_Model.getWidth(),
                m_Controller.m_Model.getHeight());

        // Load the CSS file
        m_SnakeScene.getStylesheets().add(getClass().getResource
                ("/SnakeStyle.css").toExternalForm());

        // Add an image view to the pane
        ImageView imageView = new ImageView();

        // Set the background of the image.
        this.setBackgroundImage(imageView, "cloud-background");
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
        m_SnakeMusic = new SnakeMusic(SnakeMusicUtil.getMedia("frogger"));
        // Play the music
        m_SnakeMusic.playMusic();
        // Set the volume
        m_SnakeMusic.setVolume(this.M_MusicVolume);
        // Sets the music to loop until it is told otherwise.
        m_SnakeMusic.setLooping(true);

        m_SnakeScene.setOnKeyPressed(event -> m_Controller.handleKeyPress(event.getCode()));

        // Set the scene and show the page.
        M_PrimaryStage.setScene(m_SnakeScene);
        M_PrimaryStage.show();
    }

    private void setSettingsScene(){
        // Initialise the menu scene and stack pane.
        StackPane settingsPane = new StackPane();
        Scene settingsScene = new Scene(settingsPane, m_Controller.m_Model.getWidth(),
                m_Controller.m_Model.getHeight());
        // Add the CSS to the scene.
        settingsScene.getStylesheets().add(getClass().getResource
                ("/SnakeStyle.css").toExternalForm());

        // Add an image view to the pane
        ImageView imageView = new ImageView();
        // Set the background of the image.
        this.setBackgroundImage(imageView, "jungle-background");
        // Add the background to the pane.
        settingsPane.getChildren().add(imageView);
        // Create a transparent VBox that goes over the top of the jungle
        // image so that it isnt so glaring.
        VBox darkBox = new VBox(10);
        // Set the box background to be transparent black.
        darkBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6);");
        settingsPane.getChildren().add(darkBox);
        // New label for the settings screen
        Label settingsLabel = new Label("Settings");
        settingsLabel.getStyleClass().add("label-with-padding");
        settingsLabel.setStyle("-fx-text-fill: white;"); // Set the text to be white.
        // Set the position of the label
        StackPane.setAlignment(settingsLabel, javafx.geometry.Pos.TOP_CENTER);
        settingsPane.getChildren().add(settingsLabel);
        settingsLabel.setTranslateY(50);

        // Create a label to display the slider value
        Label volumeLevelLabel = new Label("Volume: " + (int)(this.M_MusicVolume * 100) + "%");

        // Create a horizontal slider
        Slider volumeSlider = new Slider(0, 100, this.M_MusicVolume * 100); // min, max, initial value
        volumeSlider.setShowTickMarks(true);

        // Add a listener to respond to changes in the slider value and update the volume.
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                volumeLevelLabel.setText(String.format("Volume: %.0f%%", newValue));
                // Update the local variable so that all music is synced
                this.M_MusicVolume = (double) newValue / 100;
                // Update the music object itself
                m_SnakeMusic.setVolume(((double)newValue / 100));

        });

        // Set the size of the slider.
        volumeSlider.setMinHeight((int)(m_Controller.m_Model.getHeight() / 20));
        volumeSlider.setMinWidth((int)(m_Controller.m_Model.getWidth() / 2));

        volumeSlider.setMaxHeight((int)(m_Controller.m_Model.getHeight() / 20));
        volumeSlider.setMaxWidth((int)(m_Controller.m_Model.getWidth() / 2));
        // Add styling
        volumeLevelLabel.getStyleClass().add("label-with-padding");
        volumeLevelLabel.setStyle("-fx-text-fill: white;"); // Set the text to be white.

        // Create a button that returns to the main menu.
        Button menuButton = new Button("Main Menu");
        // Set what happens when button is clicked.
        menuButton.setOnAction(event -> {
            // Go to the menu
            this.setMenuScene();
        });
        // Add styling and set location
        menuButton.getStyleClass().add("snake-button");
        // Set the size of the
        menuButton.setMinHeight((int)(m_Controller.m_Model.getHeight() / 9));
        menuButton.setMinWidth((int)(m_Controller.m_Model.getWidth() / 7));

        menuButton.setMaxHeight((int)(m_Controller.m_Model.getHeight() / 9));
        menuButton.setMaxWidth((int)(m_Controller.m_Model.getWidth() / 7));
        // Add to the pane.
        settingsPane.getChildren().addAll(volumeLevelLabel, volumeSlider, menuButton);

        volumeLevelLabel.setTranslateY(-110);
        volumeSlider.setTranslateY(-75);



        // Set the scene and show the page.
        M_PrimaryStage.setScene(settingsScene);
        M_PrimaryStage.show();
    }






}
