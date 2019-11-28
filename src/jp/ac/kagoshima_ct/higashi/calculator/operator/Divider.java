package jp.ac.kagoshima_ct.higashi.calculator.operator;

import jp.ac.kagoshima_ct.higashi.calculator.Operator;

public class Divider extends Operator {
    public Divider(double calculatedNumber) {
        super(calculatedNumber);
        this.priority = 2;
    }

    @Override
    public double calc(double numberOfCalclate) {
        return this.calculatedNumber / numberOfCalclate;
    }
}
