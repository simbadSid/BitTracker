package main.java.IO;

public class Logger_terminal implements LoggerInterface
{
//=======================================
// Local methods
//=======================================
	public void log(String str)
	{
		System.out.println("-- " + str);
	}
}
