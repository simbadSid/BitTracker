package main.java.IO;



public abstract class DataPersistence
{
//=======================================
// Attributes
//=======================================
	protected static DataPersistenceInterface dataPersistenceClass;


//=======================================
// Local methods
//=======================================
	public static void initDataPersistence(DataPersistenceInterface dataPersistenceInterface)
	{
		DataPersistence.dataPersistenceClass = dataPersistenceInterface;
	}

	public static void XXXXX(String str)
	{
		assert(DataPersistence.dataPersistenceClass != null);
		DataPersistence.dataPersistenceClass.XXXXX(str);
	};
}
