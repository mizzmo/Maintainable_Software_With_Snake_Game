package comp2013.Model;

/**
 * Represents a movable snake object.
 */
public interface IMovable
{

	/**
	 * Returns the previous direction the snake was moving.
	 * @return Value of the previous direction.
	 */
	int getPreviousDirection();


	/**
	 * Set the direction of the snake.
	 * @param direction Direction that the snake is moving.
	 */
	void setDirection(int direction);

	/**
	 * Get the direction of the snake.
	 * @return Integer that represents the direction of the snake.
	 */
	int getDirection();


}
