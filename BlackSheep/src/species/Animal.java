package species;

import stuff.Coordinates;
import stuff.Logger;
import stuff.Names;
import stuff.World;

public abstract class Animal extends Organism
{
	protected String name;
	
	public Animal(World fromWorld, int x, int y)
	{
		super(fromWorld, x, y);
		this.Init();
	}
	
	public Animal(World fromWorld)
	{
		super(fromWorld);
		this.Init();
	}
	
	// default animal movement
	@Override
	public void action()
	{
		this.move(this.randomizeField());
	}
	
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public void incrementAge()
	{
		super.incrementAge();
		if (this.getAge() == ADULT_AGE)
			Logger.writeMessage(this.introduce() + " is all grown up now!");
	}
	
	@Override
	public String introduce()
	{
		return Names.getSpeciesName(this.type) + " " + this.name;
	}
	
	@Override
	public String toString()
	{
		return super.toString() + ";" + this.name;
	}
	
	protected void move(Coordinates nextPosition)
	{
		Organism collider = this.fromWorld.getOrganismByPosition(nextPosition);
		if (Collision(collider))
		{
			position = nextPosition;
			Logger.writeMessage(this.introduce() + " moved to (" + this.position.x + "," + this.position.y + ")");
		}

	}
	
	protected Coordinates randomizeField()
	{
		return this.randomizeFields().get(0); // just grab first random generated value
	}
	
	private void Init()
	{
		this.name = Names.getRandomName();
	}
	
	// default animal collision logic. Returns: if animal should be moved or not
	private boolean Collision(Organism other)
	{
		if (other == null)
		{
			// nothing stands on new field or there's something dead -> just move there
			return true;
		}
		else if (this.getType() == other.getType())
		{
			// animal of the same type stands on this field -> just have sex and don't move there
			
			// check all four directions in random order
			for (Coordinates coords : randomizeFields())
			{
				Animal child = (Animal)(this.clone(this.fromWorld, coords));
				if (child instanceof Animal && this.fromWorld.addOrganism(child))
				{
					Logger.writeMessage("D'awww. " + this.introduce() + " and " + other.introduce() + " are having a baby! And it's name is " + child.getName());
					return false;
				}
			}

			// no place for new animal
			Logger.writeMessage(this.introduce() + " and " + other.introduce() + " wanted to have a baby, but the world decided otherwise");
			return false;
		}
		else
		{
			// stronger (or attacker) wins and takes looser's field
			if (this.getStrength() >= other.getStrength())
			{
				if (other.tryResistAttack(this))
				{
					Logger.writeMessage(other.introduce() + " resisted " + this.introduce() + "'s attack!");
					return false; // other organism reflected the attack -> don't move
				}
				else
				{
					Logger.writeMessage("Yeah! " + this.introduce() + " ate " + other.introduce() + "!");
					other.die();
					return true;
				}
			}
			else
			{
				if(other instanceof Plant)
					Logger.writeMessage("Oh no! " + this.introduce() + " ate " + other.introduce() + " and died!"); // other organism is plant
				else
					Logger.writeMessage("Oh no! " + other.introduce() + " ate " + this.introduce() + "!"); // other organism is animal

				this.die();
				return false;
			}
		}
	}
}