package edu.fit.cs.robotics.model;

import edu.fit.cs.robotics.clients.StockClients;
import edu.fit.cs.robotics.constants.Constants;
import javafx.scene.image.Image;

public class RobotArm {
	
	public StockClients restClient; 
	int posX,posY,posZ,handTwist,wrist;
	
	public RobotArm()
	{
		restClient = new StockClients();
		posZ = 5000;
		posX = 0;
		posY = 0;
		handTwist = 0;
		wrist = 0;
		
	}

	private String constructCommand()
	{
		return handTwist+" "+wrist+" "+posX+" "+posY+" "+posZ+" TMOVETO"; 
	}
	
	private String issueCommand()
	{
		return restClient.commandRobot(constructCommand());
	}
	
	public String Capture(int num)
	{
		return restClient.commandRobot(num+" CAPTURE");
	}
	
	public String addPoint(int stepX, int stepY,int stepZ)
	{
		posX += stepX;
		posY += stepY;
		posZ += stepZ;
		
		return issueCommand();
	}
	
	public String addX(int step)
	{
		posX += step;
		return issueCommand();
	}
	
	public String addY(int step)
	{
		posY += step;
		return issueCommand();
	}
	
	public String addZ(int step)
	{
		posZ += step;
		return issueCommand();
	}
	
	public String moveWrist(int step)
	{
		wrist += step;
		return issueCommand();
	}
	
	public String moveTwist(int step)
	{
		handTwist += step;
		return issueCommand();
	}
	
	public String moveTo(int posX,int posY,int posZ)
	{
		return moveTo(handTwist, wrist, posX, posY, posZ);
	}
	
	public String moveTo(int handTwist, int wrist, int posX,int posY,int posZ)
	{
		this.handTwist = handTwist;
		this.wrist = wrist;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		
	//			System.out.println("Command Given "+str);
		return issueCommand();
	}
	
	public String home()
	{
		
		return restClient.commandRobot("HOME");
	}
	
	public Image getLastImage()
	{
		
		return restClient.getImage(Constants.LAST_IMAGE_URL);
	}
	
	public Image getImageAt(int num)
	{
		return restClient.getImage(Constants.IMAGE_URL+num+".bmp");
	}
	
}
