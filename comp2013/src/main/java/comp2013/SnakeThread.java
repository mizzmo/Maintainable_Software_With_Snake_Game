package comp2013;

import java.awt.*;
import java.awt.Component;
public class SnakeThread extends Thread{
    @Override
    public void run()
    {
        super.run();
        while (true)
        {
            // Refresh the screen.
            try
            {
                sleep(30);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
