package comp2013;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains a hashmap that holds all of the image files for the game.
 */
public class SnakeImageUtil
{
	// Fruit dimensions in pixels.
	private static final int FRUIT_HEIGHT = 32;
	private static final int FRUIT_WIDTH = 32;
	// Snake dimensions in pixels.
	private static final int SNAKE_HEIGHT = 425;
	private static final int SNAKE_WIDTH = 425;

	public static Map<String, Image> m_SnakeImages = new HashMap<>();

	private static Map<String, int[]> M_SpriteLocation = new HashMap<>();

	/**
	 *  Returns the image at the given index in the hash map.
	 * @param imageID Image ID that corrisponds to the image you want to use.
	 * @return Image at the location in the hash map.
	 */
	public static Image getImage(String imageID){
		// Returns null if no image in the index
		return m_SnakeImages.get(imageID);
	}

	private static int[] getSpriteLocation(String imageID){
		return M_SpriteLocation.get(imageID);
	}

	/**
	 * Gets the sprite and returns it as an ImageView with its viewport set.
	 * @param imageID The image to get.
	 * @param toggle Is the image a Fruit (True) or a Snake (False)
	 * @return ImageView with the initialised image.
	 */
	public static ImageView getSprite(String imageID, boolean toggle){
		// Create a new image view.
		ImageView imageView = new ImageView(SnakeImageUtil.getImage(imageID));
		// Get the location of the image.
		int[] location = getSpriteLocation(imageID);
		// Set the viewport to that image location
		if(toggle) {
			imageView.setViewport(new Rectangle2D(location[0], location[1], FRUIT_WIDTH, FRUIT_HEIGHT));
		}
		else{
			imageView.setViewport(new Rectangle2D(location[0], location[1], SNAKE_WIDTH, SNAKE_HEIGHT));
			// Set the size of the image view.
			imageView.setFitHeight(25);
			imageView.setFitWidth(25);
		}
		return imageView;
	}




	/**
	 * Hash map that contains all of the images that will be used during the game.
	 */


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
				("/images/snake-head-right.png").toExternalForm()));

		m_SnakeImages.put("snakeHeadLeft", new Image(SnakeImageUtil.class.getResource
				("/images/snake-head-left.png").toExternalForm()));

		m_SnakeImages.put("snakeHeadUp", new Image(SnakeImageUtil.class.getResource
				("/images/snake-head-up.png").toExternalForm()));

		m_SnakeImages.put("snakeHeadDown", new Image(SnakeImageUtil.class.getResource
				("/images/snake-head-down.png").toExternalForm()));

		m_SnakeImages.put("snakeBody", new Image(SnakeImageUtil.class.getResource
				("/images/snake-body.png").toExternalForm()));

		m_SnakeImages.put("snakeSprite", new Image(SnakeImageUtil.class.getResource
				("/images/highResSnakeSheet.png").toExternalForm()));

		// Obstacles.
		m_SnakeImages.put("fruitSprites", new Image(SnakeImageUtil.class.getResource
				("/images/fruitSprites.png").toExternalForm()));

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
				("/images/food-watermelon.png").toExternalForm()));

		m_SnakeImages.put("16", new Image(SnakeImageUtil.class.getResource
				("/images/food-lemon.png").toExternalForm()));

		m_SnakeImages.put("17", new Image(SnakeImageUtil.class.getResource
				("/images/food-pitaya.png").toExternalForm()));

		// Snake Parts
		M_SpriteLocation.put("snakeBody", new int[]{0,0});
		M_SpriteLocation.put("headLeft", new int[]{425, 0});
		M_SpriteLocation.put("headUp", new int[]{850, 0});
		M_SpriteLocation.put("headDown", new int[]{0, 425});
		M_SpriteLocation.put("headRight", new int[]{425, 425});

		// Fruit
		// First Row.
		M_SpriteLocation.put("13", new int[]{0, 0});
		M_SpriteLocation.put("14", new int[]{32, 0});
		M_SpriteLocation.put("15", new int[]{64, 0});
		M_SpriteLocation.put("16", new int[]{96, 0});
		M_SpriteLocation.put("4", new int[]{128, 0});
		// Second Row
		M_SpriteLocation.put("0", new int[]{0, 32});
		M_SpriteLocation.put("1", new int[]{32, 32});
		M_SpriteLocation.put("2", new int[]{64, 32});
		M_SpriteLocation.put("3", new int[]{96, 32});
		// Third Row
		M_SpriteLocation.put("5", new int[]{0, 64});
		M_SpriteLocation.put("6", new int[]{32, 64});
		M_SpriteLocation.put("7", new int[]{64, 64});
		M_SpriteLocation.put("8", new int[]{96, 64});

		// Fourth Row
		M_SpriteLocation.put("9", new int[]{0, 96});
		M_SpriteLocation.put("10", new int[]{32, 96});
		M_SpriteLocation.put("11", new int[]{64, 96});
		M_SpriteLocation.put("12", new int[]{96, 96});








	}
}
