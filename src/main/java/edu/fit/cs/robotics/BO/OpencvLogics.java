package edu.fit.cs.robotics.BO;

import java.io.ByteArrayInputStream;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import javafx.scene.image.Image;

public class OpencvLogics {

	
	public static Image mat2Image(Mat frame)
	{
		// create a temporary buffer
		MatOfByte buffer = new MatOfByte();
		// encode the frame in the buffer
		Imgcodecs.imencode(".png", frame, buffer);
		// build and return an Image created from the image encoded in the
		// buffer
		return new Image(new ByteArrayInputStream(buffer.toArray()));
	}
	
	public static double findCentroid(Mat img,Point centroid)
	{
	

    	Moments moments = Imgproc.moments(img, true);
		double mom10 = moments.m10;
		double mom01 = moments.m01;
		double area = moments.m00;
		
		centroid.x =  (mom10 / area);
		centroid.y =  (mom01 / area);

		return area;
	}
	
}
