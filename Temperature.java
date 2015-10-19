package com.example.lucky.inaminute;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Temperature extends ActionBarActivity {

    private String[] cityName = {"Ottawa","Toronto","Montreal","Calgary","Vancouver","Halifax"};



    private String[] temp={"0","0","0","0","0","0"};

    TextView temp_cont1;
    TextView temp_cont2;
    TextView temp_cont3;
    TextView temp_cont4;
    TextView temp_cont5;
    TextView temp_cont6;
    Button ref;
    int j;
    //String output = "temp";
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setNavigationIcon(R.mipmap.ic_homelogo);




        temp_cont1 = (TextView) findViewById(R.id.cont_temp1);
        temp_cont2 = (TextView) findViewById(R.id.cont_temp2);
        temp_cont3 = (TextView) findViewById(R.id.cont_temp3);
        temp_cont4 = (TextView) findViewById(R.id.cont_temp4);
        temp_cont5 = (TextView) findViewById(R.id.cont_temp5);
        temp_cont6 = (TextView) findViewById(R.id.cont_temp6);
        ref = (Button) findViewById(R.id.refresh);
        showlist();


        ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showlist();
              /* temp_cont1.setText(temp[0]);
                temp_cont2.setText(temp[1]);
                temp_cont3.setText(temp[2]);
                temp_cont4.setText(temp[3]);
                temp_cont5.setText(temp[4]);
                temp_cont6.setText(temp[5]);
*/

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_temperature, menu);
        return true;
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

        return super.onOptionsItemSelected(item);
    }


    void showlist() {

        for (j = 0; j <cityName.length; j++) {
            final RequestQueue queue = Volley.newRequestQueue(Temperature.this);
            final int index = j;
            String url = ("http://api.openweathermap.org/data/2.5/weather?q="+cityName[index]);
            JsonObjectRequest jarequest = new JsonObjectRequest(Request.Method.POST, url, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(Temperature.this, "request sent!", Toast.LENGTH_SHORT).show();

                    try {

                        JSONObject jsobject = response.getJSONObject("main");
                        int tmvalue=(int) (Float.parseFloat(jsobject.getString("temp")) - 273.15);
                        temp[index] =String.valueOf(tmvalue)+"\u00b0"+"C";





                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
                    , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Temperature.this, "request not sent!", Toast.LENGTH_SHORT).show();


                }

            });

            queue.add(jarequest);


        }

        temp_cont1.setText(temp[0]);
        temp_cont2.setText(temp[1]);
        temp_cont3.setText(temp[2]);
        temp_cont4.setText(temp[3]);
        temp_cont5.setText(temp[4]);
        temp_cont6.setText(temp[5]);
    }
}








