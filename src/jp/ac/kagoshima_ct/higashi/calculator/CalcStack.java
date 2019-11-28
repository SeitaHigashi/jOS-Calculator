package jp.ac.kagoshima_ct.higashi.calculator;

import jp.ac.kagoshima_ct.higashi.calculator.Operator;

import java.util.EmptyStackException;
import java.util.Stack;

public class CalcStack extends Stack<Operator> {

    @Override
    public Operator push(Operator operator){
        try {
            Operator wasStacked = super.pop();

            while(wasStacked.getPriority() >= operator.getPriority()) {
                double calculatedNumber = operator.getCalculatedNumber();
                calculatedNumber = wasStacked.calc(calculatedNumber);
                operator.setCalclatedNumber(calculatedNumber);
                wasStacked = super.pop();
            }
            super.push(wasStacked);
        }catch (EmptyStackException e){
        }finally {
            return super.push(operator);
        }
    }

    public double calc(double numberOfCalclate){
        while(!this.isEmpty()){
            numberOfCalclate = super.pop().calc(numberOfCalclate);
        }
        return numberOfCalclate;
    }
}
