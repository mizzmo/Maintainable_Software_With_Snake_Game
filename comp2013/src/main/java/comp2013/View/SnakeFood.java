package comp2013.View;

import comp2013.Controller.SnakeController;
import comp2013.Model.SnakeBody;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;

import java.util.Random;

public class SnakeFood
{
	private SnakeController M_Controller = SnakeController.getInstance();
	public boolean m_BonusFruit =false, m_NegativeFruit =false;
	public boolean m_Eaten;
	private Image M_Image;
	private double M_Width, M_Height;
	private int M_X, M_Y;
	public SnakeFood()	{
		// Gets a random image from the hash map and sets it.
		this.M_Image = SnakeImageUtil.getImage(String.valueOf(new Random().nextInt(10)));
		this.m_Eaten = true;
		// Get the width and height of the image.
		this.M_Width = M_Image.getWidth();
		this.M_Height = M_Image.getHeight();
		// Set a random location on the screen
		this.M_X = (int) (Math.random() * (M_Controller.m_Model.getWidth()*0.9 - M_Width));
		this.M_Y = (int) (Math.random() * (M_Controller.m_Model.getHeight()*0.9 - M_Height));
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
				// If special fruit
				if(m_BonusFruit){
					// Increase the score by 500
					M_Controller.m_Model.setScore(M_Controller.m_Model.getScore() + 500);
				}
				else if(m_NegativeFruit){
					// Decrease the score by 100
					M_Controller.m_Model.setScore(M_Controller.m_Model.getScore() - 100);
				}
				// If normal fruit.
				else{M_Controller.m_Model.setScore(M_Controller.m_Model.getScore() + 100);}
				this.m_Eaten = true;
				// If the timeline in view is not finished
				if(M_Controller.m_View.M_FoodTimeline != null && M_Controller.m_View.M_FoodTimeline.getCurrentTime().toSeconds() <= 5){
					// Stop the timeline to prevent it resetting the new fruit
					M_Controller.m_View.M_FoodTimeline.stop();
				}
				return true;
			}
		}
		this.m_Eaten = false;
		return false;
	}

	/**
	 * Draw the fruit to the screen.
	 * @param canvas Canvas to draw the fruit onto.
	 */
	public void drawFruit(Canvas canvas){
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setEffect(null);
		// Draw the clear rectangle
		gc.clearRect(0, 0, gc.getCanvas().getWidth(),
				gc.getCanvas().getHeight());
		// Add the effect back again
		if(M_Controller.m_Model.getColourMode() == 1) {
			gc.setEffect(M_Controller.m_View.M_ColorAdjust);
		}
		// Draw the fruit
		gc.drawImage(this.M_Image, this.M_X, this.M_Y);
		this.m_Eaten = false;
	};

	/**
	 * Change the fruit to a different type with a new location.
	 */
	public void newFruit(){
		m_BonusFruit = false;
		m_NegativeFruit = false;
		int randomInt = new Random().nextInt(19);
		// If a special fruit, set the boolean to true.
		if(randomInt == 17){
			m_NegativeFruit = true;
		}
		else if(randomInt == 18)
		{
			m_BonusFruit = true;
		}
		// Carries out the same as the constructor, but can be called by other functions.
		this.M_Image = SnakeImageUtil.getImage(String.valueOf(randomInt));
		this.m_Eaten = false;
		this.M_Width = M_Image.getWidth();
		this.M_Height = M_Image.getHeight();
		// Sets a x and y coordinate for the fruit that is at least 500 pixels away from the wall.
		this.M_X = (int) (Math.random() * (M_Controller.m_Model.getWidth()*0.9 - M_Width));
		this.M_Y = (int) (Math.random() * (M_Controller.m_Model.getHeight()*0.9 - M_Height));
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
	public static boolean areImagesIntersecting(double x1, double y1, double w1, double h1,
										  double x2, double y2, double w2, double h2) {
		return (x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2);
	}
}
