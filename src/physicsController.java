import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import Shapes.Rectangle;
import util.Constants;
import util.Corner;
import util.PointMath;
import util.Vector2i;
import util.XDirection;
import util.YDirection;

public class physicsController extends GameComponent
{

	private static ArrayList<ArrayList<GameComponent>> Assoc;
	private static physicsController Instance = null;
	private Components Components;

	private physicsController()
	{
		Components = Components.GetInstance();
		Assoc = new ArrayList<ArrayList<GameComponent>>();
	}

	public static physicsController getInstance()
	{
		if (Instance == null)
		{
			Instance = new physicsController();
		}
		return Instance;
	}

	@Override
	public void Initialize()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void LoadContent()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void Update()
	{
		if (Assoc.size() > 0)
		{
			for (GameComponent component : Components)
			{
				if (component.getClass().getSuperclass().getName() == "MovableGameComponent")
				{
					MovableGameComponent Subject = (MovableGameComponent) component;
					updateAssoc(Subject);
					int x = (int) Math.floor(Subject.getPosition().x / 32);
					int y = (int) Math.floor(Subject.getPosition().y / 32);
					if (isFalling(Subject))
					{
						Subject.falling = true;
						
					} else
					{  
						Subject.falling = false;
						
					}

					isColliding(Subject);
					
					if(Keyboard.isKeyDown(Keyboard.KEY_L)){
						try {
							MapDebugger.WriteMaptoLog(this.Assoc);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					Subject.setOldPosition(Subject.getPosition());
					
					if(Subject instanceof Player){
						if(Subject.getPosition().x / Constants.BLOCKWIDTH >= Constants.MAPWIDTH - 2){
							rotateMapClockwise();
							
							System.out.println("as");
							((Player) component).position.x = 100;
							((Player) component).position.y = 100;
							((Player) component).rect.setPosition(new Vector2f(100,100));
						}
					}
				}
			}
		}

	}
	
	public void rotateMapClockwise(){
		for (GameComponent component : Components)
		{
			createNewAssoc();
			if(component instanceof Block){
				int x = (int) ((Block) component).position.x;
				int y = (int) ((Block) component).position.y;
				
				((Block) component).position.y = x;
				((Block) component).position.x = y;
				
				((Block) component).rect.setPosition(new Vector2f(y,x));
				
				
				
			}

		}
	}
	
	public void createNewAssoc(){
		Object[][] array = new Object[Assoc.size()][];
		for (int i = 0; i < Assoc.size(); i++) {
		    ArrayList<GameComponent> row = Assoc.get(i);
		    array[i] = row.toArray();
		}
		
		Assoc = new ArrayList<ArrayList<GameComponent>>();
		
		for(int i = 0; i < array[0].length; i++){
			ArrayList<GameComponent> tempRow = new ArrayList<GameComponent>();
			for(int x = 0; x < array.length; x++){
				
				tempRow.add((GameComponent) array[x][i]);
			}
			Assoc.add(tempRow);
		}
		Constants.MAPHEIGHT = Assoc.size();
		Constants.MAPWIDTH = Assoc.get(0).size(); 
	}
	
	
	private void updateAssoc(MovableGameComponent subject)
	{
		if(subject instanceof Block)
		{
			if(subject.falling)
				GravitationalSwap(subject);
			
			if(!subject.falling){
				fixTexture(subject);
			}
		}
	} 
	
	
	
	/* Quick and dirty texturefixing*/
	private void fixTexture(MovableGameComponent subject) {
		int x = (int) (subject.position.x / Constants.BLOCKWIDTH);
		int y = (int) (subject.position.y/ Constants.BLOCKHEIGHT);
		
		int xfinal = x* Constants.BLOCKWIDTH;
		int yfinal = y * Constants.BLOCKHEIGHT;
		
		subject.position = new Vector2f(xfinal,yfinal);
		
	}

	private void GravitationalSwap(MovableGameComponent subject) {
		int x = (int) (subject.getPosition().x/Constants.BLOCKWIDTH);
		int y = (int) (subject.getPosition().y/Constants.BLOCKWIDTH);
		
		Block tempBlock = (Block) this.Assoc.get(y+1).get(x);
		if(tempBlock.getType().name() != "Air"){
			tempBlock = new Block(new Rectangle(x*32, (y+1)*32, 32, 32), 0);
		}
		this.setBlock((Block) subject, x, y);
		this.setBlock(tempBlock, x, y-1);
	}
	

	//NOT READY
	private boolean isColliding(MovableGameComponent subject){
		if (subject instanceof Player){
			System.out.println("Player Pos: x:" + ((Player)subject).getPosition().x + " y: " + ((Player)subject).getPosition().y);
			System.out.println("Player NextPos: x:" + ((Player)subject).getNextPosition().x + " y: " + ((Player)subject).getNextPosition().y);
			Rectangle moveRectangle = subject.getMoveRectangle();
			ArrayList<Block> blocklist = getNonAirBlocks(moveRectangle);
			System.out.println(blocklist.size());
			if(!blocklist.isEmpty()){	//collision while moving!
				Corner otherPosition;
				Corner myPosition;
				if(subject.getXDirection() == XDirection.Left){
					if(subject.getYDirection() == YDirection.Up){
						otherPosition = Corner.BottomRight;
						myPosition = Corner.TopLeft;
					}else{
						otherPosition = Corner.TopRight;
						myPosition = Corner.BottomLeft;
					}
				}else{
					if(subject.getYDirection() == YDirection.Up){
						otherPosition = Corner.BottomLeft;
						myPosition = Corner.TopRight;
					}else{
						otherPosition = Corner.TopLeft;
						myPosition = Corner.BottomRight;
					}
				}
				float nearestx = subject.getVelocity().x;
				float nearesty = subject.getVelocity().y;
				if(subject.getVelocity().x < 0) nearestx *= -1;
				if(subject.getVelocity().y < 0) nearesty *= -1;
				float curx = subject.getPosition(myPosition).x;
				float cury = subject.getPosition(myPosition).y;
				for(Block b : blocklist){
					float dif = b.getPosition(Corner.TopLeft).y - subject.getNextPosition(Corner.BottomRight).y;
					if ((dif < 0.0f && dif > -0.1f) || (dif > 0.0f && dif < 0.1f) || dif == 0.0f) continue;
					float distx = PointMath.distanceFloat(curx, b.getPosition(otherPosition).x);
					float disty = PointMath.distanceFloat(cury, b.getPosition(otherPosition).y);
					if(distx < nearestx) nearestx = distx;
					if(disty < nearesty) nearesty = disty;
				}
				if(subject.getVelocity().x < 0) nearestx *= -1;
				if(subject.getVelocity().y < 0) nearesty *= -1;
				
				subject.setVelocity(new Vector2f(nearestx, nearesty));
				System.out.println("Players Limited Pos: x:" + ((Player)subject).getNextPosition().x + " y: " + ((Player)subject).getNextPosition().y);
				
				//TODO ?Boolean? i've done all the work
				return true;
			}else{
				//TODO ?Boolean? i've done all the work
				return false;
			}
		}
		return false;
	}


	public void addAssociationList(ArrayList<ArrayList<GameComponent>> Assoc)
	{
		this.Assoc = Assoc;
	}

	private boolean isFalling(MovableGameComponent Subject)
	{
		if (Subject instanceof Block)
		{
			Subject = (Block) Subject;
			boolean affected = ((Block) Subject).getType()
					.isAffectedbyGravity();
			int x = (int) Math.floor(Subject.getPosition().x
					/ Constants.BLOCKWIDTH);
			int y = (int) Math.floor(Subject.getPosition().y
					/ Constants.BLOCKHEIGHT) + 1;
			if (y > Constants.MAPHEIGHT - 1)
			{
				return false;
			}

			Block tmpBlock = (Block) Assoc.get(y).get(x);
			if (tmpBlock.getType() == Type.Air && affected)
			{
				return true;
			}
		}
		if (Subject instanceof Player)
		{
			Subject = (Player) Subject;

			int x = (int) Math
					.floor((Subject.getPosition().x + Constants.PLAYERWIDTH)
							/ Constants.BLOCKWIDTH);
			int y = (int) Math
					.floor((Subject.getPosition().y + Constants.PLAYERHEIGHT)
							/ Constants.BLOCKHEIGHT);
			if (x < 0 || x > Constants.MAPWIDTH - 1)
			{
				return true;
			}
			if (y < 0)
			{
				return true;
			}
			Block tmpBlock = (Block) Assoc.get(y).get(x);
			if (tmpBlock.getType() == Type.Air)
			{
				return true;
			}
		}
		return false;

	}
	
	/**
	 * Returns all blocks with are within and on the border of the reactangle
	 */
	public ArrayList<Block> getBlocks(Rectangle r){
		ArrayList<Block> list = new ArrayList<Block>();
		Vector2i rBlockTopLeft = r.getBlockRasterTopLeftPosition(Corner.TopLeft);
		Vector2i rBlockBottomRight = r.getBlockRasterTopLeftPosition(Corner.BottomRight);
		System.out.println("Looking between: "+ rBlockTopLeft + " and " + rBlockBottomRight + "for blocks");
		for (int y = rBlockTopLeft.y; y <= rBlockBottomRight.y; y++){
			if(y >= Assoc.size()) break;
			for(int x = rBlockTopLeft.x; x <= rBlockBottomRight.x; x++){
				if(x >= Assoc.get(y).size()) break;
				GameComponent temp = Assoc.get(y).get(x);
				if(temp instanceof Block){
					list.add((Block) temp);
					System.out.println(((Block)temp).getType().toString() + " x: " + x + " y: " + y);
				}
			}
		}
		return list;
	}
	/**
	 * Returns all NonAir blocks with are within and over the border of the reactangle
	 */
	public ArrayList<Block> getNonAirBlocks(Rectangle r){
		ArrayList<Block> list = getBlocks(r);
		ArrayList<Block> copy = new ArrayList<Block>();
		for(Block b: list){
			if(b.getType() != Type.Air){
				copy.add(b);
			}
		}
		return copy;
	}

	private void setBlock(Block block, int x, int y){
		ArrayList<GameComponent> temp = this.Assoc.get(y);
		temp.set(x, block);
		this.Assoc.set(y, temp);
		
	}

}
