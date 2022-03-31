package main.java.backend;


import java.time.LocalDateTime;

import main.java.IO.DataPersistence;
import main.java.IO.DataPersistenceInterface;
import main.java.IO.DataPersistence_file;
import main.java.IO.Logger;
import main.java.IO.LoggerInterface;
import main.java.IO.Logger_terminal;
import main.java.backend.automaton.StateAutomaton;
import main.java.backend.automaton.Strategy0_straightForward_buy;
import main.java.backend.automaton.Strategy0_straightForward_sell;
import main.java.backend.engine.BasePoint;
import main.java.backend.engine.BitValue;
import main.java.backend.engine.Engine;
import main.java.backend.engine.Engine_timmer;
import main.java.backend.engine.PositionType;

public class BitTracker
{
//=======================================
// Attributes
//=======================================
	private Class<? extends StateAutomaton>	classStateAutomatonBuyer;
	private Class<? extends StateAutomaton>	classStateAutomatonSeller;
	private Engine engine;


//=======================================
// Constructor
//=======================================
	public BitTracker(	Class<? extends StateAutomaton>				classStateAutomatonBuyer,
						Class<? extends StateAutomaton>				classStateAutomatonSeller,
						Class<? extends Engine>						classEngine,
						Class<? extends LoggerInterface>			classLogger,
						Class<? extends DataPersistenceInterface>	classDataPersistence)
	{
		this.classStateAutomatonBuyer = classStateAutomatonBuyer;
		this.classStateAutomatonSeller= classStateAutomatonSeller;
		try
		{
			this.engine									= classEngine.getConstructor().newInstance();
			LoggerInterface logger						= classLogger.getConstructor().newInstance();
			DataPersistenceInterface dataPersistence	= classDataPersistence.getConstructor().newInstance();

			Logger.initLogger(logger);
			DataPersistence.initDataPersistence(dataPersistence);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException();
		}
	}


//=======================================
// Local methods
//=======================================
	public void setBasePoint		(BasePoint basePoint)			{this.engine.setBasePoint(basePoint);}
	public void setStateAutomaton	(StateAutomaton stateAutomaton)	{this.engine.setStateAutomaton(stateAutomaton);}

	public void launch()
	{
		Logger.log("Launch " + this.getClass().getSimpleName());
		this.initBuyer();
		this.engine.start();
	}

	public void initBuyer()
	{
		this.engine.stop();

		try
		{
			StateAutomaton	stateAutomaton	= classStateAutomatonBuyer.getConstructor().newInstance();
			BasePoint		basePoint		= new BasePoint(LocalDateTime.now(), PositionType.BUYER_OBSERVER, BitValue.fetchNewValue());

			this.setBasePoint(basePoint);
			this.setStateAutomaton(stateAutomaton);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public void initSeller()
	{
		this.engine.stop();

		try
		{
			StateAutomaton	stateAutomaton	= classStateAutomatonSeller.getConstructor().newInstance();
			BasePoint		basePoint		= new BasePoint(LocalDateTime.now(), PositionType.SELLER_OBSERVER, BitValue.fetchNewValue());

			this.setBasePoint(basePoint);
			this.setStateAutomaton(stateAutomaton);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException();
		}
	}


//=======================================
// Local methods
//=======================================
	public static void main(String[] args)
	{
		BitTracker bitTracker = new BitTracker(
				Strategy0_straightForward_buy.class,
				Strategy0_straightForward_sell.class,
				Engine_timmer.class,
				Logger_terminal.class,
				DataPersistence_file.class);

		bitTracker.launch();
	}
}
