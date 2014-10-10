package com.iatcontinue.demo.activity;

import java.io.IOException;

import com.iatcontinue.demo.R;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.VideoView;

public class MeidaActivity extends Activity implements Callback,
		OnCompletionListener, OnErrorListener, OnVideoSizeChangedListener,
		OnInfoListener, OnPreparedListener, OnSeekCompleteListener ,MediaPlayerControl{
	private VideoView vv;
	private SurfaceView sv;
	private SurfaceHolder svHolder;
	private MediaPlayer player;
	private int w, h;
	private Display display;
	private MediaController controller ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.media_layout);
		vv = (VideoView) findViewById(R.id.videoView1);
		String path = Environment.getExternalStorageDirectory().getPath()
				.toString()
				+ "/test.mp4";
		Uri uri = Uri.parse(path);
		vv.setMediaController(new MediaController(this));
		vv.setVideoURI(uri);
		vv.requestFocus();

		sv = (SurfaceView) findViewById(R.id.surfaceView1);
		svHolder = sv.getHolder();
		svHolder.addCallback(this);
		svHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		player = new MediaPlayer();
		player.setOnCompletionListener(this);
		player.setOnErrorListener(this);
		player.setOnInfoListener(this);
		player.setOnPreparedListener(this);
		player.setOnSeekCompleteListener(this);
		player.setOnVideoSizeChangedListener(this);
		try {
			player.setDataSource(path);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		display = getWindowManager().getDefaultDisplay();
//		controller = (MediaController) findViewById(R.id.mediaController1);
		controller = new MediaController(this);
		  View anchorView = sv.getParent() instanceof View ?
                  (View)sv.getParent() : sv;
		controller.setAnchorView(anchorView);
		controller.setMediaPlayer(this);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		player.setDisplay(svHolder);
		player.prepareAsync();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {

	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSeekComplete(MediaPlayer mp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		player.start();
		controller.setEnabled(true);
		controller.show();
	}

	@Override
	public boolean onInfo(MediaPlayer mp, int what, int extra) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDuration() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCurrentPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void seekTo(int pos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isPlaying() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getBufferPercentage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canPause() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canSeekBackward() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canSeekForward() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getAudioSessionId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
