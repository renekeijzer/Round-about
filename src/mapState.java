
public enum mapState {
	left(false),
	right(false),
	up(false),
	down(false);
	
	mapState(boolean ren){
		this.rendered = ren;
	}
	private boolean rendered = false;
	
	public void setRendered(boolean ren){
		this.rendered = ren;
	}
	public boolean isRendered(){
		return this.rendered;
	}
}
