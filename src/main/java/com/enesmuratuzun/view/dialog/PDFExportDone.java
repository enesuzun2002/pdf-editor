package com.enesmuratuzun.view.dialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PDFExportDone extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel messageLabel;

    public PDFExportDone(String message) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        messageLabel.setText("PDF file is successfully exported as " + message);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    public static void main(String[] args, String message) {
        PDFExportDone dialog = new PDFExportDone(message);
        dialog.setTitle("Export Done");
        dialog.pack();
        dialog.setVisible(true);
    }
}
