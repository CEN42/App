package com.ezsnips.ez_snips;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class view_reservation extends ActionBarActivity {

    /*
        The 3 variable. You can pull these from the Database
        I set them to garbage so it won't crash
     */
    String nameOfStylist = "Name";
    String workToBeDone = "Work";
    String Date = "Some Date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reservation);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_reservation, menu);
        View.OnClickListener listnr8;
        listnr8 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(view_reservation.this, company_page.class);
                startActivity(j);
                //finish();
            }
        };
        Button b8 =(Button) findViewById(R.id.go_back);
        b8.setOnClickListener(listnr8);
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

        return super.onOptionsItemSelected(item);
    }
    public void OnButtonClickChangeTextOfTextView(View view){
        TextView name = (TextView)findViewById(R.id.stylist_name_view);
        name.setText("Name of Stylist: " + nameOfStylist);
        TextView  work= (TextView)findViewById(R.id.work_view);
        work.setText("Work: " + workToBeDone);
        TextView appt = (TextView)findViewById(R.id.date_view);
        appt.setText("Date: " + Date);
    }
}
