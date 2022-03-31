package main.java.backend.engine;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;

import main.java.IO.DataPersistence;
import main.java.IO.Logger;
import main.java.backend.automaton.StateAutomaton;






public abstract class Engine
{
//=======================================
// Attributes
//=======================================
	protected final static long sampleDelayInSec = 3000;

	protected	BasePoint		basePoint;
	private		StateAutomaton	stateAutomaton;
	private		ChronoUnit		candleStickUnit = ChronoUnit.MINUTES;


//=======================================
// Setter
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
        LocalDateTime currentSampleDate = LocalDateTime.now();

        Logger.log("\n--------------------------------------");
		Logger.log(String.format(" New tick at %s for position %s", currentSampleDate, this.stateAutomaton.getCurrentPosition()));

		BitValue currentSample = BitValue.fetchNewValue();
        Logger.log(" Current sample: " + currentSample);
        this.persistCurrentSample(currentSample, currentSampleDate);

        this.stateAutomaton.updatePosition(basePoint, currentSample, currentSampleDate);
		PositionType positionType = this.stateAutomaton.getCurrentPosition();
		Logger.log(" => " + positionType);

//TODO react to the new position
	}

	private void persistCurrentSample(BitValue currentSample, LocalDateTime currentSampleDate)
	{
		LocalDateTime previousSampleDate = this.stateAutomaton.getPreviousSampleDate();

		if ((previousSampleDate == null) || (isInSameCandleStickUnit(currentSampleDate, previousSampleDate)))
			DataPersistence.addLine(currentSample);
		else
		{
			LinkedList<Object> bitValueList = DataPersistence.getBuffer();
			if (bitValueList.isEmpty())
				return;

			BitValue min = null;
			BitValue max = null;
			for (Object obj: bitValueList)
			{
				BitValue bitValue = (BitValue)obj;
				if ((min == null) || (bitValue.isLower(min)))
					min = bitValue;
				if ((max == null) || (bitValue.isHigher(max)))
					max = bitValue;
			}

			candleStick candleStick = new candleStick(min, max, (BitValue)bitValueList.getFirst(), (BitValue)bitValueList.getLast());
			DataPersistence.addLine(candleStick);
			DataPersistence.flushBuffer();
			Logger.log("New candleStick: " + candleStick);
		}
	}

	private boolean isInSameCandleStickUnit(LocalDateTime currentSampleDate, LocalDateTime previousSampleDate)
	{
		int			current_minutes		= currentSampleDate.getMinute();
		int			current_hour		= currentSampleDate.getHour();
		int			current_day			= currentSampleDate.getDayOfMonth();
		int			current_year		= currentSampleDate.getYear();

		int			previous_minutes	= currentSampleDate.getMinute();
		int			previous_hour		= previousSampleDate.getHour();
		int			previous_day		= previousSampleDate.getDayOfMonth();
		int			previous_year		= previousSampleDate.getYear();

		switch (this.candleStickUnit)
		{
			case MINUTES:
				return ((current_year == previous_year) & (current_day == previous_day) & (current_hour == previous_hour) & (current_minutes == previous_minutes));
			case HOURS:
				return ((current_year == previous_year) & (current_day == previous_day) & (current_hour == previous_hour));
			case DAYS:
				return ((current_year == previous_year) & (current_day == previous_day));
			case WEEKS:
				return ((current_year == previous_year));
			default:
				throw new RuntimeException("Unhandled candle-stick unit: " + this.candleStickUnit);
		}
	}
}
