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
	private String M_FileName;
	private Media M_MusicMedia;

	private MediaPlayer M_MediaPlayer;
	private double M_CurrentVolume;

	public SnakeMusic(String filename)
	{
		this.M_FileName = filename;
		this.M_MusicMedia = new Media(M_FileName);
		this.M_MediaPlayer = new MediaPlayer(M_MusicMedia);
	}

	// Sets the music that you want to play
	@Override
	public void setMusic(String filename) {

		if(filename != null) {
			// Set the filename.
			this.M_FileName = filename;
			// Update the media object.
			this.M_MusicMedia = new Media(filename);
			// Create a new Media Player with the new song.
			this.M_MediaPlayer = new MediaPlayer(M_MusicMedia);
			// Play the song.
			this.M_MediaPlayer.play();
			// Set the voulme.
			this.M_MediaPlayer.setVolume(this.M_CurrentVolume);
		}
		else { this.M_FileName = null; }
	}

	public MediaPlayer getMediaPlayer() {
		return M_MediaPlayer;
	}

	// Gets the File Path of the currently playing song.
	@Override
	public String getMusic(){
		return this.M_FileName;
	}

	@Override
	public void setVolume(double volume){
		// Update the volume variable
		this.M_CurrentVolume = volume;
		// Set the new volume of the player
		this.M_MediaPlayer.setVolume(M_CurrentVolume);

	}
	@Override
	public double getVolume(){ return this.M_CurrentVolume;}
	@Override
	public void increaseVolume(){
		if(this.M_CurrentVolume <= 1){
			// Add 0.1 to the volume
			this.M_CurrentVolume += 0.1F;
		}
		// If volume is already max, doesnt increase it.
		this.M_MediaPlayer.setVolume(M_CurrentVolume);
	}

	@Override
	public void decreaseVolume(){
		if(this.M_CurrentVolume >= 0){
			// Take 0.1 from the volume
			this.M_CurrentVolume -= 0.1F;
		}
		// If volume is already min, doesnt decrease it.
		this.M_MediaPlayer.setVolume(M_CurrentVolume);
	}

	@Override
	public void muteVolume(){
		// Set the volume to 0
		this.M_CurrentVolume = 0;
		this.setVolume(M_CurrentVolume);
	}
	@Override
	public void setLooping(boolean loop) {
		if (this.M_MediaPlayer != null) {
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
	}

	// Plays the currently loaded song.
	@Override
	public void playMusic() {
		if(this.M_MediaPlayer.getStatus() != MediaPlayer.Status.PLAYING){
			this.M_MediaPlayer.play();
			// Set the voulme.
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

	// Stops the music from playing
	@Override
	public void pauseMusic() {
		if(this.M_MediaPlayer.getStatus() == MediaPlayer.Status.PLAYING){
			this.M_MediaPlayer.pause();
		}
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
