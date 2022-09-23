package com.enesmuratuzun.view;

import com.enesmuratuzun.model.Preferences;
import com.enesmuratuzun.util.SwingFunctions;

import javax.swing.*;
import java.io.File;

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
            }
        });
        fileMenu.add(fileChooseMenuItem);
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
