package comp2013.View.SnakeScenes;

import comp2013.Controller.SnakeController;
import comp2013.View.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * Sets the scene to be the main snake game
 * @author Toby Surtees
 */
public class GameScene {

    private final SnakeView M_View;
    
    private final SnakeController M_Controller;

    /**
     * Constructor to set the View and Controller
     * @param view View to set to.
     */
    public GameScene(SnakeView view){
        M_View = view;
        M_Controller = M_View.m_Controller;
    }

    /**
     * Sets the scene to the Game Scene
     */
    public void setGameScene(){
        // Stop any music that is already playing.
        if(M_View.m_SnakeMusic != null) {
            M_View.m_SnakeMusic.stopMusic();
        }

        M_View.m_SnakePane = new StackPane();

        Scene m_SnakeScene = new Scene(M_View.m_SnakePane,
                M_Controller.m_Model.getWidth(),
                M_Controller.m_Model.getHeight());

        // Load the CSS file
        m_SnakeScene.getStylesheets().add(getClass().getResource
                ("/SnakeStyle.css").toExternalForm());

        // Add an image view to the pane
        ImageView imageView = new ImageView();

        // Set the background of the image.
        imageView.setImage(M_View.m_BackgroundImage);
        // Set the size of the image view.
        imageView.setFitHeight((M_Controller.m_Model.getHeight()));
        imageView.setFitWidth((M_Controller.m_Model.getWidth()));
        // Add the background to the pane.
        M_View.m_SnakePane.getChildren().add(imageView);

        // Create a canvas that will be used to draw on the snake.
        M_View.m_SnakeCanvas = new Canvas(M_Controller.m_Model.getWidth(),
                M_Controller.m_Model.getHeight());
        // Create a separate canvas for the food.
        M_View.m_FoodCanvas = new Canvas(M_Controller.m_Model.getWidth(),
                M_Controller.m_Model.getHeight());
        // Create a new canvas for the wall.
        M_View.m_WallCanvas = new Canvas(M_Controller.m_Model.getWidth(),
                M_Controller.m_Model.getHeight());

        // Add both of the canvases to the screen
        M_View.m_SnakePane.getChildren().addAll(M_View.m_FoodCanvas, M_View.m_SnakeCanvas,
                M_View.m_WallCanvas);

        M_View.m_ScoreLabel = new Label("Score: 0");

        // Apply the CSS style to the Label
        M_View.m_ScoreLabel.getStyleClass().add("label-with-padding");

        // Set alignment of the label within the StackPane
        StackPane.setAlignment(M_View.m_ScoreLabel, Pos.TOP_CENTER);

        // Add the label to the StackPane
        M_View.m_SnakePane.getChildren().add(M_View.m_ScoreLabel);

        // Build the initial snake.
        M_View.buildSnake();
        // Create a new food and draw it.
        M_View.m_SnakeFood = new SnakeFood();
        M_View.m_SnakeFood.drawFruit(M_View.m_FoodCanvas);
        // Create a wall and draw it on the wall canvas
        M_View.m_SnakeWall = new SnakeWall();

        M_View.m_WallTimeline = new Timeline(new KeyFrame(Duration.seconds(5),
                event -> {
                    // Generate and draw a new wall every 5 seconds.
                    // Draw a new wall if option is active.
                    if(M_Controller.m_Model.getWallMode() == 1) {
                        M_View.m_SnakeWall.newWall();
                        M_View.m_SnakeWall.drawWall(M_View.m_WallCanvas);
                    }
                }));
        M_View.m_WallTimeline.setCycleCount(Animation.INDEFINITE);
        M_View.m_WallTimeline.play();

        // Define the timeline that controls how the snake moves.
        M_View.m_Timeline = new Timeline(new KeyFrame(Duration.millis(150),
                event -> {
                    M_View.refreshSnake();
                    M_Controller.moveSnake();
                }));

        M_View.m_Timeline.setCycleCount(Animation.INDEFINITE);
        M_View.m_Timeline.play();

        // Create a new SnakeMusic to be used to play music.
        M_View.m_SnakeMusic = new SnakeMusic(SnakeMusicUtil.getMedia
                ("frogger"));
        // Play the music
        M_View.m_SnakeMusic.playMusic();
        // Set the volume
        M_View.m_SnakeMusic.setVolume(M_View.m_MusicVolume);
        // Sets the music to loop until it is told otherwise.
        M_View.m_SnakeMusic.setLooping(true);

        m_SnakeScene.setOnKeyPressed(event ->
                M_Controller.handleKeyPress(event.getCode()));

        // Set the scene and show the page.
        M_View.m_PrimaryStage.setScene(m_SnakeScene);
        M_View.m_PrimaryStage.show();
    }
}
