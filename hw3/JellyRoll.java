package hw3;

public class JellyRoll extends Food{
	//define a JellyRoll with its description and its type

	Order.RollType rollType;
	
	public JellyRoll() {
		rollType = Order.RollType.Jelly;
		description = "JellyRoll";
	}
	
	public double cost() {
		return 3.00;
	}

	public String toString() {
		return description;
	}
	
	public Order.RollType getRollType() {
		return rollType;
	}
}