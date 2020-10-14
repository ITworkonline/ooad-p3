package hw3;

public class SausageRoll extends Food{
	//define a SausageRoll with its description and its type

	Order.RollType rollType;
	
	public SausageRoll() {
		rollType = Order.RollType.Sausage;
		description = "SausageRoll";
	}
	
	public double cost() {
		return 5.00;
	}

	public String toString() {
		return description;
	}
	
	public Order.RollType getRollType() {
		return rollType;
	}
}