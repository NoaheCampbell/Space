import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;

public class MenuPlayerStatus extends Menu
{
    
    private double timeElapsed;

    public MenuPlayerStatus(GameState state)
    {
        this.state = state;
    }

    public void draw(Graphics g) 
    {
        g.setColor(Color.WHITE);

        g.drawString("Follow Mouse: " + state.getFollowMouse(), 20, 25);
        g.drawString("Player Score: " + state.getPlayer().getScore(), 20, 65);
        g.drawString("Player Health: " + state.getPlayer().getHealth(), 20, 105);
        g.drawString("Player Shields: " + state.getPlayer().getShields(), 20, 145);
        drawTimer(g);
    }

    public void update() 
    {
        if(!state.getGameOver())
            timeElapsed = state.getTimeElapsed();
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
    
    public void drawTimer(Graphics g)
    {
        if((int)timeElapsed < 60)
        {
            if((int)timeElapsed < 10)
            {
                g.drawString("Timer: 0:0" + (int)timeElapsed, 20, 185);
            }
            else
            {
                g.drawString("Timer: 0:" + (int)timeElapsed, 20, 185);
            }
        }
        else
        {
            if(timeElapsed - (timeElapsed - (timeElapsed % 60)) < 10)
            {
                g.drawString("Timer: " + (int)(timeElapsed / 60) + ":0" + (int)(timeElapsed - (timeElapsed - (timeElapsed % 60))), 20, 185);
            }
            else
            {
                g.drawString("Timer: " + (int)(timeElapsed / 60) + ":" + (int)(timeElapsed - (timeElapsed - (timeElapsed % 60))), 20, 185);
            }
        }
    }

}
