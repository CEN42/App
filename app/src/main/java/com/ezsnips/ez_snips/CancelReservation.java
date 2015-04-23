package com.ezsnips.ez_snips;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by nigel on 4/22/2015.
 */
public class CancelReservation extends Activity {

    ArrayAdapter<String> adapter;
    ListView listv;
    ArrayList<String> data;
    SharedPreferences pref;
    Context context;
    SimpleDateFormat fmt;
    Date date,edate;
    String email, format,nformat, current, newformat, end,endformat;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel);

        format = "yyyy-MM-dd'T'hh:mm:ss";
        nformat = "MM.dd.yyyy hh:mm:ss a";
        fmt = new SimpleDateFormat(format);

        context = this;
        data = new ArrayList<String>();
        listv = (ListView) findViewById(R.id.list_res);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listv.setAdapter(adapter);

        pref = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        email =  pref.getString("username","nothing");

        new AsyncLoadReservations().execute();

        listv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showSimplePopUp();
            }
        });

    }

    private void showSimplePopUp() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Cancel Reservation");
        helpBuilder.setMessage("Cancel?");
        helpBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog

                        CancelTable cancelTable = new CancelTable(email);
                        new AsyncCancelReservation().execute(cancelTable);

                    }
                });

        helpBuilder.setNegativeButton("No!!!!",
                new DialogInterface.OnClickListener(){


                    public void onClick(DialogInterface dialog){

                    }

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();


    }

    protected class AsyncCancelReservation extends AsyncTask<CancelTable, Void, Void> {

        @Override
        protected Void doInBackground(CancelTable... params) {

            RestAPI api = new RestAPI();
            try {

                api.CancelReservation(params[0].getEmail());

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncCreateUser", e.getMessage());

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            Intent i = new Intent(CancelReservation.this, company_page.class);
            startActivity(i);
        }

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

            Intent i= new Intent(CancelReservation.this, MainActivity.class);
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