package edu.fit.cs.robotics.controller.gui;

import java.io.IOException;

import edu.fit.cs.robotics.constants.FXMLConstants;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class Navigator {

	public static BaseController baseControl;
	
	public static CommandController commandControl;
	
	public static MoveToController moveControl;
	
	public static AJmaController jmaControl;
	
	public static ImageShowerController imageControl;
	
	public static void LoadNode(ObservableList<Node> node,String fxml)
	{
		
		try {
			node.setAll(
					(Node)FXMLLoader.load(
			                CommandController.class.getResource(
			                        fxml
			                    )));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
}
