package com.iatcontinue.demo.activity;

import com.iatcontinue.demo.R;
import com.iatcontinue.demo.db.DatabaseHelp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DatabaseActivity extends Activity implements OnClickListener{

	private Button create_btn ;
	DatabaseHelp db ;
	private Button query_btn ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.database_layout);
		create_btn = (Button) findViewById(R.id.create_btn);
		create_btn.setOnClickListener(this);
		db = new DatabaseHelp(this);
		
		query_btn = (Button) findViewById(R.id.query_btn);
		query_btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.create_btn:
			db.insert();
			break;
		case R.id.query_btn:
			db.query();
			break;
		}
	}
}
