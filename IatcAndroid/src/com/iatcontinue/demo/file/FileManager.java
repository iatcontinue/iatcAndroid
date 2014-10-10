package com.iatcontinue.demo.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.os.Environment;
import android.util.Log;

public class FileManager {

	public static final String ROOT_DIR = Environment
			.getExternalStorageDirectory().getAbsolutePath();

	public FileManager() {
		Log.i("FileManager", "ROOT_DIR:" + ROOT_DIR);
	}

	/**
	 * ��ȡһ���ļ�Ŀ¼�µ������ļ�
	 * 
	 * @param currentDir
	 * @return
	 */
	public List<String> getFiles(String currentDir) {
		Log.i("FileManager", "getFiles:" + currentDir);
		List<String> files = new ArrayList<String>();
		File currentFile = new File(currentDir);
		if (currentFile.isDirectory()) {
			File[] fs = currentFile.listFiles() ;
			for (File f : fs) {
				files.add(f.getAbsolutePath());
			}
		}
		return files;
	}

}
