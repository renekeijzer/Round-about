import java.util.ArrayList;

public abstract class Game {

	private Components components;
	private ArrayList<GameComponent> removeList;

	public Game() {
		initialize();
		loadContent();
		removeList = new ArrayList<>();
	}

	public abstract void initialize();

	public abstract void loadContent();

	public void update() {
		if (components == null) {
			this.components = Components.GetInstance();
		}
		if (components.Size() != 0) {
			for (GameComponent gc : components) {
				if (gc.isRemove()) {
					removeList.add(gc);
				} else {
					gc.update();
					gc.draw();
				}
			}
		}
		if(!removeList.isEmpty())
		{
			components.remove(removeList);
		}
	}
}
