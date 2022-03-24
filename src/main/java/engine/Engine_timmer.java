package main.java.engine;

import java.util.Timer;
import java.util.TimerTask;

import sun.misc.Signal;
import main.java.utils.Logger;




public class Engine_timmer extends Engine
{
//=======================================
// Attributes
//=======================================
	private Timer		timer;
	private TimerTask	timerTask;
	
//=======================================
// Local methods
//=======================================
	@Override
	public void start()
	{
		Logger.log("Start " + this.getClass());

		Signal.handle(new Signal("INT"),  signal -> this.stop());
		this.timer		= new Timer(); 
	    this.timerTask	= new TimerTask()
	    {
			public void run() 
			{
				checkUpdatePosition();
			}
	    };

	    timer.schedule(timerTask, 0, 3000);
	}

	@Override
	public void stop()
	{
		Logger.log("Stop " + this.getClass());

		this.timer.cancel();
		this.timer.purge();
	}
}
