package comp2013;

public class SnakeModel implements IModel {

    // Stores a reference to the controller.
    SnakeController m_Controller;
    // Store the width and height of the screen in pixels.
    // Store the score of the game.
    // Store the length of the snake.
    // Store the alive status of the snake.
    private int m_Width, m_Height, m_Score, m_Length, m_Alive;
    // Stores status on whether game is finished.
    private boolean m_Finished;

    // Initialise the model.
    SnakeModel(int width, int height, SnakeController controller) {
        this.m_Height = height;
        this.m_Width = width;
        this.m_Controller = controller;
        this.m_Score = 0;
        this.m_Length = 0;
        this.m_Finished = false;
        this.m_Alive = 1;
    }
    // Get the life status of the snake.
    @Override
    public int getAlive() { return this.m_Alive; }
    // Set the life status of the snake.
    @Override
    public void setAlive(int status) { this.m_Alive = status;}

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

    // Check if the game is over or not.
    @Override
    public boolean hasFinished() {
        return this.m_Finished;
    }


    //Set the game to be finished
    @Override
    public void setFinished(boolean finished) {
        this.m_Finished = finished;
    }


    // Restart the game from the beginning.
    @Override
    public void restart() {

    }

}
