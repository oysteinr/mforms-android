package org.openxdata.activities;

import org.openxdata.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	protected static final String SUPER_SECRET = "mark";
	private EditText txtUserName;
	private EditText txtPassword;
	private Button btnLogin;
	private Button btnCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		txtUserName = (EditText) this.findViewById(R.id.unameTxt);
		txtPassword = (EditText) this.findViewById(R.id.passTxt);

		btnLogin = (Button) this.findViewById(R.id.loginBtn);
		btnCancel = (Button) this.findViewById(R.id.cancelBtn);

		btnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (txtUserName.getText().toString().equals(SUPER_SECRET)
						&& txtPassword.getText().toString().equals(SUPER_SECRET)) {
					login();
				} else {
					Toast.makeText(LoginActivity.this, "Invalid Login", Toast.LENGTH_LONG).show();
				}
			}
		});

		btnCancel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				login();
			};
		});

	}

	private void login() {
		Intent intent = new Intent(this, MainMenuActivity.class);
		startActivity(intent);

	}

}
