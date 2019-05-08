package org.genetic.schedule.model;

public class Project {

	int duration;
	int investmentCost;
	int profit;
	boolean craneRequired;
	int workerRequired;
	int timeRequiredForBid;
	
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getInvestmentCost() {
		return investmentCost;
	}
	public void setInvestmentCost(int investmentCost) {
		this.investmentCost = investmentCost;
	}
	public int getProfit() {
		return profit;
	}
	public void setProfit(int profit) {
		this.profit = profit;
	}
	public boolean isCraneRequired() {
		return craneRequired;
	}
	public void setCraneRequired(boolean craneRequired) {
		this.craneRequired = craneRequired;
	}
	public int getWorkerRequired() {
		return workerRequired;
	}
	public void setWorkerRequired(int workerRequired) {
		this.workerRequired = workerRequired;
	}
	public int getTimeRequiredForBid() {
		return timeRequiredForBid;
	}
	public void setTimeRequiredForBid(int timeRequiredForBid) {
		this.timeRequiredForBid = timeRequiredForBid;
	}
	
	
	
}
