import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import util.Vector2i;

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
//	private boolean isColliding(MovableGameComponent subject){
//		Vector2f curPos = subject.getBlockRasterPosition();
//		Vector2f nextPos = subject.getNextBlockRasterPosition();
//		Vector2f velocity = subject.getBlockRasterVelocity();
//		
//		//we are going to check all blocks in with and between curPos and nextPos
//		Vector2i curTopLeftBlockPos = new Vector2i(curPos);
//		Vector2i curBottomRightBlockPos = new Vector2i(Constants.ConvertToBlockRaster(new Vector2f(subject.getPosition().x + Constants.PLAYERWIDTH, subject.getPosition().y + Constants.PLAYERHEIGHT)));
//		Vector2i nextTopLeftBlockPos = new Vector2i(nextPos);
//		Vector2i nextBottomRightBlockPos = new Vector2i(Constants.ConvertToBlockRaster(new Vector2f(subject.getNextPosition().x + Constants.PLAYERWIDTH, subject.getNextPosition().y + Constants.PLAYERHEIGHT)));
//		
//		int lowesty;
//		int lowestx;
//		
//		int heighsty;
//		int heighstx;
//		
//		//will be behind set to the farrest block
//		int closestBlockx;
//		int closestBlocky;
//		
//		if(velocity.x < 0){		//naar links
//			heighstx = curBottomRightBlockPos.x;
//			lowestx = nextTopLeftBlockPos.x;
//			closestBlockx = nextTopLeftBlockPos.x -1;
//		}else{					//naar rechts
//			heighstx = nextBottomRightBlockPos.x;
//			lowestx = curTopLeftBlockPos.x;
//			closestBlockx = nextBottomRightBlockPos.x + 1;
//		}
//		
//		if(velocity.y < 0){		//naar omhoog
//			heighsty = nextTopLeftBlockPos.y;
//			lowesty = curBottomRightBlockPos.y;
//			closestBlocky = nextTopLeftBlockPos.y + 1;
//		}else{					//naar omlaag
//			heighsty = curTopLeftBlockPos.y;
//			lowesty = nextBottomRightBlockPos.y;
//			closestBlocky = nextBottomRightBlockPos.y - 1;
//		}
//		
//		for (int mx = lowestx; mx <= heighstx; mx++){
//			for(int my = lowesty; my <= heighsty; my++){
//				Block tmpBlock = (Block) Assoc.get(my).get(mx);
//				if (!tmpBlock.getType().isfluid() && tmpBlock.getType().getMass() > 0){
//					//collision with block
//					Vector2i blockLocation = new Vector2i(tmpBlock.getBlockRasterPosition());
//					if(Constants.distanceInt(blockLocation.x, curTopLeftBlockPos.x) < Constants.distanceInt(closestBlockx, curTopLeftBlockPos.x)){
//						closestBlockx = blockLocation.x;
//					}
//					if(Constants.distanceInt(blockLocation.y, curTopLeftBlockPos.y) < Constants.distanceInt(closestBlocky, curTopLeftBlockPos.y)){
//						closestBlockx = blockLocation.y;
//					}
//				}
//			}
//		}
//	}

	private boolean isColliding(MovableGameComponent subject)
	{
		if (subject instanceof Player)
		{
			int x = (int) Math
					.floor(subject.position.x / Constants.PLAYERWIDTH);
			int y = (int) Math
					.floor(subject.position.y / Constants.PLAYERWIDTH);

			if (subject.direction == Direction.Left)
			{
				if (x >= 0)
				{
					Block tmpBlock = (Block) Assoc.get(y).get(x);
					if (!tmpBlock.getType().isfluid()
							&& tmpBlock.getType().getMass() > 0)
					{
						subject.collidedDirection = Direction.Left;
						subject.colided = true;
					}
					y++;
					System.out.println("x: " + x + " y: " + y + " Postition: "
							+ subject.position);
					tmpBlock = (Block) Assoc.get(y).get(x);

					if (!tmpBlock.getType().isfluid()
							&& tmpBlock.getType().getMass() > 0)
					{
						subject.collidedDirection = Direction.Left;
						subject.colided = true;
					}
				}

			} else if (subject.direction == Direction.Right)
			{
				x++;
				if (x <= Constants.MAPWIDTH - 1)
				{
					Block tmpBlock = (Block) Assoc.get(y).get(x);
					if (!tmpBlock.getType().isfluid()
							&& tmpBlock.getType().getMass() > 0)
					{
						subject.collidedDirection = Direction.Right;
						subject.colided = true;
					}
					y++;
					System.out.println("x: " + x + " y: " + y + " Postition: "
							+ subject.position);
					tmpBlock = (Block) Assoc.get(y).get(x);

					if (!tmpBlock.getType().isfluid()
							&& tmpBlock.getType().getMass() > 0)
					{
						subject.collidedDirection = Direction.Right;
						subject.colided = true;
					}
				}

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

}
