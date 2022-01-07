import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Bullet implements OnScreen
{
    private Point bulletPos;
    private int bulletSpeed;
    private int bulletDamage;
    private Ship player;
    private OnScreen parent;
    private GameState state;
    private String side;
    private ArrayList<Enemy> enemies;
    private int width;
    private int height;
    
    public Bullet(Point bulletPos, int bulletDamage, OnScreen parent, GameState state)
    {
        this.bulletPos = bulletPos;
        bulletSpeed = 10;
        this.bulletDamage = bulletDamage;
        this.state = state;
        side = "left";
        this.parent = parent;
        player = state.getPlayer();
        width = 10;
        height = 20;
    }

    public void update() 
    {
        enemies = state.getEnemies();
        if(bulletPos.y > 800 || bulletPos.y < 0)
        {
            state.removeObject(this);
        }

        if(parent instanceof Enemy)
        {
            bulletPos.y += bulletSpeed;
        }
        else
        {
            bulletPos.y -= bulletSpeed;
        }

        // Checks to see if the bullet is colliding with the player
        if(parent instanceof Enemy)
        {
            if((bulletPos.x >= (player.getPosition().x - player.getWidth() / 2) && bulletPos.y >= (player.getPosition().y - player.getHeight() / 2)) &&
                bulletPos.x <= (player.getPosition().x + player.getWidth() / 2) && bulletPos.y <= (player.getPosition().y + player.getHeight() / 2))
            {
                if(state.playerHasShield())
                {
                    state.getPlayer().changeShields(-bulletDamage);
                    state.removeObject(this);
                }
                else
                {
                    state.getPlayer().changeHealth(-bulletDamage);
                    state.removeObject(this);
                }
            }
        }
        // Checks to see if the bullet is colliding with the enemy
        else 
        {
            for(int i = 0; i < enemies.size(); i++)
            {
                if(isCollision(enemies.get(i).getPosition(), enemies.get(i).getWidth(), enemies.get(i).getHeight()))
                {
                    if(enemies.get(i).getShields() >= 0)
                    {
                        enemies.get(i).changeShields(-bulletDamage);
                        state.removeObject(this);
                    }
                    else
                    {
                        enemies.get(i).changeHealth(-bulletDamage);
                        state.removeObject(this);
                    }
                }
            }
        }
    }
    

    public void draw(Graphics g) 
    {
        if(parent instanceof Ship)
        {
            if(side.equals("left"))
            {
                g.fillRect(bulletPos.x + 10, bulletPos.y - 40, width, height);
                state.changeShootStatus(false);
            }
            else if(side.equals("right"))
            {
                g.fillRect(bulletPos.x - 20, bulletPos.y - 40, width, height);
                state.changeShootStatus(false);
            }
        }
        else
        {
            g.fillRect(bulletPos.x, bulletPos.y + 25, width, height);
            state.changeShootStatus(false);
        }
    }

    public Boolean isCollision(Point point, int width, int height)
    {
        if(point.x >= ((bulletPos.x - this.width / 2) - width / 2) && point.y >= ((bulletPos.y - this.height / 2) - height / 2) &&
            point.x <= ((bulletPos.x + this.width / 2) + width / 2) && point.y <= ((bulletPos.y + this.height / 2) + height / 2))
        {
            return true;
        }
        else
        {
            return false;
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

    public int getWidth()
    {
        return width;
    }

    public int getHeight() 
    {
        return height;
    }

    public void changeHealth(int bulletDamage) 
    {
        
    }
    
}
