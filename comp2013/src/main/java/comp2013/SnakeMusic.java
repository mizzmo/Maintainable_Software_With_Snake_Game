package comp2013;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import javafx.util.Duration;

public class SnakeMusic implements IMusic
{
	private String M_FileName;
	private Media M_MusicMedia;
	private MediaPlayer M_MediaPlayer;

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
			this.M_MusicMedia = new Media(new File(filename).toURI().toString());
			// Create a new Media Player with the new song.
			this.M_MediaPlayer = new MediaPlayer(M_MusicMedia);
			// Play the song.
			this.M_MediaPlayer.play();
		}
		else { this.M_FileName = null; }
	}


	// Gets the File Path of the currently playing song.
	@Override
	public String getMusic(){
		return this.M_FileName;
	}

	// Plays the currently loaded song.
	@Override
	public void playMusic() {
		if(M_MediaPlayer.getStatus() != MediaPlayer.Status.PLAYING){
			M_MediaPlayer.play();
		}
	}


	// Overloaded function to play music from a specified timestamp.
	@Override
	public void playMusic(int timeStamp) {
		// Set the number of seconds from the timestamp.
		Duration seekTime = Duration.seconds(timeStamp);
		M_MediaPlayer.seek(seekTime);
		M_MediaPlayer.play();
	}

	// Stops the music from playing
	@Override
	public void stopMusic() {
		if(M_MediaPlayer.getStatus() == MediaPlayer.Status.PLAYING){
			M_MediaPlayer.pause();
		}
	}

}
