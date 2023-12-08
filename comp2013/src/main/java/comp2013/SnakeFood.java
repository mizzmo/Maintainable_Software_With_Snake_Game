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
		// For every point in the body.
		for (SnakeBody snakePart : M_Controller.m_Snake.m_SnakeBody) {
			int snakeBodyX = snakePart.getX();
			int snakeBodyY = snakePart.getY();
			// Checks if the fruit is intersecting with any part of the snake
			boolean isIntersecting = areImagesIntersecting(snakeBodyX, snakeBodyY, 25, 25,
					this.M_X, this.M_Y, 32, 32);
			// If intersecting, return true.
			if(isIntersecting){
				M_Controller.m_Model.setScore(M_Controller.m_Model.getScore()+100);
				return true;
			}
		}
		return false;
	}

	/**
	 * Draw the fruit to the screen.
	 * @param canvas Canvas to draw the fruit onto.
	 */
	public void drawFruit(Canvas canvas){
		GraphicsContext gc = canvas.getGraphicsContext2D();
		// Clear the canvas by filling it with a transparent color
		gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		// Draw the fruit
		gc.drawImage(this.M_Image, this.M_X, this.M_Y);
		this.m_Eaten = false;
	};

	/**
	 * Change the fruit to a different type with a new location.
	 */
	public void newFruit(){
		// Carries out the same as the constructor, but can be called by other functions.
		this.M_Image = ImageUtil.getImage(String.valueOf(new Random().nextInt(10)));
		this.m_Eaten = false;
		this.M_Width = M_Image.getWidth();
		this.M_Height = M_Image.getHeight();
		// Sets a x and y coordinate for the fruit that is at least 500 pixels away from the wall.
		this.M_X = (int) (Math.random() * (M_Controller.m_Model.getWidth() - M_Width - 40));
		this.M_Y = (int) (Math.random() * (M_Controller.m_Model.getHeight() - M_Height - 40));
	}

	/**
	 * Detects if images are interesecting
	 * @param x1 first X coordinate
	 * @param y1 first Y coordinaTe
	 * @param w1 first image width
	 * @param h1 first image height
	 * @param x2 second X coordinate
	 * @param y2 second Y coordinate
	 * @param w2 second image width
	 * @param h2 second image height
	 * @return Returns true if the image are intersecting, false otherwise.
	 */
	private boolean areImagesIntersecting(double x1, double y1, double w1, double h1,
										  double x2, double y2, double w2, double h2) {
		return (x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2);
	}
}
