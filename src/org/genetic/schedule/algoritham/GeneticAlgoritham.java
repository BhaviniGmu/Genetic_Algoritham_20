package org.genetic.schedule.algoritham;

import java.util.Collections;
import java.util.Random;

import org.genetic.schedule.model.Data;
import org.genetic.schedule.model.Invidual;
import org.genetic.schedule.model.Population;
import org.genetic.schedule.model.Project;

public class GeneticAlgoritham {

	private Population p;

	public void calculateFitness(Invidual inv) {
		int i = 0;

		inv.totalCost = 0;
		inv.totalCraneRequired = 0;
		inv.totalProfit = 0;
		inv.totalWorkerRequired = 0;
		inv.totalBidTimeRequired = 0;
		inv.conflicts = 0;

		for (Project p : Data.lProjectData) {
			inv.totalCost = inv.totalCost + (inv.getCs()[i] == '1' ? 1 : 0) * p.getInvestmentCost();
			inv.totalCraneRequired = inv.totalCraneRequired
					+ (int) (inv.getCs()[i] == '1' && p.isCraneRequired() ? 1 : 0);
			inv.totalProfit = inv.totalProfit + (inv.getCs()[i] == '1' ? 1 : 0) * p.getProfit();
			inv.totalWorkerRequired = inv.totalWorkerRequired + (inv.getCs()[i] == '1' ? 1 : 0) * p.getWorkerRequired();
			inv.totalBidTimeRequired = inv.totalBidTimeRequired
					+ (inv.getCs()[i] == '1' ? 1 : 0) * p.getTimeRequiredForBid();
			i++;
		}

		// System.out.println("Total Cost : " + inv.totalCost + "--Total Profit : " +
		// inv.totalProfit + " -- TotalEquimentRequired : " + inv.totalCraneRequired + "
		// -- TotalWorkerRequired : "+ inv.totalWorkerRequired + " --
		// TotalBidTimeRequired: "+ inv.totalBidTimeRequired);

		if (inv.totalCost > 270000) {
			inv.conflicts++;
		}
		if (inv.totalCraneRequired > 4) {
			inv.conflicts++;
		}
		if (inv.totalWorkerRequired > 30) {
			inv.conflicts++;
		}

		if (inv.totalBidTimeRequired > 65) {
			inv.conflicts++;
		}

		inv.setFitness(inv.totalProfit);
	}

	public static void main(String[] args) {

		GeneticAlgoritham ga = new GeneticAlgoritham();
		ga.initilizePopulation();
		int i = 1;
		int j = 0;
		int random1 = 0;
		int random2 = 0;

		System.out.println("-------------------------------------------------------------------------- ");
		System.out.println(" ----------------------------INTIAL DATA ---------------------------------");

		System.out.println("-------------------------------------------------------------------------- ");
		for (Invidual inv : ga.p.individuals) {
			ga.calculateFitness(inv);
			System.out.println("Chromosome : --> " + new String(inv.getCs()) + "  -- Fitness for Chromosome :" + i
					+ " --> " + inv.getFitness() + " -- Constraint Violation -- >" + inv.conflicts);

			// System.out.println("Chromosome : --> " + new String(inv.getCs()) + " --
			// Fitness for Chromosome :" +i+ " --> " +inv.getFitness() + " -- Constraint
			// Violation -- >" + inv.conflicts);
			// i++;
		}

		
		Population p1;
		Population p2;
		Invidual fittestInv=null;

		while (j <= 8192) {
			
			Collections.sort(ga.p.individuals);
			
			System.out.println("-------------------------------------------------------------------------- ");
			
			System.out.println("Sorting Population : BASED PROFIT (FITNESS) -- MOST PROFIT CHROMOSOME ON TOP -- ");

			System.out.println("-------------------------------------------------------------------------- ");
			for (Invidual inv : ga.p.individuals) {
				System.out.println("Chromosome : --> " + new String(inv.getCs()) + "  -- Fitness for Chromosome :" + i
						+ " --> " + inv.getFitness() + " -- Constraint Violation -- >" + inv.conflicts);

			}

			System.out.println("-------------------------------------------------------------------------- ");

			System.out.println("-------------------------------------------------------------------------- ");

			fittestInv = ga.findFittest(ga.p);
			
			if(fittestInv.getFitness() >= 20300)
			{
				break;
			}
			
			
			System.out.println("-------------------------------------------------------------------------- ");

			p1 = new Population();
			p2 = new Population();
			p1 = ga.crossOverPopulation(p1, ga.roulettewheelSelection(ga.p), ga.roulettewheelSelection(ga.p));

			p2 = ga.mutatePopulation(p2, p1.individuals.get(0));
			p2 = ga.mutatePopulation(p2, p1.individuals.get(1));

			System.out.println("Generation -- " + j);

			System.out.println("Chromosome : --> " + new String(ga.p.individuals.get(random1).getCs())
					+ " -- Fitness for Chromosome :  --> " + ga.p.individuals.get(random1).getFitness()
					+ " -- Constraint Violation -- >" + ga.p.individuals.get(random1).conflicts);
			System.out.println("Chromosome : --> " + new String(ga.p.individuals.get(random2).getCs())
					+ " -- Fitness for Chromosome :  --> " + ga.p.individuals.get(random2).getFitness()
					+ " -- Constraint Violation -- >" + ga.p.individuals.get(random2).conflicts);

			for (Invidual inv : p1.individuals) {
				ga.calculateFitness(inv);
				System.out.println("Chromosome : --> " + new String(inv.getCs()) + " -- Fitness for Chromosome :" + i
						+ " --> " + inv.getFitness() + " -- Constraint Violation -- >" + inv.conflicts);
				// i++;
			}

			for (Invidual inv : p2.individuals) {
				ga.calculateFitness(inv);
				System.out.println("Chromosome : --> " + new String(inv.getCs()) + " -- Fitness for Chromosome :" + i
						+ " --> " + inv.getFitness() + " -- Constraint Violation -- >" + inv.conflicts);
				// i++;
			}

			System.out.println("-------------------------------------------------------------------------- ");
			
			System.out.println("Finding feasible Chromosome from Mutation Chromosomes");

			Invidual feasibleInv = ga.findFeasible(p2);
			System.out.println("Found Feasible Chromosome : --> " + new String(feasibleInv.getCs())
					+ "  -- Fitness for Chromosome : --> " + feasibleInv.getFitness() + " -- Constraint Violation -- >"
					+ feasibleInv.conflicts);
			System.out.println("Replacing Feasible chromosome in Main Population and regenerating population");
			
			System.out.println("-------------------------------------------------------------------------- ");
			ga.replaceFeasible(ga.p, feasibleInv);
			j++;
		}

		System.out.println(" Fittest Chromosome ( Maxium Profit ): --> "
				+ new String(fittestInv.getCs()) + "  -- Fitness for Chromosome : --> "
				+ fittestInv.getFitness() + " -- Constraint Violation -- >" + fittestInv.conflicts);

		
	}

	public Invidual findFittest(Population p) {

		for (Invidual inv : p.individuals) {
			if (inv.conflicts == 0) {
				return inv;
			}
		}
		return null;
	}

	/**
	 * Replace most feasible solution with least feasible solution in Main
	 * Population
	 * 
	 */

	public void replaceFeasible(Population p, Invidual mostFeasilbeInv) {
		p.individuals.remove(p.individuals.get(p.individuals.size() - 1));
		p.individuals.add(mostFeasilbeInv);
	}

	public void evolvePopulation() {

	}

	public Invidual findFeasible(Population population) {
		// System.out.println("Sorting Population");

		Collections.sort(population.individuals);

		// Get the most profitable Chromosome
		Invidual inv = population.individuals.get(0);

		// System.out.println("Got most profit Chromosome : " + inv.fitness + " --
		// Conflicts --" +inv.conflicts);
		if (inv.conflicts == 0)
			return inv;
		else {
			// System.out.println("Having conflicts. Let's make it feasible");
			this.makeFeasible(inv);
			this.findFeasible(population);
		}
		return inv;
	}

	public Invidual makeFeasible(Invidual inv) {
		if (inv.conflicts == 0)
			return inv;
		else {
			// System.out.println("changing first gene 1 to 0");
			char[] cs = inv.getCs();
			for (int i = 0; i < cs.length; i++) {
				if (cs[i] == '1') {
					cs[i] = '0';
					break;
				}
			}
			// System.out.println("Calculating fitness again");
			this.calculateFitness(inv);
			// System.out.println("New profit - : " + inv.fitness + " -- Conflicts --"
			// +inv.conflicts);
			// System.out.println("checking again if it is feasible of not");
			this.makeFeasible(inv);
		}
		return inv;
	}

	public void initilizePopulation() {

		setP(new Population());

		char[] cs1 = { '1', '0', '1', '0', '1', '1', '1', '1', '1', '1', '0', '1', '1' };
		char[] cs2 = { '1', '1', '0', '0', '1', '0', '1', '1', '0', '1', '0', '1', '0' };
		char[] cs3 = { '1', '0', '0', '1', '0', '1', '1', '1', '1', '1', '0', '0', '0' };
		char[] cs4 = { '1', '0', '1', '1', '0', '1', '1', '0', '0', '1', '1', '1', '0' };
		char[] cs5 = { '0', '0', '0', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0' };
		char[] cs6 = { '0', '1', '0', '1', '1', '1', '1', '0', '0', '0', '1', '1', '0' };
		char[] cs7 = { '1', '1', '0', '0', '0', '0', '0', '1', '0', '1', '1', '0', '1' };
		char[] cs8 = { '0', '1', '0', '0', '0', '1', '0', '0', '1', '0', '0', '0', '1' };
		char[] cs9 = { '0', '0', '0', '0', '0', '1', '0', '1', '1', '1', '1', '1', '0' };
		char[] cs10 = { '1', '1', '0', '0', '1', '0', '1', '1', '1', '0', '1', '0', '0' };

		Invidual inv1 = new Invidual();
		Invidual inv2 = new Invidual();
		Invidual inv3 = new Invidual();
		Invidual inv4 = new Invidual();
		Invidual inv5 = new Invidual();
		Invidual inv6 = new Invidual();
		Invidual inv7 = new Invidual();
		Invidual inv8 = new Invidual();
		Invidual inv9 = new Invidual();
		Invidual inv10 = new Invidual();

		inv1.setCs(cs1);
		inv2.setCs(cs2);
		inv3.setCs(cs3);
		inv4.setCs(cs4);
		inv5.setCs(cs5);
		inv6.setCs(cs6);
		inv7.setCs(cs7);
		inv8.setCs(cs8);
		inv9.setCs(cs9);
		inv10.setCs(cs10);

		getP().individuals.add(inv1);
		getP().individuals.add(inv2);
		getP().individuals.add(inv3);
		getP().individuals.add(inv4);
		getP().individuals.add(inv5);
		getP().individuals.add(inv6);
		getP().individuals.add(inv7);
		getP().individuals.add(inv8);
		getP().individuals.add(inv9);
		getP().individuals.add(inv10);
	}

	public Population crossOverPopulation(Population p, Invidual inv1, Invidual inv2) {

		char[] cs1 = inv1.getCs();
		char[] cs2 = inv2.getCs();
		char[] cs3 = new char[13];
		char[] cs4 = new char[13];

		int l = cs1.length - 1;

		for (int i = 0; i < (cs1.length + 1) / 2; i++) {
			cs3[i] = cs1[i];
			cs3[l - i] = cs2[l - i];

			cs4[i] = cs2[i];
			cs4[l - i] = cs1[l - i];
		}

		Invidual inv3 = new Invidual();
		Invidual inv4 = new Invidual();
		inv3.setCs(cs3);
		inv4.setCs(cs4);

		p.individuals.add(inv3);
		p.individuals.add(inv4);

		return p;
	}

	public Population mutatePopulation(Population p, Invidual inv) {
		Invidual invMutated = new Invidual();
		char[] cs = inv.getCs();
		char[] csModified = new char[13];
		for (int i = 0; i < inv.getCs().length; i++) {
			if (Math.random() <= 0.8) {
				if (Math.round(Math.random()) == 1) {
					csModified[i] = '1';
				} else {
					csModified[i] = '0';
				}
			} else {
				csModified[i] = cs[i];
			}
		}
		invMutated.setCs(csModified);
		// System.out.println("Before Mutation :" + new String(cs) + " -- After Mutation
		// :" + new String(csModified));
		p.individuals.add(invMutated);
		return p;
	}

	public Population getP() {
		return p;
	}

	public void setP(Population p) {
		this.p = p;
	}


	public Invidual roulettewheelSelection(Population p) {

		Random r = new Random();
		int totalFitness = 0;
		for (Invidual inv : p.individuals) {
			totalFitness = totalFitness + inv.getTotalProfit();
		}
		int max = totalFitness + 1;

		int randomNumber = r.nextInt(max) + 1;
		int runningSum = 0;
		int index = 0;
		int lastAddedIndex = 0;
		while (runningSum < randomNumber) {
			runningSum += p.individuals.get(index).getTotalProfit();
			lastAddedIndex = index;
			index++;
		}
		return p.individuals.get(lastAddedIndex);
	}

}
