package com.iatcontinue.demo.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.iatcontinue.demo.R;
import com.iatcontinue.demo.service.LocalService;
import com.iatcontinue.demo.service.LocalService.LocalBinder;
import com.iatcontinue.demo.service.MessengerService;

public class ServiceActivity extends Activity implements OnTouchListener,OnClickListener{

	private final String TAG = "SecondActivity";
	private ActionBar actionBar;
	private Fragment mFragment;
	private GridView mGv;
	private Button sendMsgBtn ;
	private Button randomNumBtn;
	private TextView resultTV ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_);
		actionBar = getActionBar();
		// Messenger msg = new Messenger(target)
		actionBar.show();
		Looper.getMainLooper();
		// MessageQueue
		actionBar.setIcon(R.drawable.back);
		actionBar.setTitle("");
		actionBar.setDisplayHomeAsUpEnabled(true);
		sendMsgBtn = (Button) findViewById(R.id.sendMsgBtn);
		sendMsgBtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				sayHello(v);
			}
		});
		randomNumBtn = (Button) findViewById(R.id.randomNumBtn);
		randomNumBtn.setOnClickListener(this);
		
		resultTV  = (TextView) findViewById(R.id.resultTV);
		
		Spinner spin = (Spinner) findViewById(R.id.spinner1);
		// createFromResouce将返回ArrayAdapter<CharSequence>，具有三个参数：
		// 第一个是conetxt，也就是application的环境，可以设置为this，也可以通过getContext()获取.
		// 第二个参数是从data source中的array ID，也就是我们在strings中设置的ID号；
		// 第三个参数是spinner未展开的UI格式
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.planets_arry,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(adapter);
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
		// mask)
		// Tab tab = actionBar.newTab();
		// tab.setText("是");
		// actionBar.addTab(tab);
		// Tab tab = actionBar.newTab()
		// .setText(R.string.artist)
		// .setTabListener(new TabListener<ArtistFragment>(
		// this, "artist", ArtistFragment.class));
		// actionBar.addTab(tab);
		//
		// tab = actionBar.newTab()
		// .setText(R.string.album)
		// .setTabListener(new TabListener<AlbumFragment>(
		// this, "album", AlbumFragment.class));
		// actionBar.addTab(tab);
		mFragment = new Fragment();
		mFragment.getFragmentManager();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater flater = getMenuInflater();
		flater.inflate(R.menu.second_menu, menu);
		SearchView searchView = (SearchView) menu.findItem(R.id.menu_search)
				.getActionView();
		searchView.setQueryHint("ssdasdsa");
		return super.onCreateOptionsMenu(menu);
		// MenuInflater inflater = getMenuInflater();
		// inflater.inflate(R.menu.action_bar_list_menu, menu);
		//
		// return super.onCreateOptionsMenu(menu);
		// super.onCreateOptionsMenu(menu);
		// //添加菜单项
		// MenuItem add=menu.add(0,0,0,"add");
		// MenuItem del=menu.add(0,0,0,"del");
		// MenuItem save=menu.add(0,0,0,"save");
		// //绑定到ActionBar
		// add.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		// del.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		// save.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		// return true;
	}

	public boolean onTouch(View v, MotionEvent event) {
		return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			break;
		case R.id.menu_search:
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	
	private LocalService mLocalService ;
	
	
	Messenger mService = null;
	boolean mBound;
	private ServiceConnection mConnection = new ServiceConnection() {

		public void onServiceDisconnected(ComponentName name) {
			mService = null;
			mBound = false;
		}

		public void onServiceConnected(ComponentName name, IBinder service) {
			mService = new Messenger(service);
			mBound = true;
		}
	};
	
	private ServiceConnection mLocalConnection  = new ServiceConnection() {
		
		public void onServiceDisconnected(ComponentName name) {
			mLocalService = null;
		}
		
		public void onServiceConnected(ComponentName name, IBinder service) {
			LocalService.LocalBinder lb = (LocalBinder) service;
			mLocalService  =lb.getService();
		}
	};

	public void sayHello(View v) {
		if (!mBound) {
			return;
		}

		Message msg = Message.obtain(null, MessengerService.MSG_HELLO, 0, 0);
		try {
			mService.send(msg);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
//		bindService(new Intent(this, MessengerService.class), mConnection,
//				Context.BIND_AUTO_CREATE);
		bindService(new Intent(this,LocalService.class),mLocalConnection , Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (mBound) {
			unbindService(mConnection);
			mBound = false;
		}
		if(mLocalService !=null){
			unbindService(mLocalConnection);
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.randomNumBtn:
			Log.i(TAG, "mLocalService:"+mLocalService);
			if(mLocalService !=null){
				runOnUiThread(new Runnable() {
					public void run() {
						resultTV.setText(""+mLocalService.getRandomNumber());
					}
				});
			}
			break;

		default:
			break;
		}
	}

}
