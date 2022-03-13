import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Point;
import java.io.FileNotFoundException;

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
    private double timeElapsed;
    private Boolean gameOver;
    private MenuGameOver menuGameOver;
    private int enemyCount;

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
        timeElapsed = 0;
        gameOver = false;
        menuGameOver = new MenuGameOver(this);
        enemyCount = 0;
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

        if(!gameOver)
        {
            int randInt = (int)(Math.random() * 5000);

            if((int)timeElapsed % 60 == 0 && timeElapsed > 1 && enemyCount < 4)
            {
                int randomXCoord = (int)(Math.random() * 800);
                addObject(new EnemyLvLTwo(this, new Point(randomXCoord, 0), 60, 100, "EnemyLvlTwo.png"));
                enemyCount++;
            }

            if(randInt < 60)
            {
                int randomXCoord = (int)(Math.random() * 800);
                if(randomXCoord > 190 && randomXCoord < 770)
                {
                    setEnemy(new EnemyLvLOne(this, new Point(randomXCoord, 0), enemy.getWidth(),
                    enemy.getHeight(), "LevelOneEnemy.png"));
                    randInt = (int)(Math.random() * 100);
                    if(randInt < 49)
                    {
                        enemy.changeSpeedX(-enemy.getSpeedX());
                    }
                    addEnemy(enemy);
                    addObject(enemy);
                }
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

        timeElapsed += 0.017;
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

    public double getTimeElapsed()
    {
        return timeElapsed;
    }

    public Boolean enemyHasShields()
    {
        return enemy.getShields() > 0;
    }

    public void setGameOver(Boolean gameOver)
    {
        this.gameOver = gameOver;
        if(gameOver)
        {
            player.setPlayerSpeed(0);
            enemy.setSpeedX(0);
            enemy.setSpeedY(0);
            addObject(menuGameOver);
        }
    }

    public void restartGame()
    {
        gameOver = false;
        try 
        {
            player = new ShipLvLOne(this, "LevelOneShip.png", 50, 50);
        } catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }

        removeObject(menuGameOver);
        addObject(player);
        setPlayer(player);
        enemy.resetEnemySpeed();
        enemy.setShields(3);
        timeElapsed = 0;
    }
    
    public Boolean getGameOver()
    {
        return gameOver;
    }

    public void removeAllEnemies()
    {
        for(OnScreen object : gameObjects)
        {
            if(object instanceof Enemy)
            {
                objectsToRemove.add(object);
            }
        }
    }

    public int getPlayerScore()
    {
        return player.getScore();
    }
 }
