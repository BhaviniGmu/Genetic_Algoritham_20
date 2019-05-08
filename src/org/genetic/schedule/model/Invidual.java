package org.genetic.schedule.model;

public class Invidual implements Comparable<Invidual> {

	public int fitness;
	public int conflicts = 0;
	public int totalCost = 0;
	public int totalProfit = 0;
	public int totalCraneRequired = 0;
	public int totalWorkerRequired = 0;
	public int totalBidTimeRequired = 0;
	char[] cs;

	

	public char[] getCs() {
		return cs;
	}

	public void setCs(char[] cs) {
		this.cs = cs;
	}

	public int getConflicts() {
		return conflicts;
	}

	public void setConflicts(int conflicts) {
		this.conflicts = conflicts;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public int getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(int totalProfit) {
		this.totalProfit = totalProfit;
	}

	public int getTotalCraneRequired() {
		return totalCraneRequired;
	}

	public void setTotalCraneRequired(int totalCraneRequired) {
		this.totalCraneRequired = totalCraneRequired;
	}

	public int getTotalWorkerRequired() {
		return totalWorkerRequired;
	}

	public void setTotalWorkerRequired(int totalWorkerRequired) {
		this.totalWorkerRequired = totalWorkerRequired;
	}

	public int getTotalBidTimeRequired() {
		return totalBidTimeRequired;
	}

	public void setTotalBidTimeRequired(int totalBidTimeRequired) {
		this.totalBidTimeRequired = totalBidTimeRequired;
	}

	@Override
	public int compareTo(Invidual u) {
		return u.getFitness() - this.getFitness() ;
	}

	public int getFitness() {
		return fitness;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}
}
