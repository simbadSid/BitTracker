package main.java.backend.automaton;

import java.util.HashMap;

import main.java.IO.Logger;
import main.java.backend.engine.BasePoint;
import main.java.backend.engine.BitDifference;
import main.java.backend.engine.BitValue;
import main.java.backend.engine.PositionType;
import main.java.utils.Utils;

public abstract class StateAutomaton
{
//=======================================
// Automaton definition
//=======================================
	public class StateTransition
	{
		public PositionType		positionType;
		public BitDifference	bitDifference;

		public StateTransition(PositionType positionType, BitDifference bitDifference)
		{
			this.positionType	= positionType;
			this.bitDifference	= bitDifference;
		}

		@Override
		public boolean equals(Object stateTransition)
		{
			if ( ! (stateTransition instanceof StateTransition))
				return false;

			return ((positionType == ((StateTransition)stateTransition).positionType) && (bitDifference == ((StateTransition)stateTransition).bitDifference));
		}

		@Override
		public int hashCode()
		{
			return this.positionType.hashCode() + this.bitDifference.hashCode();
		}

		public String toString()
		{
			return Utils.genericToString(this.getClass().getDeclaredFields(), this);
		}
	}


//=======================================
// Attributes
//=======================================
	protected 	HashMap<StateTransition, PositionType>	transitionSet;
	private		PositionType							currentPositionType;
	private		BitValue								previousSample;


//=======================================
// Constructor
//=======================================
	public StateAutomaton(PositionType positionType)
	{
		this.transitionSet			= new HashMap<StateTransition, PositionType>();
		this.currentPositionType	= positionType;
		this.previousSample			= null;
	}


//=======================================
// Accesses
//=======================================
	public String		getId()				{return this.getClass().getSimpleName();}
	public PositionType	getCurrentPosition(){return this.currentPositionType;}


//=======================================
// Local methods
//=======================================
	/**
	 * Update the current state of the automaton using the transition built using the parameter {currentSample}
	 * @param currentSample Current value of the the price
	 * @param basePoint type of the base-point being observed
	 */
	public void updatePosition(BitValue currentSample, BasePoint basePoint)
	{
		BitDifference bitDifference;
		if (basePoint.isBuyer())
			bitDifference = BitDifference.getDifferenceDecrease(currentSample, this.previousSample, basePoint.getEntrySampleValue());
		else
			bitDifference = BitDifference.getDifferenceIncrease(currentSample, this.previousSample, basePoint.getEntrySampleValue());
			
		StateTransition stateTransition = new StateTransition(this.currentPositionType, bitDifference);

		this.currentPositionType = this.transitionSet.get(stateTransition);

		if (this.currentPositionType == null)
			throw new RuntimeException(String.format("The automaton \"%s\" has no transition from the current state \"%s\" with the new sample \"%f (%s)\"", this.getId(), stateTransition.positionType, currentSample.getValue(), stateTransition.bitDifference));

		this.previousSample = new BitValue(currentSample);
	}

	public void logTransitionSet()
	{
		Logger.log(" State transitions for the automaton: " + this.getId());

		for (HashMap.Entry<StateTransition, PositionType> set: transitionSet.entrySet())
		{
			StateTransition key = set.getKey();
			Logger.log(String.format("\t ( %s + %s ) => %s", key.positionType.toString(), key.bitDifference.toString(), set.getValue().toString()));
		}
	}
}
