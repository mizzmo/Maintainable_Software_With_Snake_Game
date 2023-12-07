package comp2013;

import javafx.scene.image.Image;

/**
 * 
 * @Project Snakee
 * @Description Fyrsta viðmótið
 * @Author Sigurður Sigurðardóttir
 * @version Ekki viss
 */ 

public interface Movable
{
	public int m_Direction = 0;

	/**
	 * Increase the speed of the snake.
	 */
	void increaseSpeed();

	/**
	 * Decrease the speed of the snake.
	 */
	void reduceSpeed();

	/**
	 * Change the snakes direction.
	 * 0 : UP
	 * 1 : DOWN
	 * 2 : LEFT
	 * 3 : RIGHT
	 * @param newDirection The direction that you want the snake to go.
	 */
	void changeDirection(int newDirection);

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
