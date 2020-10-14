package hw3;

public class EggRoll extends Food{
	//define a eggroll with its description and its type
	Order.RollType rollType;
	
	public EggRoll() {
		rollType = Order.RollType.Egg;
		description = "EggRoll";
	
	}
	
	public double cost() {
		return 2.00;
	}

	public String toString() {
		return description;
	}
	
	public Order.RollType getRollType() {
		return rollType;
	}
	
}
