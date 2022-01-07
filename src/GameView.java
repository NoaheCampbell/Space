import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Point;

public class GameView extends JPanel implements KeyListener, MouseListener, MouseMotionListener
{
    private GameState state;
    JFrame frame;
    JPanel panel;
    Dimension size;

    public GameView(GameState state)
    {
        this.state = state;
        frame = new JFrame("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = this;
        frame.setContentPane(panel);

        size = new Dimension(800, 800);
        this.setMaximumSize(size);
        this.setMinimumSize(size);
        this.setPreferredSize(size);

        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        addKeyListener(this);
        setFocusable(true); 

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void paint(Graphics g)
    {
        state.drawAll(g);
    }

    public void keyTyped(KeyEvent e) 
    {
        
    }

    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A)
        {
            state.movePlayerLeft();
        }
        else if(keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D)
        {
            state.movePlayerRight();
        }
        else if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W)
        {
            state.movePlayerUp();
        }
        else if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S)
        {
            state.movePlayerDown();
        }
        else if((keyCode == KeyEvent.VK_SPACE) && state.getPlayer().getPosition().x > 165 && state.getPlayer().getPosition().y > 185)
        {
            state.changeShootStatus(true);
        }
    }

    public void keyReleased(KeyEvent e) 
    {
        
    }

    
    public void mouseDragged(MouseEvent e) 
    {
        
    }

    
    public void mouseMoved(MouseEvent e) 
    {
        Point currentMousePos = e.getPoint();
        state.setCurrentMousePos(currentMousePos);
    }

    
    public void mouseClicked(MouseEvent e) 
    {
        
    }

    
    public void mousePressed(MouseEvent e) 
    {
        Point mousePos = new Point(e.getX(), e.getY());
        state.setClickedMousePos(mousePos);
        state.setMouseClicked(true);

        
        if(state.getCurrentMousePos().x > 165 && state.getCurrentMousePos().y > 185)
        {
            state.changeShootStatus(true);
        }
    }

    
    public void mouseReleased(MouseEvent e) 
    {
        
    }

    
    public void mouseEntered(MouseEvent e) 
    {
        
    }

    
    public void mouseExited(MouseEvent e) 
    { 
        
    }

}
