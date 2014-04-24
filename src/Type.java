
public enum Type {
	Stone(false, false, 10),
	Air(false, false, 0), 
	Sand(true, false, 6), 
	Gravel(true, false, 7), 
	Water(true, true, 5), 
	Lava(true, true, 8), 
	Solid(false, false, 100);

	private final boolean Affectedgravity;
	private final boolean fluid;
	private final float mass;
	
	Type(boolean Affectedgravity, boolean fluid, float mass)
	{
		this.Affectedgravity = Affectedgravity;
		this.fluid = fluid;
		this.mass = mass;
	}
	
	boolean isAffectedbyGravity()
	{
		return this.Affectedgravity;
	}
	boolean isfluid()
	{
		return this.fluid;
	}
	float getMass()
	{
		return this.mass;
	}
}
