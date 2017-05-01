package stuff;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public final class Names
{
	private static Map<Character, String> speciesNames;
	private static Vector<String> names;

	static
	{
		Names.speciesNames = new HashMap<Character, String>();
		Names.names = new Vector<String>();
	}
	
	public static void PutSpeciesName(Character key, String value)
	{
		speciesNames.put(key, value);
	}
	
	public static void AddName(String value)
	{
		names.add(value);
	}

	public static char GetLowercaseSymbol(char type)
	{
		return Character.toLowerCase(type);
	}

	public static String GetSpeciesName(char type)
	{
		return speciesNames.containsKey(type) ? speciesNames.get(type) : "Undefined";
	}

	public static String GetRandomName()
	{
		return names.get(ThreadLocalRandom.current().nextInt(names.size()));
	}
}
