package comp2013.Model;

import java.util.LinkedList;
import java.util.List;

/**
 * Holds information about a snake. Is made up of SnakeBody parts.
 */
public class SnakeObject implements IMovable {
    // Keeps track of how fast the snake is moving.
    public int m_SnakeSpeed;
    // Keeps track of which direction the snake is moving.
    public int m_Direction, m_PreviousDirection;
    // Constants that define direction.
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    // Added to the speed to determine how far the snake moves per tick
    public static final int SPEED_MODIFIER = 25;

    // Linked list containing all of the snake body points.
    public List<SnakeBody> m_SnakeBody = new LinkedList<>();

    /**
     * Constructor to build the head of the snake
     * @param x X coordinate for the snake head
     * @param y Y coordinate for the snake head
     */
    public SnakeObject(int x, int y){
        this.m_SnakeSpeed = 1 * SPEED_MODIFIER;
        // Set the initial direction
        this.m_PreviousDirection = RIGHT;
        this.m_Direction = RIGHT;
        // Create the head of the snake and store it in the linked list.
        SnakeBody snakeHead = new SnakeBody(x, y);
        m_SnakeBody.addFirst(snakeHead);
    }

    @Override
    public void setDirection(int direction) {
        // Checks that the direction is valid.
        if(direction == UP || direction == DOWN || direction == LEFT || direction == RIGHT) {
            // Update the previous direction, set the new direction.
            this.m_PreviousDirection = this.m_Direction;
            this.m_Direction = direction;
        }
    }

    @Override
    public int getDirection(){ return this.m_Direction; }

    @Override
    public int getPreviousDirection(){ return this.m_PreviousDirection; }
    //Increase the speed of the snake.



}
