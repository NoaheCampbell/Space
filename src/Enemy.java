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
    protected int enemyWidth;
    protected int enemyHeight;
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
        if(state.getTimeElapsed() % 10000 == 0 && state.getTimeElapsed() >= 10000)
        {
            this.changeSpeedY(1);
            this.changeShields(1);
        }
        if(!state.getGameOver())
        {
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

            if(this instanceof EnemyLvLOne)
                state.changePlayerScore(1); 
            else if(this instanceof EnemyLvLTwo)
                state.changePlayerScore(5);
                
            if(state.getPlayerScore() % 10 == 0)
            {
                state.getPlayer().changeShields(1);
            }        
            state.removeObject(this);
        }
    }

    public void draw(Graphics g)
    {
        g.setColor(Color.BLACK);

        g.drawImage(ResourceLoader.getLoader().getImage(filename), enemyPos.x - enemyWidth / 2, 
                    enemyPos.y - enemyHeight / 2, enemyWidth, enemyHeight, null);   

        if(this instanceof EnemyLvLOne)
            g.drawImage(ResourceLoader.getLoader().getImage("EnemyThrusters.png"), enemyPos.x - 18,
                        enemyPos.y - 43, 40, 20, null);
        else if(this instanceof EnemyLvLTwo)
            g.drawImage(ResourceLoader.getLoader().getImage("EnemyLvL2Thrusters.png"), enemyPos.x - 18,
                        enemyPos.y - 43, 40, 20, null);

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

    public void setSpeedY(int speedY)
    {
        enemySpeedY = speedY;
    }

    public void setSpeedX(int speedX)
    {
        enemySpeedX = speedX;
    }

    public int getSpeedX()
    {
        return enemySpeedX;
    }

    public int getSpeedY()
    {
        return enemySpeedY;
    }

    public void resetEnemySpeed()
    {
        enemySpeedX = 1;
        enemySpeedY = 2;
    }

    public void preventEnemyShooting()
    {
        shootStatus = false;
    }
}
