package animals;

import species.Animal;
import species.Organism;
import stuff.Coordinates;
import stuff.Logger;
import stuff.World;

public class Human extends Animal
{
	private static final int SPECIAL_COUNTDOWN = 5;
	private static final int SPECIAL_STRENGTH = 10;
	private int specialCountdown;
	private HumanTasks nextTask = HumanTasks.DO_NOTHING;
	
	public Human(World fromWorld, int x, int y, int age, int strength, int initiative, String name, int specialCountdown)
	{
		super(fromWorld, x, y);
		this.type = 'H';
		this.strength = strength;
		this.initiative = initiative;
		this.name = name;
		this.age = age;
		this.specialCountdown = specialCountdown;
	}
	
	public Human(World fromWorld, int x, int y)
	{
		super(fromWorld, x, y);
		this.Init();
	}
	
	public Human(World fromWorld)
	{
		super(fromWorld);
		this.Init();
	}
	
	@Override
	public Organism Clone(World fromWorld, Coordinates position)
	{
		return new Human(fromWorld, position.x, position.y);
	}
	
	@Override
	public void Action()
	{
		if (this.specialCountdown > 0)
		{
			this.specialCountdown--;
			this.strength--;
			Logger.writeMessage(this.Introduce() + "'s strength is dropping! " + this.specialCountdown + " turns till normal");
		}

		switch (this.nextTask)
		{
		case GO_UP:
				Move(new Coordinates(this.position.x, this.position.y - 1));
			break;
		case GO_DOWN:
				Move(new Coordinates(this.position.x, this.position.y + 1));
			break;
		case GO_RIGHT:
				Move(new Coordinates(this.position.x + 1, this.position.y));
			break;
		case GO_LEFT:
				Move(new Coordinates(this.position.x - 1, this.position.y));
			break;
		case DO_SPECIAL:
			Logger.writeMessage(this.Introduce() + " used their special ability!");
			this.specialCountdown = SPECIAL_COUNTDOWN;
			this.strength = SPECIAL_STRENGTH;
			break;
		default:
			Logger.writeMessage(this.Introduce() + " had nothing to do this turn");
		}
		
		this.nextTask = HumanTasks.DO_NOTHING;
	}
	
	public boolean isTaskLegal(HumanTasks task)
	{
		switch (task)
		{
		case GO_UP:
			return this.position.y - 1 >= 0;
		case GO_DOWN:
			return this.position.y + 1 < this.fromWorld.GetMaxXY().y;
		case GO_LEFT:
			return this.position.x - 1 >= 0;
		case GO_RIGHT:
			return this.position.x + 1 < this.fromWorld.GetMaxXY().x;
		case DO_SPECIAL:
			return this.specialCountdown <= 0;
		default:
			return false;
		}
	}
	
	public void setNextTask(HumanTasks task)
	{
		this.nextTask = task;
	}
	
	@Override
	public void Die()
	{
		super.Die();
		fromWorld.setHumanAlive(false);
	}
	
	@Override
	public String toString()
	{
		return super.toString() + ";" + this.specialCountdown;
	}
	
	private void Init()
	{
		this.type = 'H';
		this.strength = 5;
		this.initiative = 4;
		this.specialCountdown = 0;
	}
}