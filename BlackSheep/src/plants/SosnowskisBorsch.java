package plants;

import species.Animal;
import species.Organism;
import species.Plant;
import stuff.Coordinates;
import stuff.Logger;
import stuff.World;

public class SosnowskisBorsch extends Plant
{
	public SosnowskisBorsch(World fromWorld, int x, int y, int age, int strength, int initiative)
	{
		super(fromWorld, x, y);
		this.type = 'C';
		this.strength = strength;
		this.initiative = initiative;
		this.age = age;
	}
	
	public SosnowskisBorsch(World fromWorld, int x, int y)
	{
		super(fromWorld, x, y);
		this.init();
	}
	
	public SosnowskisBorsch(World fromWorld)
	{
		super(fromWorld);
		this.init();
	}
	
	@Override
	public Organism clone(World fromWorld, Coordinates position)
	{
		return new SosnowskisBorsch(fromWorld, position.x, position.y);
	}
	
	@Override
	public void action()
	{
		for (Coordinates target : this.randomizeFields())
		{
			Organism victim = this.fromWorld.getOrganismByPosition(target);
			// only kill animals that get too close
			if (victim != null && victim instanceof Animal)
			{
				Logger.writeMessage(victim.introduce() + " got too close to " + this.introduce());
				victim.die();
			}
		}
		super.action(); // after it kills everything, it tries to sew, like any normal plant
	}
	
	private void init()
	{
		this.type = 'C';
		this.strength = 10;
		this.initiative = 0;
	}
}