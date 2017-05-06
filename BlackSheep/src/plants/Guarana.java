package plants;

import species.Organism;
import species.Plant;
import stuff.Coordinates;
import stuff.Logger;
import stuff.World;

public class Guarana extends Plant
{
	public Guarana(World fromWorld, int x, int y, int age, int strength, int initiative)
	{
		super(fromWorld, x, y);
		this.type = 'U';
		this.strength = strength;
		this.initiative = initiative;
		this.age = age;
	}
	
	public Guarana(World fromWorld, int x, int y)
	{
		super(fromWorld, x, y);
		this.Init();
	}
	
	public Guarana(World fromWorld)
	{
		super(fromWorld);
		this.Init();
	}
	
	@Override
	public Organism clone(World fromWorld, Coordinates position)
	{
		return new Guarana(fromWorld, position.x, position.y);
	}
	
	@Override
	public boolean tryResistAttack(Organism attacker)
	{
		attacker.setStrength(attacker.getStrength() + 3);
		Logger.writeMessage(attacker.introduce() + " ate " + this.introduce() + " and is feeling stronger");
		return false;
	}
	
	private void Init()
	{
		this.type = 'U';
		this.strength = 0;
		this.initiative = 0;
	}
}