import org.lwjgl.util.vector.Vector2f;

import util.Corner;
import util.PointMath;
import util.XDirection;
import util.YDirection;
import Shapes.Rectangle;


public abstract class MovableGameComponent extends GameComponent {

	protected Vector2f position, velocity, oldPosition;
	protected Boolean falling = false;
	protected float gravity = 7f;
	protected float mass, force;
	protected Direction direction, collidedDirection;
	protected boolean colided;
	protected Rectangle rect;
	public MovableGameComponent(Rectangle rect)
	{
		this.rect = rect;
		this.position = new Vector2f(0,0);
		this.velocity = new Vector2f(0,0);
		this.falling = false;
	}
	@Override
	public abstract void Initialize();

	@Override
	public abstract void LoadContent();

	@Override
	public abstract void Update();
	
	@Override
	public abstract void Draw();
	
	public abstract int getHeight();
	public abstract int getWidth();
	
	/*
	 * Returns top-left position
	 */
	public Vector2f getPosition()
	{
		return new Vector2f(
				(this.position.x < 0 ? this.position.x *-1:this.position.x),
				(this.position.y < 0 ? this.position.y *-1:this.position.y)
				);
	}
	
	public Vector2f getAbsolutePosition(){
		return this.position;
	}
	
	public Vector2f getOldPosition()
	{
		return this.oldPosition;
	}
	
	public void setOldPosition(Vector2f oldPosition)
	{
		this.oldPosition = oldPosition;
	}
	
	public Vector2f getPosition(Corner courner){
		return PointMath.getPosition(position, getWidth(), getHeight(), courner);
	}
	
	/**
	 * Returns the new top-left position of the object
	 */
	public Vector2f getNextPosition(){
		return new Vector2f(this.position.x + this.velocity.x, this.position.y + this.velocity.y);
	}
	
	public Vector2f getNextPosition(Corner courner){
		return PointMath.getPosition(getNextPosition(), getWidth(), getHeight(), courner);
	}
	
	/**
	 * Returns the velocity
	 */
	public Vector2f getVelocity(){
		return velocity;
	}
	
	public boolean isMoving(){
		return velocity.x == 0 && velocity.y == 0;
	}
	
	public void setVelocity(Vector2f velocity){
		this.velocity = velocity;
	}
	
	public Rectangle getRectangle(){
		return new Rectangle(position, getWidth(), getHeight());
	}
	
	public Rectangle getNextRectangle(){
		return new Rectangle(getNextPosition(), getWidth(), getHeight());
	}
	
	public XDirection getXDirection(){
		if(velocity.x == 0.0f){
			return XDirection.None;
		}else if(velocity.x < 0){
			return XDirection.Left;
		}else{
			return XDirection.Right;
		}
	}
	
	public YDirection getYDirection(){
		if(velocity.x == 0.0f){
			return YDirection.None;
		}else if(velocity.x < 0){
			return YDirection.Up;
		}else{
			return YDirection.Down;
		}
	}
	
	public Rectangle getMoveRectangle(){
		return new Rectangle(getRectangle(), getNextRectangle());
	}
}
