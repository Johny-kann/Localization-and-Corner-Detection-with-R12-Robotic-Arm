package edu.fit.cs.robotics.model;

public class JMA {

//	<HAND_STEPS> <WRIST_STEPS> <ELBOW_STEPS> <SHOULDER_STEPS> <WAIST_STEPS> JMA
	public int handSteps, wristSteps, elbowSteps, shoulderSteps, waistSteps;
	
	public void set(int handSteps, int wristSteps, int elbowSteps, int shoulderSteps, int waistSteps)
	{
		this.handSteps = handSteps;
		this.waistSteps = waistSteps;
		this.wristSteps = wristSteps;
		this.elbowSteps = elbowSteps;
		this.shoulderSteps = shoulderSteps;
	}
	
}
