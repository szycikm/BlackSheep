package plants;

import species.Organism;
import species.Plant;
import stuff.Coordinates;
import stuff.World;

public class Grass extends Plant
{
	public Grass(World fromWorld, int x, int y, int age, int strength, int initiative)
	{
		super(fromWorld, x, y);
		this.type = 'G';
		this.strength = strength;
		this.initiative = initiative;
		this.age = age;
	}
	
	public Grass(World fromWorld, int x, int y)
	{
		super(fromWorld, x, y);
		this.Init();
	}
	
	public Grass(World fromWorld)
	{
		super(fromWorld);
		this.Init();
	}
	
	@Override
	public Organism clone(World fromWorld, Coordinates position)
	{
		return new Grass(fromWorld, position.x, position.y);
	}
	
	private void Init()
	{
		this.type = 'G';
		this.strength = 0;
		this.initiative = 0;
	}
}