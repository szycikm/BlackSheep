package stuff;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Organism
{
	protected char type;
	protected int age;
	protected World fromWorld;
	protected int strength;
	protected int initiative;
	protected Coordinates position;
	private static final int ADULT_AGE = 5;
	private	boolean alive;

	public Organism(World fromWorld, int x, int y)
	{
		this.fromWorld = fromWorld;
		this.Init(x, y);
	}

	public Organism(World fromWorld)
	{
		this.fromWorld = fromWorld;
		this.Init(ThreadLocalRandom.current().nextInt(0, fromWorld.GetMaxXY().x), ThreadLocalRandom.current().nextInt(0, fromWorld.GetMaxXY().y));
	}
	
	public abstract Organism Clone(World fromWorld, Coordinates position);
	
	public abstract void Action();
	
	public abstract String Introduce();

	public boolean TryResistAttack(Organism attacker)
	{
		return false; // standard organisms can't resist attack
	}

	public char GetType()
	{
		return this.type;
	}

	public char Draw()
	{
		// if organism is below 5 turns draw it small. Just for fun.
		return this.age < ADULT_AGE ? Names.GetLowercaseSymbol(this.type) : this.type;
	}

	public Coordinates GetXY()
	{
		return this.position;
	}

	public int GetAge()
	{
		return this.age;
	}

	public int GetStrength()
	{
		return this.strength;
	}

	public int GetInitiative()
	{
		return this.initiative;
	}

	public void IncrementAge()
	{
		this.age++;
	}

	public void SetStrength(int strength)
	{
		this.strength = strength;
	}

	public boolean isAlive()
	{
		return this.alive;
	}

	// fly, fly, PIZZA DIE!
	public void Die()
	{
		this.alive = false; // mark organism as dead
	}

	@Override
	public String toString()
	{
		return this.type + ";" + this.age + ";" + this.strength + ";" + this.initiative + ";" + this.position.x + ";" + this.position.y;
	}
	
	// randomizes 2 to 4 new coordinates respecting the world limits
		protected ArrayList<Coordinates> RandomizeFields()
		{
			ArrayList<Coordinates> randomized = new ArrayList<Coordinates>();

			if (this.position.x + 1 < fromWorld.GetMaxXY().x)
				randomized.add(new Coordinates(this.position.x + 1, this.position.y));
			if (this.position.x > 0)
				randomized.add(new Coordinates(this.position.x - 1, this.position.y));
			if (this.position.y + 1 < fromWorld.GetMaxXY().y)
				randomized.add(new Coordinates(this.position.x, this.position.y + 1));
			if (this.position.y > 0)
				randomized.add(new Coordinates(this.position.x, this.position.y - 1));

			Collections.shuffle(randomized);
			return randomized;
		}
		
	private void Init(int x, int y)
	{
		this.position = new Coordinates(x, y);
		this.age = 0;
		this.alive = true;
	}
}