package com.enesmuratuzun.util;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PDFFunctions {
    public static ArrayList<BufferedImage> getPDFPageImages(PDDocument document, float scale) {
        ArrayList<BufferedImage> bufferedImages = new ArrayList<>();
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        for (int i = 0; i < document.getPages().getCount(); i++) {
            try {
                bufferedImages.add(pdfRenderer.renderImage(i, scale, ImageType.RGB));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            document.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bufferedImages;
    }

    public static boolean splitPDF(File documentFile, int amount) throws IOException {
        PDDocument document = Loader.loadPDF(documentFile);
        PDDocument temp = null;
        int pagesLength = document.getPages().getCount();
        int count = 0;
        if (amount < pagesLength) {
            for (int i = 0; i < pagesLength; i += amount) {
                temp = new PDDocument();
                if (i + amount < pagesLength) {
                    for (int j = i; j < i + amount; j++) {
                        temp.addPage(document.getPage(j));
                    }
                } else {
                    for (int j = i; j < pagesLength; j++) {
                        temp.addPage(document.getPage(j));
                    }
                }
            }
            count++;
            temp.save(documentFile.getPath().replaceAll("\\.pdf", "-" + count + ".pdf"));
        }
        return true;
    }
}
