import java.awt.Point;

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
