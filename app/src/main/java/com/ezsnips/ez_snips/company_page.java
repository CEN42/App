package com.ezsnips.ez_snips;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class company_page extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_page);
        //testing calendar
        //calendar = (calendar)this.getApplicationContext();
        View.OnClickListener listnr4;
        listnr4 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(company_page.this, calendar.class);
                startActivity(i);
                //finish();
            }
        };
        Button butt =(Button) findViewById(R.id.Make_Reservation);
        butt.setOnClickListener(listnr4);



        //View Reservation
        View.OnClickListener listnr9;
        listnr9 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(company_page.this, view_reservation.class);
                startActivity(i);
                //finish();
            }
        };
        Button butt9 =(Button) findViewById(R.id.View_Reservation);
        butt9.setOnClickListener(listnr9);



        /*
            This is going to CANCEL_RES
         */

        View.OnClickListener listnr6;
        listnr6 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(company_page.this, cancel_res.class);
                startActivity(i);
                //finish();
            }
        };
        Button butt6 =(Button) findViewById(R.id.Cancel_Reservation);
        butt6.setOnClickListener(listnr6);


        //Services
        View.OnClickListener listnr7;
        listnr7 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(company_page.this, services.class);
                startActivity(j);
                //finish();
            }
        };
        Button b7 =(Button) findViewById(R.id.Service);
        b7.setOnClickListener(listnr7);

        //Rewards
        View.OnClickListener listnr8;
        listnr8 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(company_page.this, rewards_page.class);
                startActivity(j);
                //finish();
            }
        };
        Button b8 =(Button) findViewById(R.id.Rewards);
        b8.setOnClickListener(listnr8);


        //Map API
        View.OnClickListener listnr5;
        listnr5 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(company_page.this, storeLocation.class);
                startActivity(j);
                //finish();
            }
        };
        Button b2 =(Button) findViewById(R.id.Location);
        b2.setOnClickListener(listnr5);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_company_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.Log_out){

            Intent i= new Intent(company_page.this, MainActivity.class);
            startActivity(i);
            finish();

            Context context = getApplicationContext();
            CharSequence text = "Logged out!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
