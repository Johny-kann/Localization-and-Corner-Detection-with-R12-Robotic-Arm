package tester;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import org.springframework.web.filter.HiddenHttpMethodFilter;

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
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import opencv.OpencvTest;

public class TestGUI extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			
				
/*			FXMLLoader loader = new FXMLLoader();
			
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			
			URL location = getClass().getResource(FXMLConstants.IMAGE_FXML);
		
			FXMLLoader fxmlLoader = new FXMLLoader();
			
			fxmlLoader.setLocation(location);
			
			fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
*/
			
			StackPane root2 =  new StackPane();
			
			ImageView view = new ImageView();
			view.setFitHeight(600);
			view.setPreserveRatio(true);
			
	
			root2.getChildren().setAll(view);
			
	//		ImageShowerController test = fxmlLoader.getController();
			
			
		
			Scene scene = new Scene(root2,800,600,true);
		
			primaryStage.setTitle("Johny's Wrapper");

			primaryStage.setScene(scene);
			
			primaryStage.show();
			
//			nu.pattern.OpenCV.loadShared();
//			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		
			
			
			Mat m = Highgui.imread("Images/Noon_AJMA2.png",Highgui.CV_LOAD_IMAGE_COLOR);
	//		System.out.println(m.channels());
			
/*			MatOfByte byteMat = new MatOfByte();
			Highgui.imencode(".bmp", m, byteMat);
			test.addImages(new Image(new ByteArrayInputStream(byteMat.toArray())));
			
	*/
			Mat kernel = new Mat(3,3, CvType.CV_32F){
	            {
	               put(0,0,0);
	               put(0,1,1);
	               put(0,2,0);

	               put(1,0,1);
	               put(1,1,-4);
	               put(1,2,1);

	               put(2,0,0);
	               put(2,1,1);
	               put(2,2,0);
	            }
	         };
			
			 Mat k = m.clone();
	//		 m.copyTo(k);
			 
			 
			 
			Imgproc.filter2D(m, k, m.depth(), kernel);
			
					
		/*			(Mat_<char>(3,3) <<  0, -1,  0,
                    -1,  5, -1,
                     0, -1,  0);
			*/
			
			
			view.setImage(new OpencvTest().imageFX(k)
					);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		nu.pattern.OpenCV.loadShared();
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	
		launch(args);
	}
}
