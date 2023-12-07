package comp2013;

import java.util.LinkedList;
import java.util.List;

public class SnakeObject implements Movable {
    // Keeps track of how fast the snake is moving.
    public int m_SnakeSpeed;
    // Keeps track of which direction the snake is moving.
    public int m_Direction;
    // Constants that define direction.
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;

    // Linked list containing all of the snake body points.
    public List<SnakeBody> m_SnakeBody = new LinkedList<>();

    public SnakeObject(int x, int y){
        this.m_SnakeSpeed = 5;
        // Create the head of the snake and store it in the linked list.
        SnakeBody snakeHead = new SnakeBody(x, y);
        m_SnakeBody.addFirst(snakeHead);
    }

    @Override
    public void setDirection(int direction) {
        // Checks that the direction is valid.
        if(direction == UP || direction == DOWN || direction == LEFT || direction == RIGHT) {
            this.m_Direction = direction;
        }
    }

    @Override
    public int getDirection(){ return this.m_Direction; }

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
