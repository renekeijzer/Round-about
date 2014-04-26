package util;
import org.lwjgl.util.vector.Vector2f;


public class PointMath {
	public static Vector2f ConvertToBlockRaster(Vector2f vector){
		return new Vector2f(vector.x / Constants.BLOCKWIDTH, vector.y / Constants.BLOCKHEIGHT);
	}
	
	public static Vector2f ConvertFromBlockRaster(Vector2f vector){
		return new Vector2f(vector.x * Constants.BLOCKWIDTH, vector.y * Constants.BLOCKHEIGHT);
	}
	
	public static int distanceInt(int point1, int point2){
		if(point1 > point2){
			return point1 - point2;
		}else{
			return point2 - point1;
		}
	}
	
	public static float distanceFloat(float point1, float point2){
		if(point1 > point2){
			return point1 - point2;
		}else{
			return point2 - point1;
		}
	}
	
	public static Vector2f getPosition(Vector2f topleft, float width, float height, Courner courner){
		switch(courner){
		case TopLeft:
			return topleft;
		case TopRight:
			return new Vector2f(topleft.x + width, topleft.y);
		case BottomRight:
			return new Vector2f(topleft.x + width, topleft.y + height);
		case BottomLeft:
			return new Vector2f(topleft.x, topleft.y + height);
		default:
			return topleft;
		}
	}
	
	public static Vector2f getCenterPosition(Vector2f topleft, float width, float height){
		return new Vector2f(topleft.x + (width / 2), topleft.y + (height / 2));
	}
}
