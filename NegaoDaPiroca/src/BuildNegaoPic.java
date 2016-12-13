import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;


public class BuildNegaoPic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedImage myImg = loadImage("src/img/baseImg.jpg");
		BufferedImage varImg = loadImage("C:\\Users\\mpereira\\Downloads\\fotos\\purple.jpg");
		//Todo resize varImg to 308 x 321
		Image scaledImg = varImg.getScaledInstance(308, 321, Image.SCALE_DEFAULT);
		Graphics g = myImg.getGraphics();
		g.drawImage(scaledImg, 1, 261, null);
		boolean l = saveImage(myImg);
		System.out.println("Saved? " + String.valueOf(l) );
		
	}
	
	private static BufferedImage loadImage(String path) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path));			
		} catch (IOException e) {
			
		}
		return img;
	}
	
	private static boolean saveImage(BufferedImage img) {
		File outputFile = new File("c:\\Users\\mpereira\\Downloads\\NegaoApp\\saved.jpg");
		try {
			ImageIO.write(img, "jpg", outputFile);
		} catch (IOException e) {
			return false;
		}
		return true;
	}

}
