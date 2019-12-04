package jp.ac.kagoshima_ct.higashi.button;

import java.awt.*;

public class ControlButton extends NumberButton {
    public ControlButton(String text) {
        super(text);
        setNormalColor(new Color(147, 148, 149));
        setPressingColor(new Color(207, 208, 209));
        setPressedColor(new Color(147, 148, 149));
        setNormalTextColor(new Color(0, 0, 1));
        setPressedTextColor(new Color(0, 0, 1));
    }

    @Override
    public void updateUI(){
        super.updateUI();
        setFont(getFont().deriveFont((float)getHeight()*(float)0.4));
    }
}
