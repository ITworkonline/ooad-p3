
# Title: Project 3: Roll Store Management
----**Team Members:** Fei Hu, Jie Wang

> class Food.java    

>> subclass EggRoll.java, JellyRoll.java, SpringRoll.java, Pastry.java, SausageRoll.java 

>> subclass ToppingDecorator.java

>>> subclass ExtraFilling.java, ExtraSauce.java, ExtraTopping.java

> Class Order.java

>> subclass Casual.java, Business.java, Catering.java

> Class Factory.java 

----**Language and environment:** Java SE 8[1.8], Eclipse. 

----**Comments:**
We use Factory Pattern, Decorator Pattern, and Observer Pattern in this project. For the factory pattern, we designed a FoodFactory to generate a new roll by its type. For the decorator pattern, we designed a class called ToppingDecorator（It acctually not only works for topping, it works for all extra). The decorator can helped us to allow behavior to be added to an individual object and without affecting the behavior of other objects from the same class. And we used extraFilling, extraTopping, and extraSauce to inherit from this decorator. For the observer pattern, we designed a obersver to ......

----**Demo Codes:**  
Enum RollType(Order.java): This helped us to define the type of the roll.
```java
public enum RollType {
		Egg,
		Pastry,
		Sausage,
		Spring,
		Jelly,
	}
```
Factory Pattern: Switching to genby its types
```java
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
```  
Decorator Pattern: adding behavior to each extra
```java
public abstract class ToppingDecorator extends Food {
	Food food;
	public abstract String getDescription();
	public Order.RollType getRollType(){
		return food.getRollType();
	}
}
```

----**Issues or problems:**
We also face some issues or problemencountered in this project. For example, how to pass every customer's order to the current storage, and then check if the certain roll storage finished the requirement. And if not, for causal and catering customers, we have to switch the roll type. For business customers, we have to talk him "Can't make the order".  

To solve this issues, we mainly used two functions: checkIfCasualOk(), checkIfCateringOk(), and pickAnother():
checkIfCasualOk() is checking the raw request from customers in the storage. If we still got this roll which is greater or equal to 1, we directly charged from our storage by the certain type. If the number of certain roll was equal to 0, then we used pickAnother() to see if other type of roll finished the requirement. If not, we just returned NULL and remove the request from the list.  

Here is the code for these two functions:  
checkIfCasualOk():  
```java
public void checkIfCasualOk(ArrayList<Food> a){
		for (int i = 0; i< a.size(); i++) {
			if(storeAssis.getFoodCount(a.get(i).getRollType())>=1) {
				storeAssis.chargeFoodCount(a.get(i).getRollType());
			}else {
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
```
pickAnother():  
```java
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
		storeAssis.chargeFoodCount(roll.getRollType());
		//System.out.println("charge by cater!");

		return roll;
	}
	
```
----**Instruction of running**:  

All of our test script is in the test.java. It includes the new instances and main function. 


