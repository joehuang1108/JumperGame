import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Enemy {
    int x;
    int y;
    int width = 40;
    int height = 40;
    int dy = 2;

    private Image sprite;
    private Platform platform;
    private boolean stationary = false;

    public Enemy(int x, int y){
        this.x = x;
        this.y = y;
        sprite = new ImageIcon("assets/lik-puca.png").getImage();
    }

    public Enemy(Platform platform){
        this.platform = platform;
        this.stationary = true;
        this.x = platform.x + (platform.width - width) / 2;
        this.y = platform.y - height;
        this.sprite = new ImageIcon("assets/lik-puca.png").getImage();
    }

    public void update(){
        if (stationary && platform != null) {
            x = platform.x + (platform.width - width) / 2;
            y = platform.y - height;
        } else {
            y += dy;
        }
    }

    public boolean isStationary(){
        return stationary;
    }

    public Platform getPlatform(){
        return platform;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g){
        g.drawImage(sprite, x, y, width, height, null);
    }
}