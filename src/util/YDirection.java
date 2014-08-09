package util;

public enum YDirection {
	None, Up, Down;
	
	public static YDirection FromInt(int yVector){
		if(yVector == 0){
			return None;
		}else if(yVector < 0){
			return Up;
		}else{
			return Down;
		}
	}
	
	public static int ToInt(YDirection value){
		if(value == None){
			return 0;
		}else if(value == Up){
			return -1;
		}else{
			return 1;
		}
	}
	
	public int GetValue(){
		return ToInt(this);
	}
}
