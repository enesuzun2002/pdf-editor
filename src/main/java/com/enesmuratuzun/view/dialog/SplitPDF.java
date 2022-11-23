package com.enesmuratuzun.view.dialog;

import com.enesmuratuzun.model.Preferences;
import com.enesmuratuzun.util.PDFFunctions;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class SplitPDF extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField splitAmountTextField;

    public SplitPDF() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        try {
            int amount = Integer.parseInt(splitAmountTextField.getText());
            PDFFunctions.splitPDF(Preferences.document, amount);
            PDFExportDone.main(null, "splitted PDF files to " + Preferences.document.getParent());
        } catch (RuntimeException | IOException ex) {
            Error.main(null, ex.getMessage());
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        SplitPDF dialog = new SplitPDF();
        dialog.pack();
        dialog.setVisible(true);
    }
}
