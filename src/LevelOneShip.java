import java.awt.Point;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.Graphics;
import java.awt.Color;

public class LevelOneShip extends Ship
{
    public LevelOneShip(GameState state, String filename) throws FileNotFoundException
    {
        this.state = state;
        File statFile = new File("src/ShipStats.txt");
        Scanner statsScanner = new Scanner(statFile);
        playerPos = new Point(400, 700);
        playerScore = 0;
        bulletSpeed = 20;
        bulletPos = new Point(0, 0);
        this.filename = filename;

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
        if(state.getFollowMouse())
        {
            if(!((state.getCurrentMousePos().x < 150 && state.getCurrentMousePos().x > 10) && 
                (state.getCurrentMousePos().y > 15 && state.getCurrentMousePos().y < 55)))
                {
                    playerPos.x = state.getCurrentMousePos().x;
                    playerPos.y = state.getCurrentMousePos().y;
                }
        }
    }

}