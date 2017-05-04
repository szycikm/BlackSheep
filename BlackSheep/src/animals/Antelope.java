package animals;

import species.Animal;
import species.Organism;
import stuff.Coordinates;
import stuff.Logger;
import stuff.World;

public class Antelope extends Animal
{
	public Antelope(World fromWorld, int x, int y, int age, int strength, int initiative, String name)
	{
		super(fromWorld, x, y);
		this.type = 'A';
		this.strength = strength;
		this.initiative = initiative;
		this.name = name;
		this.age = age;
	}
	public Antelope(World fromWorld, int x, int y)
	{
		super(fromWorld, x, y);
		this.Init();
	}
	
	public Antelope(World fromWorld)
	{
		super(fromWorld);
		this.Init();
	}
	
	@Override
	public Organism Clone(World fromWorld, Coordinates position)
	{
		return new Antelope(fromWorld, position.x, position.y);
	}
	
	@Override
	public void Action()
	{
		for (int i = 0; i < 2; i++)
		{
			if(this.isAlive())
				this.Move(this.RandomizeField());
		}

	}
	
	@Override
	public boolean TryResistAttack(Organism attacker)
	{
		for (Coordinates newPosition : this.RandomizeFields())
		{
			if (this.fromWorld.GetOrganismByPosition(newPosition) == null)
			{
				this.Move(newPosition);
				Logger.writeMessage(this.Introduce() + " got away from " + attacker.Introduce());
				return true;
			}
		}
		Logger.writeMessage(this.Introduce() + " tried to get away, but you can't hide from " + attacker.Introduce());
		return false;

	}
	
	@Override
	public String toString()
	{
		return super.toString() + ";";
	}
	
	private void Init()
	{
		this.type = 'A';
		this.strength = 4;
		this.initiative = 4;
	}
}