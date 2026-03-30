import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;


// Class that handles the entire game logic/panel
public class GamePanel extends JPanel implements ActionListener, KeyListener{
    
    private Timer timer;
    private Player player;
    private ArrayList<Platform> platforms;

    private final int PANEL_WIDTH = 400;
    private final int PANEL_HEIGHT = 600;

    public GamePanel(){
        setFocusable(true);
        addKeyListener(this);
        player = new Player(PANEL_WIDTH/2 - 20, PANEL_HEIGHT - 100);
        platforms = new ArrayList<>();
        initPlatforms();
        timer = new Timer(15, this);
    }

    public void startGame(){
        timer.start();
    }

    private void initPlatforms(){
        // create 10 random platforms to spawn on screen
        // first platform MUST BE directly under the player
        // incremental height for the platforms to spawn
        Random rand = new Random();
        // first platform directly under player
        platforms.add(new Platform(player.x - 10, player.y + player.height + 10)); 
        for (int i = 1; i < 10; i++){
            int x = rand.nextInt(PANEL_WIDTH - 60);
            int y = PANEL_HEIGHT - i * 60;
            platforms.add(new Platform(x, y));
        }
    }

    private void checkPlatformCollsion(){
        // everytime player collides with platform,
        // perform a jump
        if(player.dy > 0){
            for(Platform p : platforms){
                if (player.getBounds().intersects(p.getBounds())){
                    player.jump();
                }
            }
        }
    }




    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Color.WHITE);

        // draws the player
        player.draw(g);

        // draws the platforms
        g.setColor(Color.GREEN);
        for (Platform p : platforms){
            p.draw(g);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();
        checkPlatformCollsion();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) player.left = true;
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) player.right = true;
        // testing jump
        // if(e.getKeyCode() == KeyEvent.VK_SPACE) player.jump();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) player.left = false;
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) player.right = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}