package main.java.backend.engine;

import main.java.utils.Utils;

public class candleStick
{
//=======================================
// Attributes
//=======================================
	private BitValue min;
	private BitValue max;
	private BitValue firstValue;
	private BitValue lastValue;


//=======================================
// Constructor
//=======================================
	public candleStick(BitValue min, BitValue max, BitValue firstValue, BitValue lastValue)
	{
		this.min		= min;
		this.max		= max;
		this.firstValue	= firstValue;
		this.lastValue	= lastValue;
	}


//=======================================
// Local methods
//=======================================
	public String toString ()
	{
		return Utils.genericToString(this.getClass().getDeclaredFields(), this);
	}
}
