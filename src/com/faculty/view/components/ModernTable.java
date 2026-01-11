package com.faculty.view.components;

import com.faculty.util.UITheme;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class ModernTable extends JTable {
    public ModernTable(TableModel model) {
        super(model);

        setRowHeight(45);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setShowVerticalLines(false);
        setIntercellSpacing(new Dimension(0, 0));
        setFocusable(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(JLabel.CENTER);
                if (!isSelected) {
                    c.setForeground(Color.BLACK);
                } else {
                    c.setForeground(UITheme.TEXT_DARK);
                }
                return c;
            }
        };
        setDefaultRenderer(Object.class, centerRenderer);

        JTableHeader header = getTableHeader();
        header.setPreferredSize(new Dimension(0, 50));
        header.setReorderingAllowed(false);

        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Set Header Background to White
                setBackground(Color.WHITE);

                // Set Header Font Color to Black
                setForeground(Color.BLACK);

                setFont(new Font("Inter", Font.BOLD, 13));
                setHorizontalAlignment(JLabel.CENTER);

                // Using a slightly darker border at the bottom for separation
                setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(230, 235, 240)));

                return this;
            }
        });

        setSelectionBackground(new Color(46, 196, 182, 40));
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);
        if (!isRowSelected(row)) {
            c.setBackground(row % 2 == 0 ? Color.WHITE : UITheme.GREEN_SOFT);
        }
        return c;
    }
}