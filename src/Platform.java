
// this is create and handles platforms
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

public class Platform {
    int x;
    int y;
    int width = 60;
    int height = 10;

    private Image sprite;
    private static final Random RANDOM = new Random();

    // Platform speed
    int dx = 0;

    public Platform(int x, int y){
        this(x, y, true);
    }

    public Platform(int x, int y, boolean canMove){
        this.x = x;
        this.y = y;
        sprite = new ImageIcon("assets/platform.png").getImage();

        // Randomize chances to be a moving platform
        if (canMove && RANDOM.nextInt(100) < 20){
            dx = 2;
        }
    }

    public void update(){
        x += dx;
        // Bounce off edges
        if(x < 0 || x + width > 400){
            dx = -dx;
        }
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g){
        g.drawImage(sprite, x, y, width, height, null);
    }
}