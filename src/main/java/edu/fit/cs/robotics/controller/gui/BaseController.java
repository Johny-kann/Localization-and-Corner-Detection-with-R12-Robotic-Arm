package edu.fit.cs.robotics.controller.gui;


import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

import edu.fit.cs.robotics.controller.RoboticsOperator;
import edu.fit.cs.robotics.threads.MyService;
import edu.fit.cs.robotics.threads.ServiceHTTP;
import edu.fit.cs.robotics.threads.ServiceTask;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.util.Callback;

public class BaseController {
	
	private RoboticsOperator robotController;

	@FXML
    private WebView browser;
	
	@FXML
    private Button input;
	
	@FXML
	private Button input2;
	
	@FXML
	private Button home;
	
	 @FXML
	 private Button upBut;

	 @FXML
	 private Button dwnBut;

	 @FXML
	 private Button rightBut;

	 @FXML
	 private Button frontBut;
	 
	 @FXML
	 private Button leftBut;

	 @FXML
	 private Button bckBut;

	 @FXML
	 private TextField stepText;
	 
	 @FXML
	 private Label alertLabel;
	 
	 private ServiceTask<String> service;
	
	@FXML
    void initialize() {
    	
  //  	WrapperNavigator.wrapper = this;
		
//		service = new MyService();
		
		service = new ServiceTask<String>();
		
		
		
		alertLabel.textProperty().bind(
				service.messageProperty()
				);
		
		
		robotController = new RoboticsOperator();
		
		initActions();
		
		
		service.setMytask(new Task<String>() {

			@Override
			protected String call() throws Exception {
				
				this.updateMessage("Going");
				
				for(int i=0;i<1000;i++)
				{
					Thread.sleep(10);
					this.updateMessage(i+"");
				}
				
				this.updateMessage("Done");
				
				return "Done";
			}
		});
    	
    }
	
	
	void callMe()
	{
		alertLabel.setText("Mudinjan Da");
	}
	
	void initActions()
	{
		input.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
		//		System.out.println("Button Pressed");
				
				
				service.start();
				
	//			System.out.println(service.getMessage());
			}
		});
		
		input2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
			//	System.out.println("Button pressed");
				
//				alertLabel.setText("Progressing");
//				String url  = robotController.moveToFrontTOPMiddle();
//				System.out.println(url);
				
				

				System.out.println(service.getValue());
//				disBrows(url);
				
				
//				browser.getEngine().loadContent(url);
								
			}
		});
		
		home.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
			//	System.out.println("Button pressed");
				
				alertLabel.setText("Progressing");
				String url  = robotController.getArm().home();
//				System.out.println(url);
				
				
				disBrows(url);
				
//				browser.getEngine().loadContent(url);
				Service<String> service = new Service<String>() {

					@Override
					protected Task<String> createTask() {
						// TODO Auto-generated method stub
						return null;
					}
					
				};
				
			
				
			}
		});
		
		upBut.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				int step = Integer.parseInt(stepText.getText());
				
				alertLabel.setText("Progressing");
				disBrows(robotController.getArm().addZ(step));
				
			}
		});
		
		
		dwnBut.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
				alertLabel.setText("Progressing");
				int step = Integer.parseInt(stepText.getText());
				
				
				disBrows(robotController.getArm().addZ(-step));
				
			}
		});
		
		leftBut.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				int step = Integer.parseInt(stepText.getText());
				
				alertLabel.setText("Progressing");
				disBrows(robotController.getArm().addX(-step));
				
			}
		});
		
		rightBut.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				int step = Integer.parseInt(stepText.getText());
				
				alertLabel.setText("Progressing");
				disBrows(robotController.getArm().addX(step));
				
			}
		});
		
		frontBut.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				int step = Integer.parseInt(stepText.getText());
				
				alertLabel.setText("Progressing");
				disBrows(robotController.getArm().addY(step));
				
			}
		});
		
		bckBut.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				int step = Integer.parseInt(stepText.getText());
			
				alertLabel.setText("Progressing");
				disBrows(robotController.getArm().addY(-step));
				
			}
		});


		

		
	}
	
	void disBrows(String content)
	{
		if(content.contains("CAPTURE DONE")||content.contains("Cannot reach out"))
			alertLabel.setText("Captured");
		else
			alertLabel.setText("Moved");
		browser.getEngine().loadContent(content);
	}
	
	
}
