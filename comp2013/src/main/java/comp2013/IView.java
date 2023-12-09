package comp2013;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;

public interface IView {
    /**
     * Sets the controller to use.
     * @param controller Controller to use.
     */
    void setController(SnakeController controller);


    /**
     * Draw the snake again from its new coordinates.
     */
    void refreshSnake();

    /**
     * Build the snake at the beginning of the game.
     * @param length Length of the snake to build.
     */
    void buildSnake(int length);

    /**
     * Sets the background image of the game.
     * @param imageView ImageView that holds the image.
     */
    public void setBackgroundImage(ImageView imageView);

    /**
     * Draws the current score of the game.
     */
    public void drawScore();

    /**
     * Swaps the snake-head image depending on the direction of travel
     */
    void changeHeadDirection();

    /**
     * Handles what happens to the view when a game over condition is met.
     */
    public void gameOverScreen();
}
