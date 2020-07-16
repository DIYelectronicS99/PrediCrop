package com.example.weatheappv3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ClimateDataActivity extends AppCompatActivity {

    private  RecyclerView recyclerView;
    private  ArrayList<ClimateDataList> arrayList;
    private ProgressBar spinner;

    String cropname;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    int flag;
    TextView test, cropview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_climate_data);


        cropview = findViewById(R.id.tvcrop);

        test = findViewById(R.id.tvid);
        spinner = (ProgressBar)findViewById(R.id.pb1);
        recyclerView = findViewById(R.id.clrclv1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();


        String obcityid = hasID();
        getWeatherStats(obcityid);
        spinner.setVisibility(View.VISIBLE);

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {

            @Override

            public void run() {

                getCrop();
                spinner.setVisibility(View.GONE);
            }

        }, 4000);



        //cropname();


    }



    public String hasID(){
        String cityID = null;
        if (getIntent().hasExtra("ID")){

             cityID = getIntent().getStringExtra("ID");



        }

        return  cityID;
    }

   /* public void cropname(){

        if (getIntent().hasExtra("Cropname")){
            cropview.setText(getIntent().getStringExtra("Cropname"));
        }
    }*/

    public  void getWeatherStats(final String cityid){
        flag = 0;

        String wurl = "https://api.meteostat.net/v1/climate/normals?station=".concat(cityid).concat("&key=wMzoCfs8");
        Log.i("final stat url", wurl);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, wurl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONObject jsonObject = response.getJSONObject("data");


                    JSONObject temp = jsonObject.getJSONObject("temperature");
                    JSONObject rain = jsonObject.getJSONObject("precipitation");
                    JSONObject pressure = jsonObject.getJSONObject("pressure");

                    for (int i = 202001; i <= 202012; i++) {


                        String month = String.valueOf(i).concat("01");


                    //String startmonth = "20200101";

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

                    try {
                        Date date = simpleDateFormat.parse(month);
                        String monthString = (String) DateFormat.format("MMM", date); // Jan
                        monthString = monthString.toUpperCase();

                        String monthlytemp = temp.getString(monthString);
                        String monthlyrain = rain.getString(monthString);

                        int rainint = rain.getInt(monthString);
                        String monthlypress = pressure.getString(monthString);


                        if(flag == 0) {
                            flag = 1;
                            Map<String, Object> weather = new HashMap<>();
                            //weather.put("temp", monthlytemp);
                            weather.put("rain", rainint);
                            //weather.put("pressure", monthlypress);
                            db.collection("weather").document("d1").update(weather);

                        }

                        arrayList.add(new ClimateDataList(monthString, monthlytemp.concat(" C"), monthlyrain.concat(" mm"), monthlypress.concat(" hPa")));
                        ClimateAdapter climateAdapter = new ClimateAdapter(arrayList, getApplicationContext());
                       // recyclerView.setAdapter(climateAdapter);



                        test.setText(cityid);

                        Log.i("temp: ", monthString);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                }




                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);


       // return list;
    }


    public String getCrop(){


        DocumentReference dref = db.collection("weather").document("op1");
        dref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                //spinner.setVisibility(View.VISIBLE);
                if (task.isSuccessful()){
                    DocumentSnapshot ds = task.getResult();
                    Log.i("crop: " , String.valueOf(ds.getData()));

                    cropname = String.valueOf(ds.getData());
                    cropview.setText(String.valueOf(ds.get("crop")));
                    //spinner.setVisibility(View.GONE);
                }
                else{
                    //spinner.setVisibility(View.GONE);
                    cropview.setText(" ");
                }
            }
        });

        return cropname;
    }
}
