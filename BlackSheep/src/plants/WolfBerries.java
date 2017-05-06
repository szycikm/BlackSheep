package plants;

import species.Organism;
import species.Plant;
import stuff.Coordinates;
import stuff.World;

public class WolfBerries extends Plant
{
	public WolfBerries(World fromWorld, int x, int y, int age, int strength, int initiative)
	{
		super(fromWorld, x, y);
		this.type = 'B';
		this.strength = strength;
		this.initiative = initiative;
		this.age = age;
	}
	
	public WolfBerries(World fromWorld, int x, int y)
	{
		super(fromWorld, x, y);
		this.init();
	}
	
	public WolfBerries(World fromWorld)
	{
		super(fromWorld);
		this.init();
	}
	
	@Override
	public Organism clone(World fromWorld, Coordinates position)
	{
		return new WolfBerries(fromWorld, position.x, position.y);
	}
		
	private void init()
	{
		this.type = 'B';
		this.strength = 99;
		this.initiative = 0;
	}
}