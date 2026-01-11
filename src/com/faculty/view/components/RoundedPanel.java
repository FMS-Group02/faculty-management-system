package com.faculty.view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

public class RoundedPanel extends JPanel {
    private final int radius;
    private boolean shadow = true;

    public RoundedPanel(int radius) {
        this.radius = radius;
        setOpaque(false);
        setBackground(Color.WHITE);
    }

    public void setShadow(boolean shadow) {
        this.shadow = shadow;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();
        int offset = shadow ? 10 : 0;

        if (shadow) {
            for (int i = 0; i < offset; i++) {
                g2.setColor(new Color(0, 0, 0, (10 - i) * 2));
                g2.drawRoundRect(i, i, w - 1 - (i * 2), h - 1 - (i * 2), radius, radius);
            }
        }

        Area area = new Area(new RoundRectangle2D.Double(0, 0, w - offset, h - offset, radius, radius));
        g2.setColor(getBackground());
        g2.fill(area);

        g2.setStroke(new BasicStroke(1.2f));
        g2.setColor(new Color(230, 230, 230));
        g2.draw(area);

        g2.dispose();
    }

    @Override
    public Insets getInsets() {
        return new Insets(20, 20, 20, 20);
    }
}
