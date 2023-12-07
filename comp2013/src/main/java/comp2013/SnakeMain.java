package comp2013;

import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.text.View;

public class SnakeMain extends Application {
    static SnakeModel M_Model;
     static SnakeController M_Controller;
    // Initialise the Model, View and Controller.
    private static void initialise() {
        M_Model = new SnakeModel(700, 500);
        M_Controller = new SnakeController();
    }
    public static void main(String[] args) {
        // Initialise the game.
        initialise();
        // Launch the JavaFX.
        launch(SnakeView.class, args);
    }
    //This method is only needed to satisfy the Application Class.
    // The actual start method is in the SnakeView class
    @Override
    public void start(Stage stage) throws Exception {

    }
}
