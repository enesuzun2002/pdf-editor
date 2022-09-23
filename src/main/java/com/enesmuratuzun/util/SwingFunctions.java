package com.enesmuratuzun.util;

import javax.swing.*;
import java.awt.event.ActionListener;

public class SwingFunctions {
    public static JMenuItem createMenuItem(String title, String toolTip, ActionListener a) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.setToolTipText(toolTip);
        menuItem.addActionListener(a);
        return menuItem;
    }

}
