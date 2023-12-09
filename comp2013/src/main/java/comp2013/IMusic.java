package comp2013;

/**
 * Utility to play music using JavaFX MediaPlayer.
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
    public void setLooping(boolean loop);

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
     * Stops the music from playing
     * @return Timestamp of the song that was playing
     */
    void stopMusic();
}
