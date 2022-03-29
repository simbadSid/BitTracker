package main.java;


import java.time.LocalDateTime;

import main.java.automaton.StateAutomaton;
import main.java.automaton.Strategy0_straightForward_buy;
import main.java.automaton.Strategy0_straightForward_sell;
import main.java.engine.BasePoint;
import main.java.engine.BitValue;
import main.java.engine.Engine;
import main.java.engine.Engine_timmer;
import main.java.engine.PositionType;
import main.java.utils.Logger;

public class BitTracker
{
//=======================================
// Attributes
//=======================================
	private Class<? extends StateAutomaton>	classStateAutomatonBuyer	= Strategy0_straightForward_buy.class;
	private Class<? extends StateAutomaton>	classStateAutomatonSeller	= Strategy0_straightForward_sell.class;

	private Engine engine;


//=======================================
// Constructor
//=======================================
	public BitTracker(Class<? extends Engine> classEngine)
	{
		try
		{
			this.engine = classEngine.getConstructor().newInstance();
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
		BitTracker bitTracker = new BitTracker(Engine_timmer.class);

		bitTracker.initBuyer();
		bitTracker.launch();
	}
}
