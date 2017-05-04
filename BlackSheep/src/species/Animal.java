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
	public void Action()
	{
		this.Move(this.RandomizeField());
	}
	
	public String GetName()
	{
		return this.name;
	}
	
	@Override
	public void IncrementAge()
	{
		super.IncrementAge();
		if (this.GetAge() == ADULT_AGE)
			Logger.writeMessage(this.Introduce() + " is all grown up now!");
	}
	
	@Override
	public String Introduce()
	{
		return Names.GetSpeciesName(this.type) + " " + this.name;
	}
	
	@Override
	public String toString()
	{
		return super.toString() + ";" + this.name;
	}
	
	protected void Move(Coordinates nextPosition)
	{
		Organism collider = this.fromWorld.GetOrganismByPosition(nextPosition);
		if (Collision(collider))
		{
			position = nextPosition;
			Logger.writeMessage(this.Introduce() + " moved to (" + this.position.x + "," + this.position.y + ")");
		}

	}
	
	protected Coordinates RandomizeField()
	{
		return this.RandomizeFields().get(0); // just grab first random generated value
	}
	
	private void Init()
	{
		this.name = Names.GetRandomName();
	}
	
	// default animal collision logic. Returns: if animal should be moved or not
	private boolean Collision(Organism other)
	{
		if (other == null)
		{
			// nothing stands on new field or there's something dead -> just move there
			return true;
		}
		else if (this.GetType() == other.GetType())
		{
			// animal of the same type stands on this field -> just have sex and don't move there
			
			// check all four directions in random order
			for (Coordinates coords : RandomizeFields())
			{
				Animal child = (Animal)(this.Clone(this.fromWorld, coords));
				if (child instanceof Animal && this.fromWorld.AddOrganism(child))
				{
					Logger.writeMessage("D'awww. " + this.Introduce() + " and " + other.Introduce() + " are having a baby! And it's name is " + child.GetName());
					return false;
				}
			}

			// no place for new animal
			Logger.writeMessage(this.Introduce() + " and " + other.Introduce() + " wanted to have a baby, but the world decided otherwise");
			return false;
		}
		else
		{
			// stronger (or attacker) wins and takes looser's field
			if (this.GetStrength() >= other.GetStrength())
			{
				if (other.TryResistAttack(this))
				{
					Logger.writeMessage(other.Introduce() + " resisted " + this.Introduce() + "'s attack!");
					return false; // other organism reflected the attack -> don't move
				}
				else
				{
					Logger.writeMessage("Yeah! " + this.Introduce() + " ate " + other.Introduce() + "!");
					other.Die();
					return true;
				}
			}
			else
			{
				if(other instanceof Plant)
					Logger.writeMessage("Oh no! " + this.Introduce() + " ate " + other.Introduce() + " and died!"); // other organism is plant
				else
					Logger.writeMessage("Oh no! " + other.Introduce() + " ate " + this.Introduce() + "!"); // other organism is animal

				this.Die();
				return false;
			}
		}
	}
}