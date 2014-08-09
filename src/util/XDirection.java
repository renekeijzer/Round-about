package util;

public enum XDirection {
	None, Left, Right;
	
	public static XDirection FromInt(int xVector){
		if(xVector == 0){
			return None;
		}else if(xVector < 0){
			return Left;
		}else{
			return Right;
		}
	}
	
	public static int ToInt(XDirection value){
		if(value == None){
			return 0;
		}else if(value == Left){
			return -1;
		}else{
			return 1;
		}
	}
	
	public int GetValue(){
		return ToInt(this);
	}
}