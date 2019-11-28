package jp.ac.kagoshima_ct.higashi.calculator;

public abstract class Operator {
    protected double calculatedNumber;

    protected int priority = 0;

    public abstract double calc(double numberOfCalclate);

    public Operator setCalclatedNumber(double calculatedNumber) {
    	this.calculatedNumber = calculatedNumber;
    	return this;
    }

    public double getCalculatedNumber(){
        return this.calculatedNumber;
    }

    public int getPriority(){
        return priority;
    }

    public Operator() {
    	this.calculatedNumber = 0.0;
    }

    public Operator(double calculatedNumber){
        this.calculatedNumber = calculatedNumber;
    }
}
