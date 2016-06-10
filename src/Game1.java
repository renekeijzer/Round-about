import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glViewport;

import org.lwjgl.opengl.Display;

import screens.GameWindow;
import screens.Window;


public class Game1 extends Game {
	
	Cds cds;
	public Game1()
	{
		do{
			glClear(GL_COLOR_BUFFER_BIT);
			this.update();
			Display.update();
		}
		while(!Display.isCloseRequested());
	}

	@Override
	public void initialize() {
		new GameWindow(800, 800);
		glEnable(GL_BLEND);
		glViewport(0,0, Window.getWidth(), Window.getHeight());
		glMatrixMode(GL_MODELVIEW);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Window.getWidth(), Window.getHeight(), 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);	
		
	}

	@Override
	public void loadContent() {
		cds = new Cds();
	}

}
