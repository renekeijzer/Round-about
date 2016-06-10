
public abstract class GameComponent {
	
	private boolean remove;
	
	public GameComponent()
	{
	}
	
	public abstract void initialize();
	public abstract void loadContent();
	public abstract void update();
	public void draw()
	{
		
	}
	public boolean isRemove() {
		return remove;
	}
	public void setRemove(boolean rem) {
		remove = rem;
	}
	
}
