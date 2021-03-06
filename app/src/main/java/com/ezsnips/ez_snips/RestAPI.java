/* JSON API for android appliation */
package com.ezsnips.ez_snips;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.json.JSONArray;

public class RestAPI {
   
//	private final String urlString ="http://10.0.2.2:100/EZ/Handler1.ashx";
   private final String urlString ="http://www.ezsnips.somee.com/Handler1.ashx";


    //"http://192.168.56.1/JSONWebAPI/Handler1.ashx"; // Genymotion AVD
    								//"http://10.0.2.2/JSONWebAPI/Handler1.ashx"; // Default ANDROID AVD //
    								
    		
    private static String convertStreamToUTF8String(InputStream stream) throws IOException {
	    String result = "";
	    StringBuilder sb = new StringBuilder();
	    try {
            InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
            char[] buffer = new char[4096];
            int readedChars = 0;
            while (readedChars != -1) {
                readedChars = reader.read(buffer);
                if (readedChars > 0)
                   sb.append(buffer, 0, readedChars);
            }
            result = sb.toString();
		} catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }


    private String load(String contents) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(60000);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        OutputStreamWriter w = new OutputStreamWriter(conn.getOutputStream());
        w.write(contents);
        w.flush();
        InputStream istream = conn.getInputStream();
        String result = convertStreamToUTF8String(istream);
        return result;
    }


    private Object mapObject(Object o) {
		Object finalValue = null;
		if (o.getClass() == String.class) {
			finalValue = o;
		}
		else if (Number.class.isInstance(o)) {
			finalValue = String.valueOf(o);
		} else if (Date.class.isInstance(o)) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss", new Locale("en", "USA"));
			finalValue = sdf.format((Date)o);
		}
		else if (Collection.class.isInstance(o)) {
			Collection<?> col = (Collection<?>) o;
			JSONArray jarray = new JSONArray();
			for (Object item : col) {
				jarray.put(mapObject(item));
			}
			finalValue = jarray;
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			Method[] methods = o.getClass().getMethods();
			for (Method method : methods) {
				if (method.getDeclaringClass() == o.getClass()
						&& method.getModifiers() == Modifier.PUBLIC
						&& method.getName().startsWith("get")) {
					String key = method.getName().substring(3);
					try {
						Object obj = method.invoke(o, null);
						Object value = mapObject(obj);
						map.put(key, value);
						finalValue = new JSONObject(map);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		}
		return finalValue;
	}

    public JSONObject CreateNewAccount(String firstName,String lastName,String email, String password, Double phonenumber) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "CreateNewAccount");
        p.put("firstName",mapObject(firstName));
        p.put("lastName",mapObject(lastName));
        p.put("email",mapObject(email));
        p.put("password", mapObject(password));
        p.put("phonenumber", mapObject(phonenumber));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject GetUserDetails(String email) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "GetUserDetails");
        p.put("email",mapObject(email));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UserAuthentication(String email,String passsword) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "UserAuthentication");
        p.put("email",mapObject(email));
        p.put("passsword",mapObject(passsword));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject SetReservation(String email, String stylist, String service, String fdate, String edate)throws Exception
    {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "SetReservation");
        p.put("email",mapObject(email));
        p.put("stylist",mapObject(stylist));
        p.put("service",mapObject(service));
        p.put("fdate", mapObject(fdate));
        p.put("edate", mapObject(edate));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);

        return result;
    }

    public JSONObject CancelReservation(String email)throws Exception
    {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "CancelReservation");
        p.put("email",mapObject(email));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject FindReservations(String email) throws Exception
    {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "FindReservations");
        p.put("email",mapObject(email));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject GetStylistNames() throws Exception
    {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "GetStylistNames");
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject DisplayServices() throws Exception
    {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "DisplayServices");
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

}


