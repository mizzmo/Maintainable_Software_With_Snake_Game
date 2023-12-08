package comp2013;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageUtil
{
	/**
	 *  Returns the image at the given index in the hash map.
	 * @param imageID Image ID that corrisponds to the image you want to use.
	 * @return Image at the location in the hash map.
	 */
	public static Image getImage(String imageID){
		// Returns null if no image in the index
		return m_SnakeImages.get(imageID);
	}
	/**
	 * Swaps the snake-head image depending on the direction of travel
	 */
	public static Image changeHeadDirection(int direction){
		// Finds out which way the snake is facing and sets the image accordingly.
		Image snakeHeadImage;
		switch (direction) {
			case SnakeObject.UP: {
				snakeHeadImage = ImageUtil.getImage("snakeHeadUp");
			}
			break;
			case SnakeObject.DOWN: {
				snakeHeadImage = ImageUtil.getImage("snakeHeaDown");
			}
			break;
			case SnakeObject.LEFT: {
				snakeHeadImage = ImageUtil.getImage("snakeHeadLeft");
			}
			break;
			case SnakeObject.RIGHT: {
				snakeHeadImage = ImageUtil.getImage("snakeHeadRight");
			}
			break;
			default:
				return null;
		}
		return snakeHeadImage;
	}


	/**
	 * Hash map that contains all of the images that will be used during the game.
	 */
	public static Map<String, Image> m_SnakeImages = new HashMap<>();

	static
	{
		// Game Scenes
		m_SnakeImages.put("background", new Image(ImageUtil.class.getResource
				("/images/UI-background.png").toExternalForm()));

		m_SnakeImages.put("endImage", new Image(ImageUtil.class.getResource
				("/images/theEnd.jpg").toExternalForm()));

		m_SnakeImages.put("snakeIcon", new Image(ImageUtil.class.getResource
				("/images/snake-logo.png").toExternalForm()));

		// Snake Body Parts.
		m_SnakeImages.put("snakeHeadRight", new Image(ImageUtil.class.getResource
				("/images/snake-head-right.png").toExternalForm()));

		m_SnakeImages.put("snakeHeadLeft", new Image(ImageUtil.class.getResource
				("/images/snake-head-left.png").toExternalForm()));

		m_SnakeImages.put("snakeHeadUp", new Image(ImageUtil.class.getResource
				("/images/snake-head-up.png").toExternalForm()));

		m_SnakeImages.put("snakeHeadDown", new Image(ImageUtil.class.getResource
				("/images/snake-head-down.png").toExternalForm()));

		m_SnakeImages.put("snakeBody", new Image(ImageUtil.class.getResource
				("/images/snake-body.png").toExternalForm()));

		// Obstacles.
		m_SnakeImages.put("0", new Image(ImageUtil.class.getResource
				("/images/food-kiwi.png").toExternalForm()));

		m_SnakeImages.put("1", new Image(ImageUtil.class.getResource
				("/images/food-lichi.png").toExternalForm()));

		m_SnakeImages.put("2", new Image(ImageUtil.class.getResource
				("/images/food-mango.png").toExternalForm()));

		m_SnakeImages.put("3", new Image(ImageUtil.class.getResource
				("/images/food-apple.png").toExternalForm()));

		m_SnakeImages.put("4", new Image(ImageUtil.class.getResource
				("/images/food-banana.png").toExternalForm()));

		m_SnakeImages.put("5", new Image(ImageUtil.class.getResource
				("/images/food-blueberry.png").toExternalForm()));

		m_SnakeImages.put("6", new Image(ImageUtil.class.getResource
				("/images/food-cherry.png").toExternalForm()));

		m_SnakeImages.put("7", new Image(ImageUtil.class.getResource
				("/images/food-durian.png").toExternalForm()));

		m_SnakeImages.put("8", new Image(ImageUtil.class.getResource
				("/images/food-grape.png").toExternalForm()));

		m_SnakeImages.put("9", new Image(ImageUtil.class.getResource
				("/images/food-grapefruit.png").toExternalForm()));

		m_SnakeImages.put("10", new Image(ImageUtil.class.getResource
				("/images/food-peach.png").toExternalForm()));

		m_SnakeImages.put("11", new Image(ImageUtil.class.getResource
				("/images/food-pear.png").toExternalForm()));

		m_SnakeImages.put("12", new Image(ImageUtil.class.getResource
				("/images/food-orange.png").toExternalForm()));

		m_SnakeImages.put("13", new Image(ImageUtil.class.getResource
				("/images/food-pineapple.png").toExternalForm()));

		m_SnakeImages.put("14", new Image(ImageUtil.class.getResource
				("/images/food-strawberry.png").toExternalForm()));

		m_SnakeImages.put("15", new Image(ImageUtil.class.getResource
				("/images/food-watermelon.png").toExternalForm()));

		m_SnakeImages.put("16", new Image(ImageUtil.class.getResource
				("/images/food-lemon.png").toExternalForm()));

		m_SnakeImages.put("17", new Image(ImageUtil.class.getResource
				("/images/food-pitaya.png").toExternalForm()));


	}
}
