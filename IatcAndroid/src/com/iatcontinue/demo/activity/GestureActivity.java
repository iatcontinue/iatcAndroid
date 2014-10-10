package com.iatcontinue.demo.activity;

import com.iatcontinue.demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnGenericMotionListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class GestureActivity extends Activity {
	private final String TAG =GestureActivity.class.getName();

	private Button button1;
	private GestureDetector gd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guesture_layout);

		gd = new GestureDetector(this, new TestOnGestureListener());

		button1 = (Button) findViewById(R.id.button1);
		button1.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.i(TAG, "onTouch,event:"+event.getAction());
				return false;
			}
		});
	}
	
	

	class TestOnGestureListener extends SimpleOnGestureListener {

		@Override
		public boolean onDoubleTap(MotionEvent e) {
			Log.i(TAG, "onDoubleTap");
			return super.onDoubleTap(e);
		}
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			Log.i(TAG, "onFling");
			return super.onFling(e1, e2, velocityX, velocityY);
		}
		
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			Log.i(TAG, "onScroll");
			return super.onScroll(e1, e2, distanceX, distanceY);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		gd.onTouchEvent(event);
		return true;
	}
}
