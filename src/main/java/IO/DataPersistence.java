package main.java.IO;

import java.util.LinkedList;

public abstract class DataPersistence
{
//=======================================
// Attributes
//=======================================
	protected static DataPersistenceInterface dataPersistenceClass;

	protected static LinkedList<Object> buffer = new LinkedList<Object>();


//=======================================
// Local methods
//=======================================
	public static void initDataPersistence(DataPersistenceInterface dataPersistenceInterface)
	{
		DataPersistence.dataPersistenceClass = dataPersistenceInterface;
	}

	/**
	 * Add a new line to the buffer.
	 * @warning The given line is not persisted (till we call flushBuffer).
	 * @param line Object to persist (using its toString method).
	 */
	public static void addLine(Object line)
	{
		DataPersistence.checkInitialisation();
		DataPersistence.buffer.add(line);
	}

	/**
	 * @return the list of object that have been added using addLine
	 */
	public static LinkedList<Object> getBuffer()
	{
		DataPersistence.checkInitialisation();
		return new LinkedList<Object>(DataPersistence.buffer);
	}

	/**
	 * Remove all the objects that have been added (using addLine).
	 * @warning The lines in the buffer are lost and will not be persisted.
	 */
	public static void emptyBuffer()
	{
		DataPersistence.checkInitialisation();
		DataPersistence.buffer.clear();
	}

	/**
	 * Persist the lines within the buffer and empty the buffer.
	 */
	public static void flushBuffer()
	{
		DataPersistence.checkInitialisation();
		for (Object line : DataPersistence.buffer)
		{
			DataPersistence.dataPersistenceClass.persistLine(line);
		}
	}


//=======================================
// Private methods
//=======================================
	private static void checkInitialisation()
	{
		if (DataPersistence.dataPersistenceClass == null)
		{
			throw new RuntimeException("Class " + DataPersistence.class + " is called without being initialized");
		}
	}
}
