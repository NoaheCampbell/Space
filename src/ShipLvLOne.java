import java.awt.Point;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.Graphics;

public class ShipLvLOne extends Ship
{
    public ShipLvLOne(GameState state, String filename, int width, int height) throws FileNotFoundException
    {
        this.state = state;
        File statFile = new File("src/ShipStats.txt");
        Scanner statsScanner = new Scanner(statFile);
        playerPos = new Point(400, 700);
        playerScore = 0;
        bulletSpeed = 20;
        bulletPos = new Point(0, 0);
        this.filename = filename;
        this.width = width;
        this.height = height;

        String word = statsScanner.next();
        
        if(word.equals("One:"))
        {
            while(statsScanner.hasNext())
            {
                word = statsScanner.next();
                if(word.equals("Health:"))
                {
                    playerHealth = statsScanner.nextInt();
                }
                else if(word.equals("Shields:"))
                {
                    playerShields = statsScanner.nextInt();
                }
                else if(word.equals("Damage:"))
                {
                    playerDamage = statsScanner.nextInt();
                }
                else if(word.equals("Speed:"))
                {
                    playerSpeed = statsScanner.nextInt();
                }

            }
        }
        statsScanner.close();
    }

    public void draw(Graphics g)
    {
        super.draw(g);
    }

    public void update()
    {
        super.update();
        
        if(state.getFollowMouse())
        {
            if(!(state.getCurrentMousePos().x < 165 && state.getCurrentMousePos().y < 185))
            {
                    playerPos.x = state.getCurrentMousePos().x;
                    playerPos.y = state.getCurrentMousePos().y;
            }
        }
    }

    public int getWidth() 
    {
        return width;
    }

    public int getHeight() 
    {
        return height;
    }

    public void setShields(int shields) 
    {
        playerShields = shields;
    }

}