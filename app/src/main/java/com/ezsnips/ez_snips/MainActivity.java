package com.ezsnips.ez_snips;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                Intent k= new Intent(MainActivity.this, company_page.class);
                startActivity(k);
                finish();
            }
        };
        Button sign_in =(Button) findViewById(R.id.sign_in);
        sign_in.setOnClickListener(listnr3);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

}