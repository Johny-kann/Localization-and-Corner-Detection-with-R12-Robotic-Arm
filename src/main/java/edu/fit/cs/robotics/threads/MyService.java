package edu.fit.cs.robotics.threads;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class MyService extends Service<String>{

	@Override
	protected Task<String> createTask() { 
		// TODO Auto-generated method stub
		return new Task<String>() {

			@Override
			protected String call() throws Exception {
				
				this.updateMessage("Running Ba");
				for(int i=0;i<500;i++)
				{
					Thread.sleep(10);
					this.updateMessage(i+"");
				}
				
				System.out.println("Loop Done");
				this.updateMessage("Done");
				return "Done";
			}
			
		};
	}

	
}
