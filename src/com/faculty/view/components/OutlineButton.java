package com.faculty.view.components;

import com.faculty.util.UITheme;
import javax.swing.*;
import java.awt.*;

public class OutlineButton extends JButton {
    public OutlineButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(UITheme.NAVY_DARK);
        setBackground(Color.WHITE);
        setFont(new Font("Inter", Font.BOLD, 13));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(new Color(220, 225, 235));
        if (getModel().isRollover()) g2.setColor(UITheme.EMERALD);

        // Square border
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawRect(1, 1, getWidth() - 2, getHeight() - 2);

        g2.dispose();
        super.paintComponent(g);
    }
}