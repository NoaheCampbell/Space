import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;

abstract public class Ship implements OnScreen
{
    protected Point playerPos;
    protected int playerHealth;
    protected int playerShields;
    protected int playerScore;
    protected int playerDamage;
    protected int playerSpeed;
    protected boolean shootStatus;
    protected int bulletSpeed;
    protected Point bulletPos;
    protected GameState state;
    protected String filename;
    protected Bullet bullet;


    public void draw(Graphics g) 
    {
        state.drawFollowMouseStatus(g);

        g.setColor(Color.BLACK); 

        g.drawImage(ResourceLoader.getLoader().getImage(filename), playerPos.x - 20, playerPos.y - 60, 40, 120, null);

        Point bulletPos = new Point(playerPos.x, playerPos.y);
        bullet = new Bullet(bulletPos, bulletSpeed, playerDamage, this, state);


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


    public Point getPosition()
    {
        return playerPos;
    }

    public int getHealth()
    {
        return playerHealth;
    }
    
    public int getShields()
    {
        return playerShields;
    }

    public int getScore()
    {
        return playerScore;
    }

    public int getDamage()
    {
        return playerDamage;
    }

    public void setPosition(int x, int y)
    {
        playerPos = new Point(x, y);
    }

    public void changeHealth(int health)
    {
        playerHealth += health;
    }

    public void changeShields(int shields)
    {
        playerShields += shields;
    }

    public void changeScore(int score)
    {
        playerScore += score;
    }

    public void changeDamage(int damage)
    {
        playerDamage += damage;
    }

    public void changeSpeed(int speed)
    {
        playerSpeed += speed;
    }

    public void moveLeft()
    {
        playerPos.x -= playerSpeed;
    }

    public void moveRight()
    {
        playerPos.x += playerSpeed;
    }

    public void moveUp()
    {
        playerPos.y -= playerSpeed;
    }

    public void moveDown()
    {
        playerPos.y += playerSpeed;
    }

    public void changeShootStatus(boolean newStatus)
    {
        shootStatus = newStatus;
    }
}
