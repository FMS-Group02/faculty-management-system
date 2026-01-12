package com.faculty.util;

import javax.swing.*;

public final class DialogUtil {
    private DialogUtil() {}

    public static void info(java.awt.Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }


}