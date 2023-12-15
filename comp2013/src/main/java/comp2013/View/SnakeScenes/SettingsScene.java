package comp2013.View.SnakeScenes;

import comp2013.Controller.SnakeController;
import comp2013.View.SnakeView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
/**
 * Sets the scene to the Settings Screen
 */
public class SettingsScene {
    private MainMenuScene M_MainMenu;
    private SnakeView M_View;
    public SnakeController m_Controller;

    /**
     * Constructor to initialise the class variables
     * @param view View to draw to
     * @param menu Main menu screen
     */
    public SettingsScene(SnakeView view, MainMenuScene menu){
        M_View = view;
        m_Controller = M_View.m_Controller;
        M_MainMenu = menu;


    }
    /**
     * Sets the scene to the Main Menu Screen
     */
    public void setSettingsScene(){
        // Initialise the menu scene and stack pane.
        Scene settingsScene = M_View.initialiseMenuScreen("Settings");
        StackPane settingsPane = M_View.m_DefaultPane;

        // Create a label to display the slider value
        Label volumeLevelLabel = new Label("Volume: "
                + (int)(M_View.m_MusicVolume * 100) + "%");

        // Create a horizontal slider
        Slider volumeSlider = new Slider(0, 100,
                M_View.m_MusicVolume * 100); // min, max, initial value
        volumeSlider.setShowTickMarks(true);

        // Add a listener to respond to changes in the slider value and
        // update the volume.
        volumeSlider.valueProperty().addListener
                ((observable, oldValue, newValue) -> {
                    volumeLevelLabel.setText(String.format
                            ("Volume: %.0f%%", newValue));
                    // Update the local variable so that all music is synced
                    M_View.m_MusicVolume = (double) newValue / 100;
                    // Update the music object itself
                    M_View.m_SnakeMusic.setVolume(((double)newValue / 100));

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

        // Create a checkbox for colour modes
        CheckBox colourCheckBox = new CheckBox("High Contrast Mode");
        // Set the box to be ticked if the colour mode has been set already
        if(m_Controller.m_Model.getColourMode() == 1) {
            colourCheckBox.setSelected(true);
        }
        // Add CSS
        colourCheckBox.getStyleClass().add("checkbox-styling");

        // Set a handler for the checkbox
        colourCheckBox.setOnAction(event -> {
            if(colourCheckBox.isSelected()) {
                // Enable the contrast mode
                m_Controller.m_Model.setColourMode(1);
            }
            // Otherwise disable the contrast mode
            else{ m_Controller.m_Model.setColourMode(0);}

        });

        // Create a checkbox for walls
        CheckBox wallCheckBox = new CheckBox("Use Walls");
        // Set the box to be ticked if the walls have been set already
        if(m_Controller.m_Model.getWallMode() == 1) {
            wallCheckBox.setSelected(true);
        }
        // Add CSS
        wallCheckBox.getStyleClass().add("checkbox-styling");

        // Set a handler for the checkbox
        wallCheckBox.setOnAction(event -> {
            if(wallCheckBox.isSelected()) {
                // Enable the wall mode
                m_Controller.m_Model.setWallMode(1);
            }
            // Otherwise disable the wall mode
            else{ m_Controller.m_Model.setWallMode(0);}
        });


        // Create a button that returns to the main menu.
        Button menuButton = new Button("Back");
        // Set what happens when button is clicked.
        menuButton.setOnAction(event -> {
            // Go to the menu
            M_MainMenu.setMenuScene();
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
                snakeLengthLabel, snakeLengthSlider, menuButton,
                colourCheckBox, wallCheckBox);

        volumeLevelLabel.setTranslateY(-110);
        volumeSlider.setTranslateY(-75);

        snakeLengthLabel.setTranslateY(0);
        snakeLengthSlider.setTranslateY(50);

        menuButton.setTranslateY(175);
        colourCheckBox.setTranslateY(100);
        wallCheckBox.setTranslateY(100);
        colourCheckBox.setTranslateX(-100);
        wallCheckBox.setTranslateX(100);

        // Set the scene and show the page.
        M_View.m_PrimaryStage.setScene(settingsScene);
        M_View.m_PrimaryStage.show();
    }
}
