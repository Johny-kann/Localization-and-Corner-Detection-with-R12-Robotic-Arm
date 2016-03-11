package opencv;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageReader;

import org.aspectj.bridge.IProgressListener;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Range;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javafx.scene.image.Image;

public class OpencvTest {

	
	
	public static void main1(String argv[])
	{
		Map<String,String> map = new HashMap<String,String>();
		
		for(int i=0;i<10;i++)
		map.put("Slot"+i, i+"th person");

		System.out.println(map.get("Slot5"));
		
	}
	
	public static void main(String argv[])
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
