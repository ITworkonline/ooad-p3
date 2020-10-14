package hw3;

public abstract class Food {
	String description = "Unknown Food";	
	public abstract Order.RollType getRollType();

	public String getDescription() {
		return description;
	}
	
	public abstract double cost();
}
