/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package synchronization;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

/**
 *
 * @author Satria Ramadhani
 */

public class Game extends Canvas implements Runnable
{
    /**
     * 
     * Attribute declaration.
     */
    
    /* View-related attributes. */
    public static final int width = 900;
    public static final int height = 540;
    private Display display;
    
    /* Process-related attributes. */
    private boolean running;
    private Handler handler;
    private Thread thread;
    
    /* Animation-related attributes. */
    private boolean startCounting = false;
    private int score = 0;
    private int oldScore = 0;
    private int coin = 0;
    private int pointDelay = 0;
    private int counter = 0;
    private int stateCounter = 0;
    private int direction = 0;
    
    // Default constructor.
    public Game()
    {
        try
        {
            // Initialize display.
            display = new Display(width, height, "Synchronization Tutorial");
            display.open(this); 
            
            // Initialize game handler.
            handler = new Handler();
            
            // Initialize controller (keyboard input).
            this.setFocusable(true);
            this.requestFocus();
            this.addKeyListener(new Controller(this, handler));
            
            // Initialize all object.
            running = true;
            if(running)
            {
                handler.add(new Player(320, 160));
                handler.add(new Coin((int) (Math.random() * width), (int) (Math.random() * height)));
            }
        } catch(Exception e)
        {
            System.err.println("Failed to instance data.");
        }
    }
    
    /**
     * 
     * Getter and Setter.
     */
    
    /* Game running status. */
    
    public boolean isRunning()
    {
        return running;
    }

    public void setRunning(boolean running)
    {
        this.running = running;
    }
    
    /* Game score. */
    
    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score += score;
    }
    
    /* Game coin. */
    
    public int getCoin(){
        return coin;
    }
    
    public void setCoin(int coin){
        this.coin += coin;
    }
    
    /**
     * 
     * Public methods.
     */
    
    // Clamp, so player won't get offset the display bound.
    public static int clamp(int var, int min, int max)
    {
        if(var >= max)
        {
            return var = max;
        }
        else if(var <= min)
        {
            return var = min;
        }
        
        return var;
    }
    
    // Close display.
    public void close()
    {
        display.close();
    }
    
    /**
     * 
     * Game controller.
     */
    
    // Start threading.
    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start(); 
        
        running = true;
    }
    
    // Stop threading.
    public synchronized void stop()
    {
        try
        {
            thread.join();
            running = false;
        }
        catch(InterruptedException e)
        {
            System.out.println("Thread error : " + e.getMessage());
        }
    }
    
    // Initialize game when it run for the first time.
    public void render()
    {
        // Use buffer strategy.
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
            this.createBufferStrategy(SOMEBITS);
            return;
        }
        
        // Initialize graphics.
        Graphics g = bs.getDrawGraphics();
        Image bg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/background2.png"));
        g.drawImage(bg, 0, 0, null);
        
        
        if(running == true)
        {
            // Render handler.
            handler.render(g);
            
            // Render score.
            Font oldFont = g.getFont();
            Font newFont = oldFont.deriveFont(oldFont.getSize() * 1.3f).deriveFont(Font.BOLD);
            g.setFont(newFont);
            
            if(oldScore != score){
                if(score - oldScore > 0){
                    g.setColor(Color.GREEN);
                }
                else{
                    g.setColor(Color.RED);
                }      
            }
            else if(pointDelay > 10){
                g.setColor(Color.BLACK);
            }
            g.drawString("Score : " + Integer.toString(score), 20, 30);
            
            g.setColor(Color.BLACK);
            g.drawString("Coin : " + Integer.toString(coin), 150, 30);
        
        }
        
        // Loop the process so it seems like "FPS".
        g.dispose();
        bs.show();
    }

    // colision detection
    public void checkCollision(GameObject player) {
        // search in handle
        for (int i = 0; i < handler.count(); i++) {
            GameObject obj = handler.get(i);
            
            // check object is not player
            if (!(obj instanceof Player)) {
                
                // check collision player with object
                if (player.getBounds().intersects(obj.getBounds())) {
                    
                    // collision detected
                    if (obj instanceof Pipe) {
                        // do something if player collides with a pipe
                        this.setScore(-1);
                        ((Player) player).sumGotPoint(-1);;

                    } else if (obj instanceof Coin) {
                        // do something if player collides with a coin
                        this.setScore(+10);
                        this.setCoin(+1);
                        ((Player) player).sumGotPoint(+10);;
                        System.out.println("+"+10);

                        // set random position
                        obj.setX((int) (Math.random() * width));
                        obj.setY((int) (Math.random() * height));
                    }
                    
                    this.pointDelay = 0;
                }
            }
        }
    }

    // Main loop proccess.
    public void loop()
    {
        GameObject object = null;
        oldScore = score;
        
        handler.loop();
        if(this.running)
        {   
            counter++;
            pointDelay++;
            if(startCounting)
            {
                stateCounter++;
            }
            
            if(stateCounter >= 40)
            {
                stateCounter = 0;
                startCounting = false;
            }
            
            if(counter >= 80)
            {
                // add some pipe
                int ran = (int)(Math.random() * (460 - 200 + 1)) + 200;
                handler.add(new Pipe(900, ran, "/assets/pipe1.png"));
                handler.add(new Pipe(900, ran - 460, "/assets/pipe2.png"));
                
                direction = (direction == 0) ? 1 : 0;
                counter = 0;
            }
            
            for(int i = 0; i < handler.count(); i++)
            {
                if(handler.get(i).getType().equals("Player")){
                    object = handler.get(i);
                    
                    // set got point with delay
                    if(pointDelay > 20){
                        ((Player) object).setGotPoint(0);;
                    }
                    
                    // check collision
                    this.checkCollision(object);
                }
                else if(handler.get(i).getType().equals("Pipe"))
                {
                    object = handler.get(i);
                    
                    // move pipe if out of frame
                    if(object.getX() < -50){
                        handler.remove(object);
                    }
                }
            }
        }
    }
    
    /**
     * 
     * Override interface.
     */
    
    @Override
    public void run()
    {
        double fps = 60.0;
        double ns = (1000000000 / fps);
        double delta = 0;
        
        // Timer attributes.
        long time = System.nanoTime();
        long now = 0;
        long timer = System.currentTimeMillis();
        
        int frames = 0;
        while(running)
        {
            now = System.nanoTime();
            delta += ((now - time) / ns);
            time = now;
            
            while(delta > 1)
            {
                loop();
                delta--;
            }
            
            if(running)
            {
                render();
                frames++;
            }
            
            if((System.currentTimeMillis() - timer) > 1000)
            {
                timer += 1000;
                frames = 0;
            }
        }
        
        stop();
    }
}
