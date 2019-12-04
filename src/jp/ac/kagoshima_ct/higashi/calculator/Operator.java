package jp.ac.kagoshima_ct.higashi.calculator;

/**
 * 演算子を表す抽象クラスです
 */
public abstract class Operator {
    /**
     * 被演算子
     */
    protected double calculatedNumber;

    /**
     * 演算子の優先度を表します
     */
    protected int priority = 0;

    /**
     * 演算子ごとに計算内容を記述します
     * @param numberOfCalclate
     * @param stack
     * @return 計算結果
     */
    public abstract double calc(double numberOfCalclate, CalcStack stack);

    /**
     * 被演算子を設定します
     * @param calculatedNumber
     * @return 演算子本体
     */
    public Operator setCalclatedNumber(double calculatedNumber) {
        this.calculatedNumber = calculatedNumber;
        return this;
    }

    /**
     * 被演算子を取得します
     * @return 被演算子
     */
    public double getCalculatedNumber() {
        return this.calculatedNumber;
    }

    /**
     * 優先度を取得します
     * @return 優先度
     */
    public int getPriority() {
        return priority;
    }

    public Operator(double calculatedNumber) {
        this.calculatedNumber = calculatedNumber;
    }

    @Override
    public String toString(){
        return this.toString("operator");
    }

    protected String toString(String operator){
       return String.valueOf(calculatedNumber)  + " " +operator+ " ";
    }
}
