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
import edu.fit.cs.robotics.controller.RoboticsOperator;
import edu.fit.cs.robotics.model.PhotoGrid;


/**
 * Class for implementing the Business logic for Stock Controller
 * 
 * @author Johny
 *
 */
public class RobotLogics {
	
	private static int calculateEdge(char c)
	{
		int score = 0;
		
		if(c == 'C')
		{
			score += 50;
		}else if(c == 'P')
		{
			score += 10;
		}
		else if(c == 'F')
		{
			score += 5;
		}
		else
		{
			score += 1;
		}
		
		return score;
	}
	
	private static int calculateCorner(char c)
	{
		int score = 0;
		
		if(c == 'C')
		{
			score += 50;
		}else if(c == 'P')
		{
			score += 10;
		}
		else if(c == 'F')
		{
			score += 5;
		}
		else
		{
			score += 1;
		}
		
		return score;
	}
	
	private static int calculateCenter(char c)
	{
		int score = 0;
		
		if(c == 'C')
		{
			score += 50;
		}
		else if(c == 'P')
		{
			score += 10;
		}
		else if(c == 'F')
		{
			score += 2;
		}
		else
		{
			score += 1;
		}
		
		return score;
	}
	
	
	public static void diveIn(RoboticsOperator controller)
	{
		controller.getArm().addZ(-200);
	}
	
	
	public static org.opencv.core.Point pointGrid(PhotoGrid grid)
	{
		
		int left,right,up,down,center;
		
		center = calculateCenter(grid.getchar(1, 1));
		
		left = calculateEdge(grid.getchar(1, 0)) + calculateCorner(grid.getchar(0, 0)) + calculateCorner(grid.getchar(2, 0));
		
		right = calculateEdge(grid.getchar(1, 2)) + calculateCorner(grid.getchar(0, 2)) + calculateCorner(grid.getchar(2, 2));
				
		up = calculateEdge(grid.getchar(0, 1)) + calculateCorner(grid.getchar(0, 0)) + calculateCorner(grid.getchar(0, 2));
		
		down = calculateEdge(grid.getchar(2, 1)) + calculateCorner(grid.getchar(2, 0)) + calculateCorner(grid.getchar(2, 2));
		
		int x =  right - left;
		
		int y = up - down;
		
		System.out.println(x+","+y);
		
		if(x==0 && y==0)
		{
			x = 30;
			
		}
		
		else
		{
		
		if(y>0)
		{
			y = 20;
		}
		else if(y==0)
		{
			y = 0;
		}
		else
		{
			y = -20;
		}
		
		if(x>0)
		{
			x = 20;
		}
		else if(x==0)
		{
			x = 0;
		}
		else
		{
			x = -20;
		}
		
		}
		
//		if(x == 0)
//		{
//			x = 30;
//		}
	
		x = x*10/center;
		y = y*10/center;
		
//		x = x*10/center;
//		y = y*10/center;
		
		return new org.opencv.core.Point(x, y);
		
	}
	
	public static int scoreGrid(PhotoGrid grid)
	{
		int score = 0;
		
		score += calculateCenter(grid.getchar(1, 1));
		
//=========================================================		
		score += calculateEdge(grid.getchar(0, 1));
		
		score += calculateEdge(grid.getchar(1, 0));
		
		score += calculateEdge(grid.getchar(1, 2));
		
		score += calculateEdge(grid.getchar(2, 1));
		
//==============================================================
		
		score += calculateCorner(grid.getchar(0, 0));
		score += calculateCorner(grid.getchar(0, 2));
		score += calculateCorner(grid.getchar(2, 0));
		score += calculateCorner(grid.getchar(2, 2));
		
		return score;
		
		
	}
	
	
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
