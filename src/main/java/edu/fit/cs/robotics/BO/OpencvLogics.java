package edu.fit.cs.robotics.BO;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Point3;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import edu.fit.cs.robotics.constants.Constants;
import edu.fit.cs.robotics.model.images.Sectors;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class OpencvLogics {
	
	
	public static MatOfPoint convexHull = new MatOfPoint();
	public static MatOfPoint appPoly = new MatOfPoint();
	public static Mat Threshold = new Mat();

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
		
		
	List<MatOfPoint> contour = new ArrayList<MatOfPoint>();
		
		Mat hierarchy = new Mat();
				
		Mat newMat = new Mat();
		threshold.copyTo(newMat);
		threshold.copyTo(OpencvLogics.Threshold);
		
		
		
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
		
		Imgproc.drawContours(cameraFeed, contour,k, new Scalar(0,255,0));
		
		MatOfPoint dest = new MatOfPoint();
		
		
	if(contour.size()>0)
	{
		findMinArea(cameraFeed, contour.get(k));
	//	findTriangle(threshold, contour.get(k));
	}	
		
		

		if (posX > 0 && posY > 0) {
		    Imgproc.line(cameraFeed, new Point(posX - 5, posY), new Point(posX + 5, posY),
		            new Scalar(0, 0 , 255, 0), 2, 8, 0);
		    Imgproc.line(cameraFeed, new Point(posX, posY - 5), new Point(posX, posY + 5),
		            new Scalar(255,0 ,0 , 0), 2, 8, 0);
		    MyFilledCircle(cameraFeed, new Point(posX, posY));
		}
		
		return area;
		
	}
	
	
	public static void findTriangle(Mat cameraFeed, MatOfPoint contour)
	{
		MatOfPoint2f thisContour2f = new MatOfPoint2f();

		contour.convertTo(thisContour2f, CvType.CV_32FC2);
		
		Mat triangle = new Mat();
		double area = Imgproc.minEnclosingTriangle(cameraFeed, triangle);
	//			minAreaRect(thisContour2f);
		
		Imgcodecs.imwrite("Images/Traingle.png", triangle);
		
	}
	
	
	public static void findMinArea(Mat cameraFeed, MatOfPoint contour)
	{
		List<MatOfPoint> hullList = new ArrayList<>();

		MatOfPoint hullpt = findConvexHull(contour); 
		
		hullList.add(hullpt);
		
		Imgproc.drawContours(cameraFeed, hullList,-1, new Scalar(0,0,0),2);
		
//		convexHull.copyTo(hullpt);
		hullpt.copyTo(convexHull);
		
	
//============================================================================		
		
		MatOfPoint2f thisContour2f = new MatOfPoint2f();

		contour.convertTo(thisContour2f, CvType.CV_32FC2);
		
		RotatedRect rotateRect = Imgproc.minAreaRect(thisContour2f);
		
		List<MatOfPoint> apprList = new ArrayList<>();
		
		Point vertices[] = new Point[4];
		rotateRect.points(vertices);
		
		for (int i = 0; i < 4; i++)
		    Imgproc.line(cameraFeed, vertices[i], vertices[(i+1)%4], new Scalar(255,255,0),2);
		
		MatOfPoint tempCont = OpencvLogics.apprContour(hullpt);
			
	
		tempCont.copyTo(appPoly);
			
		apprList.add(tempCont);
		
		Imgproc.drawContours(cameraFeed, apprList, -1, new Scalar(0, 0, 255));
		
	}
	
	
	private static MatOfPoint hull2Points(MatOfInt hull, MatOfPoint contour) 
	{
	    List<Integer> indexes = hull.toList();
	    List<Point> points = new ArrayList<>();
	    MatOfPoint point= new MatOfPoint();
	    for(Integer index:indexes) {
	        points.add(contour.toList().get(index));
	    }
	    point.fromList(points);
	    return point;
	}
	
	public static MatOfPoint findConvexHull(MatOfPoint contour)
	{
		MatOfInt hull = new MatOfInt();
		
		Imgproc.convexHull(contour, hull, true);
 
		MatOfPoint hullpt = hull2Points(hull, contour);
		
		return hullpt;
	}
	
	public static MatOfPoint apprContour(MatOfPoint contour)
    {
    	MatOfPoint2f thisContour2f = new MatOfPoint2f();
	    MatOfPoint approxContour = new MatOfPoint();
	    MatOfPoint2f approxContour2f = new MatOfPoint2f();

	   contour.convertTo(thisContour2f, CvType.CV_32FC2);

	    
	    Imgproc.approxPolyDP(thisContour2f, approxContour2f, 20, true);

	    approxContour2f.convertTo(approxContour, CvType.CV_32S);
	    
	    
	    return  approxContour;
	}
	
	public static Mat imgToMat(Image img,String name)
	{
		RobotLogics.writeImagePng(name,img); 
		
		Mat mat = Imgcodecs.imread(name);
		
		return mat;
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
   
    /*    ByteArrayOutputStream s = new ByteArrayOutputStream();
		try {
			ImageIO.write(bi, "png", s);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		byte[] data  = s.toByteArray();
      */  
        
        
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
	
	public static int findEdge(Mat mat)
	{
		
		Mat grid[] = new Mat[9];
		
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				grid[3*i + j] = mat.rowRange(i*(mat.rows()/3), (i+1)*mat.rows()/3);
				grid[3*i + j] = grid[3*i + j].colRange(j*mat.cols()/3, (j+1)*mat.cols()/3);
				
			}
		}
		
		Mat CameraFeed = mat;
		Mat threshold = new Mat();
		Point temppt = new Point();

		
		
		double area = processImage(CameraFeed, threshold, temppt, Constants.hsvMin2, Constants.hsvMax2);
		
		
		List<MatOfPoint> hulList = new ArrayList<>();
		
		hulList.add(convexHull);
		
		Imgproc.drawContours(CameraFeed, hulList, -1, new Scalar(0,0,0));
		
		Imgproc.fillConvexPoly(CameraFeed, appPoly, new Scalar(0,0,0));
		
		System.out.println("Main "+ area);
		Imgcodecs.imwrite("Images/TEST_Main"+".png", CameraFeed);
		Imgcodecs.imwrite("Images/TEST_Contour"+".png", OpencvLogics.Threshold);
		
		
		for(int i=0;i<9;i++)
		{
			CameraFeed = grid[i];
			threshold = new Mat();
			temppt = new Point();

			
			area = processImage(CameraFeed, threshold, temppt, Constants.hsvMin2, Constants.hsvMax2);

	
			System.out.println("Grid "+i+" "+ area);
			Imgcodecs.imwrite("Images/Test"+i+".png", CameraFeed);
	
		}
		
//		System.out.println(tem.size());
		
		
		return 0;
	}
	
	
	public static void findMaxRegion(Sectors sector)
	{
		if(sector.centroid.x > 640/2 && sector.centroid.y > 480/2)
		{
			sector.region = Sectors.Region.quad_4;
			
		} else if( !(sector.centroid.x > 640/2) && sector.centroid.y > 480/2)
		{
			sector.region = Sectors.Region.quad_3;
		}
		else if(sector.centroid.x > 640/2 && !(sector.centroid.y > 480/2))
		{
			sector.region = Sectors.Region.quad_1;
		}
		else
		{
			sector.region = Sectors.Region.quad_2;
		}
	}
	
}
