package org.genetic.schedule.model;

import java.util.ArrayList;
import java.util.List;

public class Data {

	static int[] lProjectDuration = new int[] {350 ,450 ,150 ,780 ,360 ,210 ,420 ,150 ,500 ,145 ,576 ,154 ,260};
	static int[] lInvestMentCost = new int[] {35000 ,36000 ,35000 ,70000 ,42000 ,12500 ,50000 ,47550 ,73200 ,14800 ,47000 ,12000 ,36000};
	static int[] lProfit = new int[]{2000 ,3000 ,2500 ,5000 ,3000 ,1200 ,3400 ,3300 ,5000 ,1300 ,3000 ,1000 ,2500};
	static Boolean[] lEquimentRequired = new Boolean[] {false ,true,false,true ,true ,false ,true ,true ,true ,false ,true ,false ,false};
	static int[] lWorkerRequired = new int[] {5 ,6 ,4 ,8 ,4 ,3 ,5 ,2 ,5 ,1 ,6 ,1 ,3};
	static int[] lBidTimeRequired = new int[] {8 ,8 ,7 ,13 ,9 ,4 ,10 ,9 ,14 ,4 ,9 ,3 ,8};
	public static List<Project> lProjectData=new ArrayList<Project>();
	
	static
	{
		Project p=null;
		
		System.out.println("-------------------------------------------------------------------------- ");
		
		System.out.println("-------------------------- Project Data ---------------------------------- ");
		
		System.out.println("-------------------------------------------------------------------------- ");
		
		
		System.out.println(" Project | Duration | Investment Cost | Profit | Equiment Req | Workder Req | Bid Time Req");
		
		
		for(int i=0;i<13;i++)
		{
			p=new Project();
			p.setDuration(lProjectDuration[i]);
			p.setInvestmentCost(lInvestMentCost[i]);
			p.setProfit(lProfit[i]);
			p.setCraneRequired(lEquimentRequired[i]);
			p.setWorkerRequired(lWorkerRequired[i]);
			p.setTimeRequiredForBid(lBidTimeRequired[i]);
			lProjectData.add(p);
			
			System.out.format("%8d", i);
			System.out.format("%8d",lProjectDuration[i]);
			System.out.format("%16d",lInvestMentCost[i]);
			System.out.format("%14d",lProfit[i]);
			System.out.format("%12d",lEquimentRequired[i] ? 1 : 0);
			System.out.format("%12d",lWorkerRequired[i]);
			System.out.format("%12d",lBidTimeRequired[i]);
			
			System.out.println();
			
			//System.out.println( i +  " | " + lProjectDuration[i] + " | " +lInvestMentCost[i] + " | " +lEquimentRequired[i] + " | " +lWorkerRequired[i] + " | " + lBidTimeRequired[i] + " | ");
		}
		
		System.out.println("--------------------------------------------------------------------------------------------------------"); 
		System.out.println("Contraints --> MAXIMUM AVAILABLE BUDGET : 270000  --> MAXIUM AVAILABLE CRANES : 4 --> Maxium Available Workers : 30 --> Maxium Available Bid Time : 65 ");
		System.out.println("--------------------------------------------------------------------------------------------------------");
	}
}
