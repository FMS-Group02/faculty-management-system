package com.faculty.view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public class DangerButton extends ModernButton {
    private final Color baseColor = new Color(220, 50, 50);
    private final Color hoverColor = new Color(240, 70, 70);
    private final Color clickColor = new Color(180, 35, 35);
    private float alpha = 0.0f;

    public DangerButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 14));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        Color currentBackground;
        if (getModel().isPressed()) {
            currentBackground = clickColor;
        } else if (getModel().isRollover()) {
            currentBackground = hoverColor;
        } else {
            currentBackground = baseColor;
        }

        g2.setColor(currentBackground);
        g2.fill(new RoundRectangle2D.Double(0, 0, width, height, 15, 15));

        if (getModel().isRollover()) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
            g2.setColor(Color.WHITE);
            g2.fill(new RoundRectangle2D.Double(0, 0, width, height / 2.0, 15, 15));
        }

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2.setColor(new Color(0, 0, 0, 50));
        g2.draw(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, 15, 15));

        FontMetrics metrics = g2.getFontMetrics(getFont());
        int x = (width - metrics.stringWidth(getText())) / 2;
        int y = ((height - metrics.getHeight()) / 2) + metrics.getAscent();

        g2.setColor(getForeground());
        g2.drawString(getText(), x, y);
        g2.dispose();
    }
}
