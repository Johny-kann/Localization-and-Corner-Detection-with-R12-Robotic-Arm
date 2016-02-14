package edu.fit.cs.robotics.controller;

import edu.fit.cs.robotics.BO.RobotLogics;
import edu.fit.cs.robotics.constants.Constants;
import edu.fit.cs.robotics.model.RobotArm;
import javafx.geometry.Point3D;

public class RoboticsOperator {

private RobotArm robot;
static int count = 0;
	
	public RoboticsOperator() {
	// TODO Auto-generated constructor stub
		robot = new RobotArm();
		
	}	
	
	public void getCamera()
	{
		robot.Capture(count);
		
	}
	
	public void printPhotos(String fileName)
	{
		for(int i=1;i<=count;i++)
		{
		 new RobotLogics().writeImage(i, robot.getImageAt(i), fileName);
		}
	
	}
	
	public void uvIterator(Point3D PLD, Point3D PRD, Point3D PRU, Point3D PLU,int divx,int divy)
	{
		double stX = 1.0/divx;
		double stY = 1.0/divy; 
		
		RobotLogics logic = new RobotLogics();
		
		for(int j=0;j<=divy;j++)
		{
			if(j%2==0)
			{
				for(int i=0;i<=divx;i++)
				{
				//	Tester.tester(i*stX, j*stY);
					Point3D temp = logic.map2Dto3D(i*stX, j*stY, PLD, PRD, PRU, PLU);
				//	robot.moveTo(, wrist, posX, posY, posZ)
					robot.moveTo(
							(int)temp.getX(), (int)temp.getY(), (int)temp.getZ());
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		//			System.out.print((int)temp.getX()+","+ (int)temp.getY()+","+ (int)temp.getZ()+'\t');
					robot.Capture(++count);
					
					
				}
			}else
			{
				for(int i=divx;i>=0;i--)
				{
					Point3D temp = logic.map2Dto3D(i*stX, j*stY, PLD, PRD, PRU, PLU);
					robot.moveTo(
							(int)temp.getX(), (int)temp.getY(), (int)temp.getZ());
					
			//		System.out.print((int)temp.getX()+","+ (int)temp.getY()+","+ (int)temp.getZ()+'\t');
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					robot.Capture(++count);
				}	
			}
		//	System.out.println();
		}
	}
	
	public RobotArm getArm()
	{
		return robot;
	}
	
	void moveToFrontTOPMiddle()
	{
	//	00 -2251 -500 -1500
		robot.moveTo(Constants.FRONT_WRIST, Constants.FRONT_HAND, -2251, -500, 100);
	}
	
	void moveToTopTOPMiddle()
	{
		robot.moveTo(Constants.TOP_WRIST, Constants.TOP_HAND, -3500, -500, 200);
	}
	
	void moveToLeftDown()
	{
		Point3D temp = Constants.FRONT_PLD;
		robot.moveTo((int)temp.getX(), (int)temp.getY(), (int)temp.getZ());
	}
	
	void moveToRightDown()
	{
		Point3D	temp = Constants.FRONT_PRD;
		robot.moveTo((int)temp.getX(), (int)temp.getY(), (int)temp.getZ());
	}
	
	void moveToRightUp()
	{
		Point3D temp = Constants.FRONT_PRU;
		robot.moveTo((int)temp.getX(), (int)temp.getY(), (int)temp.getZ());
	}
	
	void moveToLeftUp()
	{
		Point3D temp = Constants.FRONT_PLU;
		robot.moveTo((int)temp.getX(), (int)temp.getY(), (int)temp.getZ());
	}
	
	void caputreFrontFace()
	{
			
//		Point3D LeftEndUp = new Point3D(-2201, -1000, -1500);
//		
//		Point3D rightEndUp = new Point3D(-2320, -200, -1500);
	
		Point3D PLD = Constants.FRONT_PLD; 
			//	new Point3D(-2201, -1000, -1830);
		Point3D	PRD = Constants.FRONT_PRD; 
			//	new Point3D(-2320, -200, -1830);
		Point3D PRU = Constants.FRONT_PRU; 
			//	new Point3D(-2320, -200,  -1500);
		Point3D PLU = Constants.FRONT_PLU; 
			//	new Point3D(-2201, -1000, -1500);
		
		uvIterator(PLD, PRD, PRU, PLU, Constants.FRONT_XDIV, Constants.FRONT_YDIV);	
		
	}
	
	void captureTopFace()
	{
		
		Point3D PLD = Constants.TOP_PLD; 
		//	new Point3D(-2201, -1000, -1830);
	Point3D	PRD = Constants.TOP_PRD; 
		//	new Point3D(-2320, -200, -1830);
	Point3D PRU = Constants.TOP_PRU; 
		//	new Point3D(-2320, -200,  -1500);
	Point3D PLU = Constants.TOP_PLU; 
		//	new Point3D(-2201, -1000, -1500);
	
	uvIterator(PLD, PRD, PRU, PLU, Constants.TOP_XDIV, Constants.TOP_YDIV);	
	}
	
	void captureAnyFace()
	{
		Point3D PLD =  
			new Point3D(1500, -2000, 000);
	Point3D	PRD =  
			new Point3D(1500, 2000, 000);
	Point3D PRU =  
			new Point3D(1500, 2000,  3000);
	Point3D PLU =  
			new Point3D(1500, -2000, 3000);
	
	uvIterator(PLD, PRD, PRU, PLU, Constants.TOP_XDIV, Constants.TOP_YDIV);	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RoboticsOperator operator = new RoboticsOperator();
		
	//	operator.getArm().home();
		
//			operator.moveToFrontTOPMiddle();
	//	Constants.FRONT_WRIST = 1000;
//		operator.getArm().
//		moveTo(3000,-8500,-3900, -1450, -100);
		
//		operator.getArm().Capture(1688);
//		;
//		new RobotLogics().writeImage(1, operator.getArm().getLastImage(), "AImage");
		
//		operator.moveToTopTOPMiddle();
//		operator.getArm().moveTo(Constants.TOP_WRIST, Constants.TOP_HAND, -4450, -1550, -50);
//		operator.getArm().moveTo(3000,-8500,-3900, -1450, -100);
		
		operator.getArm().home();
		
		operator.moveToFrontTOPMiddle();
		
		operator.caputreFrontFace();
		
		operator.moveToFrontTOPMiddle();
		
		
		operator.moveToTopTOPMiddle();
		
		operator.captureTopFace();
		
		operator.moveToTopTOPMiddle();
		
		operator.getArm().home();
		
		operator.printPhotos("Camera");
		
		
//		operator.getArm().moveTo(1500, -2000, 3000);
//		operator.getArm().addY(-1000);
//		operator.captureAnyFace();
//		operator.getArm().home();

 
	}

}
