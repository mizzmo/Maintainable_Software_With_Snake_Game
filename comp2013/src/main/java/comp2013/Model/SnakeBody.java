package comp2013.Model;

/**
 * Class that contains information about a snake body point.
 */
public class SnakeBody{
    // Stores the x and y values for the snake part.
    private int M_X, M_Y;
    // Stores the previous x and y values for the snake part.
    private int M_PreviousX, M_PreviousY;
    public SnakeBody(int x, int y){
        // Initialise the variables of the class.
        this.M_X = x;
        this.M_Y = y;
        this.M_PreviousX = x;
        this.M_PreviousY = y;
    }
    public int getX(){ return this.M_X; }

    public int getY(){ return this.M_Y; }

    public int getPrevX(){ return this.M_PreviousX; }
    public int getPrevY() { return this.M_PreviousY; }

    public void setX(int x){
        this.M_PreviousX = this.M_X;
        this.M_X = x;
    }

    public void setY(int y){
        this.M_PreviousY = this.M_Y;
        this.M_Y = y;
    }

}
