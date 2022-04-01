package main.java.backend.engine;

import main.java.utils.Utils;

public class candleStick
{
//=======================================
// Attributes
//=======================================
	private BitValue firstValue;
	private BitValue lastValue;
	private BitValue min;
	private BitValue max;


//=======================================
// Constructor
//=======================================
	public candleStick(BitValue firstValue, BitValue lastValue, BitValue min, BitValue max)
	{
		this.firstValue	= firstValue;
		this.lastValue	= lastValue;
		this.min		= min;
		this.max		= max;
	}


//=======================================
// Local methods
//=======================================
	public String toString ()
	{
		return Utils.genericToString(this.getClass().getDeclaredFields(), this);
	}
}
