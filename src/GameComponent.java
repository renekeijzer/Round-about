
public abstract class GameComponent {
	
	public GameComponent()
	{
		//Initialize();
		//LoadContent();
	}
	
	private boolean Remove;
	
	public abstract void Initialize();
	public abstract void LoadContent();
	public abstract void Update();
	public void Draw()
	{
		
	}
	public boolean isRemove() {
		return Remove;
	}
	public void setRemove(boolean remove) {
		Remove = remove;
	}
	
}
