package org.openxdata.activities;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.openxdata.R;
import org.openxdata.mforms.model.StudyDef;
import org.openxdata.transport.HttpTransport;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SynchronizeActivity extends Activity {

	List<StudyDef> studies;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sync);

		studies = downloadStudies();
	}

	private List<StudyDef> downloadStudies() {

		List<StudyDef> data = null;

		Log.i("Synchronize Activity", "Downloading Studies");
		HttpTransport transport = new HttpTransport("http://10.0.2.2:8889/mpsubmit");

		try {
			data = transport.getData();
		} catch (ClientProtocolException e) {
			Log.i("Synchronize Activity", "ClientProtocolException: " + e.getMessage());
		} catch (IOException e) {
			Log.i("Synchronize Activity", "IOException: " + e.getMessage());
		} catch (InstantiationException e) {
			Log.i("Synchronize Activity", "InstantiationException: " + e.getMessage());
		} catch (IllegalAccessException e) {
			Log.i("Synchronize Activity", "IllegalAccessException: " + e.getMessage());
		}

		return data;
	}
}
