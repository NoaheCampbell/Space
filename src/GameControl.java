import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.awt.Point;  

public class GameControl implements Runnable, ActionListener
{
    private GameState state;
    private GameView view;
    private LevelOneShip player;
    private LevelOneEnemy enemy;

    public void run() 
    {
        state = new GameState(player, enemy);
        view = new GameView(state);

        int randomXCoord = (int)(Math.random() * 800);

        try 
        {
            player = new LevelOneShip(state, "LevelOneShip.png", 50, 50);
            enemy = new LevelOneEnemy(state, new Point(randomXCoord, 0), 50, 50, "LevelOneEnemy.png");
            state.addEnemyPos(enemy.getPosition());
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }

        state.setPlayer(player);
        state.setEnemy(enemy);
        state.addEnemy(enemy);

        state.addObject(player);
        state.addObject(enemy);

        Timer timer = new Timer(17, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e)
    {
        state.updateAll();

        state.setMouseClicked(false);

        view.repaint();  

    }

    
}
