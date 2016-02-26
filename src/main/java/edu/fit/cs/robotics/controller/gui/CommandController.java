package edu.fit.cs.robotics.controller.gui;

import java.io.IOException;

import edu.fit.cs.robotics.constants.FXMLConstants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class CommandController {


	@FXML
    private Button left;

    @FXML
    private Button right;

    @FXML
    private StackPane centerPane;
    
        
    @FXML
    void initialize()
    {
    	Navigator.commandControl = this;
    	
    	initBasic();
    	
    	initButtons();
    }
    
    void initBasic()
    {
       	
    }
    
    void initButtons()
    {
    	
    	left.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
			
				Navigator.LoadNode(centerPane.getChildren(), FXMLConstants.MOVETO_FXML);

		
			}
		} );
    	
    	right.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				Navigator.LoadNode(centerPane.getChildren(), FXMLConstants.AJMA_FXML);
	
			}
		});
    	
    }
    
    
  }
