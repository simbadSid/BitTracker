package main.java.automaton;

import main.java.engine.BasePoint;
import main.java.engine.BitValue;
import main.java.engine.PositionAction;

public interface State
{

	PositionAction updatePosition(BitValue bitValue, BasePoint basePoint, State currentState);

}
