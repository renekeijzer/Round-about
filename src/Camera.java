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
	private float x;
	private float y;
	private float xb;
	private float yb;
	
	private Vector2f position;
	
	public Camera(MovableGameComponent focus)
	{
		x = focus.getAbsolutePosition().x;
		y = focus.getAbsolutePosition().y;
		this.focusObject = focus;
		
	}
	
	@Override
	public void update() {
		doLogic();
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(x, xb, yb, y, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
	}
	
	private void doLogic()
	{
		position = new Vector2f(focusObject.getAbsolutePosition().x, focusObject.getAbsolutePosition().y);
		x = (float) (position.x+15 - 800 /2);
		y = (float) (position.y+30 - 800 /2);
		
		xb = (float) (position.x+15 + 800 / 2);
		yb = (float) (position.y+30 + 800 / 2);

		
	}


}
