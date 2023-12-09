package comp2013;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class SnakeMusic extends Thread
{
	private String filename;
	public Player player;

	public SnakeMusic(String filename)
	{
		this.filename = filename;
	}

	public void play()
	{
		new Thread()
		{
			@Override
			public void run()
			{
				super.run();
				try
				{
					//BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(filename));
					player = new Player(new BufferedInputStream(new FileInputStream(filename)));
					player.play();

				} catch (Exception e)
				{
					System.out.println(e);
				}
			}
		}.start();
	}



	public static void getMusicPlay(String filename)
	{
		SnakeMusic snakeMusic = new SnakeMusic(filename);
		snakeMusic.play();
	}
}
