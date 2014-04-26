import org.lwjgl.util.vector.Vector2f;

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
	
	/*
	 * Returns top-left position
	 */
	public Vector2f getPosition()
	{
		return this.position;
	}
	
	/**
	 * Returns the new top-left position of the object
	 */
	public Vector2f getNextPosition(){
		return new Vector2f(this.position.x + this.velocity.x, this.position.y + this.position.x);
	}
	
	/**
	 * Returns the velocity
	 * @return
	 */
	public Vector2f getVelocity(){
		return velocity;
	}
	
}
