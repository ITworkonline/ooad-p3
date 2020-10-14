package hw3;

public class SpringRoll extends Food{
	//define a SpringRoll with its description and its type

	Order.RollType rollType;
	
	public SpringRoll() {
		rollType = Order.RollType.Spring;
		description = "SpringRoll";
	}
	
	public double cost() {
		return 6.00;
	}

	public String toString() {
		return description;
	}
	
	public Order.RollType getRollType() {
		return rollType;
	}
}