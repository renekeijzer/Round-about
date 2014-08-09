import util.Direction;
import util.XDirection;
import util.YDirection;

public class Vectori {
	public int x;
	public int y;
	
	public Direction GetDirection(){
		return Direction.FromDirections(XDirection.FromInt(x), YDirection.FromInt(y));
	}
}
