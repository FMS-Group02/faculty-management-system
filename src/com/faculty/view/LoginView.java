package com.faculty.view;

import com.faculty.controller.LoginController;
import com.faculty.model.Role;
import com.faculty.util.UITheme;
import com.faculty.view.components.ModernButton;
import com.faculty.view.components.StyledPasswordField;
import com.faculty.view.components.StyledTextField;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class LoginView extends JFrame {

    private final StyledTextField userField = new StyledTextField();
    private final StyledPasswordField passField = new StyledPasswordField();
    private final RoleToggle adminBtn = new RoleToggle("Admin", Role.Admin);
    private final RoleToggle studentBtn = new RoleToggle("Student", Role.Student);
    private final RoleToggle lecturerBtn = new RoleToggle("Lecturer", Role.Lecturer);
    private final ModernButton loginBtn = new ModernButton("Sign In");

    public LoginView() {
        setTitle("Faculty Management System | Entry");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainContent = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(UITheme.BG);
                g2.fillRect(0, 0, getWidth(), getHeight());

                g2.setColor(new Color(46, 196, 182, 30));
                g2.fill(new Ellipse2D.Double(-150, -150, 450, 450));
                g2.setColor(new Color(46, 196, 182, 20));
                g2.fill(new Ellipse2D.Double(getWidth() - 300, getHeight() - 300, 500, 500));
                g2.dispose();
            }
        };

        JPanel floatingCard = new JPanel(new BorderLayout());
        floatingCard.setOpaque(false);
        floatingCard.setBounds(135, 80, 830, 500);

        floatingCard.add(buildBrandSection(), BorderLayout.WEST);
        floatingCard.add(buildFormSection(), BorderLayout.CENTER);

        mainContent.add(floatingCard);
        setContentPane(mainContent);

        new LoginController(this);
    }

    private JPanel buildBrandSection() {
        JPanel brand = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(UITheme.NAVY_DARK);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        brand.setPreferredSize(new Dimension(380, 500));
        brand.setLayout(new GridBagLayout());

        JLabel titleMain = new JLabel("<html><div style='text-align: center;'>Faculty Management<br>System</div></html>");
        titleMain.setForeground(Color.WHITE);
        titleMain.setFont(new Font("Inter", Font.BOLD, 36));

        JLabel titleSub = new JLabel("Faculty of Computing & Technology");
        titleSub.setForeground(new Color(230, 230, 230));
        titleSub.setFont(new Font("Inter", Font.BOLD, 18));

        JLabel university = new JLabel("University Of Kelaniya");
        university.setForeground(new Color(200, 200, 200));
        university.setFont(new Font("Inter", Font.ITALIC, 14));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridy = 0; gbc.insets = new Insets(0, 20, 60, 20);
        brand.add(titleMain, gbc);

        gbc.gridy = 1; gbc.insets = new Insets(0, 20, 10, 20);
        brand.add(titleSub, gbc);

        gbc.gridy = 2;
        brand.add(university, gbc);

        return brand;
    }

    private JPanel buildFormSection() {
        JPanel form = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(UITheme.SURFACE);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        form.setOpaque(false);

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 0));
        headerPanel.setOpaque(false);

        JLabel signInHeader = new JLabel("Sign In");
        signInHeader.setFont(new Font("Inter", Font.BOLD, 36));
        signInHeader.setForeground(UITheme.EMERALD);

        // SIGN UP HEADER WITH MOUSE LISTENER
        JLabel signUpHeader = new JLabel("Sign Up");
        signUpHeader.setFont(new Font("Inter", Font.BOLD, 36));
        signUpHeader.setForeground(new Color(200, 200, 200));
        signUpHeader.setCursor(new Cursor(Cursor.HAND_CURSOR));

        signUpHeader.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Action when "Sign Up" is clicked
                JOptionPane.showMessageDialog(null, "Opening Registration Form...");
                // You can call new RegisterView().setVisible(true); here later
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                signUpHeader.setForeground(UITheme.EMERALD.brighter());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                signUpHeader.setForeground(new Color(200, 200, 200));
            }
        });

        headerPanel.add(signInHeader);
        headerPanel.add(signUpHeader);

        JPanel rolePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        rolePanel.setOpaque(false);
        ButtonGroup g = new ButtonGroup();
        g.add(adminBtn); g.add(studentBtn); g.add(lecturerBtn);
        adminBtn.setSelected(true);
        rolePanel.add(adminBtn); rolePanel.add(studentBtn); rolePanel.add(lecturerBtn);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5, 50, 5, 50);

        c.gridy = 0; c.insets = new Insets(0, 50, 40, 50);
        form.add(headerPanel, c);

        c.gridy = 1; c.insets = new Insets(5, 50, 5, 50);
        form.add(createInputLabel("Username"), c);
        c.gridy = 2; c.fill = GridBagConstraints.HORIZONTAL;
        form.add(userField, c);

        c.gridy = 3; c.fill = GridBagConstraints.NONE;
        form.add(createInputLabel("Password"), c);
        c.gridy = 4; c.fill = GridBagConstraints.HORIZONTAL;
        form.add(passField, c);

        c.gridy = 5; c.fill = GridBagConstraints.NONE; c.insets = new Insets(15, 50, 5, 50);
        form.add(createInputLabel("Role"), c);
        c.gridy = 6; c.insets = new Insets(0, 50, 0, 50);
        form.add(rolePanel, c);

        c.gridy = 7; c.insets = new Insets(40, 50, 10, 50); c.fill = GridBagConstraints.HORIZONTAL;
        loginBtn.setPreferredSize(new Dimension(0, 45));
        form.add(loginBtn, c);

        return form;
    }

    private JLabel createInputLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Inter", Font.BOLD, 13));
        l.setForeground(UITheme.EMERALD);
        return l;
    }

    public String getUsername() { return userField.getText().trim(); }
    public String getPassword() { return new String(passField.getPassword()); }
    public void addLoginListener(ActionListener listener) { loginBtn.addActionListener(listener); }

    public Role getSelectedRole() {
        if (adminBtn.isSelected()) return Role.Admin;
        if (studentBtn.isSelected()) return Role.Student;
        return Role.Lecturer;
    }

    private static class RoleToggle extends JToggleButton {
        public RoleToggle(String text, Role role) {
            super(text);
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setFont(new Font("Inter", Font.BOLD, 12));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setPreferredSize(new Dimension(100, 35));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (isSelected()) {
                g2.setColor(UITheme.EMERALD);
                setForeground(Color.WHITE);
            } else {
                g2.setColor(new Color(235, 235, 235));
                setForeground(new Color(120, 120, 120));
            }
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}