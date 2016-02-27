package edu.fit.cs.robotics.BO;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Point3D;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;


import edu.fit.cs.robotics.constants.Constants;


/**
 * Class for implementing the Business logic for Stock Controller
 * 
 * @author Johny
 *
 */
public class RobotLogics {
	
	
	public static Image readImageFromFile(String fileLoc)
	{
		
		InputStream is = null;
		try {
			is = new FileInputStream(fileLoc);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image image = new Image(is);
		return image;
	}
	
	public static boolean writeImagePng(String fileLoc,Image image)
	{
		File outputFile = new File(fileLoc);
	    BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
	    
	    boolean bool;
	    try {
	      bool = ImageIO.write(bImage, "png", outputFile);
	    } catch (IOException e) {
	      throw new RuntimeException(e);
	    }
	    
	    return bool;
	}
	
	/**
	 * Writes the Image after reading from the server
	 * @param num of the image to be written
	 * @param image object
	 * @param per_fix name
	 * @return
	 */
	public boolean writeImage(int num, Image image,String name)
	{
		return writeImagePng("Images/"+name+num+".png", image);
	}
	
	public Point3D map2Dto3D(double u1,double u2,Point3D PLD, Point3D PRD, Point3D PRU, Point3D PLU)
	{

		Point3D temp = PLD.multiply((1-u1)*(1-u2)).add(
								PRD.multiply(u1*(1-u2))
								).add(
								PRU.multiply(u1*u2)
								).add(
								PLU.multiply((1-u1)*u2)
								);
		
		return temp;
	}
	
}
