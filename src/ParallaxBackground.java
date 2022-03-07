import java.awt.Graphics;
import java.awt.Point;

public class ParallaxBackground implements OnScreen
{
    private int speed;
    private String filename;

    public ParallaxBackground(String filename)
    {
        this.speed = 1;
        this.filename = filename;
    }

    public void draw(Graphics g) 
    {
        g.drawImage(ResourceLoader.getLoader().getImage(filename), 0, speed, null);
        g.drawImage(ResourceLoader.getLoader().getImage(filename), 0, speed - 789, null);
        g.drawImage(ResourceLoader.getLoader().getImage(filename), 0, speed - 1578, null);
    }

    public void update() 
    {
        speed += 1;
        if(speed >= 800)
        {
            speed = 10;
        }
    }

    public Point getPosition() 
    {
        return null;
    }

    public int getWidth() 
    {
        return 0;
    }

    public int getHeight() 
    {
        return 0;
    }

    public void changeHealth(int bulletDamage) 
    {
        
    }

    public void changeSpeed(int speed)
    {
        this.speed = speed;
    }

    public void changeFilename(String filename)
    {
        this.filename = filename;
    }
}