package animals;

import species.Animal;
import species.Organism;
import stuff.Coordinates;
import stuff.Logger;
import stuff.World;

public class Fox extends Animal
{
	public Fox(World fromWorld, int x, int y, int age, int strength, int initiative, String name)
	{
		super(fromWorld, x, y);
		this.type = 'F';
		this.strength = strength;
		this.initiative = initiative;
		this.name = name;
		this.age = age;
	}
	
	public Fox(World fromWorld, int x, int y)
	{
		super(fromWorld, x, y);
		this.Init();
	}
	
	public Fox(World fromWorld)
	{
		super(fromWorld);
		this.Init();
	}
	
	@Override
	public Organism Clone(World fromWorld, Coordinates position)
	{
		return new Fox(fromWorld, position.x, position.y);
	}
	
	@Override
	public void Action()
	{
		// sneaky fox checks all directions and decides where to move (if at all)
		for (Coordinates coords : RandomizeFields())
		{
			Organism collider = this.fromWorld.GetOrganismByPosition(coords);
			// move to empty field, or attack weaker organism. sneaky
			if (collider == null || (collider != null && collider.GetStrength() <= this.GetStrength()))
			{
				this.Move(coords);
				return;
			}
		}

		Logger.writeMessage(this.Introduce() + " decided to stay in place");
	}
	
	@Override
	public String toString()
	{
		return super.toString() + ";";
	}
		
	private void Init()
	{
		this.type = 'F';
		this.strength = 3;
		this.initiative = 7;
	}
}