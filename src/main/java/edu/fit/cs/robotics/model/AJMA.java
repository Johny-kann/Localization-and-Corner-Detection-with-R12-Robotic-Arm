package edu.fit.cs.robotics.model;

public class AJMA {

//	<HAND_ANGLE> <WRIST_ANGLE> <ELBOW_ANGLE> <SHOULDER_ANGLE> <WAIST_ANGLE> AJMA
	public int handAngle, wristAngle, elbowAngle, shoulderAngle, waistAngle;
	
	public void set(int handAngle,int wristAngle, int elbowAngle,int shoulderAngle, int waistAngle)
	{
		this.handAngle = handAngle;
		this.wristAngle = wristAngle;
		this.elbowAngle = elbowAngle;
		this.shoulderAngle = shoulderAngle;
		this.waistAngle = waistAngle;
		
	}
}
