package edu.fit.cs.robotics.model.images;

import org.opencv.core.Point;

import javafx.scene.image.Image;

public class Sectors {

	public Image pic;
	public Image threshold;
	public double area;
	public Point centroid;
	
	public String name;
	
	public String command;
	
	public Region region;
	
	public String quad1_command;
	
	public String quad2_command;
	
	public String quad3_command;
	
	public String quad4_command;
	
public enum Region {quad_1,quad_2,quad_3,quad_4};
	
}
