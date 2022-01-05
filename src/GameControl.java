import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class GameControl implements Runnable, ActionListener
{
    private GameState state;
    private GameView view;
    private LevelOneShip player;

    public void run() 
    {
        state = new GameState(player);
        view = new GameView(state);

        try 
        {
            player = new LevelOneShip(state);
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }

        state.setLevelOne(player);

        state.addObject(player);

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
