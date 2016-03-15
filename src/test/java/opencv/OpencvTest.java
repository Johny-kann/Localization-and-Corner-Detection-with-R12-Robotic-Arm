package opencv;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

import org.aspectj.bridge.IProgressListener;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Range;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import edu.fit.cs.robotics.BO.OpencvLogics;
import edu.fit.cs.robotics.BO.RobotLogics;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class OpencvTest {

	
	
	public static void main(String argv[])
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Image image = RobotLogics.readImageFromFile("Images/Sectors3.png");
		
//		BufferedImage bi = SwingFXUtils.fromFXImage(image, null);
		
		File outputFile = new File("Images/Sectors3.png");
	    BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
	    
	    boolean bool;
	    try {
	      bool = ImageIO.write(bImage, "png", outputFile);
	    } catch (IOException e) {
	      throw new RuntimeException(e);
	    }
		
	    Mat mat = new Mat(bImage.getHeight(), bImage.getWidth(), CvType.CV_8UC3);
	   
    ByteArrayOutputStream s = new ByteArrayOutputStream();
	try {
		ImageIO.write(bImage, "png", s);
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	byte[] data  = s.toByteArray();
    
	try
	{
   mat.put(0, 0, data);
	}catch(java.lang.UnsupportedOperationException e)
	{
	//	data[0]
		e.printStackTrace();
		
	}
   
   System.out.println(mat.size());
   System.out.println(image.getHeight());
		
	}
	
	public static void main1(String argv[])
	{
		
		
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
			System.out.println("mat = "+mat.dump());
			
			Mat m = Imgcodecs.imread("Images/Camera1.png");
			int type = BufferedImage.TYPE_BYTE_GRAY;
			if ( m.channels() > 1 ) {
			    Mat m2 = new Mat();
			    Imgproc.cvtColor(m,m2,Imgproc.COLOR_BGR2RGB);
			    type = BufferedImage.TYPE_3BYTE_BGR;
			    m = m2;
			}
			byte [] b = new byte[m.channels()*m.cols()*m.rows()];
			
			System.out.println(m.size());
	}
}
