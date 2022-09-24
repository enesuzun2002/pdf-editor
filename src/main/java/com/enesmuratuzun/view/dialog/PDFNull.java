package com.enesmuratuzun.view.dialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PDFNull extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;

    public PDFNull() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        dispose();
    }

    public static void main(String[] args) {
        PDFNull dialog = new PDFNull();
        dialog.setTitle("Error");
        dialog.pack();
        dialog.setVisible(true);
    }
}
