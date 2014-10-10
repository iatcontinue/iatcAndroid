package com.iatcontinue.demo.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.iatcontinue.demo.file.FileManager;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FileManagerActivity extends ListActivity {

	private ArrayAdapter<String> fileAdapter;
	private List<String> files = new ArrayList<String>();

	private FileManager fileManager = new FileManager();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fileAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, files);
		setListAdapter(fileAdapter);
		init();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		updateFiles(files.get(position));
	}

	private void init() {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				fileAdapter.addAll(fileManager.getFiles(FileManager.ROOT_DIR));
				fileAdapter.notifyDataSetChanged();
			}
		});
	}
	
	public void updateFiles(final String currentDir){
		fileAdapter.clear();
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				fileAdapter.addAll(fileManager.getFiles(currentDir));
				fileAdapter.notifyDataSetChanged();
			}
		});
	}

}
