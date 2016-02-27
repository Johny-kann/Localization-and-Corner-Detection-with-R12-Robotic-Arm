package tester;

import java.net.URL;

import edu.fit.cs.robotics.BO.RobotLogics;
import edu.fit.cs.robotics.constants.FXMLConstants;
import edu.fit.cs.robotics.controller.gui.BaseController;
import edu.fit.cs.robotics.controller.gui.ImageShowerController;
import edu.fit.cs.robotics.controller.gui.Navigator;
import edu.fit.cs.robotics.threads.ImageService;
import javafx.application.Application;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestGUI extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			
				
			FXMLLoader loader = new FXMLLoader();
			
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			
			URL location = getClass().getResource(FXMLConstants.IMAGE_FXML);
		
			FXMLLoader fxmlLoader = new FXMLLoader();
			
			fxmlLoader.setLocation(location);
			
			fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
			
			StackPane root2 =  fxmlLoader.load(location.openStream());
	
			ImageShowerController test = fxmlLoader.getController();
			
			
		
			Scene scene = new Scene(root2,800,600,true);
		
			primaryStage.setTitle("Johny's Wrapper");

			primaryStage.setScene(scene);
			
			primaryStage.show();
			
	/*		ImageService serviceImage = new ImageService();
			
			serviceImage.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

				@Override
				public void handle(WorkerStateEvent event) {
					// TODO Auto-generated method stub
					System.out.println("Succeded");
					serviceImage.reset();
					
					Navigator.imageControl.addImages(serviceImage.getValue());
					
				}
			});
			
			
			serviceImage.setUrl("http://debatedecide.fit.edu/robot/4.bmp");
			
			serviceImage.start();
			
		*/	
			
	/*		test.addImages(RobotLogics.readImageFromFile("Images/Camera1.png"));
			test.addImages(RobotLogics.readImageFromFile("Images/Camera2.png"));
			test.addImages(RobotLogics.readImageFromFile("Images/Camera3.png"));
		*/	
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
