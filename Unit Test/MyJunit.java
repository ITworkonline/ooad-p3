package myJunit;
import hw3.Food;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import hw3.Busniess;
import hw3.Casual;
import hw3.Catering;
import hw3.EggRoll;
import hw3.ExtraTopping;
import hw3.Food;
import hw3.JellyRoll;
import hw3.Order;
import hw3.Order.RollType;
import hw3.SpringRoll;
import hw3.ToppingDecorator;
import hw3.storeAssistant;

public class MyJunit {

	@Test //Test1: cost of one single jellyroll
	public void test1() {
		
		Food jelly = new JellyRoll();
		assertEquals(jelly.cost(), 3.00, 0);
				
	}
	@Test //Test2: cost of eggroll + springroll
	public void test2() {
		
		Food egg = new EggRoll();
		Food spring = new SpringRoll();
		assertEquals(egg.cost()+spring.cost(),8.00, 0);
	}
	
	@Test //Test3: cost of eggroll+extrafilling
	public void test3() {
		Food egg = new EggRoll();
		ToppingDecorator eggtop = new ExtraTopping(egg);
		assertEquals(eggtop.cost(), 2.50, 0);
	}
	
	@Test //Test4: Check day 1 inventory for each roll, it should be 30;
	public void test4() {
		storeAssistant sa = new storeAssistant();
		System.out.println(sa.foodCounts[0]);
		assertEquals(sa.foodCounts[0],30,0);	
	}
	
	@Test //Test5: Check Customer typer, take Business type as an example;
	public void test5() {
		storeAssistant storeAssis = new storeAssistant();
		Busniess newCustomer = new Busniess(storeAssis);
		assertEquals(newCustomer.name,"Busniess customer");
	}
	
	@Test //Test6: As Catering customer place order if and only if get 10 rolls, two of each type, 
	      // So Check roll number for Business, it should be 10.
	public void test6() {
		storeAssistant storeAssis = new storeAssistant();
		Busniess newCustomer = new Busniess(storeAssis);
		assertEquals(newCustomer.pickNRandom().size(), 10,0);
			
	}
	
	@Test //Test7: As Catering customer place order if and only if get 10 rolls, two of each type, 
    // So it must contain every type of roll.
	public void test7() {
		storeAssistant storeAssis = new storeAssistant();
		Busniess newCustomer = new Busniess(storeAssis);
		Food egg_roll = new EggRoll();
		ArrayList<Food> m = newCustomer.pickNRandom();
		for(Food i: m) {
			if(i.getRollType() == Order.RollType.Egg) {
				assertEquals(egg_roll.getRollType(),i.getRollType());
			}
		}	 
	}
	
	@Test //Test8: As every kind of customer will order something, so the order list should not be empty,
	      // Take Business Customer as an example;
	public void test8() {
		storeAssistant storeAssis = new storeAssistant();
		Busniess newCustomer = new Busniess(storeAssis);
		assertFalse(newCustomer.pickNRandom().isEmpty());
			
	}
	
	@Test //Test9: Check chargeFoodCount() in Class storeAssistant.
	//Every time this function is called, it will minus the number of specific roll, 
	//Take eggroll as an example, it is 29 egg roll after function chargeFoodCount()
	public void test9() {
		storeAssistant storeAssis = new storeAssistant();
		storeAssis.chargeFoodCount(Order.RollType.Egg);
		assertEquals(storeAssis.foodCounts[0],29,0);
	}
	
	@Test //Test10: Check RollType Description, take EggRoll as an example, which should return "EggRoll" as its description;
	public void test10() {
		Food egg = new EggRoll();
		assertEquals(egg.getDescription(),"EggRoll");
		
		
		
			
	}
	

}
