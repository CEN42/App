package com.ezsnips.ez_snips;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class CreateUserActivity extends Activity {

	EditText etfirstName, etLastName, etPassword,etEmail, etPNumber;
	Button btnCreateUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_user);
		// Show the Up button in the action bar.
		setupActionBar();

		etfirstName =(EditText) findViewById(R.id.et_fisrtname);
		etLastName = (EditText) findViewById(R.id.et_lastname);
		etPassword = (EditText) findViewById(R.id.et_cu_password);
        etEmail = (EditText) findViewById(R.id.et_cu_email);
        etPNumber = (EditText) findViewById(R.id.et_cu_phonenumber);
		btnCreateUser=(Button) findViewById(R.id.btn_createuser);

		btnCreateUser.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String firstname, lastname, password, email;
                Double  phonenumber;

				firstname = etfirstName.getText().toString();
				lastname = etLastName.getText().toString();
                password = etPassword.getText().toString();
                email = etEmail.getText().toString();
                phonenumber = Double.parseDouble(etPNumber.getText().toString());

                CustomersTable customers = new CustomersTable(firstname,
						lastname, email, password, phonenumber);
				
				
				new AsyncCreateUser().execute(customers);

			}
		});

	}

	protected class AsyncCreateUser extends
			AsyncTask<CustomersTable, Void, Void> {

		@Override
		protected Void doInBackground(CustomersTable... params) {

			RestAPI api = new RestAPI();
			try {

				api.CreateNewAccount(params[0].getFirstName(),
						params[0].getLastName(), params[0].getEmail(),
						params[0].getPassword(), params[0].getPhonenumber());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.d("AsyncCreateUser", e.getMessage());

			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			Intent i = new Intent(CreateUserActivity.this, LoginActivity.class);
			startActivity(i);
		}

	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.KITKAT)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_user, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
