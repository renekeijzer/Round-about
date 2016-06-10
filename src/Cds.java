import org.lwjgl.util.vector.Vector2f;

import util.Constants;
import Shapes.Rectangle;


public class Cds extends GameComponent {
	Components comp = Components.GetInstance();
	public Cds()
	{
		Player p = new Player(new Rectangle(new Vector2f(4512, 100), Constants.PLAYERWIDTH, Constants.PLAYERHEIGHT));
		Map map =  new Map();
		comp.add(physicsController.getInstance());
		Camera camera = new Camera(p);
		map.GenerateLevel(2);
		comp = Components.GetInstance();
		comp.add(p);
		comp.add(camera);
	}
	
	


	
}
