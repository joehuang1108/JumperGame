
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet {
    int x;
    int y;
    int width = 6;
    int height = 12;
    int speed = 8;

    public Bullet(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void update(){
        y -= speed;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);
    }

}
