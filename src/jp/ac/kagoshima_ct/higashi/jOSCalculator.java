package jp.ac.kagoshima_ct.higashi;

import jp.ac.kagoshima_ct.higashi.button.*;
import jp.ac.kagoshima_ct.higashi.calculator.CalcStack;
import jp.ac.kagoshima_ct.higashi.calculator.Operator;
import jp.ac.kagoshima_ct.higashi.calculator.operator.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class jOSCalculator extends JFrame implements ActionListener , WindowListener {

    private GridBagLayout gridBagLayout;

    private CalcButton lastButton = null;

    private JLabel label;

    private CalcStack stack;

    private String calcNum;

    private boolean numButtonPressed;

    public jOSCalculator() {
        numButtonPressed = false;
        stack = new CalcStack();
        stack.push(new Multiplier(1.0));
        calcNum = "0";
        this.gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        setSize(350, 667);
        initDisplay();
        initButton();
        setTitle("jOS Calculator");
        setResizable(false);
        setVisible(true);
    }

    private void initDisplay() {
        Font font = null;
        try {
            File fontFile = new File(getClass().getClassLoader().getResource("Helvetica.ttf").toURI().toString());
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (Exception e) {
            e.getStackTrace();
            font = new Font("Arial", Font.PLAIN, 60);
        }

        JLabel stretch = new JLabel("");
        stretch.setOpaque(true);
        stretch.setBackground(new Color(0, 0, 0));
        stretch.setForeground(new Color(255, 255, 255));
        stretch.setFont(font);
        addComponents(stretch, 0, 0, 4, 1);
        label = new JLabel("0");
        label.setOpaque(true);
        label.setBackground(new Color(0, 0, 0));
        label.setForeground(new Color(255, 255, 255));
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setFont(font);
        addComponents(label, 0, 1, 4, 1);
    }

    private void initButton() {
        CalcButton zero = new ZeroButton();
        zero.addActionListener(this);
        addComponents(zero, 0, 6, 2, 1);
        CalcButton comma = new NumberButton(".");
        comma.addActionListener(this);
        addComponents(comma, 2, 6, 1, 1);
        for (int i = 1; i < 10; i++) {
            CalcButton numButton = new NumberButton(String.valueOf(i));
            numButton.addActionListener(this);
            addComponents(numButton, i % 3 - 1, 5 - (i - 1) / 3, 1, 1);
        }
        String funccmd[] = {"\u00f7", "\u00d7", "\u002B", "\u2212", "\u003d"};
        for (int i = 0; i < 5; i++) {
            CalcButton func = new FuncButton(funccmd[i]);
            func.addActionListener(this);
            addComponents(func, 3, i + 2, 1, 1);
            if(i == 4) {
                func.setPressedColor(new Color(249, 142, 15));
                func.setPressedTextColor(new Color(254, 255, 255));
            }
        }
        String controlcmd[] = {"AC", "+/-", "\u0025"};
        for (int i = 0; i < 3; i++) {
            CalcButton control = new ControlButton(controlcmd[i]);
            control.addActionListener(this);
            addComponents(control, i, 2, 1, 1);
        }
    }

    public void addComponents(Component component, int x, int y, int width, int height) {
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

    public static void main(String[] args) {
        new jOSCalculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println(stack.toString());
        try {
            if (lastButton != e.getSource())
                lastButton.anotherButtonWasPressed();
        } catch (NullPointerException error) {
        }
        lastButton = (CalcButton) e.getSource();
        String labelNum = label.getText();
        Operator calc = null;
        Double num = 0.0;
        if(lastButton instanceof FuncButton && !lastButton.getText().equals("\u003d") && !numButtonPressed){
            stack = stack.undo();
        }
        switch (lastButton.getText()) {
            case "\u002B": // +
                calc = stack.push(new Adder(Double.parseDouble(labelNum)));
                label.setText(String.valueOf(calc.getCalculatedNumber()));
                calcNum = "0";
                return;
            case "\u2212": // -
                calc = stack.push(new Subtractor(Double.parseDouble(labelNum)));
                label.setText(String.valueOf(calc.getCalculatedNumber()));
                calcNum = "0";
                return;
            case "\u00d7": // *
                calc = stack.push(new Multiplier(Double.parseDouble(labelNum)));
                label.setText(String.valueOf(calc.getCalculatedNumber()));
                calcNum = "0";
                return;
            case "\u00f7": // /
                calc = stack.push(new Divider(Double.parseDouble(labelNum)));
                label.setText(String.valueOf(calc.getCalculatedNumber()));
                calcNum = "0";
                return;
            case "\u003D":
                label.setText(String.valueOf(stack.calc(Double.parseDouble(labelNum))));
                calcNum = "0";
                return;
            case "\u0025": // %
                calcNum = String.valueOf(new Percentage(Double.parseDouble(labelNum)).calc(0.0, stack));
                label.setText(calcNum);
                return;
            case "AC":
                stack.clear();
                label.setText("0");
                calcNum = "0";
                return;
            case "+/-":
                if (calcNum.matches("-.*"))
                    calcNum = calcNum.substring(1);
                else
                    calcNum = "-" + calcNum;
                label.setText(calcNum);
                return;
        }
        if(lastButton instanceof NumberButton || lastButton instanceof ZeroButton)
            numButtonPressed = true;
        else
            numButtonPressed = false;
        if (calcNum.equals("0") && !lastButton.getText().equals("."))
            calcNum = lastButton.getText();
        else if (calcNum.matches("\\d*\\.\\d*") && lastButton.getText().equals(".")) ;
        else
            calcNum += lastButton.getText();
        label.setText(calcNum);
        //System.out.println(stack.size());
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.exit(0);

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
