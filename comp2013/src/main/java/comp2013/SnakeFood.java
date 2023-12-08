package comp2013;

import javafx.scene.image.Image;

import java.awt.Graphics;
import java.util.Random;

public class SnakeFood
{
	private SnakeController M_Controller = SnakeController.getInstance();

	private boolean M_Eaten;
	private Image M_Image;
	public SnakeFood()	{
		this.M_Eaten = false;

		this.M_Image = ImageUtil.images.get(String.valueOf(new Random().nextInt(10)));

		this.w = i.getWidth(null);
		this.h = i.getHeight(null);

		this.x = (int) (Math.random() * (870 - w + 10));
		this.y = (int) (Math.random() * (560 - h - 40));
	}

	public void eaten(MyFrame.MySnake mySnake)	{

		if (mySnake.getRectangle().intersects(this.getRectangle()) && l && mySnake.l)		{
			this.l = false;
			mySnake.changeLength(mySnake.getLength() + 1);
			mySnake.score += 521;
		}
	}
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(i, x, y, null);
	}
}
