package com.faculty.view.components;

import com.faculty.util.UITheme;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class SidebarButton extends JButton {
    private boolean selected = false;

    public SidebarButton(String text) {
        super(text);
        setForeground(new Color(180, 190, 210));
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setHorizontalAlignment(SwingConstants.LEFT);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setFont(new Font("Inter", Font.BOLD, 14));
        setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 20));
    }

    public void setSelectedState(boolean selected) {
        this.selected = selected;
        setForeground(selected ? Color.WHITE : new Color(180, 190, 210));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        if (selected) {
            Path2D.Double path = new Path2D.Double();
            path.moveTo(0, 0);
            path.lineTo(w * 0.95, 0);
            path.lineTo(w, h * 0.5);
            path.lineTo(w * 0.95, h);
            path.lineTo(0, h);
            path.closePath();

            g2.setColor(new Color(255, 255, 255, 20));
            g2.fill(path);

            g2.setColor(UITheme.EMERALD);
            g2.fillRect(0, 0, 4, h);
        } else if (getModel().isRollover()) {
            g2.setColor(new Color(255, 255, 255, 10));
            g2.fillRoundRect(10, 5, w - 20, h - 10, 12, 12);
        }

        g2.dispose();
        super.paintComponent(g);
    }
}