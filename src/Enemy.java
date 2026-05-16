import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Enemy {
    // Create enemies that spawns at random from above and falls downward 
    // Create attributes for enemies
    // Create relevant methods
    // Create constructor method
    int x;
    int y;
    int width = 40;
    int height = 40;
    int dy = 2;

    private Image sprite;

    public Enemy(int x, int y){
        this.x = x;
        this.y = y;
        sprite = new ImageIcon("assets/lik-puca.png").getImage();
    }

    public void update(){
        y += dy;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g){
        g.drawImage(sprite, x, y, width, height, null);
    }
}