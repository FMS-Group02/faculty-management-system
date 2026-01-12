package com.faculty.view.panels;

import com.faculty.util.DialogUtil;
import com.faculty.util.UITheme;
import com.faculty.view.components.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public abstract class BaseCrudPanel extends JPanel {
    protected final DefaultTableModel model;
    protected final ModernTable table;
    protected final StyledTextField searchField = new StyledTextField();

    protected final OutlineButton refreshBtn = new OutlineButton("Refresh");
    protected final ModernButton addBtn = new ModernButton("+ Add");
    protected final OutlineButton editBtn = new OutlineButton("Edit");
    protected final DangerButton deleteBtn = new DangerButton("Delete");

    protected BaseCrudPanel(String title, String[] columns) {
        setLayout(new BorderLayout(0, 20));
        setBackground(new Color(245, 247, 250));
        setBorder(new EmptyBorder(25, 30, 25, 30));

        add(buildTopSection(title), BorderLayout.NORTH);

        model = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new ModernTable(model);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(220, 225, 235)));
        scroll.getViewport().setBackground(Color.WHITE);
        add(scroll, BorderLayout.CENTER);

        add(buildBottomActionBar(), BorderLayout.SOUTH);

        refreshBtn.addActionListener(e -> reload());
        addBtn.addActionListener(e -> onAdd());
        editBtn.addActionListener(e -> {
            if (table.getSelectedRow() < 0) {
                DialogUtil.info(this, "Please select a record to edit.");
                return;
            }
            onEdit();
        });
        deleteBtn.addActionListener(e -> {
            if (table.getSelectedRow() < 0) {
                DialogUtil.info(this, "Please select a record to delete.");
                return;
            }
            onDelete();
        });

        searchField.addActionListener(e -> reload());
        SwingUtilities.invokeLater(this::reload);
    }

    private JPanel buildTopSection(String title) {
        JPanel top = new JPanel(new BorderLayout(0, 15));
        top.setOpaque(false);

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Inter", Font.BOLD, 26));
        lblTitle.setForeground(new Color(30, 35, 45));

        JPanel toolbar = new JPanel(new BorderLayout());
        toolbar.setOpaque(false);

        JPanel searchBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        searchBar.setOpaque(false);

        JLabel searchLbl = new JLabel("Search:  ");
        searchLbl.setFont(new Font("Inter", Font.BOLD, 12));
        searchLbl.setForeground(new Color(120, 130, 150));

        searchField.setPreferredSize(new Dimension(300, 40));
        searchBar.add(searchLbl);
        searchBar.add(searchField);

        JPanel refreshContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        refreshContainer.setOpaque(false);
        refreshBtn.setPreferredSize(new Dimension(110, 40));
        refreshContainer.add(refreshBtn);

        toolbar.add(searchBar, BorderLayout.WEST);
        toolbar.add(refreshContainer, BorderLayout.EAST);

        top.add(lblTitle, BorderLayout.NORTH);
        top.add(toolbar, BorderLayout.CENTER);

        return top;
    }

    private JPanel buildBottomActionBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        bar.setOpaque(false);
        bar.setBorder(new EmptyBorder(10, 0, 0, 0));

        Dimension btnSize = new Dimension(110, 42);

        addBtn.setPreferredSize(btnSize);
        editBtn.setPreferredSize(btnSize);
        deleteBtn.setPreferredSize(btnSize);

        bar.add(addBtn);
        bar.add(editBtn);
        bar.add(deleteBtn);

        return bar;
    }

    protected void reload() {
        String q = searchField.getText() == null ? "" : searchField.getText().trim();
        try {
            loadData(q);
        } catch (Exception ex) {
            DialogUtil.error(this, "Failed to load data: " + ex.getMessage());
        }
    }

    protected abstract void loadData(String filter) throws Exception;
    protected abstract void onAdd();
    protected abstract void onEdit();
    protected abstract void onDelete();
}