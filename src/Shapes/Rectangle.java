package Shapes;

import org.lwjgl.util.vector.Vector2f;

public class Rectangle {
	protected Vector2f Position;
	protected int Width, Height;
	
	public Vector2f getPosition()	{	return this.Position;	}
	public int getWidth()			{	return this.Width;		}
	public int getHeight()			{	return this.Height;		}
	
	public void setY(float y)				{	this.Position.y	= y;	}
	public void setX(float x)				{	this.Position.x = x;	}
	public void setPosition(Vector2f pos)	{	this.Position = pos;	}
	public void setWidth(int width)			{	this.Width = width;		}
	public void setHeight(int Height)		{	this.Height = Height;	}
	
	public Rectangle(Vector2f Pos, int width, int height)
	{
		this.Position = Pos;
		this.Width = width;
		this.Height = height;
	}
	
	public Rectangle(int x, int y, int width, int height)
	{
		this.Position = new Vector2f(x, y);
		this.Height = height;
		this.Width = width;
	}
	
	
	
	
}
