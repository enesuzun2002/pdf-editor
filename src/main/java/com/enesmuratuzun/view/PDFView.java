package com.enesmuratuzun.view;

import com.enesmuratuzun.model.Preferences;
import com.enesmuratuzun.util.SwingFunctions;
import org.apache.pdfbox.Loader;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class PDFView {
    private JList list1;
    private JList list2;
    private JPanel pdfViewPanel;
    private static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("PDF Editor");
        frame.setJMenuBar(setMenuBar());
        frame.setContentPane(new PDFView().pdfViewPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static JMenuBar setMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = createFileMenu();
        JMenu fileInfoMenu = createFileInfoMenu();
        menuBar.add(fileMenu);
        menuBar.add(fileInfoMenu);
        return menuBar;
    }

    private static JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem fileChooseMenuItem = SwingFunctions.createMenuItem("Open", "Open file", e -> {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int returnVal = jfc.showOpenDialog(frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = jfc.getSelectedFile();
                Preferences.document = file;
                try {
                    Preferences.documentPDF = Loader.loadPDF(file);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        JMenuItem fileSaveMenuItem = SwingFunctions.createMenuItem("Save", "Save modified pdf file", e -> {
            try {
                Preferences.documentPDF.save(Preferences.document.getPath().replaceAll("\\.pdf", "-new.pdf"));
                JOptionPane.showMessageDialog(frame, "Modified pdf saved as " + Preferences.document.getName().replaceAll("\\.pdf", "-new.pdf"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        fileMenu.add(fileChooseMenuItem);
        fileMenu.add(fileSaveMenuItem);
        return fileMenu;
    }

    private static JMenu createFileInfoMenu() {
        JMenu fileInfoMenu = new JMenu("About");
        JMenuItem infoEditorMenuItem = SwingFunctions.createMenuItem("Edit", "Edit Information", e -> {
            if (Preferences.document == null) {
                JOptionPane.showMessageDialog(frame, "Please open a PDF file.");
            } else {
                PDFInformation.main(null);
            }
        });
        fileInfoMenu.add(infoEditorMenuItem);
        return fileInfoMenu;
    }
}
