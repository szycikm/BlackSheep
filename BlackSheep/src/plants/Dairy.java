package plants;

import species.Organism;
import species.Plant;
import stuff.Coordinates;
import stuff.World;

public class Dairy extends Plant
{
	public Dairy(World fromWorld, int x, int y, int age, int strength, int initiative)
	{
		super(fromWorld, x, y);
		this.type = 'D';
		this.strength = strength;
		this.initiative = initiative;
		this.age = age;
	}
	
	public Dairy(World fromWorld, int x, int y)
	{
		super(fromWorld, x, y);
		this.Init();
	}
	
	public Dairy(World fromWorld)
	{
		super(fromWorld);
		this.Init();
	}
	
	@Override
	public Organism Clone(World fromWorld, Coordinates position)
	{
		return new Dairy(fromWorld, position.x, position.y);
	}
	
	@Override
	public void Action()
	{
		for (int i = 0; i < 3; i++)
		{
			super.Action();
		}
	}
	
	private void Init()
	{
		this.type = 'D';
		this.strength = 0;
		this.initiative = 0;
	}
}