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
    protected int enemySpeedY;
    protected int enemySpeedX;
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
        if(state.getTimeElapsed() >= 10 && state.getTimeElapsed() <= 10.05)
        {
            enemyShields = 6;
        } 
        else if(state.getTimeElapsed() >= 20 && state.getTimeElapsed() <= 20.05)
        {
            enemyShields = 8;
        } 
        else if(state.getTimeElapsed() >= 30 && state.getTimeElapsed() <= 30.05)
        {
            enemyShields = 10;
        } 
        else if(state.getTimeElapsed() >= 40 && state.getTimeElapsed() <= 40.05)
        {
            enemyShields = 12;
        } 
        else if(state.getTimeElapsed() >= 50 && state.getTimeElapsed() <= 50.05)
        {
            enemyShields = 14;
        } 
        else if(state.getTimeElapsed() >= 60 && state.getTimeElapsed() <= 60.05)
        {
            enemyShields = 16;
        } 
        else if(state.getTimeElapsed() >= 70 && state.getTimeElapsed() <= 70.05)
        {
            enemyShields = 18;
        } 
        else if(state.getTimeElapsed() >= 80 && state.getTimeElapsed() <= 80.05)
        {
            enemyShields = 20;
        } 
        else if(state.getTimeElapsed() >= 90 && state.getTimeElapsed() <= 90.05)
        {
            enemyShields = 22;
        } 
        else if(state.getTimeElapsed() >= 100 && state.getTimeElapsed() <= 100.05)
        {
            enemyShields = 24;
        } 
        else if(state.getTimeElapsed() >= 110 && state.getTimeElapsed() <= 110.05)
        {
            enemyShields = 26;
        } 

        enemyPos.x += enemySpeedX;
        if(enemyPos.x >= 800 - getWidth() / 2)
        {
            enemySpeedX = -enemySpeedX;
        }
        else if(enemyPos.x <= 0 + getWidth() / 2)
        {
            enemySpeedX = -enemySpeedX;
        }


        enemyPos.y += enemySpeedY;
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
            if(state.playerHasShield())
            {
                state.getPlayer().changeShields(-enemyDamage);
            }
            else
            {
                state.getPlayer().changeHealth(-enemyDamage);
                state.removeObject(this);
                state.removeEnemy(this);
            }
        }

        //If the enemy is at 0 health, remove it from the screen
        if(enemyHealth <= 0)
        {
            state.removeEnemy(this);
            state.changePlayerScore(1);            
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
    
    public void changeSpeedX(int speedX)
    {
        enemySpeedX += speedX;
    }

    public void changeSpeedY(int speedY)
    {
        enemySpeedY += speedY;
    }

    public int getSpeedX()
    {
        return enemySpeedX;
    }

    public int getSpeedY()
    {
        return enemySpeedY;
    }
}
