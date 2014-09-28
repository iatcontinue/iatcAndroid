package com.iatcontinue.demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

public class MessengerService extends Service {

	public static final int MSG_HELLO = 1;

	class IncomingHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_HELLO:
				Toast.makeText(getApplicationContext(), "hello",
						Toast.LENGTH_SHORT).show();
				break;

			default:
				super.handleMessage(msg);
				break;
			}

		}
	}

	private final Messenger mMessenger = new Messenger(new IncomingHandler());

	@Override
	public IBinder onBind(Intent intent) {
		Toast.makeText(getApplicationContext(), "onbind", Toast.LENGTH_SHORT)
				.show();
		return mMessenger.getBinder();
	}

}
