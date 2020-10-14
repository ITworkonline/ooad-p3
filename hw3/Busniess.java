package hw3;

import java.util.ArrayList;
import java.util.Random;

public class Busniess extends Order{
	//define a business customer
	Random rand = new Random();
	storeAssistant storeAssis;

	public Busniess(storeAssistant a) {
		this.storeAssis = a;
		name = "Busniess customer";
		rollNumber = 10;
		rawRequest = pickNRandom();
		w = (ArrayList<Food>)rawRequest.clone();
	}
	//raw request order
	public ArrayList<Food> pickNRandom() {
		ArrayList<Food> foods = new ArrayList<Food>();
		
		for (int i = 0; i < 2; i++) {
			for (RollType k:Order.RollType.values()) {
				Food roll = FoodFactory.createFoodRoll(k);
				foods.add(roll);
			}
		}
		return foods;
	}
	//check
	public boolean checkIfBusniessOk(ArrayList<Food> a){
		for (int i = 0; i< a.size(); i++) {
			if(storeAssis.getFoodCount(a.get(i).getRollType())<= 1) {
				outage+=1;
				return false;
		}
		rollnum +=10;
	}return true;
}
	
	public ArrayList<Food> getFoods(){
		return w;
	}
	//add extra
	public void addExtra(){
		ArrayList<Food> k = new ArrayList<Food>();
		for (Food i:w) {
			int extraSauceNo = rand.nextInt(4);
			int extraFillingNo = rand.nextInt(2);
			int extraToppingNo = rand.nextInt(3);
			
			for (int j = 0; j<extraSauceNo; j++) {
				i = new ExtraSauce(i);
			}
			for (int j = 0; j<extraFillingNo; j++) {
				i = new ExtraFilling(i);
			}
			for (int j = 0; j<extraToppingNo; j++) {
				i = new ExtraTopping(i);
			}
			k.add(i);
		}
		w = k;
	}
	//calculate the order
	public double costOfOrder() {
		double totalPrice = 0.00;
		for (Food i:w) {
			totalPrice = i.cost() + totalPrice;
		}
		return totalPrice;
	}
	//charge from storage
	public int[] buyProduct() {
		for (Food i:w) {
			storeAssis.chargeFoodCount(i.getRollType());
			//System.out.println("charge by busniess!");
			}
		return storeAssis.foodCounts;
		}
	public boolean checkAllCount() {
		return storeAssis.foodCounts.equals(storeAssis.emptyCounts);
	}
	}

