package comp2013;

public class SnakeObject implements Movable {
    // Keeps track of how fast the snake is moving.
    public int m_SnakeSpeed;
    // Keeps track of which direction the snake is moving.
    public int m_Direction;
    public SnakeObject(){
        this.m_SnakeSpeed = 5;
    }

    @Override
    public void moveSnake() {

    }

    //Increase the speed of the snake.
    @Override
    public void increaseSpeed() {
        this.m_SnakeSpeed++;
    }

    // Decrease the speed of the snake.

    @Override
    public void reduceSpeed() {
        this.m_SnakeSpeed--;
    }


    /* Change the snakes direction.
    0 : UP
    1 : DOWN
    2 : LEFT
    3 : RIGHT */
    @Override
    public void changeDirection(int newDirection) {
        this.m_Direction = newDirection;
        // Need to implement the rest of this.
    }

}
