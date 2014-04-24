import java.util.ArrayList;
import java.util.Iterator;


public class Components implements Iterable<GameComponent>{
	private static Components Instance;
	private ArrayList<GameComponent> ComponentList;
	private Components()
	{
		ComponentList = new ArrayList<GameComponent>();
	}
	
	public static Components GetInstance()
	{
		if(Instance == null)
		{
			Instance = new Components();
		}
		return Instance;
	}
	
	public void add(GameComponent gc)
	{
		if(ComponentList != null)
		{
			ComponentList.add(gc);
		}
	}
	
	public void remove(GameComponent gc)
	{
		if(ComponentList != null)
		{
			ComponentList.remove(gc);
		}
	}
	
	public int Size()
	{
		if(ComponentList != null)
		{
			return ComponentList.size();
		}
		return 0;
	}
	
	@Override
	public Iterator<GameComponent> iterator() {
		Iterator<GameComponent> iGameComponent = this.ComponentList.iterator();
		return iGameComponent;
	}

	public void remove(ArrayList<GameComponent> removeList) {
		for(GameComponent gc: removeList)
		{
			ComponentList.remove(gc);
		}
	}
	
}
