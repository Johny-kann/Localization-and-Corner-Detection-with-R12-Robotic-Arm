package edu.fit.cs.robotics.BO;

import java.util.StringTokenizer;

import edu.fit.cs.robotics.model.AJMA;
import edu.fit.cs.robotics.model.JMA;
import edu.fit.cs.robotics.model.MoveToARM;

/**
 * @author johnykann
 *
 *Class to Analyse the string output from the web
 *
 */
public class ContentAnalyser {

	public boolean commandSuccess(String content)
	{
		if(content.contains("CAPTURE DONE")||content.contains("Cannot reach out")
				||content.contains("You are not allowed at this time"))
			return false;
		else
			return true;
			
	}
	
	public String commanIssued(String content)
	{
		int index = content.indexOf("<body>");
		int last = content.indexOf("Starting exec time", index);
		
		return content.substring(index+6, last);
		
	}
	
	private JMA getJMA(String command)
	{
		StringTokenizer tokens = new StringTokenizer(command, " ");
		
		JMA temp = new JMA();
		
		temp.set(
				Integer.parseInt(tokens.nextToken()), 
				Integer.parseInt(tokens.nextToken()), 
			    Integer.parseInt(tokens.nextToken()), 
				Integer.parseInt(tokens.nextToken()), 
				Integer.parseInt(tokens.nextToken())
				);
		
		return temp;
	}
	
	public MoveToARM getEquivalentMoveTo(String content)
	{
		int x = content.indexOf("X=");
		int x_end = content.indexOf(' ', x);
		
		int y = content.indexOf("Y=");
		int y_end = content.indexOf(' ', y);
				
		int z = content.indexOf("Z=");
		int z_end = content.indexOf(' ', z);
		
		
		MoveToARM point = new MoveToARM();
		
		point.setPos(
				Integer.parseInt(content.substring(x+2, x_end)),
				Integer.parseInt(content.substring(y+2, y_end)),
				Integer.parseInt(content.substring(z+2, z_end))
		);
		
		return point;
		
		
	}
	
	public AJMA getAJMAFromJMA(JMA jma)
	{
		AJMA temp = new AJMA();
		
		temp.waistAngle = (int)(180.0/7280 * jma.waistSteps * 100);
		
		temp.shoulderAngle = (int)(90.0/8400 * jma.shoulderSteps * 100);
		
		temp.elbowAngle = (int)(90.0/6000 * jma.elbowSteps * 100);
		
		temp.wristAngle = (int)(90.0/4000 * jma.wristSteps * 100);
		
		temp.handAngle = (int)(20.0/1000 * jma.handSteps * 100);
		
		return temp;
	}
	
	public JMA getEquivalentJMA(String content)
	{
		int index = content.indexOf("DECIMAL");
		int last = content.indexOf("OK",index);
		
		return getJMA(content.substring(index+7,last));
		
		
	}
	
	
}
