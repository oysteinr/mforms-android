package org.openxdata.activities;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.openxdata.R;
import org.openxdata.transport.HttpTransport;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

public class SynchronizeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sync);

		downloadUsers();

	}

	private Object[] downloadUsers() {

		Object[] data = null;

		ProgressDialog progressDialog = ProgressDialog.show(this, "Synchronizing", "Fetching Data...");
		Log.i("Synchronize Activity", "Downloading Users");
		HttpTransport transport = new HttpTransport("http://10.0.2.2:8889/mpsubmit");

		try {
			data = transport.getData();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		progressDialog.dismiss();
		return data;
	}
}
