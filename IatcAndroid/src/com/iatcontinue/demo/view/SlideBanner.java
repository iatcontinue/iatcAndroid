package com.iatcontinue.demo.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class SlideBanner extends RelativeLayout {

	private final String TAG = "SlideBanner";

	private RelativeLayout behindLayout;
	private RelativeLayout contentLayout;
	private List<View> banners = new ArrayList<View>();
	private int currentPage = 0;// 当前显示的banner

	private int width = 0;// 屏幕宽度
	private int height = 0;// 屏幕高度

	private LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
			LayoutParams.MATCH_PARENT);

	/**
	 * 是否在移动
	 */
	private boolean isMove = false;

	/**
	 * 移动的状态
	 */
	private MOVE_STATUS moveStatus = MOVE_STATUS.FINISH;

	private enum MOVE_STATUS {
		/**
		 * 往右移动
		 */
		RIGHT,
		/**
		 * 往左移动
		 */
		LEFT,
		/**
		 * 停止移动
		 */
		STOP,
		/**
		 * 移动结束
		 */
		FINISH
	}

	public SlideBanner(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public SlideBanner(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public SlideBanner(Context context) {
		super(context);
		init();
	}

	private void init() {

		WindowManager wm = (WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		width = size.x;
		height = size.y;

		behindLayout = new RelativeLayout(getContext());
		behindLayout.setGravity(Gravity.CENTER);
		behindLayout.setId(123);

		contentLayout = new RelativeLayout(getContext());
		contentLayout.setGravity(Gravity.CENTER);
		contentLayout.setId(124);

		addView(behindLayout, params);
		addView(contentLayout, params);
		setOnTouchListener(onTouchListener);
	}

	/**
	 * 添加banner
	 * 
	 * @param v
	 */
	public void addBanner(View v) {
		// v.setOnTouchListener(onTouchListener);
		if (v != null) {
			banners.add(v);
		}

		initBanners();
	}

	private void initBanners() {
		contentLayout.removeAllViews();
		behindLayout.removeAllViews();

		LayoutParams params = new LayoutParams(
				android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
				android.widget.LinearLayout.LayoutParams.MATCH_PARENT);

		if (banners.size() == 2) {
			currentPage = 0;
			contentLayout.addView(banners.get(0), params);
			behindLayout.addView(banners.get(1), params);
		}

	}

	private OnTouchListener onTouchListener = new OnTouchListener() {
		float down_x = 0;

		@Override
		public boolean onTouch(View paramView, MotionEvent event) {
			// Log.i(TAG, "ontouch,event action:" + event.getAction());

			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				down_x = event.getX();
				break;
			case MotionEvent.ACTION_MOVE:

				if (event.getX() > down_x) {// 往右移动
					moveStatus = MOVE_STATUS.RIGHT;
				} else if (event.getX() < down_x) {// 往左移动
					moveStatus = MOVE_STATUS.LEFT;
				} else {
					moveStatus = MOVE_STATUS.STOP;
				}

				if (!isMove && moveStatus != MOVE_STATUS.STOP) {
					moveStart(moveStatus);
				}

				switch (moveStatus) {
				case LEFT:
					moveToLeft(event, down_x);
					break;
				case RIGHT:
					moveToRight(event, down_x);
					break;
				default:

				}
				isMove = true;
				down_x = event.getX();

				break;
			case MotionEvent.ACTION_UP:
				moveStatus = MOVE_STATUS.STOP;
				isMove = false;
				break;
			}
			return true;
		}
	};

	/**
	 * 移动开始
	 */
	public void moveStart(MOVE_STATUS status) {
		removeAllViews();
		resetBanner();

		switch (status) {
		case RIGHT:
			addView(behindLayout, params);
			addView(contentLayout, params);
			LayoutParams cp = new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
			cp.setMargins(-width, 0, 0, 0);
			contentLayout.setLayoutParams(cp);
			break;
		case LEFT:

			addView(contentLayout, params);
			addView(behindLayout, params);

			LayoutParams cp1 = new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
			
			cp1.setMargins(width, 0, 0, 0);
			contentLayout.setLayoutParams(cp1);
			break;
		default:
			break;
		}
		requestLayout();
	}

	public void resetBanner() {
		contentLayout.removeAllViews();
		behindLayout.removeAllViews();
		behindLayout.addView(banners.get(currentPage));
		if (currentPage + 1 >= banners.size()) {// 最后一页
			contentLayout.addView(banners.get(0), params);
		} else {
			contentLayout.addView(banners.get(currentPage + 1), params);
		}
	}

	/**
	 * 往左移动
	 * 
	 * @param event
	 */
	public void moveToLeft(MotionEvent event, float x) {
		Log.i(TAG, "往左移动");

		// behind 往左移动
		int temp = (int) Math.abs(event.getX() - x);

		int r = behindLayout.getRight();
		int l = behindLayout.getLeft();
		behindLayout.layout(l - temp, behindLayout.getTop(), r - temp,
				behindLayout.getBottom());

		// content 往左移动
		int r1 = contentLayout.getRight();
		Log.d(TAG, "moveToLeft r1:" + r1);
		int l1 = contentLayout.getLeft();
		contentLayout.layout(l1 - temp / 2, contentLayout.getTop(), r1 - temp
				/ 2, contentLayout.getBottom());

	}

	/**
	 * 往右移动
	 * 
	 * @param event
	 */
	public void moveToRight(MotionEvent event, float x) {
		Log.i(TAG, "往右移动");

		// behind 往右移动
		int temp = (int) Math.abs(event.getX() - x);
		int r = behindLayout.getRight();

		int l = behindLayout.getLeft();
		behindLayout.layout(l + temp/2, behindLayout.getTop(), r + temp/2,
				behindLayout.getBottom());

		// content 往右移动
		int r1 = contentLayout.getRight();
		Log.d(TAG, "moveToRight r1:" + r1);
		int l1 = contentLayout.getLeft();
		contentLayout.layout(l1 + temp, contentLayout.getTop(), r1 + temp,
				contentLayout.getBottom());
	}

}
