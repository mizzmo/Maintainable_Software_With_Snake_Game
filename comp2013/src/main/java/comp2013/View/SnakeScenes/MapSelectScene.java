package comp2013.View.SnakeScenes;

import comp2013.Controller.SnakeController;
import comp2013.View.SnakeImageUtil;
import comp2013.View.SnakeView;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 *
 * Sets the scene to the Map Select Screen
 * @author Toby Surtees
 */
public class MapSelectScene {

    private final SnakeView M_View;
    private final MainMenuScene M_MainMenuScene;

    private final GameScene M_GameScene;
    private final SnakeController M_Controller;

    /**
     * Constructor initialises the class variables
     * @param view View to use and draw to
     * @param menu Menu Scene.
     */
    public MapSelectScene(SnakeView view, MainMenuScene menu){
        M_View = view;
        M_MainMenuScene = menu;
        M_Controller = M_View.m_Controller;
        M_GameScene = new GameScene(view);
    }
    /**
     * Sets the scene to the Main Menu Screen
     */
    public void setSelectScene(){
        // Initialise the menu scene and stack pane.
        Scene mapSelectScene = M_View.initialiseMenuScreen("Map Select");
        StackPane mapSelectPane = M_View.m_DefaultPane;

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

        menuButton.setMaxHeight(((double) M_Controller.m_Model.getHeight() / 9));
        menuButton.setMaxWidth(((double) M_Controller.m_Model.getWidth() / 7));

        // Create a button that returns to the main menu.
        Button startButton = new Button("Start Game!");
        // Set what happens when button is clicked.
        startButton.setOnAction(event -> {
            // Set the new scene
            M_GameScene.setGameScene();
            // Reset the game to default.
            M_Controller.restartGame();
        });
        // Add styling and set location
        startButton.getStyleClass().add("snake-button");
        // Set the size of the
        startButton.setMinHeight(
                ((double) M_Controller.m_Model.getHeight() / 9));
        startButton.setMinWidth(
                ((double) M_Controller.m_Model.getWidth() / 7));

        startButton.setMaxHeight(
                ((double) M_Controller.m_Model.getHeight() / 9));
        startButton.setMaxWidth(
                ((double) M_Controller.m_Model.getWidth() / 7));

        Label selectLabel = new Label("Selected: Grassy Plains");
        selectLabel.getStyleClass().add("label-with-padding");
        selectLabel.setStyle("-fx-text-fill: WHITE; -fx-font-size: 20;");
        // Create a Rectangle for an outline that is slightly
        // bigger than the image view.
        Rectangle selectOutline = new Rectangle(
                ((double) M_Controller.m_Model.getWidth() / 4) + 20,
                ((double) M_Controller.m_Model.getHeight() / 4) + 20
        );
        selectOutline.setFill(null); // No fill
        selectOutline.setStroke(Color.WHITE); // Set the color of the outline
        selectOutline.setStrokeWidth(5);


        ImageView mapSelectCloud = new ImageView();
        M_View.setBackgroundImage(mapSelectCloud, "cloud-background");
        // Set the size of the image view.
        mapSelectCloud.setFitHeight(
                ((double) M_Controller.m_Model.getHeight() / 4));
        mapSelectCloud.setFitWidth(
                ((double) M_Controller.m_Model.getWidth() / 4));
        // Set an event handler for the click event
        mapSelectCloud.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                M_View.m_BackgroundImage = SnakeImageUtil.getImage
                        ("cloud-background");
                selectOutline.setTranslateX(-275);
                selectLabel.setTranslateX(-275);
                selectLabel.setText("Selected: Sky High");
            }
        });

        ImageView mapSelectGrass = new ImageView();
        M_View.setBackgroundImage(mapSelectGrass, "grass-background");
        // Set the size of the image view.
        mapSelectGrass.setFitHeight(
                ((double) M_Controller.m_Model.getHeight() / 4));
        mapSelectGrass.setFitWidth(
                ((double) M_Controller.m_Model.getWidth() / 4));

        mapSelectGrass.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                M_View.m_BackgroundImage = SnakeImageUtil.getImage
                        ("grass-background");
                selectOutline.setTranslateX(0);
                selectLabel.setTranslateX(0);
                selectLabel.setText("Selected: Grassy Plains");
            }
        });

        ImageView mapSelectOcean = new ImageView();
        M_View.setBackgroundImage(mapSelectOcean, "ocean-background");
        // Set the size of the image view.
        mapSelectOcean.setFitHeight(
                ((double) M_Controller.m_Model.getHeight() / 4));
        mapSelectOcean.setFitWidth(
                ((double) M_Controller.m_Model.getWidth() / 4));

        mapSelectOcean.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                M_View.m_BackgroundImage = SnakeImageUtil.getImage
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
        M_View.m_PrimaryStage.setScene(mapSelectScene);
        M_View.m_PrimaryStage.show();
    }
}
