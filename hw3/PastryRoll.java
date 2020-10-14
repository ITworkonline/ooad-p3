package hw3;

public class PastryRoll extends Food{
	//define a PastryRoll with its description and its type
	Order.RollType rollType;
	
	public PastryRoll() {
		rollType = Order.RollType.Pastry;
		description = "PastryRoll";
	}
	
	public double cost() {
		return 4.00;
	}

	public String toString() {
		return description;
	}
	
	public Order.RollType getRollType() {
		return rollType;
	}
}
