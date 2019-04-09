package com.example.anchala.seo_project;

import android.app.ProgressDialog;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ArrayList<User> mUSer;
    private RecyclerView.Adapter adapter;
    private RequestQueue mRequestQueue;
    private String url ="https://api.androidhive.info/volley/person_array.json";

    //private Button button;
    private TextView text ;
    private StringRequest mStringRequest;

    @Override
    @SuppressWarnings("deprecation")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rview);
        mUSer = new ArrayList<User>();

       // adapter = new Custom_Adapter(this , mUSer);
        LinearLayoutManager lm  = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        DividerItemDecoration did = new DividerItemDecoration(this , lm.getOrientation());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lm);
        recyclerView.addItemDecoration(did);

        getData();
        //recyclerView.setAdapter(adapter);


        //getData();

//        text = findViewById(R.id.text);
//        button = findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//              sendAndRequestResponse();
//            }
//        });
    }


    private void getData() {
        //final ProgressDialog progressDialog = new ProgressDialog(this);
        //progressDialog.setMessage("Loading...");
        //progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @SuppressWarnings("deprecation")
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject userJsonObject = response.getJSONObject(i);
                        User user = new User();

                        Phone phone = new Phone();
                        String name = userJsonObject.getString("name");
                        String email = userJsonObject.getString("email");
                        JSONObject phoneJsonObject = userJsonObject.getJSONObject("phone");
                        String home = phoneJsonObject.getString("home");
                        String mobile  = phoneJsonObject.getString("mobile");
                        phone.setHome(home);
                        phone.setMobile(mobile);
                        user.setName(name);
                        user.setEmail(email);
                        user.setPhone(phone);

                        mUSer.add(user);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        //progressDialog.dismiss();
                    }
                }
                //adapter.notifyDataSetChanged();
                //progressDialog.dismiss();
                adapter = new Custom_Adapter(getApplicationContext() , mUSer);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                //progressDialog.dismiss();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }


    private void sendAndRequestResponse() {

        Cache cache = new DiskBasedCache(getCacheDir(),1024*1024);
        Network network = new BasicNetwork(new HurlStack());
        //RequestQueue initialized
        mRequestQueue = new RequestQueue(cache , network);
        mRequestQueue.start();


        //RequestQueue initialized
//        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                text.setText(response);
//                Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
                mRequestQueue.stop();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("error","Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
    }

}

