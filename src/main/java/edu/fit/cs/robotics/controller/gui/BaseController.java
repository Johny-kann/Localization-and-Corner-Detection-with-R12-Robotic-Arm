package edu.fit.cs.robotics.controller.gui;


import edu.fit.cs.robotics.controller.RoboticsOperator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.web.WebView;

public class BaseController {
	
	private RoboticsOperator robotController;

	@FXML
    private WebView browser;
	
	@FXML
    private Button input;
	
	@FXML
	private Button input2;
	
	@FXML
    void initialize() {
    	
  //  	WrapperNavigator.wrapper = this;
		
		robotController = new RoboticsOperator();
		
		browser.getEngine().load("http://163.118.78.116:8081/");
		
		initActions();
    	
    }
	
	
	void initActions()
	{
		input.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Button pressed");
				
			}
		});
		
		input2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
			//	System.out.println("Button pressed");
				String url  = robotController.moveToFrontTOPMiddle();
				System.out.println(url);
				browser.getEngine().load(url);
				
				
			}
		});
	}
	
	
}
