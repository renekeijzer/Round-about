package util;

public enum XDirection {
	None(0), Left(-1), Right(1);
	
	private final int value;
	
	XDirection(int dir){
		this.value = dir;
	}
	
	int getValue(){
		return value;
	}
}