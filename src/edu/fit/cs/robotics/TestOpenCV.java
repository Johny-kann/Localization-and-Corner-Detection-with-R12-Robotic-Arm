/**
 * 
 */
package edu.fit.cs.robotics;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 * @author virus
 *
 * This is a test class which test whether opencv installed or not. 
 * Before you run this class, make sure you added opencv in user libraries 
 * and included in the project. 
 * 
 * For more information please follow the instructions from
 * {@link http://docs.opencv.org/2.4/doc/tutorials/introduction/java_eclipse/java_eclipse.html#java-eclipse} 
 */
public class TestOpenCV {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
		System.out.println("mat = "+mat.dump());
	}

}
