package comp2013;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

public class ImageUtil
{
	public static Map<String, Image> images = new HashMap<>();

	static
	{
		// snake
		images.put("snake-head-right", GameUtil.getImage("/images/snake-head-right.png"));
		images.put("snake-body", GameUtil.getImage("/images/snake-body.png"));
		// obstacles
		images.put("0", GameUtil.getImage("/images/food-kiwi.png"));
		images.put("1", GameUtil.getImage("/images/food-lemon.png"));
		images.put("2", GameUtil.getImage("/images/food-litchi.png"));
		images.put("3", GameUtil.getImage("/images/food-mango.png"));
		images.put("4", GameUtil.getImage("/images/food-apple.png"));
		images.put("5", GameUtil.getImage("/images/food-banana.png"));
		images.put("6", GameUtil.getImage("/images/food-blueberry.png"));
		images.put("7", GameUtil.getImage("/images/food-cherry.png"));
		images.put("8", GameUtil.getImage("/images/food-durian.png"));
		images.put("9", GameUtil.getImage("/images/food-grape.png"));
		images.put("10", GameUtil.getImage("/images/food-grapefruit.png"));
		images.put("11", GameUtil.getImage("/images/food-peach.png"));
		images.put("12", GameUtil.getImage("/images/food-pear.png"));
		images.put("13", GameUtil.getImage("/images/food-orange.png"));
		images.put("14", GameUtil.getImage("/images/food-pineapple.png"));
		images.put("15", GameUtil.getImage("/images/food-strawberry.png"));
		images.put("16", GameUtil.getImage("/images/food-watermelon.png"));
		images.put("UI-background", GameUtil.getImage("/images/UI-background.png"));
		images.put("game-scene-01", GameUtil.getImage("/images/theEnd.jpg"));
	}
}
