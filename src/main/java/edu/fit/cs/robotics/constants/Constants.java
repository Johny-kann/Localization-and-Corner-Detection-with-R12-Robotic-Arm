package edu.fit.cs.robotics.constants;

import edu.fit.cs.robotics.BO.RobotLogics;
import javafx.geometry.Point3D;

public class Constants {
	
	public static double IMAGE_VIEW_WIDTH = 150.0;
	
	public static String BASE_URL = "http://debatedecide.fit.edu/";
	
	public static String IMAGE_URL = BASE_URL+"robot/";
	
	public static String LAST_IMAGE_URL = BASE_URL+"robot/last.bmp";
	
	public static String JOHNY_PASS = "SJbv6vl7ty5co";
	
	public static String JEFFER_PASS = "MJJkyhcl2l2h2fy2";
	
	public static String MURALI_PASS = "MuraliShrikant173";
			
	
	public static String PASSWORD = JOHNY_PASS;
	
	
	
	public static String COMMAND_URL = BASE_URL+"robot.php?o=369&m=Y&p="+PASSWORD+"&c=";

	public static int FRONT_XDIV = 10; 
		//	7;
	
	public static int TOP_XDIV = 10;
		//	7;
	
	public static int FRONT_YDIV = 5;
	//	7;
	
	public static int TOP_YDIV = 5;
	//	7;
	
	public static Point3D FRONT_PLD = new Point3D(-2400, -900, -1830);
		//	new Point3D(-2201, -800, -1820);
	public static Point3D FRONT_PRD = new Point3D(-2600, -250, -1830);
		//	-2450, -150, -1820);
	
	
	public static Point3D FRONT_PRU = new Point3D(-2650, -250,  -1450);
		//	-2450, -150,  -1150);
		//		new Point3D(-2320, -200,  -1500);
	
	public static Point3D FRONT_PLU = new Point3D(-2400, -900, -1450);
	//		new Point3D(-2201, -1000, -1500);
	
	public static Point3D TOP_PLD = new Point3D(-4450, -1550, -50);
		//	new Point3D(-4100, -1380, 00);
	public static Point3D TOP_PRD = new Point3D(-4750, -450, -50);
	
	public static Point3D TOP_PRU = new Point3D(-4150, -350, -50);
	public static Point3D TOP_PLU = new Point3D(-3900, -1450, -50);
	
	public static int FRONT_WRIST = 3000;
	
	public static int TOP_WRIST = 3000;
	
	public static int FRONT_HAND = -100; 
		//	1000;
	
	public static int TOP_HAND = -8500;
	
	

	public static void commandURLMaker()
	{
		Constants.COMMAND_URL =  Constants.BASE_URL+"robot.php?o=369&m=Y&p="+Constants.PASSWORD+"&c=";
	}
	
	
	}
