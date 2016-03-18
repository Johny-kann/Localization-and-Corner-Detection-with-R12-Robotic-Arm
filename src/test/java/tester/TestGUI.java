package tester;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import edu.fit.cs.robotics.BO.RobotLogics;
import edu.fit.cs.robotics.model.PhotoGrid;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestGUI extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			
				
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		PhotoGrid grid = new PhotoGrid();
		char  tt[] = 
		{'P','E','E','F','P','E','P','E','E'};
		
		grid.grid = tt;
		
		grid.print();
		
		System.out.println(RobotLogics.pointGrid(grid));
		
		
	}
	
	public static void main1(String[] args) {
		
	//	nu.pattern.OpenCV.loadShared();
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	
		launch(args);
	}
}
