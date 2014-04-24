import java.util.ArrayList;

public abstract class Game {

	private Components components;
	private ArrayList<GameComponent> RemoveList;

	public Game() {
		Initialize();
		LoadContent();
		RemoveList = new ArrayList<GameComponent>();
	}

	public abstract void Initialize();

	public abstract void LoadContent();

	public void Update() {
		if (components == null) {
			this.components = Components.GetInstance();
		}
		if (components.Size() != 0) {
			for (GameComponent gc : components) {
				if (gc.isRemove()) {
					RemoveList.add(gc);
				} else {
					gc.Update();
					gc.Draw();
				}
			}
		}
		if(RemoveList.size() > 0)
		{
			components.remove(RemoveList);
		}
	}
}
