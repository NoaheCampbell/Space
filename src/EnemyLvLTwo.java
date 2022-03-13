import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EnemyLvLTwo extends Enemy
{
    public EnemyLvLTwo(GameState state, Point enemyPos, int enemyWidth, int enemyHeight, String filename)
    {
        this.state = state;
        this.enemyPos = enemyPos;
        this.enemyWidth = enemyWidth;
        this.enemyHeight = enemyHeight;
        this.filename = filename;
        shootStatus = false;
        bulletSpeed = 20;
        bulletPos = new Point(0, 0);
        statsFile = new File("src/EnemyStats.txt");
        try 
        {
            statsScanner = new Scanner(statsFile);
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }

        String word = statsScanner.next();

        while(!word.equals("Two:"))
        {
            word = statsScanner.next();
        }

        while(statsScanner.hasNext())
        {
            word = statsScanner.next();
            if(word.equals("Health:"))
            {
                enemyHealth = statsScanner.nextInt();
            }
            else if(word.equals("Shields:"))
            {
                enemyShields = statsScanner.nextInt();
            }
            else if(word.equals("Damage:"))
            {
                enemyDamage = statsScanner.nextInt();
            }
            else if(word.equals("SpeedY:"))
            {
                enemySpeedY = statsScanner.nextInt();
            }
            else if(word.equals("SpeedX:"))
            {
                enemySpeedX = statsScanner.nextInt();
            }
        }

    }

    public void update() 
    {
        super.update();
    }
}
