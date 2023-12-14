package comp2013.View;

import comp2013.Controller.SnakeController;
import comp2013.Model.SnakeBody;
import comp2013.Model.SnakeObject;
import comp2013.View.SnakeScenes.GameScene;
import comp2013.View.SnakeScenes.MainMenuScene;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.effect.ColorAdjust;

import java.io.*;
import java.util.List;

public class SnakeView extends Application implements IView {
    // Store references to the controller
    public SnakeController m_Controller;
    public Stage M_PrimaryStage;
    public SnakeMusic m_SnakeMusic;

    public SnakeWall m_SnakeWall;
    public StackPane M_SnakePane;
    public Canvas m_SnakeCanvas, m_FoodCanvas, m_WallCanvas;
    private Image M_SnakeHeadImg;
    private Image M_SnakeBodyImg;
    public Image M_BackgroundImage;
    public SnakeFood M_SnakeFood;
    public Label M_ScoreLabel;
    private Label M_CountDownLabel;
    private Label M_GameOverLabel;
    public Label M_DefaultLabel;
    private Label M_PausedLabel;
    private Label M_PauseVolumeLabel;
    private Label M_PauseResumeLabel;
    public Timeline M_Timeline;
    public Timeline M_WallTimeline;
    private Button M_RestartButton, M_MenuReturnButton,
            M_EnterNameButton;
    private int M_TimerLength;
    public double M_MusicVolume = 0.2;
    public StackPane M_DefaultPane;
    private VBox M_DarkStripVbox;
    private TextField M_EnterNameField;
    private Timeline M_CountDownTimeline;
    public Timeline M_FoodTimeline;
    private Slider M_PauseVolumeSlider;
    private boolean M_FirstEntry = true,
            M_GamePaused = false, M_GameOver = false;

    public ColorAdjust M_ColorAdjust;

    public MainMenuScene M_MainMenuScene;

    private GameScene M_GameScene;

    public SnakeView() {
        // Constructor gets the instance of controller.
        m_Controller = SnakeController.getInstance();
        // Set the controllers view to be this.
        m_Controller.setView(this);
        M_BackgroundImage = SnakeImageUtil.getImage
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
            M_Timeline.setRate(timeRate);
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
            gc.setEffect(M_ColorAdjust);
        }
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

        if ((M_SnakeFood.m_NegativeFruit || M_SnakeFood.m_BonusFruit) && M_FirstEntry) {
            // Create a new timeline that waits 5 seconds
            M_FoodTimeline = new Timeline();
            M_FoodTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(5),
                    event ->
                    {
                        if (m_Controller.m_Model.getAlive() == 1){
                            M_SnakeFood.newFruit();
                            M_SnakeFood.drawFruit(m_FoodCanvas);
                        }
                        M_FirstEntry = true;

                    }));
            // Set cycle count to just 1 and play
            M_FoodTimeline.setCycleCount(1);
            M_FoodTimeline.play();
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
        this.M_PrimaryStage = primaryStage;

        // Initialise all of the scenes
        M_MainMenuScene = new MainMenuScene(this);
        M_GameScene = new GameScene(this);
        // Initialise the colour adjust
        M_ColorAdjust = new ColorAdjust();
        // Initialise the snake wall
        m_SnakeWall = new SnakeWall();
        // Initialise the Accessablility Option
        // Adjust brightness
        M_ColorAdjust.setBrightness(0.2);
        // Adjust contrast
        M_ColorAdjust.setContrast(1);
        // Adjust hue
        M_ColorAdjust.setHue(-0.1);
        // Adjust saturation
        M_ColorAdjust.setSaturation(1);

        // Set title of screen.
        M_PrimaryStage.setTitle("Snake!");
        // Set the icon of the window.
        Image icon = SnakeImageUtil.getImage("snakeIcon");
        M_PrimaryStage.getIcons().add(icon);

        // Set the event handler for the window-closing event
        M_PrimaryStage.setOnCloseRequest(event -> {
            Platform.exit();});

        M_MainMenuScene.setMenuScene();

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
            gc.setEffect(M_ColorAdjust);
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
        M_GameOver = true;
        // Stop the timeline so the snake no longer moves.
        M_WallTimeline.stop();
        M_Timeline.stop();
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
            M_MainMenuScene.setMenuScene();
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
        M_MenuReturnButton.setTranslateY(500);
        M_MenuReturnButton.setMinSize((double)m_Controller.m_Model.getWidth()/6,
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
            M_Timeline.stop();
            // Stop new walls generating.
            M_WallTimeline.stop();
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

            M_PauseVolumeLabel = new Label(String.format("Volume: %.0f%%", M_MusicVolume * 100));
            M_PauseVolumeSlider = new Slider(0, 100,
                    this.M_MusicVolume * 100); // min, max, initial value
            M_PauseVolumeSlider.setShowTickMarks(true);

            // Add a listener to respond to changes in the slider value and
            // update the volume.
            M_PauseVolumeSlider.valueProperty().addListener
                    ((observable, oldValue, newValue) -> {
                        M_PauseVolumeLabel.setText(String.format
                                ("Volume: %.0f%%", newValue));
                        // Update the local variable so that all music is synced
                        this.M_MusicVolume = (double) newValue / 100;
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
                M_MainMenuScene.setMenuScene();
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

            M_SnakePane.getChildren().addAll(M_DarkStripVbox, M_PausedLabel,
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
        M_SnakePane.getChildren().removeAll(M_DarkStripVbox, M_PausedLabel,
                M_PauseVolumeLabel, M_PauseVolumeSlider, M_PauseResumeLabel,
                M_MenuReturnButton, M_RestartButton);
        // Play the timeline again
        M_Timeline.play();
        M_WallTimeline.play();
    }


    @Override
    public void restartGame(){
        // If the game is paused, unpause it.
        if(M_GamePaused){
            this.unpauseGame();
        }
        M_GameOver = false;
        // Reset the rate of the timeline.
        M_Timeline.setRate(1);
        // Stop the timelines.
        M_Timeline.stop();
        M_WallTimeline.stop();
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
        // Draw a new wall if option is active.
        if(m_Controller.m_Model.getWallMode() == 1) {
            m_SnakeWall.newWall();
            m_SnakeWall.drawWall(m_WallCanvas);
        }
        // Play the music on a loop.
        m_SnakeMusic.setLooping(true);
        // Start the timeline again.
        M_Timeline.play();
        M_WallTimeline.play();
    }





    public void setSelectScene(){
        // Initialise the menu scene and stack pane.
        Scene mapSelectScene = this.initialiseMenuScreen("Map Select");
        StackPane mapSelectPane = this.M_DefaultPane;

        // Create a button that returns to the main menu.
        Button menuButton = new Button("Back");
        // Set what happens when button is clicked.
        menuButton.setOnAction(event -> {
            // Go to the menu
            M_MainMenuScene.setMenuScene();
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
            M_GameScene.setGameScene();
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

    public void setLeaderboardScene(){
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
            M_MainMenuScene.setMenuScene();
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
    public Scene initialiseMenuScreen(String title){
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
