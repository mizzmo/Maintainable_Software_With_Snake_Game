package comp2013.View;

/**
 *
 * Utility to play music using JavaFX MediaPlayer.
 * @author Toby Surtees
 */
public interface IMusic {

    /**
     * Gets the current url of the song playing
     * @return URL of the currently playing song.
     */
    String getMusic();

    /**
     * Set the currently playing music
     * @param url Location of new file to play ".mp3"
     */
    void setMusic(String url);

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
     * Returns the current volume.
     * @return Current volume of music.
     */
    double getVolume();
    /**
     * Increase the volume by a fixed amount.
     */

    void increaseVolume();

    /**
     * Decrease the volume by a fixed amount
     */
    void decreaseVolume();

    /**
     * Set the volume to 0.
     */
    void muteVolume();

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
    void pauseMusic();

    /**
     * Stops the music from playing, disposes of the MediaPlayer
     */
    void stopMusic();
}
