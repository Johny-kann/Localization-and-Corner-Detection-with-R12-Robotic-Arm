package edu.fit.cs.robotics.clients;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.web.client.RestTemplate;

import edu.fit.cs.robotics.constants.Constants;
import javafx.scene.image.Image;


public class StockClients {
	
	RestTemplate restTemplate;
	ApplicationContext appContext;
	Resource resource;
	
	public StockClients()
	{
		restTemplate = new RestTemplate();

		appContext = 
		    	   new ClassPathXmlApplicationContext();
		//Student student = restTemplate.getForObject("http://localhost:5900/sms/Student/1", Student.class);
		
	}
	
	public String commandRobot(String command)
	{
		
		String status = restTemplate.getForObject(Constants.COMMAND_URL+command, String.class);
		
		return status;
	}
	
	public Image getImage(String ImageUrl)
	{
		Image image = null;// = restTemplate.getForObject(UrlConstants.IMAGE_URL, Image.class);
				
		resource = appContext.getResource(ImageUrl
				//"http://debatedecide.fit.edu/robot/last.bmp"
				);
		    	
		    try{
		     	  InputStream is = resource.getInputStream();
		     	  image = new Image(is);
	
		    }catch(IOException e){
		    		e.printStackTrace();
		    }
		
		return image;
	}
	
		
}
