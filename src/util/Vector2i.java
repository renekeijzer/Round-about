package util;

import org.lwjgl.util.vector.Vector2f;

public class Vector2i {
	public int x;
	public int y;
	
	public Vector2i(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * <b>WARNING!</b> This value wil be floored!
	 */
	public Vector2i(Vector2f vector){
		this.x = (int) Math.floor(vector.x);
		this.y = (int) Math.floor(vector.y);;
	}
}