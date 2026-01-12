package com.faculty.util;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;

public final class UITheme {
    private UITheme() {}

    public static final Color NAVY_DARK = new Color(20, 40, 35);
    public static final Color NAVY_LIGHT = new Color(30, 60, 50);
    public static final Color EMERALD = new Color(46, 196, 182);
    public static final Color GREEN_DARK = new Color(38, 166, 154);
    public static final Color GREEN_SOFT = new Color(232, 245, 243);

    public static final Color BG = new Color(245, 247, 250);
    public static final Color SURFACE = Color.WHITE;
    public static final Color TEXT_DARK = new Color(30, 35, 45);
    public static final Color TEXT_MUTED = new Color(120, 130, 150);

    public static final Font FONT_REGULAR = new Font("Inter", Font.PLAIN, 14);
    public static final Font FONT_BOLD = new Font("Inter", Font.BOLD, 14);

    public static void install() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ignored) {}

        setGlobalFont(FONT_REGULAR);

        UIManager.put("control", BG);
        UIManager.put("nimbusBase", NAVY_DARK);
        UIManager.put("nimbusBlueGrey", new Color(220, 230, 225));
        UIManager.put("nimbusFocus", EMERALD);
        UIManager.put("text", TEXT_DARK);

        UIManager.put("Table.alternateRowColor", GREEN_SOFT);
        UIManager.put("Table.gridColor", new Color(210, 225, 220));
        UIManager.put("Table.selectionBackground", new Color(46, 196, 182, 50));
        UIManager.put("Table.selectionForeground", TEXT_DARK);
    }

    private static void setGlobalFont(Font f) {
        for (Object key : UIManager.getDefaults().keySet()) {
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, new javax.swing.plaf.FontUIResource(f));
            }
        }
    }
}