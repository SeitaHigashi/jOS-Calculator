package jp.ac.kagoshima_ct.higashi.calculator;

import java.util.EmptyStackException;
import java.util.Stack;

public class CalcStack extends Stack<Operator> {

    /**
     * プッシュするたび計算可能な部分を計算します
     * @param operator
     * @return プッシュした演算子を返します
     */
    @Override
    public Operator push(Operator operator) {
        try {
            Operator wasStacked = super.pop();

            while (wasStacked.getPriority() >= operator.getPriority()) {
                double calculatedNumber = operator.getCalculatedNumber();
                calculatedNumber = wasStacked.calc(calculatedNumber, this);
                operator.setCalclatedNumber(calculatedNumber);
                wasStacked = super.pop();
            }
            super.push(wasStacked);
        } catch (EmptyStackException e) {
        } finally {
            return super.push(operator);
        }
    }

    /**
     * スタックに残っている演算子を計算します
     * @param numberOfCalclate
     * @return 計算結果
     */
    public double calc(double numberOfCalclate) {
        while (!this.isEmpty()) {
            numberOfCalclate = super.pop().calc(numberOfCalclate, this);
        }
        return numberOfCalclate;
    }
}
