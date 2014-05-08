import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.IOException;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import util.Constants;
import Resources.ResourceLoader;
import Shapes.Rectangle;


public class Block extends MovableGameComponent {

	private Type type;
	private int width, height;
	private Texture texture;
	
	public Type getType(){return this.type;}
	
	public Block(Rectangle rect, int intType) {
		super(rect);
		
		switch(intType)
		{
		case 0:
			this.type = Type.Air;
			break;
		case 1:
			this.type = Type.Solid;
			break;
		case 2:
			this.type = Type.Water;
			break;
		case 3:
			this.type = Type.Gravel;
			break;
		case 4:
			this.type = Type.Sand;
			break;
		case 5:
			this.type = Type.Solid;
			break;
		case 6:
			break;
		case 7:
			break;
		case 8:
			this.type = Type.Stone;
			break;
		case 9:
		case 10:
		default:
			this.type = Type.Air;
			break;
		
		}
		this.Initialize();
		this.LoadContent();
		this.height = this.rect.getHeight();
		this.width = this.rect.getWidth();
		this.position = this.rect.getPosition();
		this.oldPosition = this.rect.getPosition();
		velocity = new Vector2f(0,0);
	}

	@Override
	public void Initialize() {
		
	}

	@Override
	public void LoadContent() {
			if(this.type != Type.Air){
				texture = ResourceLoader.getInstance().getResourceonBlocktype(this.type.name());
			}
	}

	@Override
	public void Update() {
		if(falling)
		{
			if(this.velocity.y < this.gravity){
			this.velocity.y += 0.5f;
			}
		}
		else
		{
			velocity.y = 0;
		}
		position.x += velocity.x;
		position.y += velocity.y;
		this.rect.setPosition(position);
	}

	@Override
	public void Draw() {
		Color.white.bind();
		if(this.type!=Type.Air){	
		texture.bind();
		glBegin(GL_QUADS);

		int x = (int) this.rect.getPosition().x;
		int y = (int) this.rect.getPosition().y;

		int xb = (int) this.rect.getPosition().x + this.width;
		int yb = (int) this.rect.getPosition().y + this.height;

		glTexCoord2f(0, 0);
		glVertex2f((float) x, (float) y);

		glTexCoord2f(0, 1);
		glVertex2f((float) x, (float) yb);

		glTexCoord2f(1, 1);
		glVertex2f((float) xb, (float) yb);

		glTexCoord2f(1, 0);
		glVertex2f((float) xb, (float) y);
		glEnd();
		}
	}

	@Override
	public int getHeight() {
		return Constants.BLOCKHEIGHT;
	}

	@Override
	public int getWidth() {
		return Constants.BLOCKWIDTH;
	}
	
	public String toString(){
		return "type:\t "+this.type.name().substring(0, 2);
	}
	

}
