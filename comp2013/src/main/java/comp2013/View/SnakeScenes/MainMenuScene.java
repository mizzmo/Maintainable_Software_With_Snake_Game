package comp2013.View.SnakeScenes;

import comp2013.View.SnakeImageUtil;
import comp2013.View.SnakeMusic;
import comp2013.View.SnakeMusicUtil;
import comp2013.View.SnakeView;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * Sets the scene to the Main Menu Screen
 */
public class MainMenuScene {
    private SnakeView M_View;

    private SettingsScene M_SettingsScene;

    private MapSelectScene M_MapSelectScreen;

    private LeaderboardScene M_LeaderboardScene;

    /**
     * Constructor to initialise class variables
     * @param view View to use and draw to.
     */
    public MainMenuScene(SnakeView view){
        M_View = view;
        M_SettingsScene = new SettingsScene(view, this);
        M_MapSelectScreen = new MapSelectScene(view, this);
        M_LeaderboardScene = new LeaderboardScene(view, this);
    }

    /**
     * Sets the scene to the Main Menu Screen
     */
    public void setMenuScene(){
        // Stops music if there is any, and plays a new one.
        if(M_View.m_SnakeMusic != null) {
            M_View.m_SnakeMusic.stopMusic();
        }

        M_View.m_SnakeMusic = new SnakeMusic(SnakeMusicUtil.getMedia
                ("retroFunk"));
        M_View.m_SnakeMusic.playMusic();

        // Set the volume
        M_View.m_SnakeMusic.setVolume(M_View.m_MusicVolume);
        // Set the music to loop
        M_View.m_SnakeMusic.setLooping(true);

        // Initialise the menu scene and stack pane.
        Scene menuScene = M_View.initialiseMenuScreen("Snake!");
        StackPane menuPane = M_View.m_DefaultPane;

        Label titleLabel = M_View.m_DefaultLabel;
        titleLabel.getStyleClass().add("snake-title-label");
        titleLabel.setTranslateY(100);

        // Button to start the game.
        Button startButton = new Button("Start Game");
        startButton.setOnAction(event -> {
            M_MapSelectScreen.setSelectScene();
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
            M_SettingsScene.setSettingsScene();
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
            M_LeaderboardScene.setLeaderboardScene();
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
        // Reset the background image to default for the map select screen.
        M_View.m_BackgroundImage = SnakeImageUtil.getImage
                ("grass-background");

        // Set the scene and show the page.
        M_View.m_PrimaryStage.setScene(menuScene);
        M_View.m_PrimaryStage.show();
    }
}
