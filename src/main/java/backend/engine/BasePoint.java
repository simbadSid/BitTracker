package main.java.backend.engine;

import java.time.LocalDateTime;

import main.java.utils.Utils;

public class BasePoint
{
//=======================================
// Attributes
//=======================================
	private LocalDateTime	entryDate;
	private PositionType	positionType;
	private BitValue		entrySampleValue;


//=======================================
// Constructor
//=======================================
	public BasePoint(LocalDateTime entryDate, PositionType positionType, BitValue entrySampleValue)
	{
		this.entryDate			= entryDate;
		this.positionType		= positionType;
		this.entrySampleValue	= new BitValue(entrySampleValue);
	}

	public BasePoint(BasePoint b)
	{
		this(b.entryDate, b.positionType, b.entrySampleValue);
	}


//=======================================
// Setter/getter
//=======================================
	public String		getEntryDate		()	{return this.entryDate.toString();}
	public PositionType	getPositionType		()	{return this.positionType;}
	public BitValue		getEntrySampleValue	()	{return new BitValue(this.entrySampleValue);}

	public boolean		isBuyer				()	{return this.positionType.isBuyer();}
	public boolean		isSeller			()	{return this.positionType.isSeller();}

	public String		toString			()	{return Utils.genericToString(this.getClass().getDeclaredFields(), this);}
}
