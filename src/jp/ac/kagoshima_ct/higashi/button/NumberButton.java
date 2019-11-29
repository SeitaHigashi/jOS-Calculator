package jp.ac.kagoshima_ct.higashi.button;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class NumberButton extends CalcButton {
    public NumberButton(String str) {
        super(str);
        setNormalColor(new Color(38, 38, 39));
        setPressingColor(new Color(95, 96, 97));
        setPressedColor(new Color(38, 38, 39));
        setNormalTextColor(new Color(254, 255, 255));
        setPressedTextColor(new Color(254, 255, 255));
    }

    @Override
    protected void initShape() {
        int minLen = (getWidth() < getHeight()) ? getWidth() : getHeight();
        int margin = minLen / 20;
        int size = minLen - margin * 2;
        shape = new RoundRectangle2D.Float(
                margin, margin, size, size, size, size);
    }
}
