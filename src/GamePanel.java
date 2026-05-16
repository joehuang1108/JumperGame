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
    private ArrayList<Enemy> enemies;
    private boolean gameOver = false;
    private int score = 0;
    private int worldHeight = 0;

    private final int PANEL_WIDTH = 400;
    private final int PANEL_HEIGHT = 600;
    private Image backgroundImage = new ImageIcon("assets/bck.png").getImage();


    public GamePanel(){
        setFocusable(true);
        addKeyListener(this);
        player = new Player(PANEL_WIDTH/2 - 20, PANEL_HEIGHT - 100);
        platforms = new ArrayList<>();
        enemies = new ArrayList<>();
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

    private void updatePlatforms(){
        // Scroll platform downward indefinitely
        // Scroll platform downward if player is above half screen
        // Create new platforms while removing old platforms
        // Remove off-screen platforms and add new ones while maintaining 10 platforms at all times

        // PART 1
        // get player.y --> determines when to start scrolling
        // move platform based on player's change of placement
        if(player.y < PANEL_HEIGHT / 2){
            int dy = (PANEL_HEIGHT / 2) - player.y;
            player.y = PANEL_HEIGHT / 2;
            worldHeight += dy;

            for(Platform p : platforms){
                p.y += dy;
            }
            // PART 2
            // check for platform's position relative to screen height  
            // remove if off-screen (position > PANEL_HEIGHT)
            // while loop to check for platforms.size()
            // add more platforms until 10
            platforms.removeIf(p -> p.y > PANEL_HEIGHT); // this removes all platforms that's off screen
            Random rand = new Random();
            while (platforms.size() < 10){
                int x = rand.nextInt(PANEL_WIDTH - 60);
                int y = rand.nextInt(50);
                platforms.add(new Platform(x, y));
            }

            // Occasionally spawn enemies
            if(rand.nextInt(100) < 3){
                int x = rand.nextInt(PANEL_WIDTH - 40);
                enemies.add(new Enemy(x, -20));
            }
        }
    }

    private void updateEnemies(){
        for(Enemy en : enemies){
            en.update();
        }
        enemies.removeIf(en -> en.y > PANEL_HEIGHT);
    }

    private void checkEnemyCollision(){
        // Check for collisions, and update win/lose logic
        for(Enemy en : enemies){
            if(player.getBounds().intersects(en.getBounds())){
                gameOver = true;
            }
        }
    }

    private void checkFallOffScreen(){
        // Simple check to see if player fall below the screen
        // If lost --> gameOver variable = true
        if(player.y > PANEL_HEIGHT){
            gameOver = true;
        }
    }

    // NEXT STEPS:
    // Update blocks with sprites
    // Features: Adding bullet to shoot enemy
    // Features: Moving platforms (left to right)

    private void updateScore() {
        score = worldHeight;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        // Draw background
        g.drawImage(backgroundImage, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, null);

        // draws the player
        player.draw(g);

        // draws the platforms
        for (Platform p : platforms){
            p.draw(g);
        }

        // draws the enemy
        for (Enemy en : enemies){
            en.draw(g);
        }

        // Draw score 
        g.drawString("Score: " + score, 10, 20);

        // Draw Game Over
        if(gameOver){
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.setColor(Color.BLACK);
            g.drawString("GAME OVER", 80, PANEL_HEIGHT / 2);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!gameOver){
            player.update();
            for (Platform p : platforms){
                p.update();
            }
            checkPlatformCollsion();
            checkEnemyCollision();
            updatePlatforms();
            updateEnemies();
            updateScore();
            checkFallOffScreen();
            repaint();
        }
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