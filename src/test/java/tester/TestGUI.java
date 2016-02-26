package tester;

import java.net.URL;

import edu.fit.cs.robotics.constants.FXMLConstants;
import edu.fit.cs.robotics.controller.gui.BaseController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestGUI extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			
				
			FXMLLoader loader = new FXMLLoader();
			
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			
			URL location = getClass().getResource(FXMLConstants.COMMAND_GIVER_FXML);
		
			FXMLLoader fxmlLoader = new FXMLLoader();
			
			fxmlLoader.setLocation(location);
			
			fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
			
			StackPane root2 =  fxmlLoader.load(location.openStream());
	
		//	BaseController test = fxmlLoader.getController();
			
			
		
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
