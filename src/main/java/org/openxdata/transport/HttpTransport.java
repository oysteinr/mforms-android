package org.openxdata.transport;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.client.ClientProtocolException;
import org.openxdata.mforms.model.RequestHeader;
import org.openxdata.mforms.model.StudyDef;
import org.openxdata.mforms.model.StudyDefList;

import android.util.Log;

public class HttpTransport {

	private String serverURL;

	public HttpTransport(String url) {
		this.serverURL = url;
	}

	public Object[] getData() throws ClientProtocolException, IOException {

		Log.i("HttpTransport", "Fetching Data");

		URL url = new URL(serverURL + "?action=" + RequestHeader.ACTION_DOWNLOAD_STUDIES_FORMS);

		URLConnection urlConn = url.openConnection();
		HttpURLConnection httpConn = (HttpURLConnection) urlConn;

		httpConn.setRequestMethod("POST");
		httpConn.setRequestProperty("Content-Type", "application/octet-stream");
		httpConn.setRequestProperty("Content-Language", "en-US");
		httpConn.setRequestProperty("charset", "utf-8");
		httpConn.setDoInput(true);
		httpConn.setDoOutput(true);

		DataOutputStream out = new DataOutputStream(httpConn.getOutputStream());

		int response = 0;

		try {
			out.writeUTF("admin");
			out.writeUTF("admin");
			out.writeUTF("mforms-proto-2.1");
			// out.writeUTF(RequestHeader.getSerializer());
			out.writeUTF(Byte.toString(RequestHeader.ACTION_DOWNLOAD_STUDIES_FORMS));
			out.writeUTF("en");

			out.flush();
			out.close();

			response = httpConn.getResponseCode();
			Log.i("Responsecode:", "" + response);

		} catch (IOException e) {
			Log.e("HttpTransport", "writing to stream failed", e);
		}

		DataInputStream in = new DataInputStream(httpConn.getInputStream());
		
		// Some magic is needed here for reading the input-stream. Investigate mforms to figure it out
		try {
			int studID = in.readInt();
			Log.i("studID: ", "" + studID);
		} catch (IOException e) {
			Log.e("HttpTransport", "Unable to read studID", e);
		}
		

		in.close();

		if (response == HttpURLConnection.HTTP_OK) {
			Log.i("HttpTransport: ", "disconnecting!");
			httpConn.disconnect();
		}

		return null;
	}
}
