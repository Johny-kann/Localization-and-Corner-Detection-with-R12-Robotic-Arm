package edu.fit.cs.robotics.model;

import edu.fit.cs.robotics.BO.ContentAnalyser;
import edu.fit.cs.robotics.clients.StockClients;
import edu.fit.cs.robotics.constants.Constants;
import javafx.scene.image.Image;



public class RobotArm {
	
	public StockClients restClient; 
	private ContentAnalyser logic;
	
	public boolean last_issued_command_success;
	
	String last_command;
	
	public MoveToARM moveTo , current_MoveTo;
	
	public AJMA ajma, current_AJMA;
	
	public JMA jma, current_JMA;

	enum comType {MOVETO,AJMA,JMA,HOME,CAPTURE};
	
	//public 
	
	public RobotArm()
	{
		restClient = new StockClients();
		logic = new ContentAnalyser();
		
		moveTo = new MoveToARM();
		ajma = new AJMA();
		jma = new JMA();
		
		current_MoveTo = new MoveToARM();
		current_AJMA = new AJMA();
		current_JMA = new JMA();
		
		
		moveTo.setEverything(0, 0, 0, 0, 5000);
		current_MoveTo.setEverything(0, 0, 0, 0, 5000);
		
		current_AJMA.set(0, 0, 0, 0, 0);
		
		
		last_issued_command_success = false;
		
/*		posZ = 5000;
		posX = 0;
		posY = 0;
		handTwist = 0;
		wrist = 0;
		*/
		
	}

	private String constructMoveToCommand()
	{
		return moveTo.handTwist+" "+ moveTo.wrist+" "+ moveTo.posX+" "+ moveTo.posY+" "+ moveTo.posZ+" TMOVETO"; 
	}
	
	private String constructAJMACommand()
	{
		return ajma.handAngle+" "+ ajma.wristAngle+" "+ ajma.elbowAngle+" "+ ajma.shoulderAngle+" "+ ajma.waistAngle+" AJMA";
	}
	
	private String constructJMACommand()
	{
		return jma.handSteps+" "+ jma.wristSteps+" "+ jma.elbowSteps+" "+ jma.shoulderSteps+" "+ jma.waistSteps+" JMA";
	}
	
	private String sendCommandHTTP(String command,comType type)
	{
		String response = restClient.commandRobot(command);
		
		if(logic.commandSuccess(response))
		{	
			this.last_issued_command_success = true;
			this.last_command = logic.commanIssued(response);
			
			this.jma.copy(logic.getEquivalentJMA(response));
		
			this.current_JMA.copy(jma);
			
			if(type == comType.MOVETO)
			{
				this.current_MoveTo.copy(this.moveTo);
				this.ajma.copy(logic.getAJMAFromJMA(current_JMA));
			
				this.current_AJMA.copy(ajma);
			}
			
			else if(type == comType.AJMA)
			{
				this.current_AJMA = this.ajma;
				MoveToARM pt = logic.getEquivalentMoveTo(response);
				this.moveTo.posX = pt.posX;
				this.moveTo.posY = pt.posY;
				this.moveTo.posZ = pt.posZ;
			
				this.current_MoveTo.copy(moveTo);
			}
			
			else if(type == comType.HOME)
			{
				this.moveTo.setEverything(0, 0, 0, 0, 5000);
				this.current_MoveTo = moveTo;
				
				this.ajma.set(0, 0, 0, 0, 0);
				this.current_AJMA.copy(ajma);
			}
			
			
		}
		
		else
		{
			this.last_issued_command_success = false;
	//		System.out.println("False");
			
			if(type == comType.MOVETO)
			{
				this.moveTo.copy(current_MoveTo); 
			}
			else if(type == comType.AJMA)
			{
				 this.ajma.copy(current_AJMA);
			}
			else if(type == comType.HOME)
			{
				 this.ajma.copy(current_AJMA);
			}
			
		}
		
		return response;
	}
	
	private String issueCommand(comType type)
	{
		if(type == comType.MOVETO)
		return sendCommandHTTP(constructMoveToCommand(),type);
		
		else if(type == comType.AJMA)
			return sendCommandHTTP(constructAJMACommand(),type);
		
		else if(type == comType.JMA)
			return sendCommandHTTP(constructJMACommand(),type);
			
		return null;
	}
	
	public String Capture(int num)
	{
		 return sendCommandHTTP(num+" CAPTURE",comType.CAPTURE);
		//return restClient.commandRobot(num+" CAPTURE");
	}
	
	public String ajmaMove(int handAngle, int wristAngle, int elbowAngle, int shoulderAngle, int waistAngle)
	{
		ajma.set(handAngle, wristAngle, elbowAngle, shoulderAngle, waistAngle);
		
		return issueCommand(comType.AJMA);
	}
	
	public String ajmaAdd(int elbowAngle, int shoulderAngle, int waistAngle)
	{
		ajma.elbowAngle = elbowAngle;
		ajma.shoulderAngle = shoulderAngle;
		ajma.waistAngle = waistAngle;
		
		return issueCommand(comType.AJMA);
	}
	
	public String ajmaHandAdd(int handAngle)
	{
		ajma.handAngle+=handAngle;
		
		return issueCommand(comType.AJMA);
	}
	
	public String ajmaWristAdd(int wristAngle)
	{
		ajma.wristAngle+=wristAngle;
		
		return issueCommand(comType.AJMA);
	}
	
	public String ajmaElbowAdd(int elbAngle)
	{
		ajma.elbowAngle+=elbAngle;
		
		return issueCommand(comType.AJMA);
	}
	
	public String ajmaShoulderAdd(int shoulerAngle)
	{
		ajma.shoulderAngle+=shoulerAngle;
		
		return issueCommand(comType.AJMA);
	}
	
	public String ajmaWaistAdd(int waistAngle)
	{
		ajma.waistAngle+=waistAngle;
		
		return issueCommand(comType.AJMA);
	}

	
	public String addPoint(int stepX, int stepY,int stepZ)
	{
		moveTo.posX += stepX;
		moveTo.posY += stepY;
		moveTo.posZ += stepZ;
		
		return issueCommand(comType.MOVETO);
	}
	
	public String addX(int step)
	{
		moveTo.posX += step;
		return issueCommand(comType.MOVETO);
	}
	
	public String addY(int step)
	{
		moveTo.posY += step;
		return issueCommand(comType.MOVETO);
	}
	
	public String addZ(int step)
	{
		moveTo.posZ += step;
		return issueCommand(comType.MOVETO);
	}
	
	public String addWrist(int step)
	{
		moveTo.wrist += step;
		return issueCommand(comType.MOVETO);
	}
	
	public String addTwist(int step)
	{
		moveTo.handTwist += step;
		return issueCommand(comType.MOVETO);
	}
	
	public String moveTo(int posX,int posY,int posZ)
	{
		return moveTo(moveTo.handTwist, moveTo.wrist, posX, posY, posZ);
	}
	
	public String moveTo(int handTwist, int wrist, int posX,int posY,int posZ)
	{
		moveTo.handTwist = handTwist;
		moveTo.wrist = wrist;
		moveTo.posX = posX;
		moveTo.posY = posY;
		moveTo.posZ = posZ;
		
		return issueCommand(comType.MOVETO);
	}
	
	public String home()
	{
		 return sendCommandHTTP("HOME",comType.HOME);
//		return restClient.commandRobot("HOME");
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
