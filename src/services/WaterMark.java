package services;

import javax.imageio.ImageIO;

import exceptions.ServiceException;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WaterMark {

    public static void addTextWatermark(String text, String type, File source, File destination) throws ServiceException {
    	try{
	        BufferedImage image = ImageIO.read(source);
	
	        // determine image type and handle correct transparency
	        int imageType = "png".equalsIgnoreCase(type) ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB;
	        BufferedImage watermarked = new BufferedImage(image.getWidth(), image.getHeight(), imageType);
	
	        // initializes necessary graphic properties
	        Graphics2D w = (Graphics2D) watermarked.getGraphics();
	        w.drawImage(image, 0, 0, null);
	        AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f);
	        w.setComposite(alphaChannel);
	        w.setColor(Color.GRAY);
	        w.setFont(new Font(Font.SANS_SERIF, Font.BOLD, image.getWidth()/6 ));
	        FontMetrics fontMetrics = w.getFontMetrics();
	        Rectangle2D rect = fontMetrics.getStringBounds(text, w);
	
	        // calculate center of the image
	        int centerX = (image.getWidth() - (int) rect.getWidth()) / 2;
	        int centerY = (int) (image.getHeight() / 1.8);
	
	        // add text overlay to the image
	        w.drawString(text, centerX, centerY);
	        ImageIO.write(watermarked, type, destination);
	        w.dispose();
    	}catch(IOException e){
    		throw new ServiceException(e.getMessage());
    	}
    }
    
    public static void addImageWatermark(File watermark, String type, File source, File destination) throws ServiceException {
        
    	try{
	    	BufferedImage image = ImageIO.read(source);
	        BufferedImage overlay = resize(ImageIO.read(watermark), image.getHeight()/5, image.getWidth()/5);
	
	        // determine image type and handle correct transparency
	        int imageType = "png".equalsIgnoreCase(type) ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB;
	        BufferedImage watermarked = new BufferedImage(image.getWidth(), image.getHeight(), imageType);
	
	        // initializes necessary graphic properties
	        Graphics2D w = (Graphics2D) watermarked.getGraphics();
	        w.drawImage(image, 0, 0, null);
	        AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f);
	        w.setComposite(alphaChannel);
	
	        // calculates the coordinate where the String is painted
	        int centerX = (int) (image.getWidth() / 2.2);
	        int centerY = (int) (image.getHeight() / 2.2);
	
	        // add text watermark to the image
	        w.drawImage(overlay, centerX, centerY, null);
	        ImageIO.write(watermarked, type, destination);
	        w.dispose();
    	}catch(IOException e){
    		throw new ServiceException(e.getMessage());
    	}
    }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

}