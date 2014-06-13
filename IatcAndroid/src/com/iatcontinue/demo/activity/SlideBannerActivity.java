package com.iatcontinue.demo.activity;

import com.iatcontinue.demo.R;
import com.iatcontinue.demo.view.SlideBanner;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;

public class SlideBannerActivity extends Activity {

	private SlideBanner banner ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		banner = new SlideBanner(this);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,300);
		banner.setLayoutParams(params);
		setContentView(banner);
		
		ImageView iv = new ImageView(this);
		iv.setImageResource(R.drawable.bg);
		banner.addBanner(iv);
		
		ImageView iv1 = new ImageView(this);
		iv1.setImageResource(R.drawable.bg1);
		banner.addBanner(iv1);
	}
	
	
}
