package stuff;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import animals.Human;
import species.Organism;

public class World
{
	private	ArrayList<Organism> organisms;
	private	Coordinates maxxy;
	private boolean humanAlive = false;
	
	public World(Coordinates maxxy)
	{
		this.maxxy = maxxy;
		organisms = new ArrayList<Organism>();
	}
	
	public Coordinates getMaxXY()
	{
		return this.maxxy;
	}
	
	public void setHumanAlive(boolean state)
	{
		this.humanAlive = state;
	}
	
	public boolean isHumanAlive()
	{
		return this.humanAlive;
	}
	
	public Organism getOrganismByPosition(Coordinates position)
	{
		for (int i = 0; i < this.getOrganismCount(); i++)
		{
			if (this.organisms.get(i).isAlive() && this.organisms.get(i).getXY().x == position.x && this.organisms.get(i).getXY().y == position.y)
				return this.organisms.get(i);
		}
		return null;
	}
	
	public Human getHuman()
	{
		for (int i = 0; i < this.getOrganismCount(); i++)
		{
			if (this.organisms.get(i) instanceof Human && this.organisms.get(i).isAlive())
				return (Human)this.organisms.get(i);
		}
		return null;
	}
	
	public void doTurn()
	{
		// TODO check if this sorting actually heckin works
		Collections.sort(organisms, new Comparator<Organism>()
		{
	        public int compare(Organism first, Organism second)
	        {
	        	if (first == null || second == null) return -1; // if either organism is nullptr we should exit
	    		if (first.getInitiative() == second.getInitiative())
	    			return first.getAge() > second.getAge() ? 1 : -1;
	    		else
	    			return first.getInitiative() > second.getInitiative() ? 1 : -1;
	        }
	    });
		int cnt = this.getOrganismCount(); // organism count can get bigger so it's important to keep it in separate variable
		for (int i = 0; i < cnt; i++)
		{
			if (this.organisms.get(i).isAlive())
				this.organisms.get(i).action();

			if (this.organisms.get(i).isAlive()) // again, because this organism might have just stepped into stronger animal
				this.organisms.get(i).incrementAge();
		}

		// clean dead organisms
		for (int i = 0; i < this.getOrganismCount(); i++)
		{
			if (!this.organisms.get(i).isAlive())
			{
				this.organisms.remove(this.organisms.get(i));
				i--; // because we just deleted i element
			}
		}
	}
	
	public boolean addOrganism(Organism o)
	{
		if (o.getXY().x >= this.maxxy.x || o.getXY().y >= this.maxxy.y || o.getXY().x < 0 || o.getXY().y < 0)
		{
			// coordinates outside of this world
			return false;
		}
		else if (this.getOrganismByPosition(o.getXY()) != null)
		{
			// field already occupied
			return false;
		}
		else
		{
			this.organisms.add(o);
			return true;
		}

	}
	
	public int getOrganismCount()
	{
		return this.organisms.size();
	}
	
	public String toString()
	{
		String everything = this.maxxy.x + "\n" + this.maxxy.y;
		for (int i = 0; i < this.getOrganismCount(); i++)
		{
			everything += "\n" + this.organisms.get(i).toString();
		}
		return everything;
	}
}