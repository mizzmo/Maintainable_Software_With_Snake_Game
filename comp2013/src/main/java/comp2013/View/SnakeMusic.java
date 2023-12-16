package comp2013.View;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.util.Duration;

/**
 *
 * Some basic music functionality that works with JavaFX Media Player
 * @author Toby Surtees-modified
 */
public class SnakeMusic implements IMusic
{
	private final MediaPlayer M_MediaPlayer;
	private double M_CurrentVolume;

	public SnakeMusic(String filename)
	{
		Media m_MusicMedia = new Media(filename);
		this.M_MediaPlayer = new MediaPlayer(m_MusicMedia);
	}


	@Override
	public void setVolume(double volume){
		// Update the volume variable
		this.M_CurrentVolume = volume;
		// Set the new volume of the player
		this.M_MediaPlayer.setVolume(M_CurrentVolume);

	}

	@Override
	public void setLooping(boolean loop) {
        // Enable looping if true.
        if(loop) {
            this.M_MediaPlayer.setOnEndOfMedia(() -> {
                // When the end of media is reached,
                // reset the playback position to the beginning
                this.M_MediaPlayer.seek(Duration.ZERO);
            });
        }
        // Disable looping if false
        else{
            this.M_MediaPlayer.setOnEndOfMedia(null); // Disable looping
        }
    }

	// Plays the currently loaded song.
	@Override
	public void playMusic() {
		if(this.M_MediaPlayer.getStatus() != MediaPlayer.Status.PLAYING){
			this.M_MediaPlayer.play();
			// Set the volume.
			this.M_MediaPlayer.setVolume(this.M_CurrentVolume);
		}
	}


	// Overloaded function to play music from a specified timestamp.
	@Override
	public void playMusic(int timeStamp) {
		// Set the number of seconds from the timestamp.
		Duration seekTime = Duration.seconds(timeStamp);
		this.M_MediaPlayer.seek(seekTime);
		this.M_MediaPlayer.play();
		this.M_MediaPlayer.setVolume(this.M_CurrentVolume);
	}


	@Override
	public void stopMusic() {
		if(this.M_MediaPlayer.getStatus() == MediaPlayer.Status.PLAYING){
			this.M_MediaPlayer.pause();
			// Remove the previous object
			this.M_MediaPlayer.dispose();
		}
	}

}
