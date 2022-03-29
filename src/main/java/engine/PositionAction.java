package main.java.engine;

import java.lang.reflect.Method;


public abstract class PositionAction
{
//=======================================
// Local methods
//=======================================
	public void action(PositionType positionType)
	{
		Method method;
		try
		{
			method = this.getClass().getMethod(positionType.name());
			method.invoke(this);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException("Position " + positionType.name() + " not handled");
		}
	}


//=======================================
// Protected methods
//=======================================
	protected abstract void BUYER_OBSERVER();
	protected abstract void BUY_SOON();
	protected abstract void BUY_VERY_SOON();
	protected abstract void BUY_NOW();
	protected abstract void SELL();

}