package org.openxdata.transport;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.openxdata.mforms.model.FormDef;
import org.openxdata.mforms.model.RequestHeader;
import org.openxdata.mforms.model.ResponseHeader;
import org.openxdata.mforms.model.StudyDef;
import org.openxdata.mforms.persistent.PersistentHelper;

import android.util.Log;

import com.jcraft.jzlib.ZInputStream;

public class HttpTransport {

	private String serverURL;

	public HttpTransport(String url) {
		this.serverURL = url;
	}

	@SuppressWarnings("static-access")
	public List<StudyDef> getData() throws ClientProtocolException, IOException, InstantiationException,
			IllegalAccessException {

		Log.i("HttpTransport", "Fetching Data");

		List<StudyDef> studies = new ArrayList<StudyDef>();
		RequestHeader requestHeader = new RequestHeader();
		requestHeader.setLocale("en");
		requestHeader.setAction(RequestHeader.ACTION_DOWNLOAD_STUDY_LIST);
		requestHeader.setSerializer("android-proto-1.0-SNAPSHOT");
		requestHeader.setUserName("admin");
		requestHeader.setPassword("admin");

		URL url = new URL(serverURL + "?action=" + RequestHeader.ACTION_DOWNLOAD_STUDY_LIST);

		URLConnection urlConn = url.openConnection();
		HttpURLConnection httpConn = (HttpURLConnection) urlConn;

		httpConn.setRequestMethod("POST");
		httpConn.setRequestProperty("Content-Type", "application/octet-stream");
		httpConn.setRequestProperty("Content-Language", "en-US");
		httpConn.setRequestProperty("charset", "utf-8");
		httpConn.setDoInput(true);
		httpConn.setDoOutput(true);

		DataInputStream in = null;
		DataOutputStream out = new DataOutputStream(httpConn.getOutputStream());

		try {

			requestHeader.write(out);
			int response = httpConn.getResponseCode();
			Log.i("Responsecode:", "" + response);
			in = new DataInputStream(httpConn.getInputStream());
			if (response == HttpURLConnection.HTTP_OK) {

				ZInputStream zis = new ZInputStream(in);
				DataInputStream zdis = new DataInputStream(zis);

				ResponseHeader responseHeader = new ResponseHeader();
				
				responseHeader.read(zdis);
				byte responseStatusCode = ((ResponseHeader) responseHeader).getStatus();
				if (responseStatusCode == ResponseHeader.STATUS_SUCCESS) {

					int numberOfStudies = zdis.readShort();

					for (int i = 0; i < numberOfStudies; i++) {

						StudyDef study = new StudyDef();

						zdis.readInt();

						study.setId(zdis.readInt());
						study.setName(zdis.readUTF());
						study.setVariableName(zdis.readUTF());
						study.setForms(PersistentHelper.readMedium(zdis, FormDef.class));
						
						studies.add(study);
					}
				}
			}

		} catch (IOException e) {
			Log.e("HttpTransport", "writing to stream failed", e);
		} finally {
			out.flush();
			out.close();
			in.close();
		}

		return studies;
	}
}
