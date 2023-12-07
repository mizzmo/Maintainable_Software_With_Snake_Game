package comp2013;

/**
 * Class that contains information about a snake body point.
 */
public class SnakeBody {
    private int M_X;
    private int M_Y;

    public SnakeBody(int x, int y){
        this.M_X = x;
        this.M_Y = y;
    }
    public int getX(){ return this.M_X; }

    public int getY(){ return this.M_Y; }

    public void setX(int x){ this.M_X = x; }

    public void setY(int y){ this.M_Y = y; }
}
