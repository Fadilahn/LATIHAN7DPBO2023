/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package synchronization;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author Satria Ramadhani
 */
public class Player extends GameObject
{

    // atribute
    private int speed = 0;
    private int gotPoint = 0;
    protected boolean isJumping = false;

    /**
     * Constructor.
     */
    
    // Default constructor.
    public Player()
    {
        super(0, 0, "Player");
        super.setHeight(30);
    }
    
    // Constructor with player position.
    public Player(int x, int y)
    {
        super(x, y, "Player");
        super.setHeight(30);
    }
    
    // Constructor with player position and display size.
    public Player(int x, int y, int width, int height)
    {
        super(x, y, width, height, "Player");
    }
    
    /**
     * Method
     */
    
    /* isJumping */

    public void setJumping(boolean jump){
        this.isJumping = jump;
    }

    public boolean getJumping(){
        return isJumping;
    }

    public void setGotPoint(int point){
        this.gotPoint = point;
    }

    public void sumGotPoint(int point){
        this.gotPoint += point;
    }

    public int getGotPoint(){
        return this.gotPoint;
    }

    
    /**
     * Override interface.
     * @param object
     */
    
    @Override
    public void render(Graphics object)
    {
        // set object with image
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/flappy-bird2.png"));
        object.drawImage(image, x, y, null);
        
        // set width dan height from image
        width = image.getWidth(null);
        height = image.getHeight(null);  
        
        // set font
        Font oldFont = object.getFont();
        Font newFont = oldFont.deriveFont(oldFont.getSize() * 1.2f).deriveFont(Font.BOLD);
//        Font newFont = new Font(null, 10, 10);
        object.setFont(newFont);

        // check and display got point
        if(gotPoint > 0) {
            object.setColor(Color.GREEN);
            object.drawString("+" + gotPoint, x + 10, y - 10);
        }
        else if(gotPoint < 0){
            object.setColor(Color.RED);
            object.drawString("" + gotPoint, x + 10, y - 10);
        }
        
        
    }
    
    @Override
    public void loop()
    {
        // Initialize velocity, so object can move.
        this.x += this.velX;
        
        // check for jumping
        if(this.isJumping == true && this.velY < 0){
            
            // jumping calculate with pos y
            this.velY += grafity;
            
            // set speed if done
            if(this.velY >= 0){
                this.isJumping = false;
                speed = 0;
            }
            
            this.y += this.velY;
        }
        else{
            this.y += speed;
            if(speed <= 10){
                speed += grafity;
            }
        }

        // Initialize player bound, so it won't get offset the display.
        x = Game.clamp(x, 0, (Game.width - 50));
        y = Game.clamp(y, 0, (Game.height - 70));
    }
}
