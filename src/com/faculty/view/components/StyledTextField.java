package com.faculty.view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class StyledTextField extends JTextField {
    private final Color borderColor = new Color(210, 215, 230);
    private final Color focusColor = new Color(46, 196, 182);

    public StyledTextField() {
        setFont(new Font("Inter", Font.PLAIN, 14));
        setOpaque(false);
        setBackground(Color.WHITE);
        setForeground(new Color(50, 50, 70));
        setCaretColor(focusColor);
        setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 12, 12));

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (isFocusOwner()) {
            g2.setColor(focusColor);
            g2.setStroke(new BasicStroke(2.0f));
        } else {
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(1.5f));
        }

        g2.draw(new RoundRectangle2D.Double(1, 1, getWidth() - 2, getHeight() - 2, 12, 12));
        g2.dispose();
    }
}
