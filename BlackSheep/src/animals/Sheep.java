package animals;

import species.Animal;
import species.Organism;
import stuff.Coordinates;
import stuff.World;

public class Sheep extends Animal
{
	public Sheep(World fromWorld, int x, int y, int age, int strength, int initiative, String name)
	{
		super(fromWorld, x, y);
		this.type = 'S';
		this.strength = strength;
		this.initiative = initiative;
		this.name = name;
		this.age = age;
	}
	
	public Sheep(World fromWorld, int x, int y)
	{
		super(fromWorld, x, y);
		this.init();
	}
	
	public Sheep(World fromWorld)
	{
		super(fromWorld);
		this.init();
	}
	
	@Override
	public Organism clone(World fromWorld, Coordinates position)
	{
		return new Sheep(fromWorld, position.x, position.y);
	}
	
	@Override
	public String toString()
	{
		return super.toString() + ";";
	}
	
	private void init()
	{
		this.type = 'S';
		this.strength = 4;
		this.initiative = 4;
	}
}