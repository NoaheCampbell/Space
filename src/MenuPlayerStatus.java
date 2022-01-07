import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;

public class MenuPlayerStatus extends Menu
{
    
    public MenuPlayerStatus(GameState state)
    {
        this.state = state;
    }

    public void draw(Graphics g) 
    {
        g.setColor(Color.BLACK);    
        g.fillRect(0, 0, 140, 160);
        g.setColor(Color.WHITE);

        g.drawString("Follow Mouse: " + state.getFollowMouse(), 20, 25);
        g.drawString("Player Score: " + state.getPlayer().getScore(), 20, 65);
        g.drawString("Player Health: " + state.getPlayer().getHealth(), 20, 105);
        g.drawString("Player Shields: " + state.getPlayer().getShields(), 20, 145);

    }

    public void update() 
    {
        
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
