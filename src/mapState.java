
public enum mapState {
	left(false),
	right(false),
	up(false),
	down(false);
	
	private boolean rendered = false;
	
	mapState(boolean ren){
		this.rendered = ren;
	}
	
	public void setRendered(boolean ren){
		this.rendered = ren;
	}
	public boolean isRendered(){
		return this.rendered;
	}
}
