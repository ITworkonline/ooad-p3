package hw3;

public class ExtraFilling extends ToppingDecorator {
	//define a extrafilling with its price and description
	public ExtraFilling(Food food) {
		this.food = food;
	}
 
	public String getDescription() {
		return food.getDescription() + ", extraFilling";
	}
	
	public String toString() {
		description = food.toString() + "+ ExtraFilling";
		return description;
	}
	
	//switch price by type

	@Override
	public double cost() {
		Order.RollType a = getRollType();
		
		double cost = 0.00;
		switch(a) {
		case Egg: a = Order.RollType.Egg; cost = 1.00+food.cost(); break;
		case Pastry: a = Order.RollType.Pastry; cost = 2.00+food.cost(); break;
		case Sausage: a = Order.RollType.Sausage; cost = 3.00+food.cost();break;
		case Spring: a = Order.RollType.Spring; cost = 4.00+food.cost();break;
		case Jelly: a = Order.RollType.Jelly; cost = 5.00+food.cost();break;
	}
		return cost;
	}
}