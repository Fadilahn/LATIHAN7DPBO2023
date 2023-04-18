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
public class Pipe extends GameObject
{
    // atribute
    private String image;
    private int speed = 4;

    /**
     * Constructor.
     */
    
    // Default constructor.
    public Pipe()
    {
        super(0, 0, "Pipe");
        super.setHeight(30);
    }
    
    // Constructor with player position.
    public Pipe(int x, int y)
    {
        super(x, y, "Pipe");
        super.setHeight(30);
    }
    
    // Constructor with player position and image.
    public Pipe(int x, int y, String image)
    {
        super(x, y, "Pipe");
        super.setHeight(30);
        this.image = image;
    }

    /**
     * Method
     */
    
    public int getSpeed(){
        return speed;
    }
    
    public void setSpeed(int speed){
        this.speed = speed;
    }
    
    /**
     * Override interface.
     */
    
    @Override
    public void render(Graphics object)
    {
        // set object with image 
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource(this.image));
        object.drawImage(image, x, y,null);

        // set width dan height dari gambar
        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    
    @Override
    public void loop()
    {
        // Initialize velocity, so object can move.
        this.x -= speed;

    }
}
