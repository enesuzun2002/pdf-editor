package com.enesmuratuzun.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PDFInformation {
    private JButton okButton;
    private JTextField titleTextField;
    private JTextField subjectTextField;
    private JTextField authorTextField;
    private JTextField creatorTextField;
    private JTextField producerTextField;
    private JTextField dateTextField;
    private JTextField keywordsTextField;
    private JPanel pdfInformationPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("PDFInformation");
        frame.setContentPane(new PDFInformation().pdfInformationPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public PDFInformation() {
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button Pressed!");
            }
        });
    }
}
