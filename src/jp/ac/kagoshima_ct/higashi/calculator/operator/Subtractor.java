package jp.ac.kagoshima_ct.higashi.calculator.operator;

import jp.ac.kagoshima_ct.higashi.calculator.CalcStack;
import jp.ac.kagoshima_ct.higashi.calculator.Operator;

/**
 * 減算演算子を表します
 */
public class Subtractor extends Operator {
    public Subtractor(double calclatedNumber) {
        super(calclatedNumber);
        this.priority = 1;
    }

    @Override
    public double calc(double numberOfCalclate, CalcStack stack) {
        return this.calculatedNumber - numberOfCalclate;
    }

    @Override
    public String toString(){
        return super.toString("-");
    }
}
