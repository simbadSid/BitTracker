package main.java.IO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class DataPersistence_file implements DataPersistenceInterface
{
//=======================================
// Attributes
//=======================================
	public static final String OUTPUT_DIR = "outputFiles/";
	private String fileName;


//=======================================
// Constructor
//=======================================
	public DataPersistence_file(String dateAndTime)
	{
		this.fileName = Paths.get("").toAbsolutePath() + "/" + OUTPUT_DIR + dateAndTime;
		File file = new File(fileName);

		//Case: file already exists: empty it
		try
		{
			if (!file.createNewFile())
			{
				new FileWriter(this.fileName, false).close();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Error at the initialization of " + this.getClass().getSimpleName() + e.getStackTrace());
		}
	}


//=======================================
// Local methods
//=======================================
	@Override
	public void persistLine(Object line)
	{
	    try
	    {
		    BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName, true));

		    writer.append('\n');
		    writer.append(line.toString());
		    writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			throw new RuntimeException(String.format("Error while persisting the line %s in the file %s (class %s)", line.toString(), this.fileName, this.getClass().getSimpleName()));
		}
	}
}
