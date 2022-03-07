import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;

public class MenuGameOver extends Menu
{

    public MenuGameOver(GameState state)
    {
        this.state = state;
    }
    
    public void draw(Graphics g) 
    {
        if(state.getGameOver())
        {
            g.setColor(Color.WHITE);
            g.fillRect(385, 333, 100, 25);
            g.setColor(Color.BLACK);
            g.drawString("Game Over", 400, 350);

            g.setColor(Color.WHITE);
            g.fillRect(385, 383, 100, 25);
            g.setColor(Color.BLACK);
            g.drawString("Retry", 420, 400);
        }
    }

    
    public void update() 
    {


        if(state.getGameOver() && (state.getMouseClicked() && state.getClickedMousePos().x >= 385 && state.getClickedMousePos().x <= 485 &&
           state.getClickedMousePos().y >= 383 && state.getClickedMousePos().y <= 433))
        {
            state.restartGame();
        }
    }

    
    public Point getPosition() 
    {
        return null;
    }

    
    public int getWidth() 
    {
        return 0;
    }

    
    public int getHeight() 
    {
        return 0;
    }

    
    public void changeHealth(int bulletDamage) 
    {
        
    }
    
}
