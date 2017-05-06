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
	
	public static void putSpeciesName(Character key, String value)
	{
		speciesNames.put(key, value);
	}
	
	public static void addName(String value)
	{
		names.add(value);
	}

	public static char getLowercaseSymbol(char type)
	{
		return Character.toLowerCase(type);
	}

	public static String getSpeciesName(char type)
	{
		return speciesNames.containsKey(type) ? speciesNames.get(type) : "Undefined";
	}

	public static String getRandomName()
	{
		return names.get(ThreadLocalRandom.current().nextInt(names.size()));
	}
}
