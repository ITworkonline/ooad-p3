package hw3;

import java.util.ArrayList;
import java.util.Random;

//import static org.junit.Assert.assertEquals;

import java.util.*;
public class Test {
	public static void main(String args[]) {
		Random rand = new Random();
		int workDay = 30;
		storeAssistant storeAssis = new storeAssistant();
		//define cost and outage
		double totalCostOfCasualAll = 0.00;
		double totalCostOfBusniessAll = 0.00;
		double totalCostOfCateringAll = 0.00;
		int totalOutageCasualAll = 0;
		int totalOutageBusniessAll = 0;
		int totalOutageCateringAll = 0;
		int totalCasualRollNum = 0;
		int totalBusniesslRollNum = 0;
		int totalCateringRollNum = 0;
		double[] ds = new double[30];
		int[] dor = new int[30];
		for(int day=1;day<=workDay;day++)
		{	
			
			System.out.println();
			System.out.println();
			double dailySales = 0;
			
			System.out.println("Roll store is open on Day "+ day);
			for(int i= 0; i<storeAssis.foodCounts.length;i++) {
				if (storeAssis.foodCounts[i]<= 0) {
					storeAssis.foodCounts[i] = 60;
				}
			}
			System.out.println("The storage at the begin of the day: ");
			for (int i = 0; i<storeAssis.foodCounts.length;i++) {
				if(i == 0) {
					System.out.println("Egg roll: "+ storeAssis.foodCounts[i]);
				}
				if(i == 1) {
					System.out.println("Pastry roll: "+ storeAssis.foodCounts[i]);
				}
				if(i == 2) {
					System.out.println("Sausage roll: "+ storeAssis.foodCounts[i]);
				}
				if(i == 3) {
					System.out.println("Spring roll: "+ storeAssis.foodCounts[i]);
				}
				if(i == 4) {
					System.out.println("Jelly roll: "+ storeAssis.foodCounts[i]);
				}
			}
			//System.out.println("The storage at the begin of the day "+ Arrays.toString(storeAssis.foodCounts));
			int[] beginCounts = storeAssis.foodCounts;
			ArrayList<Order> Customers = new ArrayList<Order>();
			

			int casualCount = rand.nextInt(12)+1;
			int busniessCount = rand.nextInt(3)+1;
			int cateringCount = rand.nextInt(3)+1;

			for (int count=0; count<casualCount;count++) {
				Casual newCustomer = new Casual(storeAssis);
				Customers.add(newCustomer);
			}

			for (int count=0; count<busniessCount;count++) {
				Busniess newCustomer = new Busniess(storeAssis);
				Customers.add(newCustomer);
			}
			
			for (int count=0; count<cateringCount;count++) {
				Catering newCustomer = new Catering(storeAssis);
				Customers.add(newCustomer);
			}
			double totalCostOfCasual = 0.00;
			double totalCostOfBusniess = 0.00;
			double totalCostOfCatering = 0.00;
			
			
			int totalOutageCasual = 0;
			int totalOutageBusniess = 0;
			int totalOutageCatering = 0;
			
			
			ArrayList<Food> inventoryOrder = new ArrayList<Food>();
			Map<String, Integer> map = new HashMap<>();
			//random the customer
			Collections.shuffle(Customers);
			for (Order i:Customers) {
				
				if(i.getClass().getName() == "hw3.Casual") {
					Casual a = (Casual)i;
					Observer anno = new Announcer(a);
					//System.out.println(a.observers);
					System.out.println();
					System.out.println();

					System.out.println("There is a new "+i.name+" coming in!");
					i.pickNRandom();
					System.out.println("Casual customer wants to order "+ i.getFoods());
					
					i.checkIfCasualOk(i.getFoods());
					System.out.println("After checking stock, roll store can make " + i.getFoods());
					inventoryOrder.addAll(i.getFoods());
					i.addExtra();
					System.out.println("After adding extra, the order will be " + i.getFoods());
					double temp = i.costOfOrder();
					System.out.println("Total price is "+ temp);
					for(int u:i.buyProduct()) {
						System.out.println("Update stock status: "+u);
					}
					int rollnum = i.rollnum;
					int tempOutage = i.outage;
					totalCasualRollNum += rollnum;
					totalOutageCasual +=tempOutage;
					totalCostOfCasual +=temp;
					

					
				}else if (i.getClass().getName() == "hw3.Busniess") {
					System.out.println();
					System.out.println();

					System.out.println("There is a new "+i.name+" coming in!");
					i.pickNRandom();
					System.out.println("Business customer wants to order " + i.getFoods());
					
					if(i.checkIfBusniessOk(i.getFoods()) == false) {
						System.out.println("Out of Stock! Business customer walk away!");
					}else {
						inventoryOrder.addAll(i.getFoods());
						i.addExtra();
						System.out.println("Roll store can make this business order!");
						System.out.println("After adding extra, the order will be " + i.getFoods());
						double temp = i.costOfOrder();
						System.out.println("Total price is "+ temp);
						for(int u:i.buyProduct()) {
							System.out.println("Update stock status: "+u);
						}
						int rollnum = i.rollnum;
						int tempOutage = i.outage;
						totalBusniesslRollNum += rollnum;
						
						totalOutageBusniess += tempOutage;
						totalCostOfBusniess +=temp;
					}
					
					
				}else {
					System.out.println();
					System.out.println();
					System.out.println("There is a new "+i.name+" coming in!");
					i.pickNRandom();
					System.out.println("Catering customer wants to order " + i.getFoods());
					i.checkIfCateringOk(i.getFoods());
					System.out.println("After checking stock, roll store can make "+ i.getFoods());
					inventoryOrder.addAll(i.getFoods());
					i.addExtra();
					System.out.println("After adding extra, the order will be " + i.getFoods());
					double temp = i.costOfOrder();
					System.out.println("Total price is "+ temp);
					for(int u:i.buyProduct()) {
						System.out.println("Update stock status: "+u);
					}
					int rollnum = i.rollnum;
					int tempOutage = i.outage;
					totalCateringRollNum += rollnum;
					
					totalOutageCatering +=tempOutage;
					totalCostOfCatering +=temp;
					
				}
			}
			if(storeAssis.checkAllCount()) {
				System.out.println("Roll store close for no inventory");
				break;
			}
			dailySales = totalCostOfCasual + totalCostOfBusniess +totalCostOfCatering;
			ds[day-1] = dailySales;
			totalCostOfCasualAll+=totalCostOfCasual;
			totalCostOfBusniessAll+=totalCostOfBusniess;
			totalCostOfCateringAll+=totalCostOfCatering;
			
			totalOutageCasualAll+=totalOutageCasual;
			totalOutageBusniessAll+=totalOutageBusniess;
			totalOutageCateringAll+=totalOutageCatering;

			
			double totalPay = totalCostOfCasual+totalCostOfBusniess+totalCostOfCatering;
			System.out.println("The total payment by casual customers today was "+ totalCostOfCasual);
			System.out.println("The total payment by business customers today was "+ totalCostOfBusniess);
			System.out.println("The total payment by catering customers today was "+ totalCostOfCatering);
			System.out.println("The total payment by all customers today was "+ totalPay);
			Map<String, Integer> inven_map = new HashMap<String,Integer>();
			for(Food i: inventoryOrder) {
				if(i.getRollType() == Order.RollType.Egg) {
					inven_map.put("EggRoll", inven_map.getOrDefault("EggRoll", 1)+1);
				}
				if(i.getRollType() == Order.RollType.Pastry) {
					inven_map.put("PastryRoll", inven_map.getOrDefault("PastryRoll", 1)+1);
				}
				if(i.getRollType() == Order.RollType.Sausage) {
					inven_map.put("SausageRoll", inven_map.getOrDefault("SausageRoll", 1)+1);
				}
				if(i.getRollType() == Order.RollType.Spring) {
					inven_map.put("SpringRoll", inven_map.getOrDefault("SpringRoll", 1)+1);
				}
				if(i.getRollType() == Order.RollType.Spring) {
					inven_map.put("JellyRoll", inven_map.getOrDefault("JellyRoll", 1)+1);
				}	
			}
			System.out.println("The inventory order was "+ inven_map);
			
	        int totalOutage = totalOutageCasual+totalOutageBusniess+totalOutageCatering;
	        dor[day-1] = totalOutage;
			System.out.println("The count of orders impacted by a roll outage for casual customers today was "+ totalOutageCasual);
			System.out.println("The count of orders impacted by a roll outage for busniess customers today was "+ totalOutageBusniess);
			System.out.println("The count of orders impacted by a roll outage for catering customers today was "+ totalOutageCatering);
			
			int [] endCounts = storeAssis.foodCounts;
			System.out.println("The storage at the end of the day: ");
			for (int i = 0; i<storeAssis.foodCounts.length;i++) {
				if(i == 0) {
					System.out.println("Egg roll: "+ storeAssis.foodCounts[i]);
				}
				if(i == 1) {
					System.out.println("Pastry roll: "+ storeAssis.foodCounts[i]);
				}
				if(i == 2) {
					System.out.println("Sausage roll: "+ storeAssis.foodCounts[i]);
				}
				if(i == 3) {
					System.out.println("Spring roll: "+ storeAssis.foodCounts[i]);
				}
				if(i == 4) {
					System.out.println("Jelly roll: "+ storeAssis.foodCounts[i]);
				}
			}
			/*
			int dailySell = 0;
			for (int i = 0; i<storeAssis.foodCounts.length;i++) {
				endCounts[i] = beginCounts[i]-endCounts[i];
				dailySell += endCounts[i];
			}
			System.out.println("daily sell was "+ dailySell);
			System.out.println("daily roll Outage was "+ totalOutage);
			*/
		
			
	} 
//		System.out.println("totalCasualRollNum: "+totalCasualRollNum);
//		System.out.println("totalBusniesslRollNum: "+totalBusniesslRollNum);
//		System.out.println("totalCateringRollNum: "+totalCateringRollNum);
		int totalRollNum = totalCasualRollNum + totalBusniesslRollNum + totalCateringRollNum;
		double totalPayAll = totalCostOfCasualAll+totalCostOfBusniessAll+totalCostOfCateringAll;
		int totalOutageAll = totalOutageCasualAll+totalOutageBusniessAll+totalOutageCateringAll;
		System.out.println("Here is the total data for late day: ");
		System.out.println("Daily Sales: "+ Arrays.toString(ds));
		System.out.println("Daily Outage: "+ Arrays.toString(dor));
		System.out.println("Total roll number sold overall: "+ totalRollNum);
		System.out.println("Total money in sell was: "+ totalPayAll);
		System.out.println("Total number of outage impact was: "+ totalOutageAll);
	}}
