import java.util.ArrayList;

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
