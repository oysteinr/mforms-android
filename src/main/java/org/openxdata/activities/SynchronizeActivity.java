package org.openxdata.activities;

import org.openxdata.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class SynchronizeActivity extends Activity {

	private int mProgressStatus = 0;
	private Handler mHandler = new Handler();
	private ProgressBar mProgress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sync);
		
		downloadUsers();

	}

	private void downloadUsers() {
		
	}	

}
