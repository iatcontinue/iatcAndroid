package com.iatcontinue.demo.service;

import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class LocalService extends Service{

	private final IBinder mBinder = new LocalBinder();
	private final Random mGenerator = new Random();
	
	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}
	
	public class LocalBinder extends Binder{
		public LocalService getService(){
			return LocalService.this;
		}
	}
	
	public int getRandomNumber(){
		return mGenerator.nextInt(100);
	}
}
