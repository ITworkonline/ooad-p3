package hw3;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//import hw2.Observer;
import hw3.Order.RollType;

public class Casual extends Order implements Subject{
	//define a Casual customer

	Random rand = new Random();
	storeAssistant storeAssis;
	public List<Observer> observers;
	String action;
	
	public Casual(storeAssistant a) {
		observers = new ArrayList<Observer>();
		this.storeAssis = a;
		name = "Casual customer";
		rollNumber = randomNumber();
		rawRequest = pickNRandom();
		w = (ArrayList<Food>)rawRequest.clone();
		observers = new ArrayList<Observer>();	
	}


	public void setAction(String action) {
		this.action = action;
	}
	
	public int randomNumber() {
		
		int c = rand.nextInt(3) + 1;
		return c;
	}
	//raw request
	public ArrayList<Food> pickNRandom() {
		//setAction("Casual Customer Original order is out of rage, replace it");
		//notifyObservers();
		ArrayList<Food> foods = new ArrayList<Food>();
		//FoodFactory factory;
		for (int i = 0; i < rollNumber; i++) {
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
			Food roll = FoodFactory.createFoodRoll(rollType);
			
			foods.add(roll);

		}
		return foods;
	}
	//switch to other roll
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
		//System.out.println("charge by casual!");
		return roll;
	}
	//check
	public void checkIfCasualOk(ArrayList<Food> a){
		for (int i = 0; i< a.size(); i++) {
			if(storeAssis.getFoodCount(a.get(i).getRollType())>=1) {
				//System.out.println("charge by casual!");
				rollnum+=1;
				storeAssis.chargeFoodCount(a.get(i).getRollType());
			}else/*(storeAssis.getFoodCount(a.get(i).getRollType())==0)*/ {
				setAction("Casual Customer Original order is out of rage, replace it");
				notifyObserver();
				outage+=1;
				Food k = pickAnother();
				if(k != null) {
				a.set(i, k);
				}
			}
		}
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
	
	
	//calculate cost
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
	
	public int[] buyProduct() {
		return storeAssis.foodCounts;}
	@Override
	public void registerObserver(hw3.Observer o) {
		// TODO Auto-generated method stub
		observers.add(o);
		
	}
	@Override
	public void removeObserver(hw3.Observer o) {
		// TODO Auto-generated method stub
		observers.remove(o);
		System.out.println("remove ths observer");
		
	}
	
	public void notifyObserver() {
		for (Observer observer : observers) {
			observer.update(action);
		}
	}
	
}

