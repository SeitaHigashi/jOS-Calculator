package jp.ac.kagoshima_ct.higashi;

import java.awt.*;
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
import jp.ac.kagoshima_ct.higashi.calculator.operator.*;
import jp.ac.kagoshima_ct.higashi.calculator.Operator;

public class jOSCalculator extends JFrame implements ActionListener {

    private GridBagLayout gridBagLayout;

    private CalcButton lastButton = null;

    private JLabel label;

    private CalcStack stack;

    private String calcNum;

    public jOSCalculator(){
        stack = new CalcStack();
        stack.push(new Multiplier(1.0));
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
        label.setOpaque(true);
        label.setBackground(new Color(0, 0, 0));
        label.setForeground(new Color(255, 255, 255));
        label.setHorizontalAlignment(JLabel.RIGHT);
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
        new jOSCalculator();
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
                calcNum = String.valueOf(new Percentage(Double.parseDouble(labelNum)).calc(0.0, stack));
                label.setText(calcNum);
                return;
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
        else if(calcNum.matches("\\d*\\.\\d*") && lastButton.getText().equals("."));
        else
            calcNum += lastButton.getText();
        label.setText(calcNum);
        System.out.println(stack.size());
    }
}
