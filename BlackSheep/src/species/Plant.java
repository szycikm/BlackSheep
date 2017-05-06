package species;

import java.util.concurrent.ThreadLocalRandom;
import stuff.Coordinates;
import stuff.Names;
import stuff.World;

public abstract class Plant extends Organism
{
	public Plant(World fromWorld, int x, int y)
	{
		super(fromWorld, x, y);
	}
	
	public Plant(World fromWorld)
	{
		super(fromWorld);
	}
	
	@Override
	public void action()
	{
		// 1/4 chance to do anything
		if (ThreadLocalRandom.current().nextInt(0, 3) > 2)
		{
			for (Coordinates pos : randomizeFields())
			{
				if (this.fromWorld.getOrganismByPosition(pos) == null)
				{
					this.fromWorld.addOrganism(this.clone(this.fromWorld, pos));
					return; // end turn after successful sew
				}
			}
		}
	}
	
	@Override
	public String introduce()
	{
		return Names.getSpeciesName(this.type);
	}
	
	@Override
	public String toString()
	{
		return super.toString() + ";;";
	}
}