
// This class creates and handles player's action/movements

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {
    // x,y coordinates (placement of player on screen)
    // size of player 
    // jumping 
    // gravity
    // speed 
    int x;
    int y;
    int width = 40;
    int height = 40;
    double dy = 0;
    double gravity = 0.6;
    double jumpStrength = -12;
    int speed = 5;
    boolean left = false;
    boolean right = false;

    public Player(int x, int y){
        this.x = x;
        this.y = y;
    }

    // updates placement, dx, dy of player
    public void update(){
        if(left){
            x -= speed;
        }
        if(right){
            x += speed;
        }
        x = Math.max(0, Math.min(400-width, x));

        y += dy;
        dy += gravity;
    }

    public void jump(){
        dy = jumpStrength;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }
}