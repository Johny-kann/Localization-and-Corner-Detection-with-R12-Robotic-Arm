package edu.fit.cs.robotics.threads;

import edu.fit.cs.robotics.clients.StockClients;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.image.Image;

public class ImageService extends Service<Image> {

	private String url;
	
	public void setUrl(String url)
	{
		this.url = url;
	}
	
	@Override
	protected Task<Image> createTask() {
		// TODO Auto-generated method stub
		
		StockClients client = new StockClients();
		
		return new Task<Image>(){

			@Override
			protected Image call() throws Exception {
				// TODO Auto-generated method stub
				return client.getImage(url);
			}
			
		};
		
	}

}
