package com.ezsnips.ez_snips;
import java.io.Console;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


public class JSONParser {

	public JSONParser()
	{
	super();	
	}

	public boolean parseUserAuth(JSONObject object)
	{	boolean userAtuh=false;
			try {
				userAtuh= object.getBoolean("Value");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.d("JSONParser => parsUserA", e.getMessage());
			}
			
			return userAtuh;
	}
	
	public CustomersTable parseUserDetails(JSONObject object)
	{
        CustomersTable customers=new CustomersTable();
		
		try {


			JSONObject jsonObj=object.getJSONArray("Value").getJSONObject(0);

            customers.setFirstName(jsonObj.getString("firstName"));
            customers.setLastName(jsonObj.getString("lastName"));
            customers.setEmail(jsonObj.getString("email"));
            customers.setPassword(jsonObj.getString("password"));
            customers.setPhonenumber(jsonObj.getDouble("phonenumber"));
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.d("JSONParser => parsUserD", e.getMessage());
		}
		
		return customers;
			
	}

	public ArrayList<ReservationsTable> parseUserReservations(JSONObject object)
	{
        ArrayList<ReservationsTable> arrayList=new ArrayList<ReservationsTable>();

		try{
            JSONArray jsonArray=object.getJSONArray("Value");
            JSONObject jsonObj;

            for(int i =0; i <jsonArray.length(); ++i) {
                jsonObj=jsonArray.getJSONObject(i);
                arrayList.add(new ReservationsTable(jsonObj.getString("EmployeeFN"), jsonObj.getString("EmployeeLN"),
                        jsonObj.getString("sTitle"), jsonObj.getDouble("amount"), jsonObj.getString("resStart"),
                        jsonObj.getString("resEnd")));

            }
		}
		catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.d("JSONParser => parsUserR", e.getMessage());

		}
			return arrayList;

	}

	public ArrayList<StylistTable> parseStylist(JSONObject object)
	{
		ArrayList<StylistTable> arrayList=new ArrayList<StylistTable>();
		try {
			JSONArray jsonArray=object.getJSONArray("Value");
			JSONObject jsonObj;
			for(int i=0;i<jsonArray.length();i++)
			{
				jsonObj=jsonArray.getJSONObject(i);
				arrayList.add(new StylistTable(jsonObj.getString("FirstName"), jsonObj.getString("LastName")));
			}


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.d("JSONParser => parseName", e.getMessage());
		}
		return arrayList;
	}

	public ArrayList<ServicesTable> parseServices(JSONObject object)
	{
		ArrayList<ServicesTable> arrayList=new ArrayList<ServicesTable>();
		try {
			JSONArray jsonArray=object.getJSONArray("Value");
			JSONObject jsonObj;
			for(int i=0;i<jsonArray.length();i++)
			{
				jsonObj=jsonArray.getJSONObject(i);
				arrayList.add(new ServicesTable(jsonObj.getString("servicetitle"), jsonObj.getString("amount")));

   			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.d("JSONParser => parseServ", e.getMessage());
		}
		return arrayList;
	}
	
}
