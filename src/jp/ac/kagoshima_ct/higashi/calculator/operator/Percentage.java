package jp.ac.kagoshima_ct.higashi.calculator.operator;

import jp.ac.kagoshima_ct.higashi.calculator.CalcStack;
import jp.ac.kagoshima_ct.higashi.calculator.Operator;

public class Percentage extends Operator {
    public Percentage(double calculatedNumber){
        super(calculatedNumber);
        this.priority = 3;
    }
    @Override
    public double calc(double numberOfCalclate, CalcStack stack) {
        Operator wasStacked = stack.peek();
        if(wasStacked instanceof Multiplier || wasStacked instanceof Divider){
            return this.calculatedNumber * 0.01;
        }else {
            return wasStacked.getCalculatedNumber() * this.calculatedNumber * 0.01;
        }
    }
}
