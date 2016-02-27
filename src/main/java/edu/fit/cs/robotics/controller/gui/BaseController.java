package edu.fit.cs.robotics.controller.gui;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

import javax.annotation.processing.ProcessingEnvironment;

import edu.fit.cs.robotics.BO.RobotLogics;
import edu.fit.cs.robotics.constants.Constants;
import edu.fit.cs.robotics.controller.RoboticsOperator;
import edu.fit.cs.robotics.threads.ImageService;
import edu.fit.cs.robotics.threads.MyService;
import edu.fit.cs.robotics.threads.ServiceHTTP;
import edu.fit.cs.robotics.threads.ServiceTask;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.concurrent.Worker.State;
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
    private Label imageCount;

    @FXML
    private Button cameraBut;

    @FXML
    private Button video1;

    @FXML
    private Button video2;

    @FXML
    private Button lastCapture;

    @FXML
    private Button output;
    
    @FXML
    private Button saveImages;
	
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
	 
	 ImageService serviceImage;
	 
	 @FXML
	    private Button prevImage;

	 @FXML
	    private Button nextImage;
	 
	 
	 private int webView = 0;
	 
	 private IntegerProperty count;
	 
	 private int countt = 0;
	 
	 private int Total_images = 0;
	
	@FXML
    void initialize() {
		
		Navigator.baseControl = this;
		count = new SimpleIntegerProperty(5);
    	
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
		
		serviceImage = new ImageService();
		
		serviceImage.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

			@Override
			public void handle(WorkerStateEvent event) {
				// TODO Auto-generated method stub
				Navigator.imageControl.addImages(serviceImage.getValue());
				
				serviceImage.reset();
				
				
				
			}
		});
	}
	
	
	void callMe()
	{
		alertLabel.setText("Mudinjan Da");
		
		
	}
	
	
	void initBinds()
	{
	//	IntegerProperty test;
	//	imageCount.textProperty().bind(count.asString());
		cameraBut.setText("Capture "+Total_images);
	}
	
	void initActions()
	{
		
		saveImages.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
				service.setMytask(new Task<String>() {
					@Override
					protected String call() throws Exception {
			
						String url = robotController.printPhotos("Camera", countt);
						return url;
					}
				});
		//		robotController.printPhotos("Camera", countt);
			service.start();
			
				
			}
		});
		
		video1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				browser.getEngine().load("http://163.118.78.116:8091/");
				webView = -1;
			}
		});
		
		video2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				browser.getEngine().load("http://163.118.78.116:8081/");
				webView = 1;
			}
		});

		output.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				browser.getEngine().loadContent("Output Response will be loaded");;
				webView = 0;
			}
		});

		
	
		prevImage.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				count.add(-1);
				
				
				try
				{
					countt--;
				cameraPort.setImage(Navigator.imageControl.getImageAt(countt));;
				

				imageCount.setText(countt+"");
				}catch(Exception e)
				{
					countt++;
					e.printStackTrace();
				}
				
				
			}
		});
		
		nextImage.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				count.add(1);
				
				try
				{
					countt++;
				
				cameraPort.setImage(Navigator.imageControl.getImageAt(countt));
			
				
	
				imageCount.setText(countt+"");
				}catch(Exception e)
				{
					countt--;
					e.printStackTrace();
					
				}
			}
		});
		
		
		cameraBut.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				robotController.getArm().Capture(Total_images);
				
				Total_images++;
				cameraBut.setText("Capture "+Total_images);
				
				serviceImage.setUrl(Constants.LAST_IMAGE_URL);;
				
				serviceImage.start();
				
			}
		});
		
		captureButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				robotController.getArm().Capture(Total_images);
				
				cameraPort.setImage(robotController.getArm().getLastImage());
				Total_images++;
				cameraBut.setText("Capture "+Total_images);
				
				serviceImage.setUrl(Constants.LAST_IMAGE_URL);;
				
				serviceImage.start();
				
			}
		});
		
		lastCapture.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				cameraPort.setImage(robotController.getArm().getLastImage());
	//			serviceImage.setUrl(Constants.LAST_IMAGE_URL);;
				
	//			serviceImage.start();
				
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
								
			}	// TODO Auto-generated method stub
			
		});
		

		
	}
	
	void disBrows(String content)
	{
	//	if(content.contains("CAPTURE DONE")||content.contains("Cannot reach out"))
		if(robotController.getArm().last_issued_command_success)
			alertLabel.setText("Moved");
		else
			alertLabel.setText("Captured");
		
		if(webView==0)
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
