package hw3;

public class ExtraSauce extends ToppingDecorator {
	//define a extraSauce with its price and description

	public ExtraSauce(Food food) {
		this.food = food;
	}
 
	public String getDescription() {
		return food.getDescription() + ", extraSauce";
	}

	public String toString() {
		description = food.toString() + " + ExtraSauce";
		return description;
	}
	//switch price by type

	@Override
	public double cost() {
		Order.RollType a = getRollType();
		
		double cost = 0.00;
		switch(a) {
		case Egg: a = Order.RollType.Egg; cost = 1.50+food.cost(); break;
		case Pastry: a = Order.RollType.Pastry; cost = 2.50+food.cost(); break;
		case Sausage: a = Order.RollType.Sausage; cost = 3.50+food.cost();break;
		case Spring: a = Order.RollType.Spring; cost = 4.50+food.cost();break;
		case Jelly: a = Order.RollType.Jelly; cost = 5.50+food.cost();break;
	}
		return cost;
	}
}