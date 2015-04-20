package com.ezsnips.ez_snips;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Nigel on 4/20/2015.
 */
public class ReservationActivity extends Activity {

    int day,month,year, hour;
    String stylist, spinner_selec, minutes;
    private TextView service, time, cost, stylistName;
    StringBuilder[] times = new StringBuilder[10];
    StringBuilder dtime = new StringBuilder();
    StringBuilder tday = new StringBuilder();


    Bundle date;
    ArrayList<String> data;
    ArrayList<Double> prices;
    ArrayAdapter<String> services_adapter;
    ArrayAdapter<StringBuilder>time_adapter;
    Spinner service_spinner, time_spinner, add_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        Bundle date = getIntent().getExtras();
        day = date.getInt("day");
        month = date.getInt("month");
        year = date.getInt("year");
        stylist = date.getString("stylist");

        //declaring textview displays
        stylistName = (TextView) findViewById(R.id.name_display);
        time = (TextView) findViewById(R.id.time);
        service = (TextView) findViewById(R.id.selection);
        cost = (TextView) findViewById(R.id.cost);

        //declaring spinners
        service_spinner = (Spinner) findViewById(R.id.service);
        time_spinner = (Spinner) findViewById(R.id.stime);
        add_spinner = (Spinner) findViewById(R.id.add_service);

        //setting adapters of services
        services_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);

        //strings and ints for displays
        hour = 8;
        minutes = "00";
        tday.append("am");
        for(int i = 1; i < 10; ++i)
        {


            dtime.append(hour + ":" + minutes + tday + " to " + (hour + 1) + ":" + minutes + tday);
            times[i] = new StringBuilder(dtime);
            dtime.setLength(0);

            ++hour;
            if(hour == 11)
            {
                hour = 1;
                tday.setLength(0);
                tday.append("pm");
            }

        }
        time_adapter = new ArrayAdapter<StringBuilder>(this, android.R.layout.simple_spinner_item,times);


//      Specify the layout to use when the list of choices appears
        services_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//      Apply the adapter to the spinner
        service_spinner.setAdapter(services_adapter);
        time_spinner.setAdapter(time_adapter);


        new AsyncLoadServices().execute();

        service_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String stylist_name = "Stylist: " + stylist;
                String date = "Date Chosen: " + month + "/" + day + "/" + year;
                String job = "Work to be Done: " + service_spinner.getItemAtPosition(position).toString();
                String price = "Estimate: $" + prices.get(position);

                stylistName.setText(stylist_name);
                time.setText(date);
                service.setText(job);
                cost.setText(price);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {service.setText("");  }
        });

        time_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String date = "Date Chosen: " + time_spinner.getItemAtPosition(position).toString() + " at " + month + "/" + day + "/" + year;

                time.setText(date);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { time.setText(""); }
        });


        //buttons for add services and confirmation

        View.OnClickListener view1;
        view1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    add_spinner.setVisibility(View.VISIBLE);
            }
        };

        Button add =(Button) findViewById(R.id.add);
        add.setOnClickListener(view1);

        //once they click confirm it sends the information to the database by calling intent
        View.OnClickListener view2;
        view2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(ReservationActivity.this, company_page.class);
                startActivity(j);
                finish();   //We don't want them to press back
            }
        };

        Button confirm =(Button) findViewById(R.id.confirmation);
        confirm.setOnClickListener(view2);
    }

    protected class AsyncLoadServices extends AsyncTask<Void, JSONObject, ArrayList<ServicesTable>>{
        ArrayList<ServicesTable> servicesTable = null;


        @Override
        protected ArrayList<ServicesTable> doInBackground(Void... params) {
        // TODO Auto-generated method stub

            RestAPI api = new RestAPI();
            try {

                JSONObject jsonObj = api.DisplayServices();

                JSONParser parser = new JSONParser();

                servicesTable = parser.parseServices(jsonObj);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncLoadDeptDetails", e.getMessage());

            }

            return servicesTable;

        }

       // @Override
        protected void onPostExecute(ArrayList<ServicesTable> result) {
            // TODO Auto-generated method stub

            for (int i = 0; i < result.size(); i++) {

                data.add(result.get(i).getServicetitle());
                prices.add(result.get(i).getAmount());
            }

            services_adapter.notifyDataSetChanged();

            //Toast.makeText(context, "Loading Completed", Toast.LENGTH_SHORT).show();
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
        getMenuInflater().inflate(R.menu.menu_reservation, menu);


        day = date.getInt("day");
        month = date.getInt("month") + 1;
        year = date.getInt("year");
        stylist = date.getString("stylist");
        return true;
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

            Intent i= new Intent(ReservationActivity.this, MainActivity.class);
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