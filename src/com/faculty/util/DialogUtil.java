package com.faculty.util;

import javax.swing.*;

public final class DialogUtil {
    private DialogUtil() {}

    public static void info(java.awt.Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void error(java.awt.Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

}