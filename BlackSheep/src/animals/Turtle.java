package animals;

import java.util.concurrent.ThreadLocalRandom;
import species.Animal;
import species.Organism;
import stuff.Coordinates;
import stuff.Logger;
import stuff.World;

public class Turtle extends Animal
{
	private static final int TURTLE_RESIST_STRENGTH = 5;
	
	public Turtle(World fromWorld, int x, int y, int age, int strength, int initiative, String name)
	{
		super(fromWorld, x, y);
		this.type = 'T';
		this.strength = strength;
		this.initiative = initiative;
		this.name = name;
		this.age = age;
	}
	
	public Turtle(World fromWorld, int x, int y)
	{
		super(fromWorld, x, y);
		this.init();
	}
	
	public Turtle(World fromWorld)
	{
		super(fromWorld);
		this.init();
	}
	
	@Override
	public Organism clone(World fromWorld, Coordinates position)
	{
		return new Turtle(fromWorld, position.x, position.y);
	}
	
	@Override
	public boolean tryResistAttack(Organism attacker)
	{
		return attacker.getStrength() < TURTLE_RESIST_STRENGTH;
	}
	
	@Override
	public void action()
	{
		// turtle moves with 25% chance
		if (ThreadLocalRandom.current().nextInt(0, 3) == 0)
			super.action();
		else
			Logger.writeMessage(this.introduce() + " decided not to move");
	}
	
	@Override
	public String toString()
	{
		return super.toString() + ";";
	}
	
	private void init()
	{
		this.type = 'T';
		this.strength = 2;
		this.initiative = 1;
	}
}