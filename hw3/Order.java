package hw3;
import java.util.ArrayList;

import hw3.Observer;

abstract public class Order {
	public enum RollType {
		Egg,
		Pastry,
		Sausage,
		Spring,
		Jelly,
	}
	
	public String name;
	int rollNumber;
	ArrayList<Food> rawRequest;
	ArrayList<Food> w;
	int outage;
	int rollnum;
	
	public void registerObserver(Observer o) {
		System.out.println("order register");
	};
	public void removeObserver(Observer o) {
		System.out.println("order remove");
	};
	
	public void notifyObserver() {
		System.out.println("order notify");
	};
	
	public abstract double costOfOrder();
	public abstract ArrayList<Food> pickNRandom();
	protected abstract boolean checkAllCount();
	protected abstract ArrayList<Food> getFoods();
	protected void checkIfCasualOk(ArrayList<Food> a) {
		System.out.println("test");
	}
	protected abstract void addExtra();
	protected abstract int[] buyProduct();
	protected boolean checkIfBusniessOk(ArrayList<Food> q) {
		return false;
	}
	protected void checkIfCateringOk(ArrayList<Food> q) {
		System.out.println("test");
	}
}
