package tester;

import edu.fit.cs.robotics.BO.ContentAnalyser;
import edu.fit.cs.robotics.model.JMA;
import edu.fit.cs.robotics.model.MoveToARM;
import edu.fit.cs.robotics.threads.MyService;
import javafx.util.Callback;



public class ServiceTest {

	String disp()
	{
		return "Added";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ContentAnalyser analyze = new ContentAnalyser();
		
	  String test = "<html>"
	  		+ "	  <head>"
	  +"<title>	  Robot R12	  </title> "
	  + "</head>"
	  + "<body>"
	  + "-11500 7000 0 6975 0 AJMA"
	  + "Starting exec time: 25:02:2016 16:26:20 893000330"
	  + "<PRE>"
	  + "Accepted Command: \"\""
	  + "Answer:</PRE><div  style='background: yellow;'><PRE>"
	  + "DECIMAL -2250 3111 0 6510 0 JMA OK"
	  + "></PRE></div>"
	  + "<div style='background: green;'><PRE>"
	  + "X=0 Y=4690 Z=1730 (C=4690=2345+2345 alpha=6975 beta=0 gamma=0)"
	  + "</PRE></div>"
	  + "Completion time: 25:02:2016 16:26:32 892266713";
	  
//	  JMA temp =  analyze.getEquivalentJMA(test);
	  MoveToARM point = analyze.getEquivalentMoveTo(test);
	  
/*	  System.out.println(point.posX);
	  System.out.println(point.posY);
	  System.out.println(point.posZ);
	  */
	  
	  System.out.println((int)(800.0/91*20));
		
	}
	
	

}


