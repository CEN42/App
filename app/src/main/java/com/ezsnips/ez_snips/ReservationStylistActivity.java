package com.ezsnips.ez_snips;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Nigel on 4/19/2015.
 */
public class ReservationStylistActivity extends Activity {

    ArrayAdapter<String> adapter;
    ListView listv;
    Context context;
    ArrayList<String> data;
    int day;
    int month;
    int year;

    @Override
    protected void onCreate (Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stylist);

       // setupActionBar();
        Bundle date = getIntent().getExtras();
        day = date.getInt("day");
        month = date.getInt("month");
        year = date.getInt("year");
        data = new ArrayList<String>();
        listv = (ListView) findViewById(R.id.lv_stylist);
        context = this;

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        listv.setAdapter(adapter);
        //Toast.makeText(this, "Loading Please Wait...", Toast.LENGTH_SHORT).show();
        new AsyncLoadStylistNames().execute();

        listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String stylist =  listv.getItemAtPosition(position).toString();
                Bundle stuff = new Bundle();
                stuff.putString("stylist",stylist);
                stuff.putInt("day", day);
                stuff.putInt("month", month);
                stuff.putInt("year", year);

                Intent i = new Intent(ReservationStylistActivity.this, ReservationActivity.class);
                i.putExtras(stuff);


                startActivity(i);
            }

        });
    }

    protected class AsyncLoadStylistNames extends AsyncTask<Void, JSONObject, ArrayList<StylistTable>> {
        ArrayList<StylistTable> stylistTable = null;

        @Override
        protected ArrayList<StylistTable> doInBackground(Void... params) {
        // TODO Auto-generated method stub


            RestAPI api = new RestAPI();
            try {

                JSONObject jsonObj = api.GetStylistNames();

                JSONParser parser = new JSONParser();

                stylistTable = parser.parseStylist(jsonObj);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncLoadDeptDetails", e.getMessage());

            }

            return stylistTable;

        }


        @Override
        protected void onPostExecute(ArrayList<StylistTable> result) {
            // TODO Auto-generated method stub

            for (int i = 0; i < result.size(); i++) {
                data.add(result.get(i).getFirstName() + " " + result.get(i).getLastName());
            }

            adapter.notifyDataSetChanged();

          //  Toast.makeText(context,"Loading Completed",Toast.LENGTH_SHORT).show();
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
        getMenuInflater().inflate(R.menu.menu_reservation_stylist, menu);


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

            Intent i= new Intent(ReservationStylistActivity.this, MainActivity.class);
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
