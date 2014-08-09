package util;


public enum Direction{
	None, Up, Right, Down, Left, UpLeft, UpRight, DownRight, DownLeft;
	
	public XDirection GetXDirection(){
		if(this == Right || this == UpRight || this == DownRight){
			return XDirection.Right;
		}else if (this == Left || this == UpLeft || this == DownLeft){
			return XDirection.Left;
		}else{
			return XDirection.None;
		}
	}
	
	public YDirection GetYDirection(){
		if(this == Up || this == UpLeft || this == UpRight){
			return YDirection.Up;
		}else if(this == Down || this == DownLeft || this == DownRight){
			return YDirection.Down;
		}else{
			return YDirection.None;
		}
	}
	
	public static Direction FromDirections(XDirection xdir, YDirection ydir){
		if(xdir == XDirection.None){
			if(ydir == YDirection.None){
				return None;
			}else if(ydir == YDirection.Up){
				return Up;
			}else{ //ydir == YDirection.Down
				return Down;
			}
		}else if(xdir == XDirection.Left){
			if(ydir == YDirection.None){
				return Left;
			}else if(ydir == YDirection.Up){
				return UpLeft;
			}else{ //ydir == YDirection.Down
				return DownLeft;
			}
		}else{ //xdir == XDirection.Right
			if(ydir == YDirection.None){
				return Right;
			}else if(ydir == YDirection.Up){
				return UpRight;
			}else{ //ydir == YDirection.Down
				return DownRight;
			}
		}
	}
}
