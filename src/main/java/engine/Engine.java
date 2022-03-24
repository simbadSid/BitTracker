package main.java.engine;

import main.java.automaton.State;
import main.java.utils.Logger;






public abstract class Engine
{
//=======================================
// Attributes
//=======================================
	private BasePoint	basePoint;
	private State		currentState;


//=======================================
// Local methods
//=======================================
	public abstract void start();

	public abstract void stop();


//=======================================
// Private methods
//=======================================
	protected void checkUpdatePosition()
	{
		Logger.log(" New tick for position check");

		BitValue bitValue = BitValue.fetchNewValue();
//		PositionAction positionAction = this.currentState.updatePosition(bitValue, basePoint, currentState);
//		this.updatePosition(positionAction);
	}

	private void updatePosition(PositionAction positionAction)
	{
		// TODO update this.basePoint and this.currentState
		
	}
}
