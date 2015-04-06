package com.ezsnips.ez_snips;
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
/*
	public ArrayList<DeptTable> parseDepartment(JSONObject object)
	{
		ArrayList<DeptTable> arrayList=new ArrayList<DeptTable>();
		try {
			JSONArray jsonArray=object.getJSONArray("Value");
			JSONObject jsonObj=null;
			for(int i=0;i<jsonArray.length();i++)
			{
				jsonObj=jsonArray.getJSONObject(i);
				arrayList.add(new DeptTable(jsonObj.getInt("no"), jsonObj.getString("name")));
			}
			
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.d("JSONParser => parseDept", e.getMessage());
		}
		return arrayList;
	}
	*/
	
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
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.d("JSONParser => parsUserD", e.getMessage());
		}
		
		return customers;
			
	}
	
	
}
