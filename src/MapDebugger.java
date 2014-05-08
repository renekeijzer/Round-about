



import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MapDebugger {
	public MapDebugger()
	{
		
	}
	
	public static void writeMapToConsole(ArrayList<ArrayList<Object>> mapList)
	{
		int x = 0;
		int y = 0;
		for(ArrayList<Object> row : mapList)
		{
			for(Object cell : row)
			{
				System.out.print("["+x+","+y+"]"+cell + ",");
				x++;
			}
			System.out.println();
			x=0;
			y++;
		}
	}
	
	public static void WriteMaptoLog(ArrayList<ArrayList<GameComponent>> assoc) throws FileNotFoundException, UnsupportedEncodingException
	{
		String tempstr = "";
		PrintWriter writer = null;
		writer = new PrintWriter("map_log.log", "UTF-8");
		for(int i = 0; i < assoc.size(); i++ )
		{
			for(int y = 0; y < assoc.get(i).size(); y++)
			{	
				tempstr = tempstr + "["+i+",\t"+y+"\t]"+assoc.get(i).get(y) + ", \t\t";
			}
			writer.println(tempstr);
			tempstr = "";
		}
		writer.close();
		System.exit(0);
	}
}
