package edu.fit.cs.robotics.model;

public class MoveToARM {

public int posX,posY,posZ,handTwist,wrist;
	
	public void copy(MoveToARM point)
	{
		setEverything(point.handTwist, point.wrist, point.posX, point.posY, point.posZ);
	}

	public void setPos(int posX,int posY,int posZ)
	{
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
	}
	
	public void setEverything(int handTwist,int wrist,int posX,int posY,int posZ)
	{
		this.handTwist = handTwist;
		this.wrist = wrist;
		
		this.setPos(posX, posY, posZ);
		
	}
}
