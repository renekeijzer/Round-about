package Shapes;

import org.lwjgl.util.vector.Vector2f;

import util.Corner;
import util.PointMath;
import util.Vector2i;

import java.util.ArrayList;
import java.util.Collections;

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
	
	/*
	 * Makes the smalst possible Rectangle with both rectangles within
	 */
	public Rectangle(Rectangle r1, Rectangle r2){
		Vector2f points[] = {r1.getPosition(Corner.TopLeft), r1.getPosition(Corner.BottomRight), r2.getPosition(Corner.TopLeft), r2.getPosition(Corner.BottomRight)};
		ArrayList<Float> xlist = new ArrayList<Float>();
		ArrayList<Float> ylist = new ArrayList<Float>();
		for(Vector2f point : points){
			xlist.add(point.x);
			ylist.add(point.y);
		}
		Collections.sort(xlist); //lowest value above
		Collections.sort(ylist); //lowest value above
		//I am only intreseted in the lowest and the highst value
		this.Position = new Vector2f(xlist.get(0), ylist.get(0));
		//TODO realy convert to int?
		this.Width = (int) (xlist.get(3) - xlist.get(0));
		this.Height = (int) (ylist.get(3) - ylist.get(0));
	}
	
	public Vector2f getPosition(Corner courner){
		return PointMath.getPosition(Position, Width, Height, courner);
	}
	
	public Vector2f getBlockRasterPosition(Corner courner){
		return new Vector2f(
				(PointMath.ConvertToBlockRaster(getPosition(courner)).x < 0 ? 
						PointMath.ConvertToBlockRaster(getPosition(courner)).x*-1:PointMath.ConvertToBlockRaster(getPosition(courner)).x),
				(PointMath.ConvertToBlockRaster(getPosition(courner)).y < 0 ? 
						PointMath.ConvertToBlockRaster(getPosition(courner)).y*-1:PointMath.ConvertToBlockRaster(getPosition(courner)).y)
										
				);
	}
	
	public Vector2i getBlockRasterTopLeftPosition(Corner courner){
		return new Vector2i(getBlockRasterPosition(courner));
	}
	
	public Vector2f getCenterPosition(){
		return PointMath.getCenterPosition(Position, Width, Height);
	}
	
	public Corner checkCourner(Rectangle r){
		Corner courners[] = {Corner.TopLeft, Corner.TopRight, Corner.BottomRight, Corner.BottomLeft};
		for (Corner c : courners){
			//convert to int because of may fail when check equals on floats <- on small Rectangles not so accurate
			Vector2i pos1 = new Vector2i(this.getPosition(c));
			Vector2i pos2 = new Vector2i(this.getPosition(c));
			if(pos1.equals(pos2)) return c;
		}
		return null;
	}
}
