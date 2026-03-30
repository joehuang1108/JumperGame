
// this is create and handles platforms

import java.awt.Graphics;
import java.awt.Rectangle;

public class Platform {
    int x;
    int y;
    int width = 60;
    int height = 10;

    public Platform(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g){
        g.fillRect(x, y, width, height);
    }
}