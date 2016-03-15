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
import edu.fit.cs.robotics.model.images.Sectors.Region;
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
		
		
		sectors[0].quad1_command = "3300 -8000 -3404 3438 1156 TMOVETO";
		sectors[0].quad2_command = "5300 -8000 -4404 1438 1156 TMOVETO";
		sectors[0].quad3_command = "3300 -8000 -1104 2438 1156 TMOVETO";
		sectors[0].quad4_command = "300 -8000 -1004 3838 1156 TMOVETO";
		
		sectors[1].quad1_command = "44300 -8000 -3204 3438 1156 TMOVETO";
		sectors[1].quad2_command = "4300 -8000 -4204 2438 1156 TMOVETO";
		sectors[1].quad3_command = "1300 -8000 -1204 2438 1156 TMOVETO";
		sectors[1].quad4_command = "300 -8000 -1204 3838 1156 TMOVETO";
		
		
		sectors[2].quad1_command = "44300 -8000 -3204 3438 1156 TMOVETO";
		sectors[2].quad2_command = "4300 -8000 -4204 2438 1156 TMOVETO";
		sectors[2].quad3_command = "1300 -8000 -1204 2438 1156 TMOVETO";
		sectors[2].quad4_command = "300 -8000 -1204 3838 1156 TMOVETO";
		
		
		sectors[3].quad1_command = "8300 -8000 -4104 438 1156 TMOVETO";
		sectors[3].quad2_command = "8300 -8000 -4104 -2562 1156 TMOVETO";
		sectors[3].quad3_command = "8300 -8000 -2104 -2562 1156 TMOVETO";
		sectors[3].quad4_command = "4300 -8000 96 -4562 1156 TMOVETO";
		
		sectors[4].quad1_command = "12300 -8000 -3904 -2462 1156 TMOVETO";
		sectors[4].quad2_command = "12300 -8000 -1904 -4462 1156 TMOVETO";
		sectors[4].quad3_command = "14300 -8000 -304 -4462 1156 TMOVETO";
		sectors[4].quad4_command = "12300 -8000 -1904 -2462 1156 TMOVETO";
		
		sectors[5].quad1_command = "4300 -8000 1596 -4562 1156 TMOVETO";
		sectors[5].quad2_command = "7300 -8000 3396 -3362 1156 TMOVETO";
		sectors[5].quad3_command = "4300 -8000 2496 -2162 1156 TMOVETO";
		sectors[5].quad4_command = "4300 -8000 96 -4562 1156 TMOVETO";
		
		sectors[6].quad1_command = "-7000 -8499 -5099 -4001 2000 AJMA";
		sectors[6].quad2_command = "-5000 -8499 -5099 -4001 -2000 AJMA";
		sectors[6].quad3_command = "-6000 -8499 -7099 -2001 -1000 AJMA";
		sectors[6].quad4_command = "-7000 -8499 -7099 -2001 2000 AJMA";
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
				
				if(max>30000)
				{
					OpencvLogics.findMaxRegion(sectors[c]);
					return sectors[c];
				}
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
	
	void processRegion(Sectors sector)
	{
		if(sector.region == Region.quad_1)
			controller.getArm().issueCommand(sector.quad1_command);
		else if(sector.region == Region.quad_2)
			controller.getArm().issueCommand(sector.quad2_command);
		else if(sector.region == Region.quad_3)
			controller.getArm().issueCommand(sector.quad3_command);
		else if(sector.region == Region.quad_4)
			controller.getArm().issueCommand(sector.quad4_command);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Constants.PASSWORD = Constants.SRI_PASS;
		
		Constants.commandURLMaker();
		
		ImageController control = new ImageController();
		control.initSectors(control.sectors);
		
		Sectors maxSector = control.ScanSectors();
		
		System.out.println("Area =" + maxSector.area);
		System.out.println("Command =" + maxSector.command);
		System.out.println("Name =" + maxSector.name);
		System.out.println("Centroid =" + maxSector.centroid.x +","+maxSector.centroid.y);
		
		System.out.println(maxSector.region);
	
		processRegion(maxSector);
		
	
//		Mat mat = Imgcodecs.imread("Images/process0.png");
		
//		System.out.println(mat.size());
		
	}
	
	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		launch(args);
	}

}
