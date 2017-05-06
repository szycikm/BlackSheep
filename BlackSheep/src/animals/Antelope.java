package animals;

import species.Animal;
import species.Organism;
import stuff.Coordinates;
import stuff.Logger;
import stuff.World;

public class Antelope extends Animal
{
	public Antelope(World fromWorld, int x, int y, int age, int strength, int initiative, String name)
	{
		super(fromWorld, x, y);
		this.type = 'A';
		this.strength = strength;
		this.initiative = initiative;
		this.name = name;
		this.age = age;
	}
	public Antelope(World fromWorld, int x, int y)
	{
		super(fromWorld, x, y);
		this.init();
	}
	
	public Antelope(World fromWorld)
	{
		super(fromWorld);
		this.init();
	}
	
	@Override
	public Organism clone(World fromWorld, Coordinates position)
	{
		return new Antelope(fromWorld, position.x, position.y);
	}
	
	@Override
	public void action()
	{
		for (int i = 0; i < 2; i++)
		{
			if(this.isAlive())
				this.move(this.randomizeField());
		}

	}
	
	@Override
	public boolean tryResistAttack(Organism attacker)
	{
		for (Coordinates newPosition : this.randomizeFields())
		{
			if (this.fromWorld.getOrganismByPosition(newPosition) == null)
			{
				this.move(newPosition);
				Logger.writeMessage(this.introduce() + " got away from " + attacker.introduce());
				return true;
			}
		}
		Logger.writeMessage(this.introduce() + " tried to get away, but you can't hide from " + attacker.introduce());
		return false;

	}
	
	@Override
	public String toString()
	{
		return super.toString() + ";";
	}
	
	private void init()
	{
		this.type = 'A';
		this.strength = 4;
		this.initiative = 4;
	}
}