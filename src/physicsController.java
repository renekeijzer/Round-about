import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import Shapes.Rectangle;
import util.Constants;
import util.Courner;
import util.PointMath;
import util.Vector2i;
import util.XDirection;
import util.YDirection;

public class physicsController extends GameComponent
{

	private ArrayList<ArrayList<GameComponent>> Assoc;
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

				}
			}
		}

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
				Courner otherPosition;
				Courner myPosition;
				if(subject.getXDirection() == XDirection.Left){
					if(subject.getYDirection() == YDirection.Up){
						otherPosition = Courner.BottomRight;
						myPosition = Courner.TopLeft;
					}else{
						otherPosition = Courner.TopRight;
						myPosition = Courner.BottomLeft;
					}
				}else{
					if(subject.getYDirection() == YDirection.Up){
						otherPosition = Courner.BottomLeft;
						myPosition = Courner.TopRight;
					}else{
						otherPosition = Courner.TopLeft;
						myPosition = Courner.BottomRight;
					}
				}
				float nearestx = subject.getVelocity().x;
				float nearesty = subject.getVelocity().y;
				if(subject.getVelocity().x < 0) nearestx *= -1;
				if(subject.getVelocity().y < 0) nearesty *= -1;
				float curx = subject.getPosition(myPosition).x;
				float cury = subject.getPosition(myPosition).y;
				for(Block b : blocklist){
					float dif = b.getPosition(Courner.TopLeft).y - subject.getNextPosition(Courner.BottomRight).y;
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

//	private boolean isColliding(MovableGameComponent subject)
//	{
//		if (subject instanceof Player)
//		{
//			int x = (int) Math
//					.floor(subject.position.x / Constants.PLAYERWIDTH);
//			int y = (int) Math
//					.floor(subject.position.y / Constants.PLAYERWIDTH);
//
//			if (subject.direction == Direction.Left)
//			{
//				if (x >= 0)
//				{
//					Block tmpBlock = (Block) Assoc.get(y).get(x);
//					System.out.println(tmpBlock.getType().toString() + " x: " + tmpBlock.getPosition().x + " y: " + tmpBlock.getPosition().y);
//					if (!tmpBlock.getType().isfluid()
//							&& tmpBlock.getType().getMass() > 0)
//					{
//						subject.collidedDirection = Direction.Left;
//						subject.colided = true;
//					}
//					y++;
//					System.out.println("x: " + x + " y: " + y + " Postition: "
//							+ subject.position);
//					tmpBlock = (Block) Assoc.get(y).get(x);
//
//					if (!tmpBlock.getType().isfluid()
//							&& tmpBlock.getType().getMass() > 0)
//					{
//						subject.collidedDirection = Direction.Left;
//						subject.colided = true;
//					}
//				}
//
//			} else if (subject.direction == Direction.Right)
//			{
//				x++;
//				if (x <= Constants.MAPWIDTH - 1)
//				{
//					Block tmpBlock = (Block) Assoc.get(y).get(x);
//					System.out.println(tmpBlock.getType().toString() + " x: " + tmpBlock.getPosition().x + " y: " + tmpBlock.getPosition().y);
//					if (!tmpBlock.getType().isfluid()
//							&& tmpBlock.getType().getMass() > 0)
//					{
//						subject.collidedDirection = Direction.Right;
//						subject.colided = true;
//					}
//					y++;
//					System.out.println("x: " + x + " y: " + y + " Postition: "
//							+ subject.position);
//					tmpBlock = (Block) Assoc.get(y).get(x);
//
//					if (!tmpBlock.getType().isfluid()
//							&& tmpBlock.getType().getMass() > 0)
//					{
//						subject.collidedDirection = Direction.Right;
//						subject.colided = true;
//					}
//				}
//
//			}
//
//		}
//
//		return false;
//	}

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

	private void updateAssoc()
	{
		for (GameComponent gc : Components.GetInstance())
		{
			if (gc instanceof Block)
			{
				Block block = (Block) gc;

				int x = (int) Math.floor(block.getPosition().x
						/ Constants.BLOCKWIDTH);
				int y = (int) Math.floor(block.getPosition().y
						/ Constants.BLOCKHEIGHT);

				if (block != Assoc.get(y).get(x))
				{

				}

			}

		}
	}
	
	/**
	 * Returns all blocks with are within and on the border of the reactangle
	 */
	public ArrayList<Block> getBlocks(Rectangle r){
		ArrayList<Block> list = new ArrayList<Block>();
		Vector2i rBlockTopLeft = r.getBlockRasterTopLeftPosition(Courner.TopLeft);
		Vector2i rBlockBottomRight = r.getBlockRasterTopLeftPosition(Courner.BottomRight);
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

}
