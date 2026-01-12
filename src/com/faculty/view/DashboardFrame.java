package com.faculty.view;

import com.faculty.model.Session;
import com.faculty.util.UITheme;
import com.faculty.view.components.DangerButton;
import com.faculty.view.components.SidebarButton;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class DashboardFrame extends JFrame {
    protected final Session session;
    protected final CardLayout cardLayout = new CardLayout();
    protected final JPanel content = new JPanel(cardLayout);
    private final JLabel pageTitle = new JLabel("Dashboard");
    private final Map<String, SidebarButton> navButtons = new LinkedHashMap<>();
    private final JPanel navContainer = new JPanel();

    protected DashboardFrame(Session session, String windowTitle) {
        this.session = session;
        setTitle(windowTitle);
        setSize(1280, 820);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1000, 700));

        // Using BorderLayout instead of null layout for responsiveness
        JPanel mainWrapper = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(UITheme.BG);
                g2.fillRect(0, 0, getWidth(), getHeight());

                // The geometric path now uses getWidth() and getHeight() dynamically
                Path2D.Double path = new Path2D.Double();
                path.moveTo(0, 0);
                path.lineTo(300, 0);
                path.curveTo(320, getHeight()/2.0, 280, getHeight()/2.0, 300, getHeight());
                path.lineTo(0, getHeight());
                path.closePath();

                GradientPaint gp = new GradientPaint(0, 0, UITheme.NAVY_DARK, 300, getHeight(), UITheme.NAVY_LIGHT);
                g2.setPaint(gp);
                g2.fill(path);
                g2.dispose();
            }
        };

        // Sidebar remains a fixed width but expands vertically
        JPanel sidebar = createSidebar();
        sidebar.setPreferredSize(new Dimension(300, 0));

        // Glass panel container with Padding (replacing setBounds)
        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.setBorder(new EmptyBorder(20, 15, 20, 25));

        JPanel glassPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(UITheme.SURFACE);
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 35, 35));
                g2.dispose();
            }
        };
        glassPanel.setOpaque(false);

        JPanel header = createTopHeader();
        header.setPreferredSize(new Dimension(0, 80));

        content.setOpaque(false);
        content.setBorder(new EmptyBorder(10, 30, 30, 30));

        glassPanel.add(header, BorderLayout.NORTH);
        glassPanel.add(content, BorderLayout.CENTER);

        centerWrapper.add(glassPanel, BorderLayout.CENTER);

        mainWrapper.add(sidebar, BorderLayout.WEST);
        mainWrapper.add(centerWrapper, BorderLayout.CENTER);

        setContentPane(mainWrapper);

        buildPages();
        initializeNav();
    }


    protected abstract void buildPages();

    protected void addNavItem(String key, String label, JPanel page) {
        content.add(page, key);
        SidebarButton btn = new SidebarButton(label);
        navButtons.put(key, btn);
    }

    protected void showPage(String key, String title) {
        cardLayout.show(content, key);
        pageTitle.setText(title);
        navButtons.forEach((k, b) -> b.setSelectedState(k.equals(key)));
    }

    private JPanel createSidebar() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JPanel top = new JPanel();
        top.setOpaque(false);
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        top.setBorder(new EmptyBorder(50, 40, 30, 20));

        JLabel logo = new JLabel("WELCOME");
        logo.setForeground(UITheme.EMERALD);
        logo.setFont(new Font("Inter", Font.BOLD, 28));

        JLabel sub = new JLabel("FACULTY MANAGEMENT SYSTEM");
        sub.setForeground(UITheme.TEXT_MUTED);
        sub.setFont(new Font("Inter", Font.BOLD, 10));

        top.add(logo);
        top.add(sub);

        navContainer.setOpaque(false);
        navContainer.setLayout(new BoxLayout(navContainer, BoxLayout.Y_AXIS));
        navContainer.setBorder(new EmptyBorder(20, 25, 20, 25));

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setOpaque(false);
        bottom.setBorder(new EmptyBorder(20, 40, 50, 40));

        DangerButton logout = new DangerButton("SIGN OUT");
        logout.addActionListener(e -> {
            dispose();
            new LoginView().setVisible(true);
        });

        bottom.add(logout, BorderLayout.CENTER);

        panel.add(top, BorderLayout.NORTH);
        panel.add(navContainer, BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createTopHeader() {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setOpaque(false);
        bar.setBorder(new EmptyBorder(25, 35, 5, 35));

        pageTitle.setFont(new Font("Inter", Font.BOLD, 26));
        pageTitle.setForeground(UITheme.TEXT_DARK);

        JPanel userBox = new JPanel(new GridLayout(2, 1));
        userBox.setOpaque(false);

        JLabel name = new JLabel(session.getUsername(), SwingConstants.RIGHT);
        name.setFont(new Font("Inter", Font.BOLD, 14));

        JLabel role = new JLabel(String.valueOf(session.getRole()), SwingConstants.RIGHT);
        role.setFont(new Font("Inter", Font.PLAIN, 12));
        role.setForeground(UITheme.TEXT_MUTED);

        userBox.add(name);
        userBox.add(role);

        bar.add(pageTitle, BorderLayout.WEST);
        bar.add(userBox, BorderLayout.EAST);

        return bar;
    }

    private void initializeNav() {
        SwingUtilities.invokeLater(() -> {
            navContainer.removeAll();
            navButtons.forEach((key, btn) -> {
                btn.addActionListener(e -> showPage(key, btn.getText()));
                navContainer.add(btn);
                navContainer.add(Box.createRigidArea(new Dimension(0, 12)));
            });

            if (!navButtons.isEmpty()) {
                String firstKey = navButtons.keySet().iterator().next();
                showPage(firstKey, navButtons.get(firstKey).getText());
            }
            navContainer.revalidate();
            navContainer.repaint();
        });
    }
}