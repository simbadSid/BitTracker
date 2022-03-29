package main.java.engine;

public enum PositionType {
	BUYER_OBSERVER,
	BUY_SOON,
	BUY_VERY_SOON,
	BUY_NOW,
	SELLER_OBSERVER,
	SELL_SOON,
	SELL_VERY_SOON,
	SELL_NOW;


	public boolean isBuyer()
	{
		boolean result = false;

		result |= this.equals(PositionType.BUYER_OBSERVER);
		result |= this.equals(PositionType.BUY_SOON);
		result |= this.equals(PositionType.BUY_VERY_SOON);
		result |= this.equals(PositionType.BUY_NOW);

		return result;
	}

	public boolean isSeller()
	{
		boolean result = false;

		result |= this.equals(PositionType.SELLER_OBSERVER);
		result |= this.equals(PositionType.SELL_SOON);
		result |= this.equals(PositionType.SELL_VERY_SOON);
		result |= this.equals(PositionType.SELL_NOW);

		return result;
	}
}
