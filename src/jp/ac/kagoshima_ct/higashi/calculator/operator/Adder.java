package jp.ac.kagoshima_ct.higashi.calculator.operator;

import jp.ac.kagoshima_ct.higashi.calculator.CalcStack;
import jp.ac.kagoshima_ct.higashi.calculator.Operator;

public class Adder extends Operator {
    public Adder(double calculatedNumber) {
        super(calculatedNumber);
        this.priority = 1;
    }

    @Override
    public double calc(double numberOfCalclate, CalcStack stack) {
        return this.calculatedNumber + numberOfCalclate;
    }
}
