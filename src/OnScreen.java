import java.awt.Graphics;
import java.awt.Point;

public interface OnScreen 
{
    public void draw(Graphics g);

    public void update();

    public Point getPosition();

    public int getWidth();

    public int getHeight();

    public void changeHealth(int bulletDamage);

}
