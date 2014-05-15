import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.awt.Window;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;


public class Camera extends GameComponent {
	
	private MovableGameComponent focusObject;
	private float x, y, xb, yb, Velocity;
	private Vector2f Position;
	
	public Camera(MovableGameComponent Focus)
	{
		x = Focus.getAbsolutePosition().x;
		y = Focus.getAbsolutePosition().y;
		this.focusObject = Focus;
		
	}
	
	@Override
	public void Update() {
		DoLogic();
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(x, xb, yb, y, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
	}
	
	private void DoLogic()
	{
		Position = new Vector2f(focusObject.getAbsolutePosition().x, focusObject.getAbsolutePosition().y);
		x = (float) (Position.x+15 - 800 /2);
		y = (float) (Position.y+30 - 800 /2);
		
		xb = (float) (Position.x+15 + 800 / 2);
		yb = (float) (Position.y+30 + 800 / 2);

		
	}

	@Override
	public void Initialize()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void LoadContent()
	{
		// TODO Auto-generated method stub
		
	}

}
