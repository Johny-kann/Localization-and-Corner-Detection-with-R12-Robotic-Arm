package edu.fit.cs.robotics.threads;

import java.util.ArrayList;
import java.util.List;

import edu.fit.cs.robotics.clients.StockClients;
import edu.fit.cs.robotics.constants.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RefreshService extends Service<ObservableList<ImageView>>{

	public int num;
	
	public String url;
	
	
	
	@Override
	protected Task<ObservableList<ImageView>> createTask() {
		// TODO Auto-generated method stub
		
		return new Task<ObservableList<ImageView>>() {

			@Override
			protected ObservableList<ImageView> call() throws Exception {
				
				List<ImageView> list = new ArrayList<ImageView>();
				
				StockClients client = new StockClients();
				
				System.out.println(num);
				
				for(int i=0;i<num;i++)
				{
					updateMessage("Downloaded "+i+" "+i*100/num+"%");

					ImageView temp = new ImageView(client.getImage(url+(i+1)+".bmp"));
					temp.setFitWidth(Constants.IMAGE_VIEW_WIDTH);
					temp.setPreserveRatio(true);
					
					list.add(temp);
				}
				ObservableList<ImageView> images = FXCollections.observableArrayList(list);
				
				return images;
			}
		};
	}

}
