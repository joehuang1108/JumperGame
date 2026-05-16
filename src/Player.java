
// This class creates and handles player's action/movements

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

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

    private Image idleSprite;
    private Image leftSprite;
    private Image rightSprite;
    private Image jumpSprite;

    public Player(int x, int y){
        this.x = x;
        this.y = y;

        idleSprite = new ImageIcon("assets/bunny-left.png").getImage();
        leftSprite = new ImageIcon("assets/bunny-left.png").getImage();
        rightSprite = new ImageIcon("assets/bunny-right.png").getImage();
        jumpSprite = new ImageIcon("assets/bunny-puca.png").getImage();
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

    public Image getCurrentSprite(){
        if(dy < 0){
            return jumpSprite;
        }
        if(left){
            return leftSprite;
        }
        if(right){
            return rightSprite;
        }
        return idleSprite;
    }

    public void draw(Graphics g){
        g.drawImage(getCurrentSprite(), x, y, width, height, null);
    }
}

