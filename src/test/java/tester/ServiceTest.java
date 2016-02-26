package tester;

import edu.fit.cs.robotics.BO.ContentAnalyser;
import edu.fit.cs.robotics.controller.RoboticsOperator;
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
		
		RoboticsOperator operator = new RoboticsOperator();
		
		operator.printPhotos("Camera", 44);
		
	}
	
	

}


