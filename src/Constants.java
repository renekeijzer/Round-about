import org.lwjgl.util.vector.Vector2f;


public class Constants
{
	public final static int PLAYERWIDTH = 32;
	public final static int PLAYERHEIGHT = 64;
	public final static int BLOCKWIDTH = 32, BLOCKHEIGHT = 32;
	
	public static int MAPWIDTH, MAPHEIGHT;
	
	public static Vector2f ConvertToBlockRaster(Vector2f vector){
		return new Vector2f(vector.x / BLOCKWIDTH, vector.y / BLOCKHEIGHT);
	}
	
	public static Vector2f ConvertFromBlockRaster(Vector2f vector){
		return new Vector2f(vector.x * BLOCKWIDTH, vector.y * BLOCKHEIGHT);
	}
	
	public static int distanceInt(int point1, int point2){
		if(point1 > point2){
			return point1 - point2;
		}else{
			return point2 - point1;
		}
	}
}
