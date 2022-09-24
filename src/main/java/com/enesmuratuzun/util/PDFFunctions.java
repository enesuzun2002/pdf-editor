package com.enesmuratuzun.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
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
}
