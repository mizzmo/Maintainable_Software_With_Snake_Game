package comp2013;

import comp2013.Controller.SnakeController;
import comp2013.Model.SnakeModel;
import comp2013.View.SnakeView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class, launches the game.
 * @author Toby Surtees
 */
public class SnakeMain extends Application {
    static SnakeModel M_Model;
     static SnakeController M_Controller;

    /**
     * Initialise a new Model and Controller.
     */
    // Initialise the Model, View and Controller.
    private static void initialise() {
        M_Model = new SnakeModel(900, 600);
        M_Controller = new SnakeController();
    }

    /**
     * Start the game by initialing model and controller and
     * starting JavaFX.
     * @param args Command Line arguments.
     */
    public static void main(String[] args) {
        // Initialise the game.
        initialise();
        // Launch the JavaFX.
        launch(SnakeView.class, args);
    }

    /**
     * Redundant method for this class. Usually called by application to start javaFX.
     * @param stage Stage to use in the Game
     * @throws Exception If anything wrong occurs from Application.
     */
    //This method is only needed to satisfy the Application Class.
    // The actual start method is in the SnakeView class
    @Override
    public void start(Stage stage) throws Exception {

    }
}
