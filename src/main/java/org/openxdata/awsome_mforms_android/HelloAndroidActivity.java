package org.openxdata.awsome_mforms_android;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.openxdata.mforms.model.FormData;
import org.openxdata.mforms.model.QuestionData;
import org.openxdata.mforms.server.XForm;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HelloAndroidActivity extends ListActivity {

	// private ListView listView;

	/**
	 * Called when the activity is first created.
	 * 
	 * @param savedInstanceState
	 *            If the activity is being re-initialized after previously being shut down then this Bundle contains the
	 *            data it most recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);
		// TextView tv = new TextView(this);
		// setContentView(renderForm(formdata));

		FormData formdata = importData(R.raw.sample2, R.raw.sampledata2);
		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, renderForm(formdata)));

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// When clicked, show a toast with the TextView text
				Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});
	}

	private List<String> renderForm(FormData formdata) {
		System.out.println("RENDER");

		ArrayList<String> list = new ArrayList<String>();
		int i = 1;
		QuestionData qd = null;
		while ((qd = formdata.getQuestion((short) i)) != null) {
			System.out.println("getting id: " + i);
			list.add(qd.toString());
			i++;
		}

		return list;
	}

	private FormData importData(int formDef, int formData) {
		String dataXml = null;
		String xform = null;

		try {
			dataXml = readTextFile(formData);
			xform = readTextFile(formDef);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return XForm.fromXMLToFormData(xform, dataXml);
	}

	private String readTextFile(int fileIdentifier) throws FileNotFoundException, IOException {
		InputStream in = getResources().openRawResource(fileIdentifier);

		InputStreamReader input = new InputStreamReader(in);
		BufferedReader reader = new BufferedReader(input);

		String data = "";
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				data += line;
			}
		} catch (IOException e) {
			Toast.makeText(getApplicationContext(), e.toString() + e.getMessage(), Toast.LENGTH_SHORT).show();
			in.close();
		}
		in.close();
		return data;
	}

	static final String[] COUNTRIES = new String[] { "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra",
			"Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia",
			"Austria", "Azerbaijan", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin",
			"Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Bouvet Island", "Brazil",
			"British Indian Ocean Territory", "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso",
			"Burundi", "Cote d'Ivoire", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands",
			"Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands",
			"Colombia", "Comoros", "Congo", "Cook Islands", "Costa Rica", "Croatia", "Cuba", "Cyprus",
			"Czech Republic", "Democratic Republic of the Congo", "Denmark", "Djibouti", "Dominica",
			"Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea",
			"Estonia", "Ethiopia", "Faeroe Islands", "Falkland Islands", "Fiji", "Finland",
			"Former Yugoslav Republic of Macedonia", "France", "French Guiana", "French Polynesia",
			"French Southern Territories", "Gabon", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland",
			"Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti",
			"Heard Island and McDonald Islands", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia",
			"Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya",
			"Kiribati", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya",
			"Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Madagascar", "Malawi", "Malaysia", "Maldives",
			"Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico",
			"Micronesia", "Moldova", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia",
			"Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua",
			"Niger", "Nigeria", "Niue", "Norfolk Island", "North Korea", "Northern Marianas", "Norway", "Oman",
			"Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn Islands",
			"Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russia", "Rwanda",
			"Sqo Tome and Principe", "Saint Helena", "Saint Kitts and Nevis", "Saint Lucia",
			"Saint Pierre and Miquelon", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Saudi Arabia",
			"Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia",
			"South Africa", "South Georgia and the South Sandwich Islands", "South Korea", "Spain", "Sri Lanka",
			"Sudan", "Suriname", "Svalbard and Jan Mayen", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan",
			"Tajikistan", "Tanzania", "Thailand", "The Bahamas", "The Gambia", "Togo", "Tokelau", "Tonga",
			"Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu",
			"Virgin Islands", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States",
			"United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela",
			"Vietnam", "Wallis and Futuna", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe" };
}