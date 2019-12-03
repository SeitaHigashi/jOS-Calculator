package jp.ac.kagoshima_ct.higashi.button;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class CalcButton extends JButton {
    public CalcButton(String text) {
        super(text);
        System.out.println(getHeight());
        try {
            File fontFile = new File(getClass().getClassLoader().getResource("Helvetica.ttf").toURI().toString());
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            font = font.deriveFont(35F);
            setFont(font);
        } catch (IOException e) {
            setFont(new Font("Arial", Font.PLAIN, 35));
        } catch (FontFormatException e) {
            setFont(new Font("Arial", Font.PLAIN, 35));
        } catch (URISyntaxException e) {
            setFont(new Font("Arial", Font.PLAIN, 35));
        }
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setPreferredSize(new Dimension(0, 0));
    }

    protected abstract void initShape();

    protected Shape shape;

    protected Color normalColor;
    protected Color pressingColor;
    protected Color pressedColor;
    protected Color nowColor;

    protected Color normalTextColor;
    protected Color pressedTextColor;
    protected Color nowTextColor;

    private Graphics2D g;

    @Override
    public void updateUI() {
        super.updateUI();
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBackground(new Color(0, 0, 0));
        System.out.println("Width:" + getWidth()+"\tHeight:"+getHeight());
        setFont(getFont().deriveFont((float)getHeight()*(float)0.5));
        initShape();
    }

    private void paintFocusAndRollover(Graphics2D g2, Color color) {
        g2.clearRect(0, 0, getWidth(), getHeight());
        g2.setPaint(new GradientPaint(
                0, 0, color, getWidth(), getHeight(),
                color.brighter(), true));
        g2.fill(shape);
        updateUI();
    }

    @Override
    protected void paintComponent(Graphics g) {
        initShape();
        Graphics2D g2 = (Graphics2D) g.create();
        this.g = g2;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setBackground(new Color(0, 0, 0));
        if (getModel().isPressed()) {
            this.nowColor = this.pressingColor;
            this.nowTextColor = this.normalTextColor;
        } else if (this.nowColor == this.pressingColor) {
            this.nowColor = this.pressedColor;
            this.nowTextColor = this.pressedTextColor;
        }
        paintFocusAndRollover(g2, this.nowColor);
        setForeground(this.nowTextColor);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        initShape();
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.draw(shape);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        g2.dispose();
    }

    @Override
    public boolean contains(int x, int y) {
        initShape();
        return shape == null ? false : shape.contains(x, y);
    }

    public void anotherButtonWasPressed() {
        this.nowColor = this.normalColor;
        this.nowTextColor = this.normalTextColor;
        paintFocusAndRollover(g, this.nowColor);
    }

    public void setNormalColor(Color color) {
        this.normalColor = color;
        this.nowColor = color;
    }

    public void setPressingColor(Color color) {
        this.pressingColor = color;
    }

    public void setPressedColor(Color color) {
        this.pressedColor = color;
    }

    public void setNormalTextColor(Color color) {
        this.normalTextColor = color;
        this.nowTextColor = color;
    }

    public void setPressedTextColor(Color color) {
        this.pressedTextColor = color;
    }


}
