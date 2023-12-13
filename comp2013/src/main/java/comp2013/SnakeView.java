package comp2013;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.List;

public class SnakeView extends Application implements IView {
    // Store references to the controller
    public SnakeController m_Controller;
    private Stage M_PrimaryStage;
    public SnakeMusic m_SnakeMusic;
    private StackPane M_SnakePane;
    public Canvas m_SnakeCanvas, m_FoodCanvas;
    private Image M_SnakeHeadImg, M_SnakeBodyImg, M_BackgroundImage;
    private SnakeFood M_SnakeFood;
    private Label M_ScoreLabel, M_CountDownLabel,
            M_GameOverLabel, M_DefaultLabel;
    private Timeline M_Timeline;
    private Button M_RestartButton, M_MenuReturnButton,
            M_EnterNameButton;
    private int M_TimerLength;
    private static SnakeView m_Instance;
    private double M_MusicVolume = 0.2;
    private StackPane M_DefaultPane;
    private TextField M_EnterNameField;


    public SnakeView() {
        // Constructor gets the instance of controller.
        m_Controller = SnakeController.getInstance();
        // Set the controllers view to be this.
        m_Controller.setView(this);
        M_BackgroundImage = SnakeImageUtil.getImage
                ("grass-background");

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
        // Sets the time interval to 200 miliseconds;
        double timeRate = 1;
        // Time interval decreases as the user gets a higher score.
        if(m_Controller.m_Model.getScore() > 0){
            timeRate *= 1 + (double) m_Controller.m_Model.getScore() / 1000;
            // Update the speed of the Timeline
            M_Timeline.setRate(timeRate);
        }

        // Get the list of snake body parts
        List<SnakeBody> snakeBody = m_Controller.m_Snake.m_SnakeBody;
        // Tells the loop if it needs to add a new segment or not.
        boolean addSegment = false;
        GraphicsContext gc = m_SnakeCanvas.getGraphicsContext2D();
        // Clear the canvas by filling it with a transparent color
        gc.clearRect(0, 0, gc.getCanvas().getWidth(),
                gc.getCanvas().getHeight());
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
        gc.drawImage(M_SnakeHeadImg, snakeBody.getFirst().getX(),
                snakeBody.getFirst().getY(), 32, 32);
        // Draw the rest of the body.
        for (int i = 1; i < m_Controller.m_Model.getLength(); i++) {
            // Draws the body at the new location
            gc.drawImage(M_SnakeBodyImg, snakeBody.get(i).getX(),
                    snakeBody.get(i).getY(), 32, 32);
            if(i == m_Controller.m_Model.getLength()-1 && addSegment){
                m_Controller.addSegment(snakeBody.get(i).getX()+1,
                        snakeBody.get(i).getY()+1, true);
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
    public void buildSnake(){
        // Get the center of the screen.
        int canvasCenterHorizontal = m_Controller.m_Model.getWidth() / 2;
        int canvasCenterVertical = m_Controller.m_Model.getHeight() / 2;
        // Stores reference to currently used snake part.
        List<SnakeBody> snakeBody;
        // Stores reference to currently used snake head.
        SnakeBody snakeHead;
        // Coordinate value to add onto the coordinates
        int horizontalAdd = 25;
        int length = m_Controller.m_Model.getLength();

        // Get the image of the snake head.
        this.changeHeadDirection();
        M_SnakeBodyImg = SnakeImageUtil.getImage("snakeBody");

        // Just build the head.
        GraphicsContext gc = m_SnakeCanvas.getGraphicsContext2D();
        gc.drawImage(M_SnakeHeadImg, canvasCenterHorizontal,
                canvasCenterVertical, 32,32);

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
                // Draws the body and takes away the last horizontal
                // coordinate + a constant, creates a line of circles.
                gc.drawImage(M_SnakeBodyImg,
                        canvasCenterHorizontal-horizontalAdd,
                        canvasCenterVertical, 32, 32);
                // Update and add a new segment of the snake.
                m_Controller.addSegment
                        (canvasCenterHorizontal-horizontalAdd,
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
        M_ScoreLabel.setText
                ("Score: " + m_Controller.m_Model.getScore());
    }

    @Override
    public void changeHeadDirection(){
        // Finds out which way the snake is facing and sets the
        // image accordingly.
        switch (this.m_Controller.m_Snake.getDirection()) {
            case SnakeObject.UP: {
                M_SnakeHeadImg = SnakeImageUtil.getImage
                        ("snakeHeadUp");
            }
            break;
            case SnakeObject.DOWN: {
                M_SnakeHeadImg = SnakeImageUtil.getImage
                        ("snakeHeadDown");
            }
            break;
            case SnakeObject.LEFT: {
                M_SnakeHeadImg = SnakeImageUtil.getImage
                        ("snakeHeadLeft");
            }
            break;
            case SnakeObject.RIGHT: {
                M_SnakeHeadImg = SnakeImageUtil.getImage
                        ("snakeHeadRight");
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
        gc.clearRect(0, 0, gc.getCanvas().getWidth(),
                gc.getCanvas().getHeight());
        // Remove the Snake from the screen.
        gc = m_SnakeCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, gc.getCanvas().getWidth(),
                gc.getCanvas().getHeight());

        // Add a new label to show game over.
        M_GameOverLabel = new Label("Game Over!");
        M_GameOverLabel.getStyleClass().add("label-with-padding");
        M_GameOverLabel.getStyleClass().add("game-over-label");
        StackPane.setAlignment(M_GameOverLabel,
                javafx.geometry.Pos.TOP_CENTER);

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
        // Create a new Text Field that the user can type in
        M_EnterNameField = new TextField();
        // Set the initial text in the box.
        M_EnterNameField.setPromptText("Enter Username: ");

        // Set the size of the text bar
        M_EnterNameField.setMinSize((int)
                        (m_Controller.m_Model.getWidth() / 2.8),
                (int)(m_Controller.m_Model.getHeight() / 15));
        M_EnterNameField.setMaxSize((int)
                        (m_Controller.m_Model.getWidth() / 2.8),
                (int)(m_Controller.m_Model.getHeight() / 15));

        // Add a button to save the entry to the text file.
        M_EnterNameButton = new Button("Confirm");
        M_EnterNameButton.getStyleClass().add("snake-button");
        M_EnterNameButton.setOnAction(event -> {
            // Gets the user entry.
            String userInput = M_EnterNameField.getText();
            // If the user input has been used, add it.
            if(!userInput.isEmpty()) {
                // Saves the username and score to the text file.
                saveToTextFile(userInput + ","
                        + m_Controller.m_Model.getScore());
                // Make it so the text field can no longer be edited
                M_EnterNameField.setEditable(false);
                // Clear the field
                M_EnterNameField.clear();
                // Set a new prompt
                M_EnterNameField.setPromptText
                        ("                          Score Recorded");
            }
        });

        // Set the location of the buttons.
        StackPane.setAlignment(M_RestartButton, javafx.geometry.Pos.TOP_CENTER);
        StackPane.setAlignment(M_MenuReturnButton, javafx.geometry.Pos.TOP_CENTER);
        // Adding CSS to the buttons.
        M_RestartButton.getStyleClass().add("snake-button");
        M_MenuReturnButton.getStyleClass().add("snake-button");
        // Add new buttons to the screen.
        M_SnakePane.getChildren().addAll(M_RestartButton, M_MenuReturnButton,
                M_EnterNameField, M_EnterNameButton);
        // Set the position of the buttons.
        M_RestartButton.setTranslateY(425);
        M_RestartButton.setMinSize((double)m_Controller.m_Model.getWidth()/6,
                (double)m_Controller.m_Model.getHeight()/10);
        M_MenuReturnButton.setMaxSize((double)m_Controller.m_Model.getWidth()/6,
                (double) m_Controller.m_Model.getHeight()/10);
        M_MenuReturnButton.setTranslateY(500);
        M_MenuReturnButton.setMinSize((double)m_Controller.m_Model.getWidth()/6,
                (double) m_Controller.m_Model.getHeight()/10);
        M_MenuReturnButton.setMaxSize((double)m_Controller.m_Model.getWidth()/6,
                (double) m_Controller.m_Model.getHeight()/10);

        M_EnterNameButton.setTranslateX(125);
        M_EnterNameField.setTranslateX(0);

        // Change the location of the label, move it to the middle of the screen.
        M_ScoreLabel.setTranslateY(325);
        // Update the text.
        M_ScoreLabel.setText("Final Score: " + m_Controller.m_Model.getScore());

        M_CountDownLabel = new Label("Restart in: 10");
        M_CountDownLabel.getStyleClass().add("label-with-padding");
        // Set alignment of the label within the StackPane
        StackPane.setAlignment(M_CountDownLabel, javafx.geometry.Pos.TOP_CENTER);
        M_SnakePane.getChildren().add(M_CountDownLabel);
        M_CountDownLabel.setTranslateY(370);
        // Start the countdown timer.
        this.updateTimer();
    }
    // Write the score to a text file.
    private void saveToTextFile(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter
                ("comp2013/output/highScores.txt", true))) {
            writer.write(content + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateTimer() {
        // Countdown to restart
        M_TimerLength = 10;
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
        timeline.setCycleCount(11);
        timeline.play();
    }
    @Override
    public void restartGame(){
        M_Timeline.stop();
        // Set the timer to -1 so that the timeline loop doesnt do anything.
        M_TimerLength = -1;
        // Remove the game over labels from the screen.
        M_SnakePane.getChildren().removeAll(M_CountDownLabel,M_GameOverLabel,
                M_RestartButton, M_MenuReturnButton, M_EnterNameField,
                M_EnterNameButton);
        // Put the score back to its place.
        M_ScoreLabel.setTranslateY(0);
        // Update its text back to default.
        M_ScoreLabel.setText("Score: " + m_Controller.m_Model.getScore());
        // Build the snake again.
        this.buildSnake();
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
            m_SnakeMusic.stopMusic();
        }

        m_SnakeMusic = new SnakeMusic(SnakeMusicUtil.getMedia
                ("retroFunk"));
        m_SnakeMusic.playMusic();

        // Set the volume
        m_SnakeMusic.setVolume(this.M_MusicVolume);
        // Set the music to loop
        m_SnakeMusic.setLooping(true);

        // Initialise the menu scene and stack pane.
        Scene menuScene = this.initialiseMenuScreen("Snake!");
        StackPane menuPane = this.M_DefaultPane;

        Label titleLabel = this.M_DefaultLabel;
        titleLabel.getStyleClass().add("snake-title-label");
        titleLabel.setTranslateY(100);

        // Button to start the game.
        Button startButton = new Button("Start Game");
        startButton.setOnAction(event -> {
            this.setSelectScene();
        });
        // Add styling and set location
        startButton.getStyleClass().add("snake-button");
        startButton.getStyleClass().add("menu-button-size");
        StackPane.setAlignment(startButton, Pos.TOP_CENTER);
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
        StackPane.setAlignment(settingsButton, Pos.TOP_CENTER);
        // Add to pane
        menuPane.getChildren().add(settingsButton);
        settingsButton.setTranslateY(375);

        // Create a leaderboard button that takes you to
        // the high score page.
        Button leaderboardButton = new Button("Leaderboard");
        leaderboardButton.setOnAction(event -> {
            this.setLeaderboardScene();
        });
        // Add styling and set location
        leaderboardButton.getStyleClass().add("snake-button");
        leaderboardButton.getStyleClass().add("menu-button-size");
        StackPane.setAlignment(leaderboardButton, Pos.TOP_CENTER);
        // Add to pane
        menuPane.getChildren().add(leaderboardButton);
        leaderboardButton.setTranslateY(300);

        // Create a exit button that exits the game.
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> {
            Platform.exit();
        });
        // Add styling and set location
        exitButton.getStyleClass().add("snake-button");
        exitButton.getStyleClass().add("menu-button-size");
        StackPane.setAlignment(exitButton, Pos.TOP_CENTER);
        // Add to pane
        menuPane.getChildren().add(exitButton);
        exitButton.setTranslateY(450);

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

        Scene m_SnakeScene = new Scene(M_SnakePane,
                m_Controller.m_Model.getWidth(),
                m_Controller.m_Model.getHeight());

        // Load the CSS file
        m_SnakeScene.getStylesheets().add(getClass().getResource
                ("/SnakeStyle.css").toExternalForm());

        // Add an image view to the pane
        ImageView imageView = new ImageView();

        // Set the background of the image.
        imageView.setImage(M_BackgroundImage);
        // Set the size of the image view.
        imageView.setFitHeight((int)(m_Controller.m_Model.getHeight()));
        imageView.setFitWidth((int)(m_Controller.m_Model.getWidth()));
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
        this.buildSnake();
        // Create a new food and draw it.
        M_SnakeFood = new SnakeFood();
        M_SnakeFood.drawFruit(m_FoodCanvas);

        // Define the timeline that controlls how the snake moves.
        M_Timeline = new Timeline(new KeyFrame(Duration.millis(200),
                event -> {
                    refreshSnake();
                    m_Controller.moveSnake();
                }));

        M_Timeline.setCycleCount(Animation.INDEFINITE);
        M_Timeline.play();

        // Create a new SnakeMusic to be used to play music.
        m_SnakeMusic = new SnakeMusic(SnakeMusicUtil.getMedia
                ("frogger"));
        // Play the music
        m_SnakeMusic.playMusic();
        // Set the volume
        m_SnakeMusic.setVolume(this.M_MusicVolume);
        // Sets the music to loop until it is told otherwise.
        m_SnakeMusic.setLooping(true);

        m_SnakeScene.setOnKeyPressed(event ->
                m_Controller.handleKeyPress(event.getCode()));

        // Set the scene and show the page.
        M_PrimaryStage.setScene(m_SnakeScene);
        M_PrimaryStage.show();
    }

    private void setSettingsScene(){
        // Initialise the menu scene and stack pane.
        Scene settingsScene = this.initialiseMenuScreen("Settings");
        StackPane settingsPane = this.M_DefaultPane;

        // Create a label to display the slider value
        Label volumeLevelLabel = new Label("Volume: "
                + (int)(this.M_MusicVolume * 100) + "%");

        // Create a horizontal slider
        Slider volumeSlider = new Slider(0, 100,
                this.M_MusicVolume * 100); // min, max, initial value
        volumeSlider.setShowTickMarks(true);

        // Add a listener to respond to changes in the slider value and
        // update the volume.
        volumeSlider.valueProperty().addListener
                ((observable, oldValue, newValue) -> {
                volumeLevelLabel.setText(String.format
                        ("Volume: %.0f%%", newValue));
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
        // Set the text to be white.
        volumeLevelLabel.setStyle("-fx-text-fill: white;");

        // Create a label to display the slider value
        Label snakeLengthLabel = new Label("Snake Size: "
                + m_Controller.m_Model.getLength());

        // Create a horizontal slider
        Slider snakeLengthSlider = new Slider(2, 10,
                m_Controller.m_Model.getLength()); // min, max, initial value

        snakeLengthSlider.setShowTickMarks(true);

        // Add a listener to respond to changes in the slider value and
        // update the volume.
        snakeLengthSlider.valueProperty().addListener
                ((observable, oldValue, newValue) -> {

            snakeLengthLabel.setText(String.format
                    ("Snake Size: %d", newValue.intValue()));

            m_Controller.m_Model.setInitialLength
                    (newValue.intValue());
        });

        // Set the size of the slider.
        snakeLengthSlider.setMinHeight((int)
                (m_Controller.m_Model.getHeight() / 20));
        snakeLengthSlider.setMinWidth((int)
                (m_Controller.m_Model.getWidth() / 2));

        snakeLengthSlider.setMaxHeight((int)
                (m_Controller.m_Model.getHeight() / 20));
        snakeLengthSlider.setMaxWidth((int)
                (m_Controller.m_Model.getWidth() / 2));
        // Add styling
        snakeLengthLabel.getStyleClass().add("label-with-padding");
        // Set the text to be white.
        snakeLengthLabel.setStyle("-fx-text-fill: white;");



        // Create a button that returns to the main menu.
        Button menuButton = new Button("Back");
        // Set what happens when button is clicked.
        menuButton.setOnAction(event -> {
            // Go to the menu
            this.setMenuScene();
        });
        // Add styling and set location
        menuButton.getStyleClass().add("snake-button");
        // Set the size of the
        menuButton.setMinHeight((int)
                (m_Controller.m_Model.getHeight() / 9));
        menuButton.setMinWidth((int)
                (m_Controller.m_Model.getWidth() / 7));

        menuButton.setMaxHeight((int)
                (m_Controller.m_Model.getHeight() / 9));
        menuButton.setMaxWidth((int)
                (m_Controller.m_Model.getWidth() / 7));
        // Add to the pane.
        settingsPane.getChildren().addAll(volumeLevelLabel, volumeSlider,
                snakeLengthLabel, snakeLengthSlider, menuButton);

        volumeLevelLabel.setTranslateY(-110);
        volumeSlider.setTranslateY(-75);

        snakeLengthLabel.setTranslateY(0);
        snakeLengthSlider.setTranslateY(50);

        menuButton.setTranslateY(100);

        // Set the scene and show the page.
        M_PrimaryStage.setScene(settingsScene);
        M_PrimaryStage.show();
    }
    private void setSelectScene(){
        // Initialise the menu scene and stack pane.
        Scene mapSelectScene = this.initialiseMenuScreen("Map Select");
        StackPane mapSelectPane = this.M_DefaultPane;

        // Create a button that returns to the main menu.
        Button menuButton = new Button("Back");
        // Set what happens when button is clicked.
        menuButton.setOnAction(event -> {
            // Go to the menu
            this.setMenuScene();
        });
        // Add styling and set location
        menuButton.getStyleClass().add("snake-button");
        // Set the size of the
        menuButton.setMinHeight((int)
                (m_Controller.m_Model.getHeight() / 9));
        menuButton.setMinWidth((int)
                (m_Controller.m_Model.getWidth() / 7));

        menuButton.setMaxHeight((int)(m_Controller.m_Model.getHeight() / 9));
        menuButton.setMaxWidth((int)(m_Controller.m_Model.getWidth() / 7));

        // Create a button that returns to the main menu.
        Button startButton = new Button("Start Game!");
        // Set what happens when button is clicked.
        startButton.setOnAction(event -> {
            // Set the new scene
            this.setGameScene();
            // Reset the game to default.
            m_Controller.restartGame();
        });
        // Add styling and set location
        startButton.getStyleClass().add("snake-button");
        // Set the size of the
        startButton.setMinHeight((int)
                (m_Controller.m_Model.getHeight() / 9));
        startButton.setMinWidth((int)
                (m_Controller.m_Model.getWidth() / 7));

        startButton.setMaxHeight((int)
                (m_Controller.m_Model.getHeight() / 9));
        startButton.setMaxWidth((int)
                (m_Controller.m_Model.getWidth() / 7));

        Label selectLabel = new Label("Selected: Grassy Plains");
        selectLabel.getStyleClass().add("label-with-padding");
        selectLabel.setStyle("-fx-text-fill: WHITE; -fx-font-size: 20;");
        // Create a Rectangle for an outline that is slightly
        // bigger than the image view.
        Rectangle selectOutline = new Rectangle(
                (int)(m_Controller.m_Model.getWidth() / 4) + 20,
                (int)(m_Controller.m_Model.getHeight() / 4) + 20
        );
        selectOutline.setFill(null); // No fill
        selectOutline.setStroke(Color.WHITE); // Set the color of the outline
        selectOutline.setStrokeWidth(5);


        ImageView mapSelectCloud = new ImageView();
        this.setBackgroundImage(mapSelectCloud, "cloud-background");
        // Set the size of the image view.
        mapSelectCloud.setFitHeight((int)
                (m_Controller.m_Model.getHeight() / 4));
        mapSelectCloud.setFitWidth((int)
                (m_Controller.m_Model.getWidth() / 4));
        // Set an event handler for the click event
        mapSelectCloud.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                M_BackgroundImage = SnakeImageUtil.getImage
                        ("cloud-background");
                selectOutline.setTranslateX(-275);
                selectLabel.setTranslateX(-275);
                selectLabel.setText("Selected: Sky High");
            }
        });

        ImageView mapSelectGrass = new ImageView();
        this.setBackgroundImage(mapSelectGrass, "grass-background");
        // Set the size of the image view.
        mapSelectGrass.setFitHeight((int)
                (m_Controller.m_Model.getHeight() / 4));
        mapSelectGrass.setFitWidth((int)
                (m_Controller.m_Model.getWidth() / 4));

        mapSelectGrass.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                M_BackgroundImage = SnakeImageUtil.getImage
                        ("grass-background");
                selectOutline.setTranslateX(0);
                selectLabel.setTranslateX(0);
                selectLabel.setText("Selected: Grassy Plains");
            }
        });

        ImageView mapSelectOcean = new ImageView();
        this.setBackgroundImage(mapSelectOcean, "ocean-background");
        // Set the size of the image view.
        mapSelectOcean.setFitHeight((int)
                (m_Controller.m_Model.getHeight() / 4));
        mapSelectOcean.setFitWidth((int)
                (m_Controller.m_Model.getWidth() / 4));

        mapSelectOcean.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                M_BackgroundImage = SnakeImageUtil.getImage
                        ("ocean-background");
                selectOutline.setTranslateX(275);
                selectLabel.setTranslateX(275);
                selectLabel.setText("Selected: Ocean Dive");
            }
        });

        // Add to the pane.
        mapSelectPane.getChildren().addAll(
                menuButton, mapSelectCloud, mapSelectGrass,
                mapSelectOcean, selectOutline, selectLabel,
                startButton
        );
        // Set the locations of the elements.
        menuButton.setTranslateY(225);
        menuButton.setTranslateX(-100);
        startButton.setTranslateY(225);
        startButton.setTranslateX(100);
        mapSelectCloud.setTranslateX(-275);
        mapSelectGrass.setTranslateX(0);
        mapSelectOcean.setTranslateX(275);
        selectLabel.setTranslateY(-100);


        // Set the scene and show the page.
        M_PrimaryStage.setScene(mapSelectScene);
        M_PrimaryStage.show();
    }

    private void setLeaderboardScene(){
        // Initialise the leaderboard scene and stack pane.
        Scene leaderboardScene = this.initialiseMenuScreen
                ("Leaderboard!");
        StackPane leaderboardPane = this.M_DefaultPane;
        Label leaderboardLabel = M_DefaultLabel;
        // Create a button that returns to the main menu.
        Button menuButton = new Button("Back");
        // Set what happens when button is clicked.
        menuButton.setOnAction(event -> {
            // Go to the menu
            this.setMenuScene();
        });
        // Add styling and set location
        menuButton.getStyleClass().add("snake-button");
        // Set the size of the
        menuButton.setMinHeight((int)
                (m_Controller.m_Model.getHeight() / 9));
        menuButton.setMinWidth((int)
                (m_Controller.m_Model.getWidth() / 7));

        menuButton.setMaxHeight((int)
                (m_Controller.m_Model.getHeight() / 9));
        menuButton.setMaxWidth((int)
                (m_Controller.m_Model.getWidth() / 7));
        // Create a header label
        Label boardHeader = new Label
                ("Name          Score");
        // Add some styling
        boardHeader.getStyleClass().add("label-with-padding");
        boardHeader.setStyle("-fx-text-fill: BLACK");
        // Create a VBox to add leaderboard elements to
        VBox leaderboard = new VBox();
        // Set the size of the Vbox
        leaderboard.setMinSize
                ((double) m_Controller.m_Model.getWidth() / 2,
                        (double) m_Controller.m_Model.getHeight() / 1.5);
        leaderboard.getChildren().addAll(boardHeader);
        leaderboard.setAlignment(Pos.CENTER); // Center items horizontally
        leaderboard.setSpacing(10); // Set spacing between items
        leaderboard.setStyle("-fx-background-color: white;");

        // Create a Rectangle with stroke for the VBox
        Rectangle scrollPaneBorder = new Rectangle();
        scrollPaneBorder.setStroke(Color.BLACK); // Set the stroke color
        scrollPaneBorder.setFill(null); // Make the fill transparent
        scrollPaneBorder.setStrokeWidth(5); // Set the stroke width
        // Set the size of the rectangle
        scrollPaneBorder.setWidth(
                (double) m_Controller.m_Model.getWidth() / 2);
        scrollPaneBorder.setHeight(
                (double) m_Controller.m_Model.getHeight() / 1.5);


        try (BufferedReader reader = new BufferedReader
                (new FileReader("comp2013/output/highScores.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Splits the data based on the comma.
                String[] splitReading = line.split(",");
                // Print the data to the VBox without any whitespace
                // Also truncate the length of the string so that
                // only 30 chars can be used.
                String username;
                // If the length is more than the maximum
                if(splitReading[0].trim().length() > 30) {
                    // Truncate
                    username = splitReading[0].trim().substring(0, 30);
                }
                // Else just store it trimmed as is.
                else{ username = splitReading[0].trim();}
                // Add it to a label and add that to the scoreboard
                Label leaderboardItem = new Label
                        ( username + "        " + splitReading[1]);
                // Add some styling
                leaderboardItem.getStyleClass().add("leaderboard-item");
                // Add to scoreboard
                leaderboard.getChildren().add(leaderboardItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ScrollPane scrollingBoard = new ScrollPane(leaderboard);
        // Set the background of the ScrollPane to be transparent
        scrollingBoard.setStyle("-fx-background-color: white;");
        // Make it so you cant scroll horizontally
        scrollingBoard.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollingBoard.setMinSize
                ((double) m_Controller.m_Model.getWidth() / 2,
                        (double) m_Controller.m_Model.getHeight() / 2);
        scrollingBoard.setMaxSize
                ((double) m_Controller.m_Model.getWidth() / 2,
                        (double) m_Controller.m_Model.getHeight() / 1.5);
        leaderboardPane.getChildren().addAll(scrollingBoard,
                menuButton, scrollPaneBorder);


        menuButton.setTranslateY(250);
        leaderboardLabel.setTranslateY(25);

        M_PrimaryStage.setScene(leaderboardScene);
        M_PrimaryStage.show();
    }

    /**
     * Initialises a default menu screen.
     * @param title Title you want at the top of the screen.
     * @return Initialised Screen.
     */
    private Scene initialiseMenuScreen(String title){
        // Initialise the menu scene and stack pane.
        M_DefaultPane = new StackPane();
        Scene defaultScene = new Scene
                (M_DefaultPane, m_Controller.m_Model.getWidth(),
                m_Controller.m_Model.getHeight());
        // Add the CSS to the scene.
        defaultScene.getStylesheets().add(getClass().getResource
                ("/SnakeStyle.css").toExternalForm());

        // Add an image view to the pane
        ImageView imageView = new ImageView();
        // Set the background of the image.
        this.setBackgroundImage
                (imageView, "jungle-background");

        imageView.setFitHeight((int)
                (m_Controller.m_Model.getHeight()));
        imageView.setFitWidth((int)
                (m_Controller.m_Model.getWidth()));
        // Add the background to the pane.
        M_DefaultPane.getChildren().add(imageView);
        // Create a transparent VBox that goes over the top of the jungle
        // image so that it isnt so glaring.
        VBox darkBox = new VBox(10);
        // Set the box background to be transparent black.
        darkBox.setStyle
                ("-fx-background-color: rgba(0, 0, 0, 0.6);");

        M_DefaultPane.getChildren().add(darkBox);
        // New label with the specified text.
        M_DefaultLabel = new Label(title);
        // Add the style to the text.
        M_DefaultLabel.getStyleClass().add
                ("label-with-padding");
        // Set the text to be white.
        M_DefaultLabel.setStyle("-fx-text-fill: white;");
        // Set the position of the label
        StackPane.setAlignment(M_DefaultLabel, Pos.TOP_CENTER);
        M_DefaultPane.getChildren().add(M_DefaultLabel);
        M_DefaultLabel.setTranslateY(50);
        // Return the initialised scene to be used.
        return defaultScene;
    }
}
