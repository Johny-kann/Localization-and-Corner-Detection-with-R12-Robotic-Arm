package edu.fit.cs.robotics.controller.gui;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import edu.fit.cs.robotics.BO.OpencvLogics;
import edu.fit.cs.robotics.BO.RobotLogics;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import opencv.Detection;

public class OpencvController {
	@FXML
    private Slider vMinSlider;

    @FXML
    private Slider hmaxSlider;

    @FXML
    private ImageView normalView;

    @FXML
    private ImageView resultView;

    @FXML
    private Slider sminslider;

    @FXML
    private ImageView hsvView;

    @FXML
    private Slider hminSlider;

    @FXML
    private Slider smaxslider;

    @FXML
    private Slider vmaxSlider;
    
    @FXML
    private Label hminLabel;
    
    @FXML
    private Label hMaxLabel;
    
    @FXML
    private Label sminLabel;
    
    @FXML
    private Label sMaxLabel;
    
    @FXML
    private Label vminLabel;
    
    @FXML
    private Label vMaxLabel;
    
    @FXML
    private Button leftBut;

    @FXML
    private Button rightBut;

    
    @FXML
    void initialize()
    {
    	initChangeListeners();
    	loadImage();
    }
    
    
    private Mat cameraFeed;
    private final Mat hsv = new Mat(new Size(640, 480), 8, new Scalar(3,0,0,0)) ;
    private final Mat threshold = new Mat(new Size(640, 480), 8, new Scalar(1,0,0,0)); 
    
    private int count = 0;
    
    
    private void initChangeListeners()
    {
    	
    	hminLabel.textProperty().bind(hminSlider.valueProperty().asString());
    	hMaxLabel.textProperty().bind(hmaxSlider.valueProperty().asString());
    	vminLabel.textProperty().bind(vMinSlider.valueProperty().asString());
    	vMaxLabel.textProperty().bind(vmaxSlider.valueProperty().asString());
    	sminLabel.textProperty().bind(sminslider.valueProperty().asString());
    	sMaxLabel.textProperty().bind(smaxslider.valueProperty().asString());
    
    	
    	ChangeListener<Number> listener = new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				processImage();
				paintNormal(resultView, threshold);
								
			}
		};
    	
    	hminSlider.valueProperty().addListener(listener);
    	hmaxSlider.valueProperty().addListener(listener);
    	
    	vMinSlider.valueProperty().addListener(listener);
    	vmaxSlider.valueProperty().addListener(listener);
    	
    	smaxslider.valueProperty().addListener(listener);
    	sminslider.valueProperty().addListener(listener);
    	
    	
    	rightBut.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				count++;
				loadImage();
				
			}
		});
    	
    	leftBut.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(count!=0)
					count--;
				loadImage();
							
			}
		});
    }
    
    private void loadImage() {
     
    	cameraFeed = Imgcodecs.imread("Images/Sectors"+count+".png");
         
		Imgproc.cvtColor(cameraFeed, hsv, Imgproc.COLOR_BGR2HSV);
//    	cameraFeed.copyTo(hsv);
		Core.inRange(hsv, new Scalar(hminSlider.getValue(), sminslider.getValue(), vMinSlider.getValue(), 0), new Scalar(hmaxSlider.getValue(), smaxslider.getValue(), vmaxSlider.getValue(), 0), threshold);

		processImage();
		
		paintNormal(normalView, cameraFeed);;
		
		paintNormal(hsvView, hsv);
		
		
		
		paintNormal(resultView,threshold );
		

		
		
    }
    
    private void processImage()
    {
    	
    	Core.inRange(hsv, new Scalar(hminSlider.getValue(), sminslider.getValue(), vMinSlider.getValue(), 0), new Scalar(hmaxSlider.getValue(), smaxslider.getValue(), vmaxSlider.getValue(), 0), threshold);
    	
    	
    	int posX = 0;
		int posY = 0;

    	Moments moments = Imgproc.moments(threshold, true);
		double mom10 = moments.m10;
		double mom01 = moments.m01;
		double area = moments.m00;
		posX = (int) (mom10 / area);
		posY = (int) (mom01 / area);
		// only if its a valid position
          
		if (posX > 0 && posY > 0) {
		    Imgproc.line(cameraFeed, new Point(posX - 5, posY), new Point(posX + 5, posY),
		            new Scalar(hminSlider.getValue(), sminslider.getValue(), vMinSlider.getValue(), 0), 2, 8, 0);
		    Imgproc.line(cameraFeed, new Point(posX, posY - 5), new Point(posX, posY + 5),
		            new Scalar(hmaxSlider.getValue(), smaxslider.getValue(), vmaxSlider.getValue(), 0), 2, 8, 0);
		}
		
	//	paintNormal(resultView,threshold );
    }
    
    private void paintNormal(ImageView view,Mat cameraFeed) {
    
    	view.setImage(OpencvLogics.mat2Image(cameraFeed)); 
    }
    
 
}
