package species;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import stuff.Coordinates;
import stuff.Names;
import stuff.World;

public abstract class Organism
{
	protected char type;
	protected int age;
	protected World fromWorld;
	protected int strength;
	protected int initiative;
	protected Coordinates position;
	protected static final int ADULT_AGE = 5;
	private	boolean alive;

	public Organism(World fromWorld, int x, int y)
	{
		this.fromWorld = fromWorld;
		this.init(x, y);
	}

	public Organism(World fromWorld)
	{
		this.fromWorld = fromWorld;
		this.init(ThreadLocalRandom.current().nextInt(0, fromWorld.getMaxXY().x), ThreadLocalRandom.current().nextInt(0, fromWorld.getMaxXY().y));
	}
	
	public abstract Organism clone(World fromWorld, Coordinates position);
	
	public abstract void action();
	
	public abstract String introduce();

	public boolean tryResistAttack(Organism attacker)
	{
		return false; // standard organisms can't resist attack
	}

	public char getType()
	{
		return this.type;
	}

	public char draw()
	{
		// if organism is below 5 turns draw it small. Just for fun.
		return this.age < ADULT_AGE ? Names.getLowercaseSymbol(this.type) : this.type;
	}

	public Coordinates getXY()
	{
		return this.position;
	}

	public int getAge()
	{
		return this.age;
	}

	public int getStrength()
	{
		return this.strength;
	}

	public int getInitiative()
	{
		return this.initiative;
	}

	public void incrementAge()
	{
		this.age++;
	}

	public void setStrength(int strength)
	{
		this.strength = strength;
	}

	public boolean isAlive()
	{
		return this.alive;
	}

	// fly, fly, PIZZA DIE!
	public void die()
	{
		this.alive = false; // mark organism as dead
	}

	@Override
	public String toString()
	{
		return this.type + ";" + this.age + ";" + this.strength + ";" + this.initiative + ";" + this.position.x + ";" + this.position.y;
	}
	
	// randomizes 2 to 4 new coordinates respecting the world limits
		protected ArrayList<Coordinates> randomizeFields()
		{
			ArrayList<Coordinates> randomized = new ArrayList<Coordinates>();

			if (this.position.x + 1 < fromWorld.getMaxXY().x)
				randomized.add(new Coordinates(this.position.x + 1, this.position.y));
			if (this.position.x > 0)
				randomized.add(new Coordinates(this.position.x - 1, this.position.y));
			if (this.position.y + 1 < fromWorld.getMaxXY().y)
				randomized.add(new Coordinates(this.position.x, this.position.y + 1));
			if (this.position.y > 0)
				randomized.add(new Coordinates(this.position.x, this.position.y - 1));

			Collections.shuffle(randomized);
			return randomized;
		}
		
	private void init(int x, int y)
	{
		this.position = new Coordinates(x, y);
		this.age = 0;
		this.alive = true;
	}
}