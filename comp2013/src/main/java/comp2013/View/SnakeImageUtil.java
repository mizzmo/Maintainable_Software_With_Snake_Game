package comp2013.View;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains a hashmap that holds all of the image files for the game.
 */
public class SnakeImageUtil
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
	 * Hash map that contains all of the images that will be used during the game.
	 */
	public static Map<String, Image> m_SnakeImages = new HashMap<>();

	static
	{
		// Game Scenes
		m_SnakeImages.put("cloud-background", new Image(SnakeImageUtil.class.getResource
				("/images/cloudBackground.jpg").toExternalForm()));

		m_SnakeImages.put("grass-background", new Image(SnakeImageUtil.class.getResource
				("/images/grassBackground.jpg").toExternalForm()));

		m_SnakeImages.put("ocean-background", new Image(SnakeImageUtil.class.getResource
				("/images/oceanBackground.jpg").toExternalForm()));

		m_SnakeImages.put("jungle-background", new Image(SnakeImageUtil.class.getResource
				("/images/jungleBackground.jpg").toExternalForm()));
		// Window Icon
		m_SnakeImages.put("snakeIcon", new Image(SnakeImageUtil.class.getResource
				("/images/snake-logo.png").toExternalForm()));

		// Snake Body Parts.
		m_SnakeImages.put("snakeHeadRight", new Image(SnakeImageUtil.class.getResource
				("/images/snakeHeadTexturedRight.png").toExternalForm()));

		m_SnakeImages.put("snakeHeadLeft", new Image(SnakeImageUtil.class.getResource
				("/images/snakeHeadTexturedLeft.png").toExternalForm()));

		m_SnakeImages.put("snakeHeadUp", new Image(SnakeImageUtil.class.getResource
				("/images/snakeHeadTexturedUp.png").toExternalForm()));

		m_SnakeImages.put("snakeHeadDown", new Image(SnakeImageUtil.class.getResource
				("/images/snakeHeadTexturedDown.png").toExternalForm()));

		m_SnakeImages.put("snakeBody", new Image(SnakeImageUtil.class.getResource
				("/images/snakeBodyTextured.png").toExternalForm()));

		// Obstacles.
		m_SnakeImages.put("0", new Image(SnakeImageUtil.class.getResource
				("/images/food-kiwi.png").toExternalForm()));

		m_SnakeImages.put("1", new Image(SnakeImageUtil.class.getResource
				("/images/food-litchi.png").toExternalForm()));

		m_SnakeImages.put("2", new Image(SnakeImageUtil.class.getResource
				("/images/food-mango.png").toExternalForm()));

		m_SnakeImages.put("3", new Image(SnakeImageUtil.class.getResource
				("/images/food-apple.png").toExternalForm()));

		m_SnakeImages.put("4", new Image(SnakeImageUtil.class.getResource
				("/images/food-banana.png").toExternalForm()));

		m_SnakeImages.put("5", new Image(SnakeImageUtil.class.getResource
				("/images/food-blueberry.png").toExternalForm()));

		m_SnakeImages.put("6", new Image(SnakeImageUtil.class.getResource
				("/images/food-cherry.png").toExternalForm()));

		m_SnakeImages.put("7", new Image(SnakeImageUtil.class.getResource
				("/images/food-durian.png").toExternalForm()));

		m_SnakeImages.put("8", new Image(SnakeImageUtil.class.getResource
				("/images/food-grape.png").toExternalForm()));

		m_SnakeImages.put("9", new Image(SnakeImageUtil.class.getResource
				("/images/food-grapefruit.png").toExternalForm()));

		m_SnakeImages.put("10", new Image(SnakeImageUtil.class.getResource
				("/images/food-peach.png").toExternalForm()));

		m_SnakeImages.put("11", new Image(SnakeImageUtil.class.getResource
				("/images/food-pear.png").toExternalForm()));

		m_SnakeImages.put("12", new Image(SnakeImageUtil.class.getResource
				("/images/food-orange.png").toExternalForm()));

		m_SnakeImages.put("13", new Image(SnakeImageUtil.class.getResource
				("/images/food-pineapple.png").toExternalForm()));

		m_SnakeImages.put("14", new Image(SnakeImageUtil.class.getResource
				("/images/food-strawberry.png").toExternalForm()));

		m_SnakeImages.put("15", new Image(SnakeImageUtil.class.getResource
				("/images/food-lemon.png").toExternalForm()));

		m_SnakeImages.put("16", new Image(SnakeImageUtil.class.getResource
				("/images/food-pitaya.png").toExternalForm()));

		m_SnakeImages.put("17", new Image(SnakeImageUtil.class.getResource
				("/images/rotten-apple.png").toExternalForm()));

		m_SnakeImages.put("18", new Image(SnakeImageUtil.class.getResource
				("/images/golden-apple.png").toExternalForm()));
	}
}
