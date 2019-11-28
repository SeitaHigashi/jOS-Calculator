package jp.ac.kagoshima_ct.higashi.button;

import java.awt.*;

public class FuncButton extends NumberButton {
    public FuncButton(String text){
        super(text);
        setNormalColor(new Color(249, 142, 15));
        setPressingColor(new Color(245, 189, 124));
        setPressedColor(new Color(254, 255, 255));
        setNormalTextColor(new Color(254, 255, 255));
        setPressedTextColor(new Color(249, 142, 15));
    }
}
