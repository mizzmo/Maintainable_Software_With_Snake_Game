package comp2013;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.util.Random;

public class SnakeFood
{
	private SnakeController M_Controller = SnakeController.getInstance();

	public boolean m_Eaten;
	private Image M_Image;
	private double M_Width, M_Height;
	private int M_X, M_Y;
	public SnakeFood()	{
		// Gets a random image from the hash map and sets it.
		this.M_Image = ImageUtil.getImage(String.valueOf(new Random().nextInt(10)));
		this.m_Eaten = true;
		// Get the width and height of the image.
		this.M_Width = M_Image.getWidth();
		this.M_Height = M_Image.getHeight();
		// Set a random location on the screen
		this.M_X = (int) (Math.random() * (870 - M_Width - 10));
		this.M_Y = (int) (Math.random() * (560 - M_Height - 40));
	}

	/**
	 * Checks if the fruit has been eaten or not.
	 */
	public boolean eaten()	{
		int snakeHeadX, snakeHeadY, newPartX, newPartY;
		snakeHeadX = M_Controller.m_Snake.m_SnakeBody.getFirst().getX();
		snakeHeadY = M_Controller.m_Snake.m_SnakeBody.getFirst().getY();
		// Gets the last known x and y of the last segment.
		newPartX = M_Controller.m_Snake.m_SnakeBody.getLast().getX();
		newPartY = M_Controller.m_Snake.m_SnakeBody.getLast().getY();

		if (snakeHeadY == this.M_Y && snakeHeadX == this.M_X || this.m_Eaten == true){
			this.m_Eaten = false;
			//M_Controller.addSegment(newPartX, newPartY, true);
			M_Controller.m_Model.setScore(M_Controller.m_Model.getScore()+500);
			System.out.println("Here1");
			return true;
		}
		return false;
	}

	/**
	 * Draw the fruit to the screen.
	 * @param canvas Canvas to draw the fruit onto.
	 */
	public void drawFruit(Canvas canvas){
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(this.M_Image, this.M_X, this.M_Y);
		this.m_Eaten = false;
	};

	/**
	 * Change the fruit to a different type with a new location.
	 */
	public void newFruit(){
		// Carries out the same as the constructor, but can be called by other functions.
		this.M_Image = ImageUtil.getImage(String.valueOf(new Random().nextInt(10)));
		this.m_Eaten = true;
		this.M_Width = M_Image.getWidth();
		this.M_Height = M_Image.getHeight();
		this.M_X = (int) (Math.random() * (870 - M_Width - 10));
		this.M_Y = (int) (Math.random() * (560 - M_Height - 40));
	}
}
