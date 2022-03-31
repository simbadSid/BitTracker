package main.java.backend.engine;

public enum BitDifference
{
	EQUIVALENT_OR_HIGHER,
	LOWER,
	VERY_LOWER,
	VERY_LOWER_SLIGHT_INCREASE,

	EQUIVALENT_OR_LOWER,
	HIGHER,
	VERY_HIGHER,
	VERY_HIGHER_SLIGHT_DECREASE;


//=======================================
// Attributes
//=======================================
	public static final double QUANTUM_PERCENT			= 3;	//%
	public static final double QUANTUM_PERCENT_SMALL	= .5;	//%


	private static double getDifference (BitValue v0, BitValue v1)
	{
		return 100 * (v0.getValue() - v1.getValue()) / v0.getValue();
	}

	public static BitDifference getDifferenceDecrease(BitValue currentSample, BitValue previousSample, BitValue baseSample)
	{
		double difference = getDifference(baseSample, currentSample);
		BitDifference res;

		if (difference < QUANTUM_PERCENT)
			res = BitDifference.EQUIVALENT_OR_HIGHER;
		else
		{
			if (difference  < 2 * QUANTUM_PERCENT)
				res = BitDifference.LOWER;
			else
			{
				if ((previousSample == null) || (getDifference(currentSample, previousSample) < QUANTUM_PERCENT_SMALL))
				{
					res = BitDifference.VERY_LOWER_SLIGHT_INCREASE;
				}
				else
				{
					res = BitDifference.VERY_LOWER;
				}
			}
		}

		return res;
	}

	public static BitDifference getDifferenceIncrease(BitValue currentSample, BitValue previousSample, BitValue baseSample)
	{
		double difference = getDifference(currentSample, baseSample);
		BitDifference res;

		if (difference < QUANTUM_PERCENT)
			res = BitDifference.EQUIVALENT_OR_LOWER;
		else
		{
			if (difference  < 2 * QUANTUM_PERCENT)
				res = BitDifference.HIGHER;
			else
			{
				if ((previousSample == null) || (getDifference(previousSample, currentSample) < QUANTUM_PERCENT_SMALL))
				{
					res = BitDifference.VERY_HIGHER_SLIGHT_DECREASE;
				}
				else
				{
					res = BitDifference.VERY_HIGHER;
				}
			}
		}

		return res;
	}
}
