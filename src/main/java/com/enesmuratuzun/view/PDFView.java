package com.enesmuratuzun.view;

import com.enesmuratuzun.model.Preferences;
import com.enesmuratuzun.util.PDFFunctions;
import com.enesmuratuzun.util.SwingFunctions;
import com.enesmuratuzun.view.dialog.PDFNull;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
        JMenu fileMenu = getFileMenu();
        JMenu fileInfoMenu = getFileInfoMenu();
        menuBar.add(fileMenu);
        menuBar.add(fileInfoMenu);
        return menuBar;
    }

    private static JMenu getFileMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenu fileSaveAsMenu = getFileSaveAsMenu();
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
        fileMenu.add(fileSaveAsMenu);
        return fileMenu;
    }

    private static JMenu getFileInfoMenu() {
        JMenu fileInfoMenu = new JMenu("About");
        JMenuItem infoEditorMenuItem = SwingFunctions.createMenuItem("Edit", "Edit Information", e -> {
            if (Preferences.document == null) {
                PDFNull.main(null);
            } else {
                PDFInformation.main(null);
            }
        });
        fileInfoMenu.add(infoEditorMenuItem);
        return fileInfoMenu;
    }

    private static JMenu getFileSaveAsMenu() {
        JMenu fileSaveAsMenu = new JMenu("Save As..");
        JMenuItem imagesMenuItem = SwingFunctions.createMenuItem("Save As Images", "Save modified pdf file as images", e -> {
            if (Preferences.document != null) {
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnVal = jfc.showOpenDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = jfc.getSelectedFile();
                    try {
                        ArrayList<BufferedImage> bufferedImages = PDFFunctions.getPDFPageImages(Preferences.documentPDF, 1);
                        for (int i = 0; i < bufferedImages.size(); i++) {
                            ImageIOUtil.writeImage(bufferedImages.get(i), file.getPath() + "/" + Preferences.document.getName().replaceAll("\\.pdf", "-" + i + ".png"), 300);
                        }

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            } else {
                PDFNull.main(null);
            }
        });
        fileSaveAsMenu.add(imagesMenuItem);
        return fileSaveAsMenu;
    }
}
