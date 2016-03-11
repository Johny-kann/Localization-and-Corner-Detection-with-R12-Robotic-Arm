package edu.fit.cs.robotics.BO;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Point;
import org.opencv.core.Point3;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class OpencvLogics {

	public static double processImage(Mat cameraFeed,Mat threshold,Point temppt,Point3 hsvMin, Point3 hsvMax)
    {
		Mat hsv = new Mat(new Size(640, 480), 8, new Scalar(3,0,0,0)) ;
//		Mat threshold = new Mat(new Size(640, 480), 8, new Scalar(3,0,0,0)) ;
		Imgproc.cvtColor(cameraFeed, hsv, Imgproc.COLOR_BGR2HSV);
		
		
    	Core.inRange(hsv, new Scalar(hsvMin.x, hsvMin.y, hsvMin.z, 0), new Scalar(hsvMax.x, hsvMax.y, hsvMax.z, 0), threshold);
     	
    	int posX = 0;
		int posY = 0;
  	
		double area = OpencvLogics.findCentroid(threshold,temppt);
		posX = (int) (temppt.x);
		posY = (int) (temppt.y);

		if (posX > 0 && posY > 0) {
		    Imgproc.line(cameraFeed, new Point(posX - 5, posY), new Point(posX + 5, posY),
		            new Scalar(0, 0 , 255, 0), 2, 8, 0);
		    Imgproc.line(cameraFeed, new Point(posX, posY - 5), new Point(posX, posY + 5),
		            new Scalar(255,0 ,0 , 0), 2, 8, 0);
		    MyFilledCircle(cameraFeed, new Point(posX, posY));
		}
		
		return area;
		
	}
	
	public static Mat imageToMat(Image image)
	{
		BufferedImage buf = SwingFXUtils.fromFXImage(image, null);
		return bufferedImageToMat(buf);
	}
	
	public static void MyFilledCircle( Mat img, Point center )
    {

    Imgproc.circle(img, center, 10, new Scalar(0,0,255),2);
     
    }
	
	public static Mat bufferedImageToMat(BufferedImage bi) {
        Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
    }
	
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
