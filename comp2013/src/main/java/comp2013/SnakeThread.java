package comp2013;

import java.awt.*;
import java.awt.Component;
public class SnakeThread extends Thread{

    private SnakeController M_Controller;
    public SnakeThread(SnakeController controller){
        this.M_Controller = controller;
    }
    @Override
    public void run()
    {
        super.run();
        while (true)
        {
            // Refresh the screen.
            System.out.printf("Thread is running\n");
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
