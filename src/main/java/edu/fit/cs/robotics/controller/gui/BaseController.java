package edu.fit.cs.robotics.controller.gui;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

import edu.fit.cs.robotics.BO.RobotLogics;
import edu.fit.cs.robotics.constants.Constants;
import edu.fit.cs.robotics.controller.RoboticsOperator;
import edu.fit.cs.robotics.threads.MyService;
import edu.fit.cs.robotics.threads.ServiceHTTP;
import edu.fit.cs.robotics.threads.ServiceTask;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import javafx.util.Callback;

public class BaseController {
	
	RoboticsOperator robotController;

	@FXML
    private ComboBox<String> personSelect;
	
	@FXML
    private WebView browser;
	
	@FXML
    private Button input;
	
	@FXML
	private Button input2;
	
	 @FXML
    private Button captureButton;
	 
	  @FXML
	  private ImageView cameraPort;


	 @FXML
	 private TextField stepText;
	 
	 @FXML
	 Label alertLabel;
	 
	 @FXML
	 private Label labZMov;
	 
	 @FXML
	 private Label labWristMov;
	 
	 @FXML
	 private Label labHandMov;
	 
	 @FXML
	 private Label labXMov;

	 @FXML
	 private Label labYMov;
	 
	 ServiceTask<String> service;
	
	@FXML
    void initialize() {
		
		Navigator.baseControl = this;
    	
  //  	WrapperNavigator.wrapper = this;
		
//		service = new MyService();
		
		personSelect.getItems().add("Johny");
		personSelect.getItems().add("Jeff");
		personSelect.getItems().add("Murali");
				
	
		
		
		
		
		
	/*	alertLabel.textProperty().bind(
				service.messageProperty()
				);
		*/
		
		robotController = new RoboticsOperator();
		
		initService();
		initActions();
		
		
		
    	
    }
	
	void initService()
	{
		service = new ServiceTask<String>();
		
		service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			
			@Override
			public void handle(WorkerStateEvent event) {
			
				disBrows(service.getValue());
				
			}
		});
	}
	
	
	void callMe()
	{
		alertLabel.setText("Mudinjan Da");
		
		
	}
	
	
	void initActions()
	{
		
		captureButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				robotController.getCamera();
				cameraPort.setImage(robotController.getArm().getLastImage());
				
			}
		});
		
		personSelect.valueProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue ov, String t, String t1) {                
               if(t1.equalsIgnoreCase("Johny"))
            	   Constants.PASSWORD = Constants.JOHNY_PASS;
               
               else if(t1.equalsIgnoreCase("Jeff"))
            	   Constants.PASSWORD = Constants.JEFFER_PASS;
               
               else if(t1.equalsIgnoreCase("Murali"))
            	   Constants.PASSWORD = Constants.MURALI_PASS;
              
               Constants.commandURLMaker();
               
              }    
        });
		
		
		input.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		
		input2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println(service.getValue());
								
			}
		});
		

		
	}
	
	void disBrows(String content)
	{
	//	if(content.contains("CAPTURE DONE")||content.contains("Cannot reach out"))
		if(robotController.getArm().last_issued_command_success)
			alertLabel.setText("Moved");
		else
			alertLabel.setText("Captured");
		
		browser.getEngine().loadContent(content);
		
		System.out.println(content);
		
		
		upDateTail();
		
		
	}
	
	void upDateTail()
	{
		labHandMov.setText(robotController.getArm().current_MoveTo.handTwist+"");
		labWristMov.setText(robotController.getArm().current_MoveTo.wrist+"");
		labXMov.setText(robotController.getArm().current_MoveTo.posX+"");
		labYMov.setText(robotController.getArm().current_MoveTo.posY+"");
		labZMov.setText(robotController.getArm().current_MoveTo.posZ+"");
	}
	
	
}
