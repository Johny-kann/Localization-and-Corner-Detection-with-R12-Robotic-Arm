package edu.fit.cs.robotics.controller.gui;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.concurrent.Task;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MoveToController {


	@FXML
    private Button left;

    @FXML
    private Button back;

    @FXML
    private TextField step;

    @FXML
    private Button front;

    @FXML
    private Button right;

    @FXML
    private Button up;

    @FXML
    private Button down;

    @FXML
    private Button twist;

    @FXML
    private Button wrist;

    @FXML
    private Button home;
    
    @FXML
    private TextField wrist_text;

    @FXML
    private TextField twist_text;
    
    @FXML
    void initialize()
    {
    	Navigator.moveControl = this;
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
								
						String url = Navigator.baseControl.robotController.getArm().home();
						return url;
					}
				}
				);
				
				
				Navigator.baseControl.service.start();
							
		}
		});
    	
    	
    	up.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				int steper = Integer.parseInt(step.getText());
				Navigator.baseControl.alertLabel.setText("Progressing");
				
				Navigator.baseControl.service.setMytask(new Task<String>() {
					@Override
					protected String call() throws Exception {
							
					String url = Navigator.baseControl.robotController.getArm().addZ(steper);
					return url;
					}
				});
				
				Navigator.baseControl.service.start();
			}
		});
		
		
		down.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
								
				
				int steper = Integer.parseInt(step.getText());
				Navigator.baseControl.alertLabel.setText("Progressing");
				Navigator.baseControl.service.setMytask(new Task<String>() {
					@Override
					protected String call() throws Exception {
						
				String url =Navigator.baseControl.robotController.getArm().addZ(-steper);
				return url;
					}
				});
			
				Navigator.baseControl.service.start();
		}
		});
		
		left.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				int steper = Integer.parseInt(step.getText());
				Navigator.baseControl.alertLabel.setText("Progressing");
			
				Navigator.baseControl.service.setMytask(new Task<String>() {
					@Override
					protected String call() throws Exception {
				String url = Navigator.baseControl.robotController.getArm().addY(-steper);;
				return url;
					}
				});
				
				Navigator.baseControl.service.start();
			}
		});
		
		right.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				int steper = Integer.parseInt(step.getText());
				Navigator.baseControl.alertLabel.setText("Progressing");
				Navigator.baseControl.service.setMytask(new Task<String>() {
					@Override
					protected String call() throws Exception {
				String url = Navigator.baseControl.robotController.getArm().addY(steper);
				return url;
					}
				});
				Navigator.baseControl.service.start();	
			}
		});
		
		front.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				int steper = Integer.parseInt(step.getText());
				Navigator.baseControl.alertLabel.setText("Progressing");
				Navigator.baseControl.service.setMytask(new Task<String>() {
					@Override
					protected String call() throws Exception {
				String url = Navigator.baseControl.robotController.getArm().addX(steper);
				return url;
					}
				});
				
				Navigator.baseControl.service.start();
			}
		});
		
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				int steper = Integer.parseInt(step.getText());
				Navigator.baseControl.alertLabel.setText("Progressing");
				Navigator.baseControl.service.setMytask(new Task<String>() {
					@Override
					protected String call() throws Exception {
					String url = Navigator.baseControl.robotController.getArm().addX(-steper);
				return url;
					}
				});
			
				Navigator.baseControl.service.start();
			}
		});
		
		wrist.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				int steper = Integer.parseInt(wrist_text.getText());
				Navigator.baseControl.alertLabel.setText("Progressing");
				Navigator.baseControl.service.setMytask(new Task<String>() {
					@Override
					protected String call() throws Exception {
						
				String url = Navigator.baseControl.robotController.getArm().addWrist(steper);
				return url;
					}
				});
				
				Navigator.baseControl.service.start();
			}
		});

		twist.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				int steper = Integer.parseInt(twist_text.getText());
				Navigator.baseControl.alertLabel.setText("Progressing");
				Navigator.baseControl.service.setMytask(new Task<String>() {
					@Override
					protected String call() throws Exception {
				String url = Navigator.baseControl.robotController.getArm().addTwist(steper);
				return url;
					}
				});
				
				Navigator.baseControl.service.start();
		}
		});
    	
    	
    }
    
    
    
}
