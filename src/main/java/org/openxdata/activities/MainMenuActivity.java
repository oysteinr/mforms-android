package org.openxdata.activities;

import org.openxdata.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainMenuActivity extends Activity {

	private Button syncBtn;
	private Button tasksBtn;
	private Button prefsBtn;
	private Button studiesBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);

		syncBtn = (Button) findViewById(R.id.syncBtn);
		tasksBtn = (Button) findViewById(R.id.tasksBtn);
		prefsBtn = (Button) findViewById(R.id.prefBtn);
		studiesBtn = (Button) findViewById(R.id.studiesBtn);

		syncBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				launchSyncActivity();
			}
		});

		tasksBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				launchTaskActivity();
			}
		});

		prefsBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				launchPreferencesActivity();
			}
		});

		studiesBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				launchStudiesActivity();
			}
		});
	}

	protected void launchSyncActivity() {

		Intent intent = new Intent(this, SynchronizeActivity.class);
		startActivity(intent);
	}

	protected void launchStudiesActivity() {
		Toast.makeText(this, "Studies", Toast.LENGTH_LONG).show();

	}

	protected void launchPreferencesActivity() {
		Toast.makeText(this, "Preferences", Toast.LENGTH_LONG).show();

	}

	protected void launchTaskActivity() {
		Toast.makeText(this, "Tasks", Toast.LENGTH_LONG).show();

	}
}
