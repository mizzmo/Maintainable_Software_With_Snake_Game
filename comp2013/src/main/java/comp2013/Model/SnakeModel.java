package comp2013.Model;

import comp2013.Controller.SnakeController;

public class SnakeModel implements IModel {

    // Stores a reference to the controller.
    SnakeController m_Controller;
    // Store the width and height of the screen in pixels.
    // Store the score of the game.
    // Store the length of the snake.
    // Store the alive status of the snake.
    private int m_Width, m_Height, m_Score, m_Length, m_Alive;
    // Stores the initial length of the snake set in the settings.
    public int m_InitialLength;
    // Stores status on whether game is finished.
    private boolean m_Finished;
    // Gets and instance of the Model
    private static SnakeModel m_Instance;
    // Sets the game to different colour modes.
    private int m_ColourMode;

    // Initialise the model.
    public SnakeModel(int width, int height) {
        m_Instance = this;
        this.m_Height = height;
        this.m_Width = width;
        this.m_Score = 0;
        this.m_Length = 3;
        this.m_InitialLength = 3;
        this.m_Alive = 1;
        this.m_ColourMode = 0;
    }
    /**
     * Returns an instance of the Model.
     * @return SnakeModel Instance
     */
    public static SnakeModel getInstance() {
        return m_Instance;
    }
    // Set the controller used in the Model.
    public void setController(SnakeController controller) {
        this.m_Controller = controller;
    }
    // Get the life status of the snake.
    @Override
    public int getAlive() { return this.m_Alive; }
    // Set the life status of the snake.
    @Override
    public void setAlive(int status) { this.m_Alive = status;}

    @Override
    public void setInitialLength(int length){
        this.m_InitialLength = length;
        this.m_Length = length;
    }

    //Get the score of the game.
    @Override
    public int getScore() {
        return this.m_Score;
    }

    //Set the score of the game
    @Override
    public void setScore(int score) {
        this.m_Score = score;
    }

    //Get the current length of the snake in play.
    @Override
    public int getLength() {
        return this.m_Length;
    }

    //Set the length of the snake to a specific value.
    @Override
    public void setLength(int length) {
        this.m_Length = length;
    }

    //Adds one to the length of the snake.
    @Override
    public void incrementLength() {
        // Add one to the length of the snake.
        this.m_Length++;
    }
    // Gets the Height of the screen in pixels.
    @Override
    public int getHeight() {return this.m_Height;}


    // Gets the Width of the screen in pixels.

    @Override
    public int getWidth() {
        return this.m_Width;
    }


    // Set the height of the screen
    @Override
    public void setHeight(int m_Height) {
        // Update the value stored in model.
        this.m_Height = m_Height;
    }

    //Set the width of the screen in Pixels
    @Override
    public void setWidth(int m_Width) {
        // Update the value stored in model.
        this.m_Width = m_Width;
    }
    // Set the colour mode.
    @Override
    public void setColourMode(int colourMode){
        this.m_ColourMode = colourMode;
    }
    // Get the colour mode
    @Override
    public int getColourMode(){return this.m_ColourMode;}



}
