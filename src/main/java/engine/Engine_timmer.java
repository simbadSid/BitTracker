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
		if (this.timer != null)
			return;

		Logger.log(String.format("Start %s with base-point: %s", this.getClass().getSimpleName(), super.basePoint));

		Signal.handle(new Signal("INT"),  signal -> this.stop());
		this.timer		= new Timer();
	    this.timerTask	= new TimerTask()
	    {
			public void run()
			{
				updatePosition();
			}
	    };

	    timer.schedule(timerTask, 0, 3000);
	}

	@Override
	public void stop()
	{
		if (this.timer == null)
			return;

		Logger.log("Stop " + this.getClass());

		this.timer.cancel();
		this.timer.purge();

		this.timer		= null;
		this.timerTask	= null;
	}
}
