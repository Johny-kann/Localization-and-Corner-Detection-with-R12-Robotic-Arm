package edu.fit.cs.robotics.controller.gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import edu.fit.cs.robotics.BO.OpencvLogics;
import edu.fit.cs.robotics.BO.RobotLogics;
import edu.fit.cs.robotics.constants.Constants;
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
    private final Mat temp = new Mat(new Size(640, 480), 8, new Scalar(3,0,0,0)) ;
    private final Mat hsv = new Mat(new Size(640, 480), 8, new Scalar(3,0,0,0)) ;
    private final Mat threshold = new Mat(new Size(640, 480), 8, new Scalar(3,0,0,0)); 
    
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
//				OpencvLogics.processImage(temp, threshold, new Point(), Constants.hsvMin, Constants.hsvMax);
				paintNormal(resultView, threshold);
				paintNormal(normalView, temp);
				paintNormal(hsvView, hsv);
								
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
     
    	cameraFeed = Imgcodecs.imread("Images/Camera"+count+".png");
         
		Imgproc.cvtColor(cameraFeed, hsv, Imgproc.COLOR_BGR2HSV);
//		Imgproc.blur(hsv, hsv, new Size(20.0, 20.0));
		Imgproc.blur(hsv, hsv, new Size(5.0, 5.0));
//    	cameraFeed.copyTo(hsv);
//		Core.inRange(hsv, new Scalar(hminSlider.getValue(), sminslider.getValue(), vMinSlider.getValue(), 0), new Scalar(hmaxSlider.getValue(), smaxslider.getValue(), vmaxSlider.getValue(), 0), threshold);

		processImage();
//		OpencvLogics.processImage(cameraFeed, threshold, new Point(), Constants.hsvMin, Constants.hsvMax);
		
		paintNormal(normalView, temp);;
		
		paintNormal(hsvView, hsv);
		
		paintNormal(resultView,threshold );
		
		
    }
    
    
    private MatOfPoint apprContour(MatOfPoint contour)
    {
    	MatOfPoint2f thisContour2f = new MatOfPoint2f();
	    MatOfPoint approxContour = new MatOfPoint();
	    MatOfPoint2f approxContour2f = new MatOfPoint2f();

	   contour.convertTo(thisContour2f, CvType.CV_32FC2);

	    
	    Imgproc.approxPolyDP(thisContour2f, approxContour2f, 50, true);

	    approxContour2f.convertTo(approxContour, CvType.CV_32S);
	    
	    
	    return  approxContour;
	}
    
    private void processImage()
    {
    	
    	Core.inRange(hsv, new Scalar(hminSlider.getValue(), sminslider.getValue(), vMinSlider.getValue(), 0), new Scalar(hmaxSlider.getValue(), smaxslider.getValue(), vmaxSlider.getValue(), 0), threshold);
     	
    	cameraFeed.copyTo(temp);
        	
    	int posX = 0;
		int posY = 0;
  	
		Point temppt = new Point();
		
    	double area = OpencvLogics.findCentroid(threshold,temppt);
		posX = (int) (temppt.x);
		posY = (int) (temppt.y);

		List<MatOfPoint> contour = new ArrayList<MatOfPoint>();
		
		Mat hierarchy = new Mat();
		Mat hierarchy_norm = new Mat();
		
		Mat newMat = new Mat();
		threshold.copyTo(newMat);
		
		
		Imgproc.findContours(newMat, contour, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
	
	
		
		double con_area = 0;
		
		int k = 0;
		for(int i=0;i<contour.size();i++)
		{
			double temp = Imgproc.contourArea(contour.get(i));
			if(temp > con_area)
			{	k=i;
			con_area = temp;
//			System.out.println(k);
			}
		}
		
		Imgproc.drawContours(temp, contour,k, new Scalar(0,255,0));
		
		MatOfPoint dest = new MatOfPoint();
		
		
	//	new Mat(contour.get(k), null);
		
	/*	MatOfPoint2f thisContour2f = new MatOfPoint2f();
	    MatOfPoint approxContour = new MatOfPoint();
	    MatOfPoint2f approxContour2f = new MatOfPoint2f();
*/
	if(contour.size()>0)
	{
		MatOfPoint2f thisContour2f = new MatOfPoint2f();
		contour.get(k).convertTo(thisContour2f, CvType.CV_32FC2);
		
		RotatedRect rotateRect = Imgproc.minAreaRect(thisContour2f);
		
	
		
		List<MatOfPoint> apprList = new ArrayList<>();
		
		Point vertices[] = new Point[4];
		rotateRect.points(vertices);
		
		for (int i = 0; i < 4; i++)
		    Imgproc.line(temp, vertices[i], vertices[(i+1)%4], new Scalar(255,255,0),2);
		
//		apprList.add(rotateRect);
		
		MatOfPoint tempCont = OpencvLogics.apprContour(contour.get(k));
			
//		tempCont = this.apprContour(tempCont);
		apprList.add(tempCont);
		
		
		
		Imgproc.drawContours(temp, apprList	, -1, new Scalar(0, 0, 255));
		
		
	}	
//		Imgproc.arcLength(contour.get(k), true);
		
//		Imgproc.approxPolyDP(contour.get(k), dest, 3.0, true);
		
		double thresh = 100;
		
		System.out.println("Con Area "+ con_area);
		System.out.println("Area "+area);
		
		if (posX > 0 && posY > 0) {
		    Imgproc.line(temp, new Point(posX - 5, posY), new Point(posX + 5, posY),
		            new Scalar(0, 0 , 255, 0), 2, 8, 0);
		    Imgproc.line(temp, new Point(posX, posY - 5), new Point(posX, posY + 5),
		            new Scalar(255,0 ,0 , 0), 2, 8, 0);
		    MyFilledCircle(temp, new Point(posX, posY));
		}
		
	//	paintNormal(resultView,threshold );
    }
    
    private void paintNormal(ImageView view,Mat cameraFeed) {
    
    	view.setImage(OpencvLogics.mat2Image(cameraFeed)); 
    }
    
    void MyFilledCircle( Mat img, Point center )
    {

    Imgproc.circle(img, center, 10, new Scalar(0,0,255),2);
     
    }


 
}
