package comp2013.View;

import comp2013.Controller.SnakeController;
import comp2013.Model.SnakeBody;
import comp2013.Model.SnakeObject;
import comp2013.View.SnakeScenes.MainMenuScene;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.util.Duration;
import javafx.scene.effect.ColorAdjust;

import java.io.*;
import java.util.List;

public class SnakeView extends Application implements IView {
    // Public
    // Store references to the controller
    public SnakeController m_Controller;
    public Stage m_PrimaryStage;
    public SnakeMusic m_SnakeMusic;
    public SnakeWall m_SnakeWall;
    public StackPane m_SnakePane;
    public Canvas m_SnakeCanvas, m_FoodCanvas, m_WallCanvas;
    public Image m_BackgroundImage;
    public SnakeFood m_SnakeFood;
    public Label m_ScoreLabel;
    public Label m_DefaultLabel;
    public Timeline m_Timeline;
    public Timeline m_WallTimeline;
    public double m_MusicVolume = 0.2;
    public StackPane m_DefaultPane;
    public Timeline m_FoodTimeline;
    public ColorAdjust m_ColorAdjust;
    public MainMenuScene m_MainMenuScene;

    // Private
    private Image M_SnakeHeadImg;
    private Image M_SnakeBodyImg;
    private Label M_CountDownLabel;
    private Label M_GameOverLabel;
    private Label M_PausedLabel;
    private Label M_PauseVolumeLabel;
    private Label M_PauseResumeLabel;
    private Button M_RestartButton, M_MenuReturnButton,
            M_EnterNameButton;
    private int M_TimerLength;
    private VBox M_DarkStripVbox;
    private TextField M_EnterNameField;
    private Timeline M_CountDownTimeline;
    private Slider M_PauseVolumeSlider;
    private boolean M_FirstEntry = true,
            M_GamePaused = false, M_GameOver = false;


    public SnakeView() {
        // Constructor gets the instance of controller.
        m_Controller = SnakeController.getInstance();
        // Set the controllers view to be this.
        m_Controller.setView(this);
        m_BackgroundImage = SnakeImageUtil.getImage
                ("grass-background");

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
            m_Timeline.setRate(timeRate);
        }

        // Get the list of snake body parts
        List<SnakeBody> snakeBody = m_Controller.m_Snake.m_SnakeBody;
        // Tells the loop if it needs to add a new segment or not.
        boolean addSegment = false;
        GraphicsContext gc = m_SnakeCanvas.getGraphicsContext2D();
        // Clear the canvas by filling it with a transparent color
        // Remove the effect, otherwise it applys to the clear rectangle
        gc.setEffect(null);
        // Draw the clear rectangle
        gc.clearRect(0, 0, gc.getCanvas().getWidth(),
                gc.getCanvas().getHeight());
        // Add the effect back again
        if(m_Controller.m_Model.getColourMode() == 1) {
            gc.setEffect(m_ColorAdjust);
        }
        // If the food has been eaten, draw a new one to replace it.
        if(m_SnakeFood.eaten()){
            // Re-Roll the fruit.
            m_SnakeFood.newFruit();
            // Draw the new fruit.
            m_SnakeFood.drawFruit(m_FoodCanvas);
            // Update the score
            this.drawScore();
            // Add a new segment
            addSegment = true;
        }

        if ((m_SnakeFood.m_NegativeFruit || m_SnakeFood.m_BonusFruit) && M_FirstEntry) {
            // Create a new timeline that waits 5 seconds
            m_FoodTimeline = new Timeline();
            m_FoodTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(5),
                    event ->
                    {
                        if (m_Controller.m_Model.getAlive() == 1){
                            m_SnakeFood.newFruit();
                            m_SnakeFood.drawFruit(m_FoodCanvas);
                        }
                        M_FirstEntry = true;

                    }));
            // Set cycle count to just 1 and play
            m_FoodTimeline.setCycleCount(1);
            m_FoodTimeline.play();
            // Set to false so the loop isnt entered again
            M_FirstEntry = false;
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
    public void start(Stage primaryStage)  {
        this.m_PrimaryStage = primaryStage;

        // Initialise main menu scene
        m_MainMenuScene = new MainMenuScene(this);


        // Initialise the colour adjust
        m_ColorAdjust = new ColorAdjust();
        // Initialise the snake wall
        m_SnakeWall = new SnakeWall();
        // Initialise the Accessablility Option
        // Adjust brightness
        m_ColorAdjust.setBrightness(0.2);
        // Adjust contrast
        m_ColorAdjust.setContrast(1);
        // Adjust hue
        m_ColorAdjust.setHue(-0.1);
        // Adjust saturation
        m_ColorAdjust.setSaturation(1);

        // Set title of screen.
        m_PrimaryStage.setTitle("Snake!");
        // Set the icon of the window.
        Image icon = SnakeImageUtil.getImage("snakeIcon");
        m_PrimaryStage.getIcons().add(icon);

        // Set the event handler for the window-closing event
        m_PrimaryStage.setOnCloseRequest(event -> {
            Platform.exit();});
        // Make it so you cant resize the window.
        m_PrimaryStage.setResizable(false);


        m_MainMenuScene.setMenuScene();

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
        if(m_Controller.m_Model.getColourMode() == 1){
            gc.setEffect(m_ColorAdjust);
        }
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
        m_ScoreLabel.setText
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
        M_GameOver = true;
        // Stop the timeline so the snake no longer moves.
        m_WallTimeline.stop();
        m_Timeline.stop();
        // Remove the food from the screen
        gc = m_FoodCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, gc.getCanvas().getWidth(),
                gc.getCanvas().getHeight());
        // Remove the Snake from the screen.
        gc = m_SnakeCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, gc.getCanvas().getWidth(),
                gc.getCanvas().getHeight());
        // Clear any walls from the screen
        gc = m_WallCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, gc.getCanvas().getWidth(),
                gc.getCanvas().getHeight());

        // Add a new label to show game over.
        M_GameOverLabel = new Label("Game Over!");
        M_GameOverLabel.getStyleClass().add("label-with-padding");
        M_GameOverLabel.getStyleClass().add("game-over-label");
        StackPane.setAlignment(M_GameOverLabel,
                javafx.geometry.Pos.TOP_CENTER);

        m_SnakePane.getChildren().add(M_GameOverLabel);
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
            m_Timeline.stop();
            // Go to the menu
            m_MainMenuScene.setMenuScene();
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
        m_SnakePane.getChildren().addAll(M_RestartButton, M_MenuReturnButton,
                M_EnterNameField, M_EnterNameButton);
        // Set the position of the buttons.
        M_RestartButton.setTranslateY(425);
        M_RestartButton.setMinSize((double)m_Controller.m_Model.getWidth()/6,
                (double)m_Controller.m_Model.getHeight()/10);
        M_MenuReturnButton.setTranslateY(500);
        M_MenuReturnButton.setMinSize((double)m_Controller.m_Model.getWidth()/6,
                (double) m_Controller.m_Model.getHeight()/10);

        M_EnterNameButton.setTranslateX(125);
        M_EnterNameField.setTranslateX(0);

        // Change the location of the label, move it to the middle of the screen.
        m_ScoreLabel.setTranslateY(325);
        // Update the text.
        m_ScoreLabel.setText("Final Score: " + m_Controller.m_Model.getScore());

        M_CountDownLabel = new Label("Restart in: 10");
        M_CountDownLabel.getStyleClass().add("label-with-padding");
        // Set alignment of the label within the StackPane
        StackPane.setAlignment(M_CountDownLabel, javafx.geometry.Pos.TOP_CENTER);
        m_SnakePane.getChildren().add(M_CountDownLabel);
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
        // If the timeline doesnt exist, make it.
        if(M_CountDownTimeline == null) {
            M_CountDownTimeline = new Timeline();
            M_CountDownTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
                Platform.runLater(() -> {
                    if (M_TimerLength > 0) {
                        M_TimerLength--;
                        M_CountDownLabel.setText("Restart in: " + M_TimerLength);
                    }
                    // Check if the countdown has reached zero
                    else if (M_TimerLength == 0) {
                        // Stop the timeline before restarting the game
                        m_Controller.restartGame();
                        M_CountDownTimeline.stop();
                    }
                });
            }));
        }
        // Else reset the cycle count
        else{M_CountDownTimeline.setCycleCount(0);}
        // Play the timeline.
        M_CountDownTimeline.setCycleCount(11);
        M_CountDownTimeline.play();
    }
    @Override
    public void pauseGame() {
        if (!M_GamePaused && !M_GameOver) {
            // Set the game to paused
            M_GamePaused = true;
            // Stop the timeline so the snake no longer moves.
            m_Timeline.stop();
            // Stop new walls generating.
            m_WallTimeline.stop();
            // Create a new vbox that acts as a transparent strip.
            M_DarkStripVbox = new VBox(10);
            M_DarkStripVbox.setStyle
                    ("-fx-background-color: rgba(0, 0, 0, 0.6);");
            // Set its size
            M_DarkStripVbox.setMinSize((double) m_Controller.m_Model.getWidth() / 2,
                    m_Controller.m_Model.getHeight());
            M_DarkStripVbox.setMaxSize((double) m_Controller.m_Model.getWidth() / 2,
                    m_Controller.m_Model.getHeight());

            // Create a label for the pause screen
            M_PausedLabel = new Label("Paused");;
            // Add styling
            M_PausedLabel.getStyleClass().add("label-with-padding");
            M_PausedLabel.setStyle("-fx-text-fill: white;" +
                    " -fx-font-size: 38; -fx-underline: true");

            M_PauseVolumeLabel = new Label(String.format("Volume: %.0f%%", m_MusicVolume * 100));
            M_PauseVolumeSlider = new Slider(0, 100,
                    this.m_MusicVolume * 100); // min, max, initial value
            M_PauseVolumeSlider.setShowTickMarks(true);

            // Add a listener to respond to changes in the slider value and
            // update the volume.
            M_PauseVolumeSlider.valueProperty().addListener
                    ((observable, oldValue, newValue) -> {
                        M_PauseVolumeLabel.setText(String.format
                                ("Volume: %.0f%%", newValue));
                        // Update the local variable so that all music is synced
                        this.m_MusicVolume = (double) newValue / 100;
                        // Update the music object itself
                        m_SnakeMusic.setVolume(((double)newValue / 100));

                    });

            // Set the size of the slider.
            M_PauseVolumeSlider.setMinHeight((int)(m_Controller.m_Model.getHeight() / 20));
            M_PauseVolumeSlider.setMinWidth((int)(m_Controller.m_Model.getWidth() / 3));

            M_PauseVolumeSlider.setMaxHeight((int)(m_Controller.m_Model.getHeight() / 20));
            M_PauseVolumeSlider.setMaxWidth((int)(m_Controller.m_Model.getWidth() / 3));
            // Add styling
            M_PauseVolumeLabel.getStyleClass().add("label-with-padding");
            // Set the text to be white.
            M_PauseVolumeLabel.setStyle("-fx-text-fill: white;");

            M_PauseResumeLabel = new Label("Press ESC Again To Resume");
            M_PauseResumeLabel.getStyleClass().add("label-with-padding");
            M_PauseResumeLabel.setStyle("-fx-font-size: 15; " +
                    "-fx-text-fill: white; -fx-underline: true");

            // Create a button that returns to the main menu.
            M_MenuReturnButton = new Button("Main Menu");
            // Set what happens when button is clicked.
            M_MenuReturnButton.setOnAction(event -> {
                // Go to the menu
                m_MainMenuScene.setMenuScene();
            });

            // Restart the game.
            M_RestartButton = new Button("Restart");
            // Set what happens when button is clicked.
            M_RestartButton.setOnAction(event -> {
                // Restart the game.
                m_Controller.restartGame();
            });

            M_MenuReturnButton.setMinSize((double)m_Controller.m_Model.getWidth()/6,
                    (double) m_Controller.m_Model.getHeight()/10);
            M_RestartButton.setMinSize((double)m_Controller.m_Model.getWidth()/6,
                    (double) m_Controller.m_Model.getHeight()/10);

            M_RestartButton.getStyleClass().add("snake-button");
            M_MenuReturnButton.getStyleClass().add("snake-button");

            m_SnakePane.getChildren().addAll(M_DarkStripVbox, M_PausedLabel,
                    M_PauseVolumeLabel, M_PauseVolumeSlider, M_PauseResumeLabel,
                    M_MenuReturnButton, M_RestartButton);
            // Set the position
            M_PausedLabel.setTranslateY(-200);
            M_PauseVolumeLabel.setTranslateY(-125);
            M_PauseVolumeSlider.setTranslateY(-75);
            M_PauseResumeLabel.setTranslateY(200);
            M_MenuReturnButton.setTranslateY(0);
            M_RestartButton.setTranslateY(100);
        }
        // Do nothing if the game is over.
        else if(M_GameOver){return;}
        // Unpause the game if called again
        else{
            this.unpauseGame();
        }
    }

    @Override
    public void unpauseGame(){
        M_GamePaused = false;
        m_SnakePane.getChildren().removeAll(M_DarkStripVbox, M_PausedLabel,
                M_PauseVolumeLabel, M_PauseVolumeSlider, M_PauseResumeLabel,
                M_MenuReturnButton, M_RestartButton);
        // Play the timeline again
        m_Timeline.play();
        m_WallTimeline.play();
    }


    @Override
    public void restartGame(){
        // If the game is paused, unpause it.
        if(M_GamePaused){
            this.unpauseGame();
        }
        M_GameOver = false;
        // Reset the rate of the timeline.
        m_Timeline.setRate(1);
        // Stop the timelines.
        m_Timeline.stop();
        m_WallTimeline.stop();
        // Set the timer to -1 so that the timeline loop doesnt do anything.
        M_TimerLength = -1;
        // Remove the game over labels from the screen.
        m_SnakePane.getChildren().removeAll(M_CountDownLabel,M_GameOverLabel,
                M_RestartButton, M_MenuReturnButton, M_EnterNameField,
                M_EnterNameButton);
        // Put the score back to its place.
        m_ScoreLabel.setTranslateY(0);
        // Update its text back to default.
        m_ScoreLabel.setText("Score: " + m_Controller.m_Model.getScore());
        // Build the snake again.
        this.buildSnake();
        // Create a new food and draw it.
        m_SnakeFood = new SnakeFood();
        m_SnakeFood.drawFruit(m_FoodCanvas);
        // Draw a new wall if option is active.
        if(m_Controller.m_Model.getWallMode() == 1) {
            m_SnakeWall.newWall();
            m_SnakeWall.drawWall(m_WallCanvas);
        }
        // Play the music on a loop.
        m_SnakeMusic.setLooping(true);
        // Start the timeline again.
        m_Timeline.play();
        m_WallTimeline.play();
    }
    /**
     * Initialises a default menu screen.
     * @param title Title you want at the top of the screen.
     * @return Initialised Screen.
     */
    public Scene initialiseMenuScreen(String title){
        // Initialise the menu scene and stack pane.
        m_DefaultPane = new StackPane();
        Scene defaultScene = new Scene
                (m_DefaultPane, m_Controller.m_Model.getWidth(),
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
        m_DefaultPane.getChildren().add(imageView);
        // Create a transparent VBox that goes over the top of the jungle
        // image so that it isnt so glaring.
        VBox darkBox = new VBox(10);
        // Set the box background to be transparent black.
        darkBox.setStyle
                ("-fx-background-color: rgba(0, 0, 0, 0.6);");

        m_DefaultPane.getChildren().add(darkBox);
        // New label with the specified text.
        m_DefaultLabel = new Label(title);
        // Add the style to the text.
        m_DefaultLabel.getStyleClass().add
                ("label-with-padding");
        // Set the text to be white.
        m_DefaultLabel.setStyle("-fx-text-fill: white;");
        // Set the position of the label
        StackPane.setAlignment(m_DefaultLabel, Pos.TOP_CENTER);
        m_DefaultPane.getChildren().add(m_DefaultLabel);
        m_DefaultLabel.setTranslateY(50);
        // Return the initialised scene to be used.
        return defaultScene;
    }
}
