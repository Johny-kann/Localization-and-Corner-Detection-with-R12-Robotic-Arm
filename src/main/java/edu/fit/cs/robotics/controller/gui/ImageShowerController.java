package edu.fit.cs.robotics.controller.gui;

import edu.fit.cs.robotics.BO.RobotLogics;
import edu.fit.cs.robotics.clients.StockClients;
import edu.fit.cs.robotics.constants.Constants;
import edu.fit.cs.robotics.controller.RoboticsOperator;
import edu.fit.cs.robotics.threads.ImageService;
import edu.fit.cs.robotics.threads.RefreshService;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageShowerController {

	@FXML
    private ListView<ImageView> imageList;
	
	@FXML
    private Button down;
	
	 @FXML
	 private Button refresh;
	 
	 @FXML
	    private Label status;
	 
	 @FXML
	    private TextField fileLoc;
	 
	 @FXML
	    private Button save;


	 
	
	private ImageService serviceImage;
	
	private RefreshService refreshImage;
	
	
	@FXML
	void initialize()
	{
		Navigator.imageControl = this;
		
		serviceImage = new ImageService();
		// TODO Auto-generated method stub
		refreshImage = new RefreshService();
		
		initActions();
	}
	
	void initActions()
	{
		
		status.textProperty().bind(refreshImage.messageProperty());
		
		save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
					
//				new RoboticsOperator().printPhotos(fileLoc.getText(), imageList.getItems().size());
	
				String file = fileLoc.getText();
				for(int i=0;i<imageList.getItems().size();i++)
				{
				new RobotLogics().writeImage(i, imageList.getItems().get(i).getImage(), file);
				}
				
			}
		});
		
		
		
		down.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				
			serviceImage.setUrl(
							Constants.IMAGE_URL+
						(imageList.getItems().size()+1)+".bmp"
						);
				
				serviceImage.start();
					
			}
		});
		
		refresh.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
			
				refreshImage.num = imageList.getItems().size();
				
				refreshImage.url = Constants.IMAGE_URL;
				
				refreshImage.start();
								
			}
		});
		
		
		serviceImage.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

			@Override
			public void handle(WorkerStateEvent event) {
				// TODO Auto-generated method stub
				Navigator.imageControl.addImages(serviceImage.getValue());
				
				serviceImage.reset();
				
			}
		});
		
		refreshImage.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

			@Override
			public void handle(WorkerStateEvent event) {
				// TODO Auto-generated method stub
				//Navigator.imageControl.addImages(serviceImage.getValue());
				Navigator.imageControl.addImages(refreshImage.getValue());
				
				refreshImage.reset();
				
			}
		});

	}

	public void addImages(ObservableList<ImageView> list)
	{
		imageList.getItems().setAll(list);
	}
	
	public void addImages(Image image)
	{
//		System.out.println("Image "+image);
		ImageView temp = new ImageView(image);
		temp.setFitWidth(Constants.IMAGE_VIEW_WIDTH);
		temp.setPreserveRatio(true);
		imageList.getItems().add(temp);
		
	}
	
	public Image getImageAt(int num)
	{
		return imageList.getItems().get(num).getImage(); 
	}

}
