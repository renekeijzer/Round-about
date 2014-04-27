import org.lwjgl.util.vector.Vector2f;

import util.Courner;
import util.PointMath;
import util.XDirection;
import util.YDirection;
import Shapes.Rectangle;


public abstract class MovableGameComponent extends GameComponent {

	protected Vector2f position, velocity;
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
		return this.position;
	}
	
	public Vector2f getPosition(Courner courner){
		return PointMath.getPosition(position, getWidth(), getHeight(), courner);
	}
	
	/**
	 * Returns the new top-left position of the object
	 */
	public Vector2f getNextPosition(){
		return new Vector2f(this.position.x + this.velocity.x, this.position.y + this.position.x);
	}
	
	public Vector2f getNextPosition(Courner courner){
		return PointMath.getPosition(getNextPosition(), getWidth(), getHeight(), courner);
	}
	
	/**
	 * Returns the velocity
	 */
	public Vector2f getVelocity(){
		return velocity;
	}
	
	public void setVelocity(Vector2f velocity){
		this.velocity = velocity;
	}
	
	public Rectangle getRectangle(){
		return new Rectangle(position, getHeight(), getWidth());
	}
	
	public Rectangle getNextRectangle(){
		return new Rectangle(getNextPosition(), getHeight(), getWidth());
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
