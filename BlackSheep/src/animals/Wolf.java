package animals;

import species.Animal;
import species.Organism;
import stuff.Coordinates;
import stuff.World;

public class Wolf extends Animal
{
	public Wolf(World fromWorld, int x, int y, int age, int strength, int initiative, String name)
	{
		super(fromWorld, x, y);
		this.type = 'W';
		this.strength = strength;
		this.initiative = initiative;
		this.name = name;
		this.age = age;
	}
	
	public Wolf(World fromWorld, int x, int y)
	{
		super(fromWorld, x, y);
		this.init();
	}
	
	public Wolf(World fromWorld)
	{
		super(fromWorld);
		this.init();
	}
	
	@Override
	public Organism clone(World fromWorld, Coordinates position)
	{
		return new Wolf(fromWorld, position.x, position.y);
	}
	
	@Override
	public String toString()
	{
		return super.toString() + ";";
	}
	
	private void init()
	{
		this.type = 'W';
		this.strength = 9;
		this.initiative = 5;
	}
}