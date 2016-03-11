package edu.fit.cs.robotics.controller;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Point3;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;

import edu.fit.cs.robotics.BO.OpencvLogics;
import edu.fit.cs.robotics.BO.RobotLogics;
import edu.fit.cs.robotics.model.images.Sectors;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class ImageController {

	Sectors sectors[]= new Sectors[7];
	
	private void initSectors(Sectors sectors[])
	{
//		sectors = new Sectors[7];
		
		for(int i=0;i<7;i++)
			sectors[i] = new Sectors();
		
		sectors[0].name = "Sector 1 end";
		sectors[1].name = "Sector 2 end";
		sectors[2].name = "Sector 2";
		sectors[3].name = "Sector 3 middle";
		sectors[4].name = "Sector 4 corner High";
		sectors[5].name = "Sector 4 corner low";
		sectors[6].name = "Sector 5 left middle";
		
		sectors[0].command = "-12000 -10000 -7000 2000 -7000 AJMA";
		sectors[1].command = "4000 10000 8000 -3000 -4000 AJMA";
		sectors[2].command = "4000 10000 7000 -3000 -4317 AJMA";
		sectors[3].command = "6000 10000 8000 -3000 -10000 AJMA";
		sectors[4].command = "7570 10046 6661 -3210 -15081 AJMA";
		sectors[5].command = "6000 10000 8000 -3000 -16000 AJMA";
		sectors[6].command = "-12000 -9499 -7999 1999 0 AJMA";
		
		
		
	}
	
	
	public void ScanSector(Sectors sector,String name)
	{
//		Image img = RobotLogics.readImageFromFile(name);
		
		Mat mat = null;//OpencvLogics.imageToMat(img);
		try {
			mat = OpencvLogics.bufferedImageToMat(ImageIO.read(new File(name)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Mat threshold = new Mat(new Size(640, 480), 8, new Scalar(3,0,0,0)) ;
		
		Point point = new Point();
		
//		H 78,172
//		S 118,200
//		V 64,233
		
		Point3 hsvMin = new Point3(78, 151, 64);
		Point3 hsvMax = new Point3(172,233,233);
		
		double area = OpencvLogics.processImage(mat, threshold, point, hsvMin, hsvMax);
		
//		double area = OpencvLogics.findCentroid(mat, point);
		
//		OpencvLogics.MyFilledCircle(mat, point);
		
		sector.pic = OpencvLogics.mat2Image(mat);
		sector.threshold = OpencvLogics.mat2Image(threshold);
		sector.area = area;
		sector.centroid = point;
		
	}
	
	public Sectors ScanSectors()
	{
		
		
		String base = "Images/Sectors";
		
		double max = 2000;
		int c=0;
		
		for(int i=0;i<sectors.length;i++)
		{
	//		System.out.println(i);
			ScanSector(sectors[i], base+(i)+".png");
			
			if(sectors[i].area > max)
			{
				max = sectors[i].area;
				c = i;
			}
		
			System.out.println(sectors[i].area
					+","+(int)sectors[i].centroid.x
					+","+(int)sectors[i].centroid.y
					);
			
		}
		
		OpencvLogics.findMaxRegion(sectors[c]);
	
		return sectors[c];
	}
	
	public static void main(String[] args) {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		ImageController control = new ImageController();
		control.initSectors(control.sectors);
		
		Sectors maxSector = control.ScanSectors();
		
		System.out.println(maxSector.region);
		
	}

}
