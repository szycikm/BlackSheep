package animals;

import species.Animal;
import species.Organism;
import stuff.Coordinates;
import stuff.Logger;
import stuff.World;

public class Fox extends Animal
{
	public Fox(World fromWorld, int x, int y, int age, int strength, int initiative, String name)
	{
		super(fromWorld, x, y);
		this.type = 'F';
		this.strength = strength;
		this.initiative = initiative;
		this.name = name;
		this.age = age;
	}
	
	public Fox(World fromWorld, int x, int y)
	{
		super(fromWorld, x, y);
		this.init();
	}
	
	public Fox(World fromWorld)
	{
		super(fromWorld);
		this.init();
	}
	
	@Override
	public Organism clone(World fromWorld, Coordinates position)
	{
		return new Fox(fromWorld, position.x, position.y);
	}
	
	@Override
	public void action()
	{
		// sneaky fox checks all directions and decides where to move (if at all)
		for (Coordinates coords : randomizeFields())
		{
			Organism collider = this.fromWorld.getOrganismByPosition(coords);
			// move to empty field, or attack weaker organism. sneaky
			if (collider == null || (collider != null && collider.getStrength() <= this.getStrength()))
			{
				this.move(coords);
				return;
			}
		}

		Logger.writeMessage(this.introduce() + " decided to stay in place");
	}
	
	@Override
	public String toString()
	{
		return super.toString() + ";";
	}
		
	private void init()
	{
		this.type = 'F';
		this.strength = 3;
		this.initiative = 7;
	}
}