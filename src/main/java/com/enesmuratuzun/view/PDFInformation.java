package com.enesmuratuzun.view;

import com.enesmuratuzun.model.Preferences;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PDFInformation {
    private JButton okButton;
    private JTextField titleTextField;
    private JTextField subjectTextField;
    private JTextField authorTextField;
    private JTextField creatorTextField;
    private JTextField producerTextField;
    private JTextField keywordsTextField;
    private JPanel pdfInformationPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("PDFInformation");
        frame.setContentPane(new PDFInformation().pdfInformationPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void setDocumentInformation() {
        PDDocument document;
        try {
            document = Loader.loadPDF(Preferences.document);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PDDocumentInformation pdDocumentInformation = document.getDocumentInformation();
        pdDocumentInformation.setAuthor(authorTextField.getText());
        pdDocumentInformation.setCreator(creatorTextField.getText());
        pdDocumentInformation.setKeywords(keywordsTextField.getText());
        pdDocumentInformation.setTitle(titleTextField.getText());
        pdDocumentInformation.setSubject(subjectTextField.getText());
        pdDocumentInformation.setProducer(producerTextField.getText());
        try {
            document.save(Preferences.document.getPath().replaceAll("\\.pdf", "-new.pdf"));
            document.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void setInitialDocumentInformation() {
        PDDocument document;
        try {
            document = Loader.loadPDF(Preferences.document);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        authorTextField.setText(document.getDocumentInformation().getAuthor());
        creatorTextField.setText(document.getDocumentInformation().getCreator());
        keywordsTextField.setText(document.getDocumentInformation().getKeywords());
        titleTextField.setText(document.getDocumentInformation().getTitle());
        subjectTextField.setText(document.getDocumentInformation().getSubject());
        producerTextField.setText(document.getDocumentInformation().getProducer());
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
