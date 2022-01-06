import java.awt.Graphics;
import java.awt.Point;

public class Bullet implements OnScreen
{
    private Point bulletPos;
    private int bulletSpeed;
    private int bulletDamage;
    private OnScreen parent;
    private GameState state;
    private String side;
    
    public Bullet(Point bulletPos, int bulletSpeed, int bulletDamage, OnScreen parent, GameState state)
    {
        this.bulletPos = bulletPos;
        this.bulletSpeed = bulletSpeed;
        this.bulletDamage = bulletDamage;
        this.parent = parent;
        this.state = state;
        side = "left";
    }

    public void update() 
    {
        if(bulletPos.y > 800 || bulletPos.y < 0)
        {
            state.removeObject(this);
        }

        if(parent instanceof Ship)
        {
            bulletPos.y -= bulletSpeed;
        }
        else if(parent instanceof Enemy)
        {
            bulletPos.y += bulletSpeed;
        }
    }

    public void draw(Graphics g) 
    {
        if(parent instanceof Ship)
        {
            if(side.equals("left"))
            {
                g.fillRect(bulletPos.x + 10, bulletPos.y - 40, 10, 20);
                state.changeShootStatus(false);
            }
            else if(side.equals("right"))
            {
                g.fillRect(bulletPos.x - 20, bulletPos.y - 40, 10, 20);
                state.changeShootStatus(false);
            }
        }
        else
        {
            g.fillRect(bulletPos.x, bulletPos.y + 25, 10, 20);
            state.changeShootStatus(false);
        }
    }

    public int getBulletSpeed()
    {
        return bulletSpeed;
    }

    public int getBulletDamage()
    {
        return bulletDamage;
    }

    public void setBulletPos(Point bulletPos)
    {
        this.bulletPos = bulletPos;
    }

    public void setBulletSpeed(int bulletSpeed)
    {
        this.bulletSpeed = bulletSpeed;
    }

    public void setBulletDamage(int bulletDamage)
    {
        this.bulletDamage = bulletDamage;
    }

    public Point getPosition() 
    {
        return bulletPos;
    }

    public String getSide()
    {
        return side;
    }

    public void setSide(String side)
    {
        this.side = side;
    }
    
}
