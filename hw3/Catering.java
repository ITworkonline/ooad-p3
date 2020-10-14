package hw3;

import java.util.ArrayList;

import java.util.Random;

import hw3.Order.RollType;

public class Catering extends Order{
	//define a catering customer

	Random rand = new Random();
	storeAssistant storeAssis;
	
	public Catering(storeAssistant a) {
		this.storeAssis = a;
		name = "Catering customer";
		rollNumber = 3;
		rawRequest = pickNRandom();
		w = (ArrayList<Food>)rawRequest.clone();
		outage = 0;
		}
	//raw pick
	public ArrayList<Food> pickNRandom() {
		ArrayList<Food> foods = new ArrayList<Food>();
		
		ArrayList<RollType> a = new ArrayList<RollType>();
		int count = 0;
		while(count<3) {
			Random rand = new Random();
			int rollTypeIndex = rand.nextInt(5);
			
			Order.RollType rollType = null;
			switch(rollTypeIndex) {
			case 0: rollType = Order.RollType.Egg;break;
			case 1: rollType = Order.RollType.Pastry;break;
			case 2: rollType = Order.RollType.Sausage;break;
			case 3: rollType = Order.RollType.Spring;break;
			case 4: rollType = Order.RollType.Jelly;break;
			}
			//bug
			if(a.contains(rollType)){
				continue;
			}
			else {
				a.add(rollType);
				count+=1;
			}
			for (int k = 0; k<5; k++) {
				Food roll = FoodFactory.createFoodRoll(rollType);
				foods.add(roll);
			}
		}
		return foods;
	}
	
	//switch to another roll
	public Food pickAnother(){
		Random rand = new Random();
		Food roll = null;
		ArrayList<RollType> m = new ArrayList<RollType>();
		if(storeAssis.foodCounts[0]>0) {
			m.add(Order.RollType.Egg);
		}if(storeAssis.foodCounts[1]>0) {
			m.add(Order.RollType.Pastry);
		}if(storeAssis.foodCounts[2]>0) {
			m.add(Order.RollType.Sausage);
		}if(storeAssis.foodCounts[3]>0) {
			m.add(Order.RollType.Spring);
		}if(storeAssis.foodCounts[4]>0) {
			m.add(Order.RollType.Jelly);
		}
		int index = m.size();
		if(index ==0) {
			return null;
		}
		int rollTypeIndex = rand.nextInt(index);
		roll = FoodFactory.createFoodRoll(m.get(rollTypeIndex));
		rollnum+=1;
		storeAssis.chargeFoodCount(roll.getRollType());
		//System.out.println("charge by cater!");

		return roll;
	}
	
	
	//check 
	public void checkIfCateringOk(ArrayList<Food> a){
		for (int i = 0; i< a.size(); i++) {
			if(storeAssis.getFoodCount(a.get(i).getRollType())>=1) {
				
				rollnum+=1;
				storeAssis.chargeFoodCount(a.get(i).getRollType());
				
			}else/*(storeAssis.getFoodCount(a.get(i).getRollType())==0)*/ {
				outage+=1;
				Food k = pickAnother();
				if(k != null) {
				a.set(i, k);
				}
				else {
					a.remove(i);
				}
			}
		}
}
	public ArrayList<Food> getFoods(){
		return w;
	}
	public int getOutage() {
		return outage;
	}
	
	public int[] buyProduct() {
		return storeAssis.foodCounts;}
	
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
	public double costOfOrder() {
		double totalPrice = 0.00;
		for (Food i:w) {
			totalPrice = i.cost() + totalPrice;
		}
		return totalPrice;
	}
	public boolean checkAllCount() {
		return storeAssis.foodCounts.equals(storeAssis.emptyCounts);
	}
}
