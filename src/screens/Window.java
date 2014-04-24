package screens;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public abstract class Window {
	private static int Width;
	private static int Height;
	
	public static int getWidth(){return Width;}
	public static int getHeight(){return Height;}
	
	public Window(int Width,int Height)
	{
		Window.Width = Width;
		Window.Height = Height;
		
		try {
			Display.setDisplayMode(new DisplayMode(Width, Height));
			Display.setVSyncEnabled(true);
			Display.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
