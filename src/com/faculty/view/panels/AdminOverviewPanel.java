package com.faculty.view.panels;

import com.faculty.controller.StatsController;
import com.faculty.util.DialogUtil;
import com.faculty.util.UITheme;
import com.faculty.view.components.RoundedPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

public class AdminOverviewPanel extends JPanel {
    private final StatsController statsController = new StatsController();

    private final JLabel usersCount = new JLabel("-");
    private final JLabel studentsCount = new JLabel("-");
    private final JLabel lecturersCount = new JLabel("-");
    private final JLabel coursesCount = new JLabel("-");

    public AdminOverviewPanel() {
        setLayout(new BorderLayout());
        setBackground(UITheme.BG);
        setBorder(new EmptyBorder(22, 22, 22, 22));

        JLabel header = new JLabel("System Overview");
        header.setFont(new Font("Segoe UI", Font.BOLD, 26));
        header.setForeground(UITheme.NAVY_DARK);
        add(header, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(2, 2, 20, 20));
        grid.setOpaque(false);
        grid.setBorder(new EmptyBorder(20, 0, 0, 0));

        grid.add(statCard("Total Users", "Active accounts", usersCount, "\uD83D\uDC64"));
        grid.add(statCard("Students", "Enrolled scholars", studentsCount, "\uD83C\uDF93"));
        grid.add(statCard("Faculty", "Academic members", lecturersCount, "\uD83D\uDC69\u200D\uD83C\uDFEB"));
        grid.add(statCard("Courses", "Active modules", coursesCount, "\uD83D\uDCD6"));

        add(grid, BorderLayout.CENTER);

        refreshCounts();
    }

    private RoundedPanel statCard(String title, String subtitle, JLabel value, String icon) {
        RoundedPanel card = new RoundedPanel(22);
        card.setBackground(UITheme.SURFACE);
        card.setLayout(new BorderLayout(15, 10));
        card.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel iconLbl = new JLabel(icon);
        iconLbl.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        iconLbl.setForeground(UITheme.EMERALD);

        JPanel left = new JPanel(new BorderLayout());
        left.setOpaque(false);
        left.add(iconLbl, BorderLayout.NORTH);

        JPanel text = new JPanel();
        text.setOpaque(false);
        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));

        JLabel t = new JLabel(title);
        t.setFont(new Font("Segoe UI", Font.BOLD, 17));
        t.setForeground(UITheme.NAVY_DARK);

        JLabel sub = new JLabel(subtitle);
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        sub.setForeground(UITheme.TEXT_MUTED);

        value.setFont(new Font("Segoe UI", Font.BOLD, 42));
        value.setForeground(UITheme.NAVY_LIGHT);

        text.add(t);
        text.add(Box.createRigidArea(new Dimension(0, 4)));
        text.add(sub);
        text.add(Box.createRigidArea(new Dimension(0, 10)));
        text.add(value);

        card.add(left, BorderLayout.WEST);
        card.add(text, BorderLayout.CENTER);

        return card;
    }

    private void refreshCounts() {
        try {
            usersCount.setText(String.valueOf(statsController.countTable("users")));
            studentsCount.setText(String.valueOf(statsController.countTable("students")));
            lecturersCount.setText(String.valueOf(statsController.countTable("lecturers")));
            coursesCount.setText(String.valueOf(statsController.countTable("courses")));
        } catch (SQLException ex) {
            DialogUtil.error(this, "Connection failed: " + ex.getMessage());
        }
    }
}
