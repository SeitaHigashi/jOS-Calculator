package jp.ac.kagoshima_ct.higashi.calculator.operator;

import jp.ac.kagoshima_ct.higashi.calculator.Operator;

public class Multiplier extends Operator {
    public Multiplier(double calclatedNumber) {
        super(calclatedNumber);
        this.priority = 2;
    }

    @Override
    public double calc(double numberOfCalclate) {
        return this.calculatedNumber * numberOfCalclate;
    }
}
