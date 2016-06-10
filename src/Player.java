import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;

import util.Constants;
import Shapes.Rectangle;

public class Player extends MovableGameComponent
{

	private boolean jumping;

	@Override
	public Vector2f getPosition()
	{
		if(this.rect.getPosition().x < 0 && this.rect.getPosition().y < 0){
		return new Vector2f(this.rect.getPosition().x * -1, this.rect.getPosition().y * -1);
		}else{
		return this.rect.getPosition();
		}
	}

	public Player(Rectangle rectangle)
	{
		super(rectangle);
		this.position = this.rect.getPosition();
		this.velocity = new Vector2f(0, 0);
		this.jumping = false;
	}

	@Override
	public void Initialize()
	{
		glEnable(GL_TEXTURE_2D);
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void loadContent()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void update()
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			if (!this.colided && this.collidedDirection != Direction.LEFT)
			{

				this.direction = Direction.LEFT;
				if (velocity.x > -7)
				{
					velocity.x -= 0.5f;
				}
			} else
			{
				velocity.x = 0;
			}
			if (this.colided && this.collidedDirection == Direction.RIGHT)
			{
				this.collidedDirection = Direction.NONE;
				this.colided = false;
			}
		}else if(Keyboard.isKeyDown(Keyboard.KEY_D)){
		if (!this.colided && this.collidedDirection != Direction.RIGHT)
			{
				this.direction = Direction.RIGHT;
				if (velocity.x < 7)
					velocity.x += 0.5f;
			} else
			{
				velocity.x = 0;
			}
			if (this.colided && this.collidedDirection == Direction.LEFT)
			{
				this.collidedDirection = Direction.NONE;
				this.colided = false;
			}

		}else{		
			if (velocity.x < 0)
				velocity.x += 0.5f;
			else if (velocity.x > 0)
				velocity.x -= 0.5f;
		}		
		
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
		{
			if (!this.jumping)
			{
				this.jumping = true;
				velocity.y = -14;
				this.falling = true;
			}
		}

		if (velocity.x == 0)
		{
			this.direction = Direction.NONE;
		}

		if (this.falling)
		{
			if (velocity.y < this.gravity)
			{
				velocity.y += 0.5f;
			}
		} else
		{
			velocity.y = 0;
			this.jumping = false;
		}
		this.position.x += velocity.x;
		this.position.y += velocity.y;

		rect.setX((int) position.x);
		rect.setY((int) position.y);
	}

	@Override
	public void draw()
	{
		Color.white.bind();
		glBegin(GL_QUADS);
		glColor3f(0.5f, 0.5f, 1.0f);
		glVertex2f(position.x, position.y);
		glVertex2f(position.x, position.y + rect.getHeight());
		glVertex2f(position.x + rect.getWidth(), position.y + rect.getHeight());
		glVertex2f(position.x + rect.getWidth(), position.y);
		glEnd();
	}

	@Override
	public int getHeight() {
		return Constants.PLAYERHEIGHT;
	}

	@Override
	public int getWidth() {
		return Constants.PLAYERWIDTH;
	}

}
