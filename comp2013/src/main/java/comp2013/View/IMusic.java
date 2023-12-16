package comp2013.View;

/**
 *
 * Utility to play music using JavaFX MediaPlayer.
 * @author Toby Surtees
 */
public interface IMusic {

    /**
     * Sets the song to loop infinitely.
     * @param loop True if music is looping, false to stop looping.
     */
    void setLooping(boolean loop);

    /**
     * Set the volume to a specific value.
     * @param newVolume The volume that you want to set to
     *                  0 is off. 1 is full.
     */
    void setVolume(double newVolume);

    /**
     * Plays the currently loaded song.
     */
    void playMusic();

    /**
     * Overloaded function to play music from a specified timestamp.
     * @param timeStamp Time to skip to in SECONDS.
     */
    void playMusic(int timeStamp);

    /**
     * Pause the song.
     */

    void stopMusic();
}
