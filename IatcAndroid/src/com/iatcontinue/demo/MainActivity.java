package com.iatcontinue.demo;

import com.iatcontinue.demo.activity.ServiceActivity;
import com.iatcontinue.demo.activity.SlideBannerActivity;

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
		default:
			intent.setClass(MainActivity.this, MainActivity.class);
			break;
		}
		
		startActivity(intent);
	}


}
