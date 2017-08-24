import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.imageio.ImageIO;

public class Pic2Text {

	public static void main(String[] args) throws IOException {
		final int pictureRes = 3;
		
		BufferedImage image = ImageIO.read(Pic2Text.class.getResource("me.jpg"));
		int height = image.getHeight();
		int width = image.getWidth();
		final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		int rgb[][] = new int[height][width];
		System.out.println("File output to \"output.txt\" with resolution => "+pictureRes+" pixels.");
        PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
        System.setOut(out);
        final boolean hasAlphaChannel = image.getAlphaRaster() != null;
		
	    final int pixelLength = (hasAlphaChannel)? 4: 3;
        for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
            int argb = -16777216; // 255 alpha
            argb += ((int) pixels[pixel] & 0xff); // blue
            argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
            argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
            Color c = new Color(argb);
            int val = (c.getRed()+c.getBlue()+c.getGreen())/3;
            rgb[row][col] = val;
            col++;
            if (col == width) {
            	col = 0;
            	row++;
            }
        }
        
        for (int r = 0; r<height; r+=pictureRes) {
        	for (int c = 0; c<width; c+=pictureRes) {
        		int val = 0;
        		for (int i = 0; i<pictureRes; i++) {
        			for (int a = 0; a<pictureRes; a++) {
        				if (r+i<rgb.length && c+a<rgb[0].length) val += rgb[r+i][c+a];
        			}
        		}
        		val/=(pictureRes*pictureRes);
                if (val < 20) System.out.print("&&");
       			else if (val < 35) System.out.print("BB");
       			else if (val < 55) System.out.print("00");
       			else if (val < 100) System.out.print("ll");
       			else if (val < 160) System.out.print("::");
       			else if (val < 200) System.out.print("..");
       			else System.out.print("  ");
        	}
        	System.out.println();
        }
	}

}
