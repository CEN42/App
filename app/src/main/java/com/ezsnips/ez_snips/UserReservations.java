package com.ezsnips.ez_snips;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by nigel on 4/22/2015.
 */
public class UserReservations extends Activity {



    ArrayAdapter<String> adapter;
    ListView listv;
    ArrayList<String> data;
    SharedPreferences pref;
    String email, format,nformat, current, newformat, end,endformat;
    Context context;
    SimpleDateFormat fmt;
    Date date,edate;

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reservations);

        format = "yyyy-MM-dd'T'hh:mm:ss";
        nformat = "MM.dd.yyyy hh:mm:ss a";
        fmt = new SimpleDateFormat(format);

        context = this;
        data = new ArrayList<String>();
        listv = (ListView) findViewById(R.id.current_reservations);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listv.setAdapter(adapter);

        pref = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        email =  pref.getString("username","nothing");


        new AsyncLoadReservations().execute();

    }
    protected class AsyncLoadReservations extends AsyncTask<Void, JSONObject, ArrayList<ReservationsTable>> {
       ArrayList<ReservationsTable>  reservationsTable = null;

        @Override
        protected ArrayList<ReservationsTable> doInBackground(Void... params) {
            // TODO Auto-generated method stub


            RestAPI api = new RestAPI();
            try {

                JSONObject jsonObj = api.FindReservations(email);

                JSONParser parser = new JSONParser();

                reservationsTable = parser.parseUserReservations(jsonObj);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncLoadDeptDetails", e.getMessage());

            }

            return reservationsTable;

        }


        @Override
        protected void onPostExecute(ArrayList<ReservationsTable> result) {
            // TODO Auto-generated method stub


            for (int i = 0; i < result.size(); i++) {

                current = result.get(i).getReservation_start();
                try {
                    date = fmt.parse(current);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                end = result.get(i).getReseravation_end();
                try {
                    edate = fmt.parse(end);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                fmt.applyPattern(nformat);
                newformat = fmt.format(date);
                endformat = fmt.format(edate);


                data.add(result.get(i).getServiceName() + " with " + result.get(i).getStylistFirstName() + " " + result.get(i).getStylistLastName()
             +" on "+ newformat + "\n " + endformat);
            }

            adapter.notifyDataSetChanged();

        }

    }
    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    /*

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_reservation, menu);


        //   day = date.getInt("day");
        //    month = date.getInt("month") + 1;
        //   year = date.getInt("year");
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

            Intent i= new Intent(UserReservations.this, MainActivity.class);
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
