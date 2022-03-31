package main.java.backend.automaton;

import main.java.backend.engine.BitDifference;
import main.java.backend.engine.PositionType;

public class Strategy0_straightForward_sell extends StateAutomaton
{
//=======================================
// Constructor
//=======================================
	public Strategy0_straightForward_sell()
	{
		super(PositionType.SELLER_OBSERVER);

		super.transitionSet.put(new StateTransition(PositionType.SELLER_OBSERVER,	BitDifference.EQUIVALENT_OR_LOWER),			PositionType.SELLER_OBSERVER); 
		super.transitionSet.put(new StateTransition(PositionType.SELLER_OBSERVER,	BitDifference.HIGHER),						PositionType.SELL_SOON); 
		super.transitionSet.put(new StateTransition(PositionType.SELLER_OBSERVER,	BitDifference.VERY_HIGHER),					PositionType.SELL_VERY_SOON); 

		super.transitionSet.put(new StateTransition(PositionType.SELL_SOON,			BitDifference.EQUIVALENT_OR_LOWER),			PositionType.SELLER_OBSERVER);
		super.transitionSet.put(new StateTransition(PositionType.SELL_SOON,			BitDifference.HIGHER),						PositionType.SELL_SOON);
		super.transitionSet.put(new StateTransition(PositionType.SELL_SOON,			BitDifference.VERY_HIGHER),					PositionType.SELL_VERY_SOON);

		super.transitionSet.put(new StateTransition(PositionType.SELL_VERY_SOON,	BitDifference.EQUIVALENT_OR_LOWER),			PositionType.SELLER_OBSERVER);
		super.transitionSet.put(new StateTransition(PositionType.SELL_VERY_SOON,	BitDifference.HIGHER),						PositionType.SELL_SOON);
		super.transitionSet.put(new StateTransition(PositionType.SELL_VERY_SOON,	BitDifference.VERY_HIGHER),					PositionType.SELL_VERY_SOON);
		super.transitionSet.put(new StateTransition(PositionType.SELL_VERY_SOON,	BitDifference.VERY_HIGHER_SLIGHT_DECREASE),	PositionType.SELL_NOW);
		
		super.transitionSet.put(new StateTransition(PositionType.SELL_NOW,			BitDifference.VERY_HIGHER),					PositionType.SELL_NOW);
		super.transitionSet.put(new StateTransition(PositionType.SELL_NOW,			BitDifference.HIGHER),						PositionType.SELL_SOON);
		super.transitionSet.put(new StateTransition(PositionType.SELL_NOW,			BitDifference.EQUIVALENT_OR_LOWER),			PositionType.SELLER_OBSERVER);

		super.logTransitionSet();
	}
}
