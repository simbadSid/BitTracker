package main.java.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;

public class Utils
{
	/**
	 * 
	 * @param fields
	 * @param obj
	 * @return Transforms the input obj to a string containing all the private and public fields of the object
	 */
	public static String genericToString(Field[] fields, Object obj)
	{
		LinkedList<String> resultList = new LinkedList<String>();

		try
		{
			for (Field field : fields)
			{
				if (Modifier.isFinal(field.getModifiers()))
					continue;
				field.setAccessible(true);
				resultList.add(field.getName() + ": " + field.get(obj));
			}
		}
		catch (IllegalArgumentException | IllegalAccessException e)
		{
			e.printStackTrace();
			throw new RuntimeException();
		}

		String res = "";
		if (!resultList.isEmpty())
		{
			res += resultList.removeFirst();
			for (String str : resultList)
				res +=  ", " + str;
		}

		return "{" + res + "}";
	}
}
