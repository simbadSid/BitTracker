package main.java.automaton;

import main.java.engine.BitDifference;
import main.java.engine.PositionType;

public class Strategy0_straightForward_buy extends StateAutomaton
{
//=======================================
// Constructor
//=======================================
	public Strategy0_straightForward_buy()
	{
		super(PositionType.BUYER_OBSERVER);

		super.transitionSet.put(new StateTransition(PositionType.BUYER_OBSERVER,	BitDifference.EQUIVALENT_OR_HIGHER),		PositionType.BUYER_OBSERVER); 
		super.transitionSet.put(new StateTransition(PositionType.BUYER_OBSERVER,	BitDifference.LOWER),						PositionType.BUY_SOON); 
		super.transitionSet.put(new StateTransition(PositionType.BUYER_OBSERVER,	BitDifference.VERY_LOWER),					PositionType.BUY_VERY_SOON); 

		super.transitionSet.put(new StateTransition(PositionType.BUY_SOON,			BitDifference.EQUIVALENT_OR_HIGHER),		PositionType.BUYER_OBSERVER);
		super.transitionSet.put(new StateTransition(PositionType.BUY_SOON,			BitDifference.LOWER),						PositionType.BUY_SOON);
		super.transitionSet.put(new StateTransition(PositionType.BUY_SOON,			BitDifference.VERY_LOWER),					PositionType.BUY_VERY_SOON);

		super.transitionSet.put(new StateTransition(PositionType.BUY_VERY_SOON,		BitDifference.EQUIVALENT_OR_HIGHER),		PositionType.BUYER_OBSERVER);
		super.transitionSet.put(new StateTransition(PositionType.BUY_VERY_SOON,		BitDifference.LOWER),						PositionType.BUY_SOON);
		super.transitionSet.put(new StateTransition(PositionType.BUY_VERY_SOON,		BitDifference.VERY_LOWER),					PositionType.BUY_VERY_SOON);
		super.transitionSet.put(new StateTransition(PositionType.BUY_VERY_SOON,		BitDifference.VERY_LOWER_SLIGHT_INCREASE),	PositionType.BUY_NOW);
		
		super.transitionSet.put(new StateTransition(PositionType.BUY_NOW,			BitDifference.VERY_LOWER),					PositionType.BUY_NOW);
		super.transitionSet.put(new StateTransition(PositionType.BUY_NOW,			BitDifference.LOWER),						PositionType.BUY_SOON);
		super.transitionSet.put(new StateTransition(PositionType.BUY_NOW,			BitDifference.EQUIVALENT_OR_HIGHER),		PositionType.BUYER_OBSERVER);

		super.logTransitionSet();
	}
}
