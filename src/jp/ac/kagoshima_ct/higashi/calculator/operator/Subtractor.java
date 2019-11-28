package jp.ac.kagoshima_ct.higashi.calculator.operator;

import jp.ac.kagoshima_ct.higashi.calculator.Operator;

public class Subtractor extends Operator {
    public Subtractor(double calclatedNumber) {
        super(calclatedNumber);
        this.priority = 1;
    }

    @Override
    public double calc(double numberOfCalclate) {
        return this.calculatedNumber - numberOfCalclate;
    }
}