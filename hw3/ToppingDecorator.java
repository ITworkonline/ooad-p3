package hw3;

public abstract class ToppingDecorator extends Food {
	Food food;
	public abstract String getDescription();
	public Order.RollType getRollType(){
		return food.getRollType();
	}
}
