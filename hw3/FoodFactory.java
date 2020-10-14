package hw3;
//factory
public class FoodFactory {
	public static Food createFoodRoll(Order.RollType a) {
		Food food = null;
		switch(a) {
		case Egg: a = Order.RollType.Egg; food = new EggRoll(); break;
		case Pastry: a = Order.RollType.Pastry; food = new PastryRoll(); break;
		case Sausage: a = Order.RollType.Sausage; food = new SausageRoll();break;
		case Spring: a = Order.RollType.Spring; food = new SpringRoll();break;
		case Jelly: a = Order.RollType.Jelly; food = new JellyRoll();break;
	}
	
	return food;
}
}
