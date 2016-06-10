import java.util.ArrayList;
import java.util.Iterator;


public class Components implements Iterable<GameComponent>{
	private static Components instance;
	private ArrayList<GameComponent> componentList;
	private Components()
	{
		componentList = new ArrayList<>();
	}
	
	public static Components getInstance()
	{
		if(instance == null)
		{
			instance = new Components();
		}
		return instance;
	}
	
	public void add(GameComponent gc)
	{
		if(componentList != null)
		{
			componentList.add(gc);
		}
	}
	
	public void remove(GameComponent gc)
	{
		if(componentList != null)
		{
			componentList.remove(gc);
		}
	}
	
	public int size()
	{
		if(componentList != null)
		{
			return componentList.size();
		}
		return 0;
	}
	
	@Override
	public Iterator<GameComponent> iterator() {
		return this.componentList.iterator();
		
	}

	public void remove(List<GameComponent> removeList) {
		for(GameComponent gc: removeList)
		{
			componentList.remove(gc);
		}
	}
	
}
