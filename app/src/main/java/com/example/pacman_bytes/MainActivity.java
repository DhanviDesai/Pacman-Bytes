package com.example.pacman_bytes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RequestQueue rq;
    ArrayList<NotamData> notam;
    RecyclerView rs;
    NotamAdapter  nm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notam = new ArrayList<>();
        Toast.makeText(this, "In the onCreate Method", Toast.LENGTH_SHORT).show();
        rs  = findViewById(R.id.recyclerView);
        NotamAdapter nm = new NotamAdapter();
        rs.setAdapter(nm);
        rs.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rq = Volley.newRequestQueue(this);
        getData();
        notam.remove(0);
    }

    public void getData(){
        Toast.makeText(this, "In the getData", Toast.LENGTH_SHORT).show();
        String url = "http://192.168.137.227:5000/notams?start=0&end=30";
        JsonArrayRequest jso = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(MainActivity.this, "In the request part", Toast.LENGTH_SHORT).show();
                    Log.i("Json",response.toString());
                    for(int i=0;i<response.length();i++){
                        try {
                            JSONObject obj = (JSONObject) response.get(i);
                            String subarea = obj.getString("subarea");
                            String area = obj.getString("area");
                            String message = obj.getString("message");
                            String subject = obj.getString("subject");
                            notam.add(new NotamData(subarea,area,subject,message));
                            nm= new NotamAdapter(MainActivity.this,notam,rs);
                            rs.setAdapter(nm);
                            ItemTouchHelper itemTouchHelper = new
                                    ItemTouchHelper(new SwipeToDeleteCallback(nm));
                            itemTouchHelper.attachToRecyclerView(rs);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error",error.toString());

            }
        });
        rq.add(jso);
    }
}
