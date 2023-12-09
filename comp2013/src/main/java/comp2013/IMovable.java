package comp2013;

/**
 * 
 * @Project Snakee
 * @Description Fyrsta viðmótið
 * @Author Sigurður Sigurðardóttir
 * @version Ekki viss
 */ 

public interface IMovable
{
	public int m_Direction = 0;

	/**
	 * Returns the previous direction the snake was moving.
	 * @return Value of the previous direction.
	 */
	int getPreviousDirection();

	/**
	 * Increase the speed of the snake.
	 */
	void increaseSpeed();

	/**
	 * Decrease the speed of the snake.
	 */
	void reduceSpeed();


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
