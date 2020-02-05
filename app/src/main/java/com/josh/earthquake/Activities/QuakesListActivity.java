package com.josh.earthquake.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.josh.earthquake.Model.EarthQuake;
import com.josh.earthquake.R;
import com.josh.earthquake.Util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuakesListActivity extends AppCompatActivity {
    private ListView listView;
    private TextView textView; //only used on error response maybe change to toast?

    public QuakesListActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quakes_list);
        listView = (ListView) findViewById(R.id.listview);
        //Instantiate the RequestQueue and add the request to the queue
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(sendRequest(Constants.URL));
    }

    public JsonObjectRequest sendRequest(String url) {

        return new JsonObjectRequest
            (Request.Method.GET, url, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = response.getJSONArray("features");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    createAdapter(parseJson(jsonArray));
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    textView.setText("Oops! That Didn't Work!");
                }
            });
    }


    //populates arrayList to listView
    public void createAdapter(ArrayList<String> earthQuakeObject) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(QuakesListActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1,
                earthQuakeObject);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Clicked " + position, Toast.LENGTH_LONG).show();
            }
        });

        arrayAdapter.notifyDataSetChanged();
    }

    //parse the JSonObject
    public ArrayList<String> parseJson(JSONArray jsonArray) {
        ArrayList<String> earthQuakeObject = new ArrayList<String>();

        if (jsonArray == null || jsonArray.length() <= 0) {
            return earthQuakeObject;
        }
        try {
            for (int i = 0; i < Constants.LIMIT; i++) {
                EarthQuake earthQuake = new EarthQuake();
                JSONObject properties = jsonArray.getJSONObject(i).getJSONObject("properties");

                //get coordinates
                JSONObject geometry = jsonArray.getJSONObject(i).getJSONObject("geometry");

                //get coordinates array
                JSONArray coordinates = geometry.getJSONArray("coordinates");
                double lon = coordinates.getDouble(0);
                double lat = coordinates.getDouble(1);

                //Setup EarthQuake Object
                earthQuake.setPlace(properties.getString("place"));
                earthQuake.setType(properties.getString("type"));
                earthQuake.setTime(properties.getLong("time"));
                earthQuake.setLon(lon);
                earthQuake.setLat(lat);
                earthQuakeObject.add(earthQuake.getPlace());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return earthQuakeObject;
    }
}
