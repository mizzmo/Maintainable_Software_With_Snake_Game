package comp2013.View.SnakeScenes;

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

public class GameScene {

    private SnakeView M_View;
    public GameScene(SnakeView view){
        M_View = view;
    }
    public void setGameScene(){
        // Stop any music that is already playing.
        if(M_View.m_SnakeMusic != null) {
            M_View.m_SnakeMusic.stopMusic();
        }

        M_View.M_SnakePane = new StackPane();

        Scene m_SnakeScene = new Scene(M_View.M_SnakePane,
                M_View.m_Controller.m_Model.getWidth(),
                M_View.m_Controller.m_Model.getHeight());

        // Load the CSS file
        m_SnakeScene.getStylesheets().add(getClass().getResource
                ("/SnakeStyle.css").toExternalForm());

        // Add an image view to the pane
        ImageView imageView = new ImageView();

        // Set the background of the image.
        imageView.setImage(M_View.M_BackgroundImage);
        // Set the size of the image view.
        imageView.setFitHeight((int)(M_View.m_Controller.m_Model.getHeight()));
        imageView.setFitWidth((int)(M_View.m_Controller.m_Model.getWidth()));
        // Add the background to the pane.
        M_View.M_SnakePane.getChildren().add(imageView);

        // Create a canvas that will be used to draw on the snake.
        M_View.m_SnakeCanvas = new Canvas(M_View.m_Controller.m_Model.getWidth(),
                M_View.m_Controller.m_Model.getHeight());
        // Create a seperate canvas for the food.
        M_View.m_FoodCanvas = new Canvas(M_View.m_Controller.m_Model.getWidth(),
                M_View.m_Controller.m_Model.getHeight());
        // Create a new canvas for the wall.
        M_View.m_WallCanvas = new Canvas(M_View.m_Controller.m_Model.getWidth(),
                M_View.m_Controller.m_Model.getHeight());

        // Add both of the canvases to the screen
        M_View.M_SnakePane.getChildren().addAll(M_View.m_FoodCanvas, M_View.m_SnakeCanvas,
                M_View.m_WallCanvas);


        M_View.M_ScoreLabel = new Label("Score: 0");

        // Apply the CSS style to the Label
        M_View.M_ScoreLabel.getStyleClass().add("label-with-padding");

        // Set alignment of the label within the StackPane
        StackPane.setAlignment(M_View.M_ScoreLabel, Pos.TOP_CENTER);

        // Add the label to the StackPane
        M_View.M_SnakePane.getChildren().add(M_View.M_ScoreLabel);

        // Build the initial snake.
        M_View.buildSnake();
        // Create a new food and draw it.
        M_View.M_SnakeFood = new SnakeFood();
        M_View.M_SnakeFood.drawFruit(M_View.m_FoodCanvas);
        // Create a wall and draw it on the wall canvas
        M_View.m_SnakeWall = new SnakeWall();

        M_View.M_WallTimeline = new Timeline(new KeyFrame(Duration.seconds(5),
                event -> {
                    // Generate and draw a new wall every 5 seconds.
                    // Draw a new wall if option is active.
                    if(M_View.m_Controller.m_Model.getWallMode() == 1) {
                        M_View.m_SnakeWall.newWall();
                        M_View.m_SnakeWall.drawWall(M_View.m_WallCanvas);
                    }
                }));
        M_View.M_WallTimeline.setCycleCount(Animation.INDEFINITE);
        M_View.M_WallTimeline.play();

        // Define the timeline that controlls how the snake moves.
        M_View.M_Timeline = new Timeline(new KeyFrame(Duration.millis(150),
                event -> {
                    M_View.refreshSnake();
                    M_View.m_Controller.moveSnake();
                }));

        M_View.M_Timeline.setCycleCount(Animation.INDEFINITE);
        M_View.M_Timeline.play();

        // Create a new SnakeMusic to be used to play music.
        M_View.m_SnakeMusic = new SnakeMusic(SnakeMusicUtil.getMedia
                ("frogger"));
        // Play the music
        M_View.m_SnakeMusic.playMusic();
        // Set the volume
        M_View.m_SnakeMusic.setVolume(M_View.M_MusicVolume);
        // Sets the music to loop until it is told otherwise.
        M_View.m_SnakeMusic.setLooping(true);

        m_SnakeScene.setOnKeyPressed(event ->
                M_View.m_Controller.handleKeyPress(event.getCode()));

        // Set the scene and show the page.
        M_View.M_PrimaryStage.setScene(m_SnakeScene);
        M_View.M_PrimaryStage.show();
    }
}
