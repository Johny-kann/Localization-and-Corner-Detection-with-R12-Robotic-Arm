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
import edu.fit.cs.robotics.constants.Constants;
import edu.fit.cs.robotics.model.images.Sectors;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ImageController extends Application{

	public ImageController()
	{
		this.controller = new RoboticsOperator();
	}
	
	
	public ImageController(RoboticsOperator controller) {
		super();
		
		this.controller = controller;
	}

	Sectors sectors[]= new Sectors[7];
	RoboticsOperator controller;
	
	
	
	
	
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
		
		sectors[0].command = "-7000 -10000 -7000 2000 -7000 AJMA";
		sectors[1].command = "11000 10000 8000 -3000 -4000 AJMA";
		sectors[2].command = "12000 10000 7000 -3000 -4317 AJMA";
		sectors[3].command = "11000 10000 8000 -3000 -10000 AJMA";
		sectors[4].command = "11570 10046 6661 -3210 -15081 AJMA";
		sectors[5].command = "11000 10000 8000 -3000 -16000 AJMA";
		sectors[6].command = "-7000 -9499 -7999 1999 0 AJMA";
		
		
		
	}
	
	private Mat captureImage(String name,int num)
	{
		controller.getArm().Capture(num);
		
		RobotLogics.writeImagePng(name, controller.getArm().getImageAt(num));
		
		
		Mat mat = Imgcodecs.imread(name);
		
		System.out.println(mat.size());
		
		return mat;
	}
	
	
	public void ScanSector(Sectors sector,String name,int num)
	{
		
		controller.getArm().issueCommand(sector.command);		//Moving the ARM
		
		
		
		Mat mat = captureImage(name,num);							//Taking the picture
		
//		mat = Imgcodecs.imread(name);
		
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
		
		
		String base = "Images/process";
		
		double max = 2000;
		int c=0;
		
		for(int i=0;i<sectors.length;i++)
		{
	//		System.out.println(i);
			ScanSector(sectors[i], base+(i)+".png",i);
			
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
	
	public static void main1(String[] args)
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Constants.PASSWORD = Constants.MURALI_PASS;
		
		Constants.commandURLMaker();
		
		ImageController control = new ImageController();
		control.initSectors(control.sectors);
		
	//	control.controller.getArm().issueCommand(control.sectors[0].command);
	//	control.ScanSector(control.sectors[0], "process");
	}
	
/*	public static void main1(String[] args) {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Constants.PASSWORD = Constants.MURALI_PASS;
		
		Constants.commandURLMaker();
		
		
		ImageController control = new ImageController();
		control.initSectors(control.sectors);
		
		Sectors maxSector = control.ScanSectors();
		
		System.out.println(maxSector.region);
		
	}
*/

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Constants.PASSWORD = Constants.JEFFER_PASS;
		
		Constants.commandURLMaker();
		
		ImageController control = new ImageController();
		control.initSectors(control.sectors);
		
		Sectors maxSector = control.ScanSectors();
		
		System.out.println("Area =" + maxSector.area);
		System.out.println("Command =" + maxSector.command);
		System.out.println("Name =" + maxSector.name);
		System.out.println("Centroid =" + maxSector.centroid.x +","+maxSector.centroid.y);
		
		System.out.println(maxSector.region);
	
	
//		Mat mat = Imgcodecs.imread("Images/process0.png");
		
//		System.out.println(mat.size());
		
	}
	
	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		launch(args);
	}

}
