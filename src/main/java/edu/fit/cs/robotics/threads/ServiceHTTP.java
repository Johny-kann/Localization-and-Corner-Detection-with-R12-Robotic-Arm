package edu.fit.cs.robotics.threads;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.util.Callback;

public class ServiceHTTP<T, R> extends Service<T>{

	private Callback<R, T> caller;
	
	private R param;
	
	public void setCallBack(Callback<R, T> fnc, R parameter)
	{
		caller = fnc;
		param = parameter;
	}
	
	@Override
	protected Task<T> createTask() {

		return new Task<T>() {

			@Override
			protected T call() throws Exception {
				
				return caller.call(param);
			}
		};
	}

}
