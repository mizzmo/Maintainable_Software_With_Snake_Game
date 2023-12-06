package comp2013;

import javax.swing.text.View;

public class SnakeMain {
    private static SnakeModel Model;
    private static SnakeView View;
    private static SnakeController Controller;
    // Initialise the Model, View and Controller.
    static void initialise(){
        // Initialise the model.
        Model = new SnakeModel(700, 500, Controller);
        // Initialise the view
        View = new SnakeView(Controller);
        // Initialise the controller.
        Controller = new SnakeController(Model, View);
        // Create the display.
        View.initialiseDisplay(Model.getHeight(), Model.getWidth());

    }
    public static void main(String[] args) {
        initialise();
    }
}
