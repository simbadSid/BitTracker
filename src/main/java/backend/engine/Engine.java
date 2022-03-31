package main.java.backend.engine;

import main.java.IO.Logger;
import main.java.backend.automaton.StateAutomaton;






public abstract class Engine
{
//=======================================
// Attributes
//=======================================
	protected	BasePoint		basePoint;
	private		StateAutomaton	stateAutomaton;


//=======================================
// Local methods
//=======================================
	public void setBasePoint		(BasePoint basePoint)			{this.basePoint			= new BasePoint(basePoint);}
	public void setStateAutomaton	(StateAutomaton stateAutomaton)	{this.stateAutomaton	= stateAutomaton;}


//=======================================
// Local methods
//=======================================
	public abstract void start();

	public abstract void stop();


//=======================================
// Private methods
//=======================================
	protected void updatePosition()
	{
		Logger.log("\n--------------------------------------");
		Logger.log(" New tick for position check: " + this.stateAutomaton.getCurrentPosition());

		BitValue currentSample = BitValue.fetchNewValue();
        Logger.log(" Current sample: " + currentSample);

        this.stateAutomaton.updatePosition(currentSample, basePoint);
		PositionType positionType = this.stateAutomaton.getCurrentPosition();
		Logger.log(" => " + positionType);

//TODO react to the new position
	}

}
