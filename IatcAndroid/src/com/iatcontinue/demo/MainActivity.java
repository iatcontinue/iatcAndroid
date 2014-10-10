package com.iatcontinue.demo;

import com.iatcontinue.demo.activity.FileManagerActivity;
import com.iatcontinue.demo.activity.GestureActivity;
import com.iatcontinue.demo.activity.MeidaActivity;
import com.iatcontinue.demo.activity.ServiceActivity;
import com.iatcontinue.demo.activity.SlideBannerActivity;

import android.media.MediaActionSound;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements OnClickListener{

	private LinearLayout buttons_layout;
	
	private final int ID_SLIDE_BTN = 123;
	private final int ID_SERVICE_BTN = 124;
	private final int ID_FILE_BTN = 125;
	private final int ID_MEDIA_BTN = 126;
	private final int ID_GESTURE_BTN = 127;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initUI();
	}
	
	private void initUI(){
		buttons_layout = (LinearLayout) findViewById(R.id.buttons_layout);
		Button slide = new Button(this);
		slide.setId(ID_SLIDE_BTN);
		slide.setOnClickListener(this);
		slide.setText("SliderBanner");
		buttons_layout.addView(slide);
		Button serviceBtn = new Button(this);
		serviceBtn.setId(ID_SERVICE_BTN);
		serviceBtn.setOnClickListener(this);
		serviceBtn.setText("serviceBtn");
		buttons_layout.addView(serviceBtn);
		
		
		Button fileBtn = new Button(this);
		fileBtn.setId(ID_FILE_BTN);
		fileBtn.setOnClickListener(this);
		fileBtn.setText("fileBtn");
		buttons_layout.addView(fileBtn);
		
		Button mediaBtn = new Button(this);
		mediaBtn.setId(ID_MEDIA_BTN);
		mediaBtn.setOnClickListener(this);
		mediaBtn.setText("mediaBtn");
		buttons_layout.addView(mediaBtn);
		
		Button guestureBtn = new Button(this);
		guestureBtn.setId(ID_GESTURE_BTN);
		guestureBtn.setOnClickListener(this);
		guestureBtn.setText("guestureBtn");
		buttons_layout.addView(guestureBtn);
	}
	
	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case ID_SLIDE_BTN:
			intent.setClass(MainActivity.this, SlideBannerActivity.class);
			break;
		case ID_SERVICE_BTN:
			intent.setClass(MainActivity.this, ServiceActivity.class);
			break;
		case ID_FILE_BTN:
			intent.setClass(MainActivity.this, FileManagerActivity.class);
			break;
		case ID_MEDIA_BTN:
			intent.setClass(MainActivity.this, MeidaActivity.class);
			break;
		case ID_GESTURE_BTN:
			intent.setClass(MainActivity.this, GestureActivity.class);
			break;
		default:
			intent.setClass(MainActivity.this, MainActivity.class);
			break;
		}
		
		startActivity(intent);
	}


}
