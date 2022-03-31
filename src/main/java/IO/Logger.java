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
		assert(Logger.loggerClass != null);
		Logger.loggerClass.log(str);
	};
}
