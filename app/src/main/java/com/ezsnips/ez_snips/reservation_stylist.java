package com.ezsnips.ez_snips;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class reservation_stylist extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    //Extract Bundle
    int day;
    int month;
    int year;
    private TextView expl;
    Bundle date;

    Bundle stuff = new Bundle();


    //Name of the workers
    public static final String[] names = {"Betty", "James", "Jessica"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_stylist);
        date = getIntent().getExtras();
        expl = (TextView)findViewById(R.id.example);

        Spinner spinnerOfName = (Spinner) findViewById(R.id.spinner_name);

        spinnerOfName.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapterOfName = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, names);


//      Specify the layout to use when the list of choices appears
        adapterOfName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//      Apply the adapter to the spinner
        spinnerOfName.setAdapter(adapterOfName);



        //Button to the next activity page (Actual reservation)
        View.OnClickListener listnr5;
        listnr5 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(reservation_stylist.this, reservation.class);
                j.putExtras(stuff);
                startActivity(j);
                //finish();
            }
        };
        Button b2 =(Button) findViewById(R.id.toNextResPage);
        b2.setOnClickListener(listnr5);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reservation_stylist, menu);


        day = date.getInt("day");
        month = date.getInt("month") + 1;
        year = date.getInt("year");
        return true;
    }


    public void onItemSelected(AdapterView<?> parent,
                               View v, int position, long id) {

        /*
            This is where you grab the workers name.
            If you want to create a global variable that stores that
            name depending on where you make the database calls, that WILL
            work. That's what I do for the most part.
         */
        String name = "Stylist: " + names[position];
        expl.setText(name);

        stuff.putString("name", names[position]);
        stuff.putInt("day", day);
        stuff.putInt("month", month);
        stuff.putInt("year", year);

    }

    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.Log_out){

            Intent i= new Intent(reservation_stylist.this, MainActivity.class);
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
