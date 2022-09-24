package com.enesmuratuzun.view;

import com.enesmuratuzun.model.Preferences;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

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
    private JTextField keywordsTextField;
    private JPanel pdfInformationPanel;
    private static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("PDFInformation");
        frame.setContentPane(new PDFInformation().pdfInformationPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void setDocumentInformation() {
        PDDocumentInformation pdDocumentInformation = Preferences.documentPDF.getDocumentInformation();
        pdDocumentInformation.setAuthor(authorTextField.getText());
        pdDocumentInformation.setCreator(creatorTextField.getText());
        pdDocumentInformation.setKeywords(keywordsTextField.getText());
        pdDocumentInformation.setTitle(titleTextField.getText());
        pdDocumentInformation.setSubject(subjectTextField.getText());
        pdDocumentInformation.setProducer(producerTextField.getText());
        frame.dispose();
    }

    private void setInitialDocumentInformation() {
        authorTextField.setText(Preferences.documentPDF.getDocumentInformation().getAuthor());
        creatorTextField.setText(Preferences.documentPDF.getDocumentInformation().getCreator());
        keywordsTextField.setText(Preferences.documentPDF.getDocumentInformation().getKeywords());
        titleTextField.setText(Preferences.documentPDF.getDocumentInformation().getTitle());
        subjectTextField.setText(Preferences.documentPDF.getDocumentInformation().getSubject());
        producerTextField.setText(Preferences.documentPDF.getDocumentInformation().getProducer());
    }

    public PDFInformation() {
        setInitialDocumentInformation();
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDocumentInformation();
            }
        });
    }
}
