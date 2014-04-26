import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.util.vector.Vector2f;

import util.Constants;
import Shapes.Rectangle;


public class Map extends GameComponent implements Iterable<GameComponent> {

	private Components components = Components.GetInstance();
	private ArrayList<ArrayList<GameComponent>> mapList = new ArrayList<ArrayList<GameComponent>>();
	public Map() {
		// TODO Auto-generated constructor stub
	}
	
	public void GenerateLevel(int level)
    {
    		Boolean renderMap = false;
    		String[] blocks = null;
            int Tempx = 0;
            int Tempy = 0;
            ArrayList<GameComponent> temprow = new ArrayList<GameComponent>();
            BufferedReader reader = null;
            try {
                    reader = new BufferedReader(new FileReader("levels/level" + level + ".txt"));
            } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
            }
            String line = null;
            try {
                    while ((line = reader.readLine()) != null) {
                        if(renderMap)
                        {
                        	blocks = line.split(",");
                        	for(String block : blocks)
                        	{
                        		Block tmpBlock = new Block(new Rectangle(Tempx, Tempy, 32, 32), Integer.parseInt(block)); 
                        		components.add(tmpBlock);
                        		temprow.add(tmpBlock);
                        		Tempx += 32;	
                        	}
                        	
                        	Tempx = 0;
                        	Tempy += 32;
                        	mapList.add(temprow);
                        	temprow=new ArrayList<GameComponent>();
                        }   
                    	if(line.contains("width"))
                    	{
                    		String parts[] = line.split("=");
                    		System.out.println("Map width initialized at: "+parts[1]);
                    		Constants.MAPWIDTH = Integer.parseInt(parts[1]);
                    	}
                    	if(line.contains("height"))
                    	{
                    		
                    		String parts[] = line.split("=");
                    		System.out.println("Map height initialized at: " + parts[1]);
                    		Constants.MAPHEIGHT = Integer.parseInt(parts[1]);
                    	}
                        
                        
                    	if(line.contains("data"))
                        {
                        	   renderMap = true;
                        }
                           
                           
                    }
                    renderMap = false;
            }
            catch(IOException e)
            {
            	e.printStackTrace();
            }
            physicsController.getInstance().addAssociationList(mapList);
        }

	@Override
	public Iterator iterator() {
		Iterator<ArrayList<GameComponent>> iGameComponent = this.mapList.iterator();
		return iGameComponent;
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

	@Override
	public void Draw() {
		// TODO Auto-generated method stub
		
	}

}
