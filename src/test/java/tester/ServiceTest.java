package tester;

import java.net.URL;

import org.opencv.core.Core;

import edu.fit.cs.robotics.constants.FXMLConstants;
import edu.fit.cs.robotics.controller.gui.OpencvController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ServiceTest extends Application  {

	@Override
	public void start(Stage primaryStage) {
		try {
			
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
				
			FXMLLoader loader = new FXMLLoader();
			
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			
			URL location = getClass().getResource(FXMLConstants.OPENCV_FXML);
		
			FXMLLoader fxmlLoader = new FXMLLoader();
			
			fxmlLoader.setLocation(location);
			
			fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
			
		   
			
			StackPane root2 =  fxmlLoader.load(location.openStream());
	
			OpencvController test = fxmlLoader.getController();
			
			
		
			Scene scene = new Scene(root2,800,600,true);
		
			primaryStage.setTitle("Johny's Wrapper");

//			primaryStage.setFullScreen(true);
		
			
//		primaryStage.setFullScreenExitKeyCombination(new KeyCodeCombination(KeyCode.ENTER, KeyCombination.ALT_DOWN)
//			);
		
		
			primaryStage.setScene(scene);
			
			primaryStage.show();
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	

}


