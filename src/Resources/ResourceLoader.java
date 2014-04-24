package Resources;

import java.io.File;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class ResourceLoader {
	private static ResourceLoader instance;
	
	private ResourceLoader()
	{
		
	}
	
	public static ResourceLoader getInstance()
	{
		if(instance == null)
		{
			instance = new ResourceLoader();
		}
		return instance;
	}
	
	public Texture getResourceonBlocktype(String type)
	{
		File file = new File("assets/"+type);
		File[] files = file.listFiles();
		
		
		int rand = 0 +(int)(Math.random()*((files.length - 0)+ 1));
		if(rand > 0){
		rand--;
		}
		try {
			return TextureLoader.getTexture("PNG", org.newdawn.slick.util.ResourceLoader.getResourceAsStream("Assets/" + type
					+ "/tile"+rand+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
}
