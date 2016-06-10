import org.lwjgl.input.Keyboard;

public class Keys {
	private Keys(){}
	
	public static int getNext()
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			return Keyboard.KEY_A;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			return Keyboard.KEY_D;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			return Keyboard.KEY_W;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
		{
			return Keyboard.KEY_SPACE;
		}
		return 0;
	}
}
