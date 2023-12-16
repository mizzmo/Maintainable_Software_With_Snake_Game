package comp2013.View.SnakeScenes;

import comp2013.Controller.SnakeController;
import comp2013.Model.LeaderboardObject;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * Sets the scene to the leaderboard screen
 * @author Toby Surtees
 */
public class LeaderboardScene {
    private final MainMenuScene M_MainMenuScene;
    private final SnakeView M_View;

    private final SnakeController M_Controller;

    /**
     * Constructor to set the class objects
     * @param view View to draw the screen on
     * @param menu Menu screen class
     */
    public LeaderboardScene(SnakeView view, MainMenuScene menu) {
        M_View = view;
        M_MainMenuScene = menu;
        M_Controller = M_View.m_Controller;
    }

    /**
     * Changes the scene to the Leaderboard screen.
     */
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
        menuButton.setMinHeight(
                ((double) M_Controller.m_Model.getHeight() / 9));
        menuButton.setMinWidth(
                ((double) M_Controller.m_Model.getWidth() / 7));

        menuButton.setMaxHeight(
                ((double) M_Controller.m_Model.getHeight() / 9));
        menuButton.setMaxWidth(
                ((double) M_Controller.m_Model.getWidth() / 7));
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
        Rectangle scrollPaneBorder = getRectangle();


        List<LeaderboardObject> leaderboardObjects = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader
                ("comp2013/output/highScores.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitReading = line.split(",");
                String username = splitReading[0].trim();
                int score = Integer.parseInt(splitReading[1].trim());

                leaderboardObjects.add(new LeaderboardObject(username, score));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        // Sort the leaderboard items
        Collections.sort(leaderboardObjects);

        // Add sorted items to the VBox
        for (LeaderboardObject item : leaderboardObjects) {
            Label leaderboardItemLabel = new Label(item.username()
                    + "        " + item.score());
            leaderboardItemLabel.getStyleClass().add("leaderboard-item");
            leaderboard.getChildren().add(leaderboardItemLabel);
        }

        ScrollPane scrollingBoard = getScrollPane(leaderboard);
        leaderboardPane.getChildren().addAll(scrollingBoard,
                menuButton, scrollPaneBorder);


        menuButton.setTranslateY(250);
        leaderboardLabel.setTranslateY(25);

        M_View.m_PrimaryStage.setScene(leaderboardScene);
        M_View.m_PrimaryStage.show();
    }

    /**
     * Code to get the scrolling pane
     * @param leaderboard Bbox to put the pane into
     * @return Returns the  initialised ScrollPane
     */
    private ScrollPane getScrollPane(VBox leaderboard) {
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
        return scrollingBoard;
    }

    /**
     * Gets the rectangle to put around the scroll pane
     * @return Returns initialised rectangle
     */
    private Rectangle getRectangle() {
        Rectangle scrollPaneBorder = new Rectangle();
        scrollPaneBorder.setStroke(Color.BLACK); // Set the stroke color
        scrollPaneBorder.setFill(null); // Make the fill transparent
        scrollPaneBorder.setStrokeWidth(5); // Set the stroke width
        // Set the size of the rectangle
        scrollPaneBorder.setWidth(
                (double) M_Controller.m_Model.getWidth() / 2);
        scrollPaneBorder.setHeight(
                (double) M_Controller.m_Model.getHeight() / 1.5);
        return scrollPaneBorder;
    }

}
