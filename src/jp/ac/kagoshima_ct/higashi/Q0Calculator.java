package jp.ac.kagoshima_ct.higashi;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import jp.ac.kagoshima_ct.higashi.button.CalcButton;
import jp.ac.kagoshima_ct.higashi.button.ControlButton;
import jp.ac.kagoshima_ct.higashi.button.FuncButton;
import jp.ac.kagoshima_ct.higashi.button.NumberButton;
import jp.ac.kagoshima_ct.higashi.button.ZeroButton;
import jp.ac.kagoshima_ct.higashi.calculator.CalcStack;
import jp.ac.kagoshima_ct.higashi.calculator.operator.Adder;
import jp.ac.kagoshima_ct.higashi.calculator.Operator;
import jp.ac.kagoshima_ct.higashi.calculator.operator.Divider;
import jp.ac.kagoshima_ct.higashi.calculator.operator.Multiplier;
import jp.ac.kagoshima_ct.higashi.calculator.operator.Subtractor;

public class Q0Calculator extends JFrame implements ActionListener {

    private GridBagLayout gridBagLayout;

    private CalcButton lastButton = null;

    private JLabel label;

    private CalcStack stack;

    private String calcNum;

    public Q0Calculator(){
        stack = new CalcStack();
        stack.push(new Adder(0.0));
        calcNum = "0";
        this.gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        setSize(400, 400);
        initDisplay();
        initButton();
        setVisible(true);
    }

    private void initDisplay(){
        label = new JLabel("0");
        addComponents(label, 0, 0, 4, 2);
    }

    private void initButton(){
        CalcButton zero = new ZeroButton();
        zero.addActionListener(this);
        addComponents(zero, 0, 6, 2, 1);
        CalcButton comma = new NumberButton(".");
        comma.addActionListener(this);
        addComponents(comma, 2, 6, 1, 1);
        for(int i = 1; i<10;i++){
            CalcButton numButton = new NumberButton(String.valueOf(i));
            numButton.addActionListener(this);
            addComponents(numButton, i%3-1, 5-(i-1)/3, 1, 1);
        }
        String funccmd[] = {"/", "*", "-" , "+", "="};
        for(int i = 0; i< 5; i++) {
            CalcButton func = new FuncButton(funccmd[i]);
            func.addActionListener(this);
            addComponents(func, 3, i+2, 1, 1);
        }
        String controlcmd[] = {"AC", "+/-", "%"};
        for(int i = 0; i < 3; i++){
            CalcButton control = new ControlButton(controlcmd[i]);
            control.addActionListener(this);
            addComponents(control, i, 2, 1, 1);
        }
    }

    public void addComponents(Component component, int x, int y, int width, int height){
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.gridwidth = width;
        gridBagConstraints.gridheight = height;
        gridBagConstraints.weightx = 1.0d;
        gridBagConstraints.weighty = 1.0d;

        gridBagLayout.setConstraints(component, gridBagConstraints);
        add(component);
    }

    public static void main(String[] args){
        new Q0Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(lastButton != e.getSource())
                lastButton.anotherButtonWasPressed();
        }catch(NullPointerException error){
        }
        lastButton = (CalcButton) e.getSource();
        String labelNum = label.getText();
        Operator calc = null;
        Double num = 0.0;
        switch (lastButton.getText()) {
            case "+":
                calc = stack.push(new Adder(Double.parseDouble(labelNum)));
                label.setText(String.valueOf(calc.getCalculatedNumber()));
                calcNum = "0";
                return;
            case "-":
                calc = stack.push(new Subtractor(Double.parseDouble(labelNum)));
                label.setText(String.valueOf(calc.getCalculatedNumber()));
                calcNum = "0";
                return;
            case "*":
                calc = stack.push(new Multiplier(Double.parseDouble(labelNum)));
                label.setText(String.valueOf(calc.getCalculatedNumber()));
                calcNum = "0";
                return;
            case "/":
                calc = stack.push(new Divider(Double.parseDouble(labelNum)));
                label.setText(String.valueOf(calc.getCalculatedNumber()));
                calcNum = "0";
                return;
            case "=":
                label.setText(String.valueOf(stack.calc(Double.parseDouble(labelNum))));
                return;
            case "%":
            case "AC":
                stack.clear();
                label.setText("0");
                calcNum = "0";
                return;
            case "+/-":
                if(calcNum.matches("-.*"))
                    calcNum = calcNum.substring(1);
                else
                    calcNum = "-" + calcNum;
                label.setText(calcNum);
                return;
        }
        if(calcNum.equals("0") && !lastButton.getText().equals("."))
            calcNum = lastButton.getText();
        else if(calcNum.matches(".*\\..*") && lastButton.getText().equals("."));
        else
            calcNum += lastButton.getText();
        label.setText(calcNum);
        System.out.println(stack.size());
    }

    private void _MultiDiv(Operator calc, String labelNum) {
    	String stack_str = stack.peek().getClass().toString();
    	if(stack_str.equals(Multiplier.class.toString()) || stack_str.equals(Divider.class.toString())) {
    		Double num = stack.pop().calc(Double.parseDouble(labelNum));
    		stack.push(calc.setCalclatedNumber(num));
    		label.setText(num.toString());
    	}else {
    		stack.push(calc.setCalclatedNumber(Double.parseDouble(labelNum)));
    	}
    	calcNum = "0";

    }
}
