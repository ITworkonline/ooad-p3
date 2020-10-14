package hw3;

public class Announcer implements Observer{
	
	Casual cas;
	String action;
	
	public Announcer(Casual cas) {
		this.cas = cas;
		cas.registerObserver(this);
	}
	
	public void update(String action) {
		//Observer Pattern
		//update messages from Subject and display
		this.action = action;
		System.out.println(action);
		
	}
	

}
