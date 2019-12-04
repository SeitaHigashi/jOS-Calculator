package jp.ac.kagoshima_ct.higashi.calculator.operator;

import jp.ac.kagoshima_ct.higashi.calculator.CalcStack;
import jp.ac.kagoshima_ct.higashi.calculator.Operator;

/**
 * 除算演算子を表します
 */
public class Divider extends Operator {
    public Divider(double calculatedNumber) {
        super(calculatedNumber);
        this.priority = 2;
    }

    @Override
    public double calc(double numberOfCalclate, CalcStack stack) {
        return this.calculatedNumber / numberOfCalclate;
    }
}
