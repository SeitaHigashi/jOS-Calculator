package jp.ac.kagoshima_ct.higashi.button;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ZeroButton extends CalcButton {
    public ZeroButton() {
        super("0");
        setNormalColor(new Color(38, 38, 39));
        setPressingColor(new Color(95, 96, 97));
        setPressedColor(new Color(38, 38, 39));
        setNormalTextColor(new Color(254, 255, 255));
        setPressedTextColor(new Color(254, 255, 255));
        setHorizontalAlignment(JButton.CENTER);
        setMargin(new Insets(0, 0, 0, getWidth()/2));
    }

    @Override
    public void updateUI(){
        super.updateUI();
        setMargin(new Insets(0, 0, 0, getWidth()/2));
    }

    @Override
    protected void initShape() {
        int minLen = (getWidth() < getHeight()) ? getWidth() : getHeight();
        int margin = minLen / 20;
        int size = minLen - margin * 2;
        shape = new RoundRectangle2D.Float(
                margin, margin, getWidth() - margin * 2, getHeight() - margin * 2, size, size);
    }
}
