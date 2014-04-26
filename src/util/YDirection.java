package util;

public enum YDirection {
	None(0), Up(-1), Down(1);
	
	private final int value;
	
	YDirection(int dir){
		this.value = dir;
	}
	
	int getValue(){
		return value;
	}
}
