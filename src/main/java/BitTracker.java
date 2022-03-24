package main.java;

import main.java.utils.Logger;

import main.java.engine.Engine;
import main.java.engine.Engine_timmer;

public class BitTracker
{

	public static void main(String[] args)
	{
		Engine engine = new Engine_timmer();

		Logger.log("Begin Program");

		engine.start();

		Logger.log("End Program");
	}
}
