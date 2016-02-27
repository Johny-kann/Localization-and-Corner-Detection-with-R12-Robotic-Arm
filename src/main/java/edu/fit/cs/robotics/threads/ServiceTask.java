package edu.fit.cs.robotics.threads;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ServiceTask<V> extends Service<V> {

	private Task<V> mytask;
	
	
	public void setMytask(Task<V> mytask) {
		this.mytask = mytask;
	}


	@Override
	protected Task<V> createTask() {
		// TODO Auto-generated method stub
		return mytask;
	}
	
	@Override
	protected void succeeded() {
	//	System.out.println("Rest");
	    reset();
	}
	
	@Override
	protected void failed()
	{
		reset();
	}
	
	@Override
	protected void cancelled()
	{
		reset();
	}
}
