package hw3;


public class storeAssistant{
	
	public int[] foodCounts= { 60, 60, 60, 60, 60 };
	int[] emptyCounts= {0, 0, 0, 0, 0};
	String action;
	
	
	
	
	public int getFoodCount(Order.RollType rollType){
		switch (rollType) {
		case Egg:
			return foodCounts[0];
		case Pastry:
			return foodCounts[1];
		case Sausage:
			return foodCounts[2];
		case Spring:
			return foodCounts[3];
		case Jelly:
			return foodCounts[4];
	}
		return -1;
}
	public boolean checkAllCount() {
		return foodCounts.equals(emptyCounts);
	}
	
	
	public int chargeFoodCount(Order.RollType rollType) {
		switch (rollType) {
		case Egg:
			return foodCounts[0]-=1;
		case Pastry:
			return foodCounts[1]-=1;
		case Sausage:
			return foodCounts[2]-=1;
		case Spring:
			return foodCounts[3]-=1;
		case Jelly:
			return foodCounts[4]-=1;
	}
		return -1;
 }
}
