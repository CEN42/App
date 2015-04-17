package com.ezsnips.ez_snips;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;


public class MainActivity extends Activity {

    EditText etUserName, etPassword;
    Button btnLogin;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialize  the layout components
        context=this;
        etUserName = (EditText) findViewById(R.id.username_login);
        etPassword = (EditText) findViewById(R.id.password_login);

        //Sign-up class
        View.OnClickListener listnr;
        listnr = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this, CreateUserActivity.class);
                startActivity(i);
                finish();
            }
        };

        Button btn =(Button) findViewById(R.id.btn);
        btn.setOnClickListener(listnr);


        //lost password class
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        View.OnClickListener listnr2;
        listnr2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j= new Intent(MainActivity.this, get_password.class);
                startActivity(j);
                finish();
            }
        };
        Button lostpassword =(Button) findViewById(R.id.lostPassword);
        lostpassword.setOnClickListener(listnr2);


        //Sign_in
        View.OnClickListener listnr3;
        listnr3 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=etUserName.getText().toString();
                String password=etPassword.getText().toString();

                // Execute the AsyncLogin class
                new AsyncLogin().execute(email,password);

              //  Intent k= new Intent(MainActivity.this, company_page.class);
              //  startActivity(k);
              //  finish();


            }
        };
        Button sign_in =(Button) findViewById(R.id.sign_in);
        sign_in.setOnClickListener(listnr3);

    }
    protected class AsyncLogin extends AsyncTask<String, JSONObject, Boolean> {

        String Email=null;
        @Override
        protected Boolean doInBackground(String... params) {

            RestAPI api = new RestAPI();
            boolean userAuth = false;
            try {


                // Call the User Authentication Method in API
                JSONObject jsonObj = api.UserAuthentication(params[0],
                        params[1]);

                //Parse the JSON Object to boolean
                JSONParser parser = new JSONParser();
                userAuth = parser.parseUserAuth(jsonObj);
                Email=params[0];
            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncLogin", e.getMessage());

            }
            return userAuth;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            Toast.makeText(context, "Please Wait...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            // TODO Auto-generated method stub


            //Check user validity
            if (result) {
                Intent i = new Intent(MainActivity.this,
                        company_page.class);
                i.putExtra("email",Email);
                startActivity(i);
            }
            else
            {
                Toast.makeText(context, "Not valid username/password ",Toast.LENGTH_SHORT).show();
            }

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

}