import org.lwjgl.util.vector.Vector2f;

import Shapes.Rectangle;


public class Cds extends GameComponent {
	Components comp = Components.GetInstance();
	public Cds()
	{
		Player p = new Player(new Rectangle(new Vector2f(100, 100), 32, 64));
		Map map =  new Map();
		comp.add(physicsController.getInstance());
		Camera camera = new Camera(p);
		map.GenerateLevel(2);
		comp = Components.GetInstance();
		comp.add(p);
		comp.add(camera);
	}
	
	
	@Override
	public void Initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void LoadContent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}
	
}
