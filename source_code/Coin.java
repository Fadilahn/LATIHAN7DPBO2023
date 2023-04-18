/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package synchronization;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author Satria Ramadhani
 */
public class Coin extends GameObject
{

    /**
     * Constructor.
     */
    
    // Default constructor.
    public Coin()
    {
        super(0, 0, "Coin");
        super.setHeight(30);
    }
    
    // Constructor with player position.
    public Coin(int x, int y)
    {
        super(x, y, "Coin");
        super.setHeight(15);
    }

    /**
     * Method
     */

    
    /**
     * Override interface.
     */
    
    @Override
    public void render(Graphics object)
    {
        // set width dan height
        width = 30;
        height = 30;
        
        // set object with image
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/upi-logo.png"));
        object.drawImage(image, x, y, width, height, null);

    }
    
    @Override
    public void loop()
    {
        // Initialize velocity, so object can move.
        this.x += this.velX;
        this.y += this.velY;

        // Initialize player bound, so it won't get offset the display.
        x = Game.clamp(x, 0, (Game.width - 50));
        y = Game.clamp(y, 0, (Game.height - 70));
    }
}
