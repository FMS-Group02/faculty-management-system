package com.faculty.view.components;

import com.faculty.util.UITheme;
import javax.swing.*;
import java.awt.*;

public class ModernButton extends JButton {
    public ModernButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setBackground(UITheme.EMERALD);
        setFont(new Font("Inter", Font.BOLD, 13));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isPressed()) {
            g2.setColor(getBackground().darker());
        } else if (getModel().isRollover()) {
            g2.setColor(getBackground().brighter());
        } else {
            g2.setColor(getBackground());
        }

        // Sharp square corners
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.dispose();
        super.paintComponent(g);
    }
}