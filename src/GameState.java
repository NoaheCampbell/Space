import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Point;

public class GameState 
{
    private ArrayList<OnScreen> gameObjects;
    private ArrayList<OnScreen> objectsToAdd;
    private ArrayList<OnScreen> objectsToRemove;
    private ArrayList<Enemy> enemies;
    private ArrayList<Enemy> enemiesToAdd;
    private ArrayList<Enemy> enemiesToRemove;
    private ShipLvLOne player;
    private EnemyLvLOne enemy;
    private Boolean mouseClicked;
    private Boolean followMouse;
    private Point clickedMousePos;
    private Point currentMousePos;

    public GameState(ShipLvLOne player, EnemyLvLOne enemy)
    {
        gameObjects = new ArrayList<OnScreen>();
        objectsToAdd = new ArrayList<OnScreen>();
        objectsToRemove = new ArrayList<OnScreen>();
        enemies = new ArrayList<Enemy>();
        enemiesToAdd = new ArrayList<Enemy>();
        enemiesToRemove = new ArrayList<Enemy>();
        this.player = player;
        this.enemy = enemy;
        mouseClicked = false;
        followMouse = false;
        clickedMousePos = new Point(0, 0);
        currentMousePos = new Point(0, 0);
    }

    public void addObject(OnScreen object)
    {
        objectsToAdd.add(object);
    }

    public void removeObject(OnScreen object)
    {
        objectsToRemove.add(object);
    }

    public void updateAll()
    {
        for (OnScreen object : gameObjects)
        {
            object.update();
        }

        int randInt = (int)(Math.random() * 5000);

        if(randInt < 49)
        {
            int randomXCoord = (int)(Math.random() * 800);
            if(randomXCoord > 185)
            {
                setEnemy(new EnemyLvLOne(this, new Point(randomXCoord, 0), enemy.getWidth(),
                enemy.getHeight(), "LevelOneEnemy.png"));
                addEnemy(enemy);
                addObject(enemy);
            }
        }
        
        if((mouseClicked && (clickedMousePos.x < 150 && clickedMousePos.x > 10) && 
          (clickedMousePos.y > 15 && clickedMousePos.y < 55)))
        {
            followMouse = !followMouse; 
            mouseClicked = false;
        }

        gameObjects.addAll(objectsToAdd);
        objectsToAdd.clear();

        gameObjects.removeAll(objectsToRemove);
        objectsToRemove.clear();

        enemies.addAll(enemiesToAdd);
        enemiesToAdd.clear();

        enemies.removeAll(enemiesToRemove);
        enemiesToRemove.clear();

    }

    public void drawAll(Graphics g)
    {
        for (OnScreen object : gameObjects)
        {
            object.draw(g);
        }
    }

    public void addPlayer(ShipLvLOne player)
    {
        this.player = player;
    }

    public Ship getPlayer()
    {
        return player;
    }

    public void movePlayerLeft()
    {
        player.moveLeft();
    }

    public void movePlayerRight()
    {
        player.moveRight();
    }

    public void movePlayerUp()
    {
        player.moveUp();
    }

    public void movePlayerDown()
    {
        player.moveDown();
    }

    public void changePlayerScore(int score)
    {
        player.changeScore(score);
    }

    public void changePlayerSpeed(int speed)
    {
        player.changeSpeed(speed);
    }

    public void changeShootStatus(Boolean newStatus)
    {
        player.changeShootStatus(newStatus);
    }

    public void setPlayer(ShipLvLOne player)
    {
        this.player = player;
    }

    public void setMouseClicked(Boolean mouseClicked)
    {
        this.mouseClicked = mouseClicked;
    }

    public Boolean getMouseClicked()
    {
        return mouseClicked;
    }   

    public void setFollowMouse(Boolean followMouse)
    {
        this.followMouse = followMouse;
    }

    public Boolean getFollowMouse()
    {
        return followMouse;
    }

    public Boolean playerHasShield()
    {
       return player.getShields() > 0;
    }

    public void setClickedMousePos(Point mousePos)
    {
        this.clickedMousePos = mousePos;
    }

    public Point getClickedMousePos()
    {
        return clickedMousePos;
    }

    public void setCurrentMousePos(Point currentMousePos)
    {
        this.currentMousePos = currentMousePos;
    }

    public Point getCurrentMousePos()
    {
        return currentMousePos;
    }

    public EnemyLvLOne getEnemy()
    {
        return enemy;
    }

    public void setEnemy(EnemyLvLOne enemy)
    {
        this.enemy = enemy;
    }

    public void addEnemy(Enemy enemy)
    {
        enemiesToAdd.add(enemy);
    }

    public void removeEnemy(Enemy enemy)
    {
        enemiesToRemove.add(enemy);
    }

    public ArrayList<Enemy> getEnemies()
    {
        return enemies;
    }
 }
