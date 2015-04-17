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


public class reservation extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    int day;
    int month;
    int year;
    String theName;
    String spinner_select;
    private TextView selection;
    private TextView time;
    private TextView cost;
    private TextView name_dis;


    //Provided Services
    private static final String[] items={"Wash & Cut", "Cut", "Bang Cut", "Child (<10)", "Teenagers",
    "Ultimate Treatment", "Moi Moi Mask", "Hair First Aid", "Wash & Blow", "Flat Iron", "Curling Iron",
    "UPDO", "Bridal UPDO", "Shortest", "Short", "Medium", "Long", "Additional Flat Iron", "Sauna",
    "Microderm", "Sweedish", "Deep tissue", "Relax Concoction"};

    //Prices of the service
    private static final String[] prices = {"$29", "$25", "$8", "$16.95", "$20.95", "$35", "$30",
    "$50","$25-$50", "$15", "$25", "$65", "$95", "$45", "$50", "$55", "$60", "$5", "$40-$49",
    "$99-$159", "$85", "$80", "$95"};

    //Name of the workers
    public static final String[] names = {"Name 1", "Name 2", "Name 3"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        Bundle date = getIntent().getExtras();
        day = date.getInt("day");
        month = date.getInt("month");
        year = date.getInt("year");
        theName = date.getString("name");

        selection=(TextView)findViewById(R.id.selection);
        time = (TextView)findViewById(R.id.time);
        cost = (TextView)findViewById(R.id.cost);
        name_dis = (TextView)findViewById(R.id.name_display);




        /*
        Here, we can have an IF statement. First, we would need to pull which stylist will be doing
        the work
        Only then we can pull their times.

         */
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);


//      Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//      Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        //When confirm has been hit
        View.OnClickListener listnr7;
        listnr7 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(reservation.this, company_page.class);
                startActivity(j);
                finish();   //We don't want them to press back
            }
        };
        Button b7 =(Button) findViewById(R.id.workToBeDone);
        b7.setOnClickListener(listnr7);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reservation, menu);



        return true;
    }

    public void onItemSelected(AdapterView<?> parent,
                               View v, int position, long id) {

        String info_name = "Stylist: " + theName;
        String info = "Date Chosen: " + month + "/" + day + "/" + year;
        String info2 = "Work to be Done: " + items[position];
        String info3 = "Estimate: " + prices[position];
        selection.setText(info2);
        time.setText(info);
        cost.setText(info3);
        name_dis.setText(info_name);
        Toast newToast = Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT);
        newToast.show();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        selection.setText("");
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

            Intent i= new Intent(reservation.this, MainActivity.class);
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
