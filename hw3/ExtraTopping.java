package hw3;

public class ExtraTopping extends ToppingDecorator {
	public ExtraTopping(Food food) {
		this.food = food;
	}
 
	public String getDescription() {
		return food.getDescription() + ", extraTopping";
	}

	public String toString() {
		description = food.toString() + "+ ExtraTopping";
		return description;
	}
	
	
	@Override
	public double cost() {
		Order.RollType a = getRollType();
		
		double cost = 0.00;
		switch(a) {
		case Egg: a = Order.RollType.Egg; cost = 0.50+food.cost(); break;
		case Pastry: a = Order.RollType.Pastry; cost = 1.50+food.cost(); break;
		case Sausage: a = Order.RollType.Sausage; cost = 2.50+food.cost();break;
		case Spring: a = Order.RollType.Spring; cost = 3.50+food.cost();break;
		case Jelly: a = Order.RollType.Jelly; cost = 4.50+food.cost();break;
	}
		return cost;
	}
}