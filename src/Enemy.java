import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Scanner;
import java.io.File;

abstract public class Enemy implements OnScreen
{
    protected Point enemyPos;
    protected int enemyHealth;
    protected int enemyShields;
    protected int enemyDamage;
    protected int enemySpeed;
    public int enemyWidth;
    public int enemyHeight;
    protected boolean shootStatus;
    protected int bulletSpeed;
    protected Point bulletPos;
    protected GameState state;
    protected String filename;
    protected Bullet bullet;
    protected Scanner statsScanner;
    protected File statsFile;

    public void update()
    {
  //      enemyPos.x += enemySpeed;
        if(enemyPos.x > 800 - (enemyWidth / 2))
        {
            // enemySpeed = -enemySpeed;
        }
        else if(enemyPos.x < 0 + (enemyWidth / 2))
        {
          //  enemySpeed = -enemySpeed;
        }

        enemyPos.y += enemySpeed;
        if(enemyPos.y > 800 - (enemyHeight / 2))
        {
            state.removeObject(this);
        }

        int randomShootChance = (int)(Math.random() * 5000);

        if(randomShootChance < 50)
        {
            shootStatus = true;
        }
        else
        {
            shootStatus = false;
        }

        if(shootStatus)
        {
            bulletPos.y += bulletSpeed;
            if(bulletPos.y > 800)
            {
                state.removeObject(bullet);
                shootStatus = false;
            }
        }

        // Checks to see if the enemy is colliding with the player
        if(state.getPlayer().getPosition().x > enemyPos.x - (enemyWidth / 2) && 
           state.getPlayer().getPosition().x < enemyPos.x + (enemyWidth / 2) && 
           state.getPlayer().getPosition().y > enemyPos.y - (enemyHeight / 2) && 
           state.getPlayer().getPosition().y < enemyPos.y + (enemyHeight / 2))
        {
            state.getPlayer().changeHealth(state.getPlayer().getHealth() - enemyDamage);
            state.removeObject(this);
        }

        //If the enemy is at 0 health, remove it from the screen
        if(enemyHealth <= 0)
        {
            state.removeEnemy(this);
            state.removeEnemyPos(this.getPosition());
            state.removeObject(this);
        }
    }

    public void draw(Graphics g)
    {
        g.setColor(Color.BLACK);

        g.drawImage(ResourceLoader.getLoader().getImage(filename), enemyPos.x - enemyWidth / 2, 
                    enemyPos.y - enemyHeight / 2, enemyWidth, enemyHeight, null);

        Point bulletPos = new Point(enemyPos.x, enemyPos.y);
        bullet = new Bullet(bulletPos, enemyDamage, this, state);

        int randInt = (int)(Math.random() * 100);
        if(randInt < 49)
        {
            bullet.setSide("right");
        }
        else
        {
            bullet.setSide("left");
        }

        if(shootStatus)
        {
            state.addObject(bullet);
        }
    }

    public int getWidth()
    {
        return enemyWidth;
    }

    public int getHeight()
    {
        return enemyHeight;
    }

    public Point getPosition()
    {
        return enemyPos;
    }

    public int getHealth()
    {
        return enemyHealth;
    }

    public int getShields()
    {
        return enemyShields;
    }

    public int getDamage()
    {
        return enemyDamage;
    }

    public void setPosition(int x, int y)
    {
        enemyPos = new Point(x, y);
    }

    public void changeHealth(int health)
    {
        enemyHealth += health;
    }

    public void changeShields(int shields)
    {
        enemyShields += shields;
    }

    public void changeDamage(int damage)
    {
        enemyDamage += damage;
    }   
}
