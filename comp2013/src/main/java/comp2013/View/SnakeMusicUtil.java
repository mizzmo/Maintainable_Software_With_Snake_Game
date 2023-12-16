package comp2013.View;

import java.util.HashMap;
import java.util.Map;
/**
 *
 * Contains a hashmap that holds all the music file locations for the game.
 * @author Toby Surtees
 */
public class SnakeMusicUtil {

    /**
     * Hash map that contains all the music locations that will be used during the game.
     */
    public static Map<String, String> m_SnakeMusic = new HashMap<>();

    /**
     *  Returns the music at the given index in the hash map.
     * @param musicID Music ID that corresponds to the music you want to use.
     * @return Music file at the location in the hash map.
     */
    public static String getMedia(String musicID){
        // Returns null if no image in the index
        return m_SnakeMusic.get(musicID);
    }


    static {
        // Music
        m_SnakeMusic.put("frogger", SnakeImageUtil.class.getResource
                ("/sound/frogger.mp3").toString());
        m_SnakeMusic.put("retroFunk", SnakeImageUtil.class.getResource
                ("/sound/8bitFunk.mp3").toString());
    }
}
