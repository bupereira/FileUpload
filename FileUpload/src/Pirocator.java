import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Pirocator {
	static String fileName;
	
	public Pirocator(String paramFileName) {
		fileName = paramFileName;		
	}
	
	public String pirocate() {
		//BufferedImage myImg = loadImage("C:\\Users\\mpereira\\Downloads\\NegaoApp\\baseImg.jpg");
		//BufferedImage myImg = loadImage("/var/www/appiroca/img/baseImg.jpg");
		//BufferedImage myImg = loadImage("baseImg.jpg");
		BufferedImage myImg = loadImage("/var/lib/tomcat7/webapps/FileUpload/WEB-INF/classes/baseImg.jpg");
		BufferedImage varImg = loadImage(fileName);
		System.out.println(fileName);
		// resize varImg to 308 x 321
		Image scaledImg = varImg.getScaledInstance(308, 321, Image.SCALE_DEFAULT);
		Graphics g = myImg.getGraphics();
		g.drawImage(scaledImg, 1, 261, null);
		boolean l = saveImage(myImg);
		System.out.println("Saved? " + String.valueOf(l) );
		return fileName;
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
		File outputFile = new File(fileName);
		try {
			ImageIO.write(img, "jpg", outputFile);
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
}
