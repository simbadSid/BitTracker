package main.java.IO;



public abstract class Logger
{
//=======================================
// Attributes
//=======================================
	protected static LoggerInterface loggerClass;


//=======================================
// Local methods
//=======================================
	public static void initLogger(LoggerInterface loggerInterface)
	{
		Logger.loggerClass = loggerInterface;
	}

	public static void log(String str)
	{
		Logger.checkInitialisation();
		Logger.loggerClass.log(str);
	};


//=======================================
// Private methods
//=======================================
	private static void checkInitialisation()
	{
		if (Logger.loggerClass == null)
		{
			throw new RuntimeException("Class " + Logger.class + " is called without being initialized");
		}
	}
}
