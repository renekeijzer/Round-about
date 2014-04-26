package Shapes;

import org.lwjgl.util.vector.Vector2f;

import util.PointMath;

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
	
	public Rectangle(Vector2f Pos1, Vector2f Pos2){
		float posx;
		float posy;
		if(Pos1.x < Pos2.x){ posx = Pos1.x; }else{ posx = Pos2.x;}
		if(Pos1.y < Pos2.y){ posy = Pos1.y; }else{ posy = Pos2.y;}
		this.Position = new Vector2f(posx, posy);
		//TODO realy convert to int?
		this.Width = (int) PointMath.distanceFloat(Pos1.x, Pos2.x);
		this.Height = (int) PointMath.distanceFloat(Pos1.y, Pos2.y);
	}
	
	
}
