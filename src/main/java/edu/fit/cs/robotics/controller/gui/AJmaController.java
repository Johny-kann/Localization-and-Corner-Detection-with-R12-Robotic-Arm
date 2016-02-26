package edu.fit.cs.robotics.controller.gui;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AJmaController {

	@FXML
    private TextField wrist_field;

    @FXML
    private Button elb_up;

    @FXML
    private TextField twist_text;

    @FXML
    private Button shoulder_up;

    @FXML
    private Button elb_down;

    @FXML
    private TextField step;

    @FXML
    private Button shoulder_down;

    @FXML
    private Button twist;

    @FXML
    private Button wrist;

    @FXML
    private Button waist_left;

    @FXML
    private Button waist_right;
    
    @FXML
    private Button home;
    
    @FXML
    void initialize()
    {
    
    	initButtons();
    	
    }
	
    
    void initButtons()
    {
    	home.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
			
				Navigator.baseControl.alertLabel.setText("Progressing");
				Navigator.baseControl.service.setMytask(new Task<String>() {
					@Override
					protected String call() throws Exception {
						String url  = Navigator.baseControl.robotController.getArm().home();
				return url;
					}
				});
				
				Navigator.baseControl.service.start();
		}
		});
    	
    	waist_left.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				int steper = Integer.parseInt(step.getText());
				Navigator.baseControl.alertLabel.setText("Progressing");
				Navigator.baseControl.service.setMytask(new Task<String>() {
					@Override
					protected String call() throws Exception {
						String url  = Navigator.baseControl.robotController.getArm().ajmaWaistAdd(steper);
				return url;
					}
				});
				
				Navigator.baseControl.service.start();
			}
		});
    	
    	waist_right.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				int steper = Integer.parseInt(step.getText());
				Navigator.baseControl.alertLabel.setText("Progressing");
				Navigator.baseControl.service.setMytask(new Task<String>() {
					@Override
					protected String call() throws Exception {
						String url  = Navigator.baseControl.robotController.getArm().ajmaWaistAdd(-steper);
				return url;
					}
				});
				
				Navigator.baseControl.service.start();
			}
		});
    	
    	shoulder_up.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				int steper = Integer.parseInt(step.getText());
				Navigator.baseControl.alertLabel.setText("Progressing");
				Navigator.baseControl.service.setMytask(new Task<String>() {
					@Override
					protected String call() throws Exception {
						String url  = Navigator.baseControl.robotController.getArm().ajmaShoulderAdd(-steper);
				return url;
					}
				});
				
				Navigator.baseControl.service.start();
			}
		});

    	shoulder_down.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				int steper = Integer.parseInt(step.getText());
				Navigator.baseControl.alertLabel.setText("Progressing");
				Navigator.baseControl.service.setMytask(new Task<String>() {
					@Override
					protected String call() throws Exception {
						String url  = Navigator.baseControl.robotController.getArm().ajmaShoulderAdd(steper);
				return url;
					}
				});
				
				Navigator.baseControl.service.start();
			}
		});
    	
    	elb_down.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				int steper = Integer.parseInt(step.getText());
				Navigator.baseControl.alertLabel.setText("Progressing");
				Navigator.baseControl.service.setMytask(new Task<String>() {
					@Override
					protected String call() throws Exception {
						String url  = Navigator.baseControl.robotController.getArm().ajmaElbowAdd(steper);
				return url;
					}
				});
				
				Navigator.baseControl.service.start();
			}
		});

    	elb_up.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				int steper = Integer.parseInt(step.getText());
				Navigator.baseControl.alertLabel.setText("Progressing");
				Navigator.baseControl.service.setMytask(new Task<String>() {
					@Override
					protected String call() throws Exception {
						String url  = Navigator.baseControl.robotController.getArm().ajmaElbowAdd(-steper);
				return url;
					}
				});
				
				Navigator.baseControl.service.start();
			}
		});


    	wrist.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				int steper = Integer.parseInt(wrist_field.getText());
				Navigator.baseControl.alertLabel.setText("Progressing");
				Navigator.baseControl.service.setMytask(new Task<String>() {
					@Override
					protected String call() throws Exception {
						String url  = Navigator.baseControl.robotController.getArm().ajmaWristAdd(steper);
				return url;
					}
				});
				
				Navigator.baseControl.service.start();
			}
		});


    	twist.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				int steper = Integer.parseInt(twist_text.getText());
				Navigator.baseControl.alertLabel.setText("Progressing");
				Navigator.baseControl.service.setMytask(new Task<String>() {
					@Override
					protected String call() throws Exception {
						String url  = Navigator.baseControl.robotController.getArm().ajmaHandAdd(steper);
				return url;
					}
				});
				
				Navigator.baseControl.service.start();
			}
		});



    }
}
