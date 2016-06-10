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

	public static mapState state = mapState.down;
	private Components components = Components.GetInstance();
	private ArrayList<ArrayList<GameComponent>> mapList = new ArrayList<>();
	public Map() {
	}
	
	public void GenerateLevel(int level)
    {
    		Boolean renderMap = false;
    
            int tempx = 0;
            int tempy = 0;
            ArrayList<GameComponent> temprow = new ArrayList<>();
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
                        	assignInMap(tempx, tempy, temprow);
                        	
                        	tempx = 0;
                        	tempy += 32;
                        	mapList.add(temprow);
                        	temprow=new ArrayList<>();
                        }   
                    	if(line.contains("width"))
                    	{
                    		String[] parts = line.split("=");
                    		Constants.MAPWIDTH = Integer.parseInt(parts[1]);
                    	}
                    	if(line.contains("height"))
                    	{
                    		
                    		String[] parts = line.split("=");
                    		Constants.MAPHEIGHT = Integer.parseInt(parts[1]);
                    	}
                        
                        
                    	if(line.contains("data"))
                        {
                        	   renderMap = true;
                        }
                           
                           
                    }
            }
            catch(IOException e)
            {
            	e.printStackTrace();
            }
            this.state.setRendered(true);
            physicsController.getInstance().addAssociationList(mapList);
        }

	private int assignInMap(int tempx, int tempy, ArrayList<GameComponent> temprow) {
		for(String block : blocks)
		{
			Block tmpBlock = new Block(new Rectangle(tempx, tempy, 32, 32), Integer.parseInt(block)); 
			components.add(tmpBlock);
			temprow.add(tmpBlock);
			tempx += 32;	
		}
		return tempx;
	}

	@Override
	public Iterator iterator() {
		return this.mapList.iterator();
	}
	



}
