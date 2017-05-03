package stuff;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class World
{
	private	ArrayList<Organism> organisms;
	private	Coordinates maxxy;
	
	public World(int maxx, int maxy)
	{
		this.maxxy.x = maxx;
		this.maxxy.y = maxy;
	}
	
	public Coordinates GetMaxXY()
	{
		return this.maxxy;
	}
	
	public Organism GetOrganismByPosition(Coordinates position)
	{
		for (int i = 0; i < this.GetOrganismCount(); i++)
		{
			if (this.organisms.get(i).isAlive() && this.organisms.get(i).GetXY().x == position.x && this.organisms.get(i).GetXY().y == position.y)
				return this.organisms.get(i);
		}
		return null;

	}
	
	public void DoTurn()
	{
		// TODO check if this sorting actually heckin works
		Collections.sort(organisms, new Comparator<Organism>()
		{
	        public int compare(Organism first, Organism second)
	        {
	        	if (first == null || second == null) return -1; // if either organism is nullptr we should exit
	    		if (first.GetInitiative() == second.GetInitiative())
	    			return first.GetAge() > second.GetAge() ? 1 : -1;
	    		else
	    			return first.GetInitiative() > second.GetInitiative() ? 1 : -1;
	        }
	    });
		int cnt = this.GetOrganismCount(); // organism count can get bigger so it's important to keep it in separate variable
		for (int i = 0; i < cnt; i++)
		{
			if (this.organisms.get(i).isAlive())
				this.organisms.get(i).Action();

			if (this.organisms.get(i).isAlive()) // again, because this organism might have just stepped into stronger animal
				this.organisms.get(i).IncrementAge();
		}

		// clean dead organisms
		for (int i = 0; i < this.GetOrganismCount(); i++)
		{
			if (!this.organisms.get(i).isAlive())
			{
				this.organisms.remove(this.organisms.get(i));
				i--; // because we just deleted i element
			}
		}
	}
	
	public boolean AddOrganism(Organism o)
	{
		if (o.GetXY().x >= this.maxxy.x || o.GetXY().y >= this.maxxy.y || o.GetXY().x < 0 || o.GetXY().y < 0)
		{
			// coordinates outside of this world
			return false;
		}
		else if (this.GetOrganismByPosition(o.GetXY()) != null)
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
	
	public int GetOrganismCount()
	{
		return this.organisms.size();
	}
	
	public void PrintWorld()
	{
		// TODO print world somehow...
	}
	
	public String toString()
	{
		String everything = this.maxxy.x + "\n" + this.maxxy.y + "\n";
		for (int i = 0; i < this.GetOrganismCount(); i++)
		{
			everything += this.organisms.get(i).toString() + "\n";
		}
		return everything;
	}
}