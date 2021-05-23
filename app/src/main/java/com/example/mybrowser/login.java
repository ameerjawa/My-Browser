package com.example.mybrowser;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    EditText editName, editEmail, editID;
    TextView textView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editName = findViewById(R.id.username);
        editEmail = findViewById(R.id.email);
        editID = findViewById(R.id.idNumber);
        textView = findViewById(R.id.mytextview);
    }

    public void add(View view) {

        //not localhost and not 127.0.0.1
        //cmd- > ipconig -> Wireless LAN adapter
        String url = "http://192.168.1.17/users/insert.php";


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {


                textView.setText(response);


            }

        }, new Response.ErrorListener() {

            @Override

            public void onErrorResponse(VolleyError error) {


                textView.setText(error.toString());


            }

        }) {

            @Override

            protected Map<String, String> getParams() {

                Map<String, String> map = new HashMap<String, String>();

                map.put("username", editName.getText().toString());

                map.put("email", editEmail.getText().toString());

                return map;

            }

        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);

    }

    public void getAll(View view) {


        String url = "http://192.168.1.17/users/get.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {


                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonResponse = jsonArray.getJSONObject(0);
                            JSONArray jsonArray_users = jsonResponse.getJSONArray("users");


                            String result = "";

                            for (int i = 0; i < jsonArray_users.length(); i++) {


                                JSONObject jsonObject = jsonArray_users.getJSONObject(i);
                                result += jsonObject.getString("user_id") + ". " + jsonObject.getString("username") + "   " + jsonObject.getString("email") + "\n";

                            }

                            textView.setText(result);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        textView.setText(error.getMessage());
                    }
                });


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    public void delete(View view) {

        //not localhost and not 127.0.0.1
        //cmd- > ipconig -> Wireless LAN adapter
        String url = "http://192.168.1.17/users/delete.php";


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {


                textView.setText(response);


            }

        }, new Response.ErrorListener() {

            @Override

            public void onErrorResponse(VolleyError error) {


                textView.setText(error.toString());


            }

        }) {

            @Override

            protected Map<String, String> getParams() {

                Map<String, String> map = new HashMap<String, String>();

                map.put("id", editID.getText().toString());


                return map;

            }

        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);


    }

    public void update(View view) {

        //not localhost and not 127.0.0.1
        //cmd- > ipconig -> Wireless LAN adapter
        String url = "http://192.168.1.17/users/update.php";


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {


                textView.setText(response);


            }

        }, new Response.ErrorListener() {

            @Override

            public void onErrorResponse(VolleyError error) {


                textView.setText(error.toString());


            }

        }) {

            @Override

            protected Map<String, String> getParams() {

                Map<String, String> map = new HashMap<String, String>();

                map.put("username", editName.getText().toString());

                map.put("email", editEmail.getText().toString());

                map.put("id", editID.getText().toString());

                return map;

            }

        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id ==R.id.login_btn){
            Intent intent =new Intent(login.this,login.class);
            startActivity(intent);
        }
        if (id ==R.id.home_btn){
            Intent intent =new Intent(login.this,MainActivity.class);
            startActivity(intent);

        }
        if (id ==R.id.about_btn){

            Intent intent =new Intent(login.this,about.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}