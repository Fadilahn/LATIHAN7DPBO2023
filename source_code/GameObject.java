/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package synchronization;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Satria Ramadhani
 */
public class GameObject implements GameInterface
{
    /**
     * Attribute declaration.
     */
    
    protected int x, y;          // Position.
    protected int width, height; // Dimension.
    protected int velX, velY;    // Velocity.
    protected String type;       // Object type.
    
    protected final int grafity = 1;
    
    /**
     * Constructor.
     */
    
    // Default constructor.
    public GameObject()
    {
        this.x = 0;
        this.y = 0;
        this.type = "";
    }
    
    // Constructor with object coordinate.
    public GameObject(int x, int y, String type)
    {
        this.x = x;
        this.y = y;
        this.type = type;
    }
    
    // Constructor with object coordinate and shape.
    public GameObject(int x, int y, int width, int height, String type)
    {
        this.x = x; this.y = y;
        this.width = width; this.height = height;
        this.type = type;
    }
    
    /**
     * Getter and Setter.
     */

    /* Object X position. */
    
    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    /* Object Y position. */
    
    public int getY()
    {
        return y;
    }
    
    public void setY(int y)
    {
        this.y = y;
    }
    
    /* Object width. */
    
    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = height;
    }
    
    /* Object height. */
    
    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }
    
    /* Object X velocity. */
    
    public int getVelX()
    {
        return velX;
    }

    public void setVelX(int velX)
    {
        this.velX = velX;
    }

    /* Object Y velocity. */
    
    public int getVelY()
    {
        return velY;
    }

    public void setVelY(int velY)
    {
        this.velY = velY;
    }
    
    /* Object type. */
    
    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    /* Bounds */

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    /**
     * Override interface (unused, only to avoid error).
     */
    
    @Override
    public void render(Graphics object)
    {
        
    }
    
    @Override
    public void loop()
    {
        
    }
}
