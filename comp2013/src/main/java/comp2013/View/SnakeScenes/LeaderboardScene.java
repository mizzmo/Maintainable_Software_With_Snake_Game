package comp2013.View.SnakeScenes;

import comp2013.Controller.SnakeController;
import comp2013.View.SnakeView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeaderboardScene {
    private MainMenuScene M_MainMenuScene;
    private SnakeView M_View;

    private SnakeController M_Controller;
    public LeaderboardScene(SnakeView view, MainMenuScene menu) {
        M_View = view;
        M_MainMenuScene = menu;
        M_Controller = M_View.m_Controller;
    }

    public void setLeaderboardScene(){
        // Initialise the leaderboard scene and stack pane.
        Scene leaderboardScene = M_View.initialiseMenuScreen
                ("Leaderboard!");
        StackPane leaderboardPane = M_View.m_DefaultPane;
        Label leaderboardLabel = M_View.m_DefaultLabel;
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
                (M_Controller.m_Model.getHeight() / 9));
        menuButton.setMinWidth((int)
                (M_Controller.m_Model.getWidth() / 7));

        menuButton.setMaxHeight((int)
                (M_Controller.m_Model.getHeight() / 9));
        menuButton.setMaxWidth((int)
                (M_Controller.m_Model.getWidth() / 7));
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
                ((double) M_Controller.m_Model.getWidth() / 2,
                        (double) M_Controller.m_Model.getHeight() / 1.5);
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
                (double) M_Controller.m_Model.getWidth() / 2);
        scrollPaneBorder.setHeight(
                (double) M_Controller.m_Model.getHeight() / 1.5);


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
                ((double) M_Controller.m_Model.getWidth() / 2,
                        (double) M_Controller.m_Model.getHeight() / 2);
        scrollingBoard.setMaxSize
                ((double) M_Controller.m_Model.getWidth() / 2,
                        (double) M_Controller.m_Model.getHeight() / 1.5);
        leaderboardPane.getChildren().addAll(scrollingBoard,
                menuButton, scrollPaneBorder);


        menuButton.setTranslateY(250);
        leaderboardLabel.setTranslateY(25);

        M_View.m_PrimaryStage.setScene(leaderboardScene);
        M_View.m_PrimaryStage.show();
    }

}
