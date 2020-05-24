package com.example.travfever;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class WeatherActivity extends AppCompatActivity {

    String daysArray[] = {"Sunday","Monday","Tuesday", "Wednesday","Thursday","Friday", "Saturday"};

    private RequestQueue queue;
    private RequestQueue requestQueue;

    TextView datehead;
    TextView Temperature;
    TextView Humid;
    TextView Wind;
    TextView Descrip;
    ImageView weathericon;
    ConstraintLayout layout;
    Calendar calendar;

//    api key removed
//    final String weatherapi = "";

    List sunny = Arrays.asList("01d", "02d", "03d", "04d", "50d");
    List night = Arrays.asList("01n", "02n", "03n", "04n", "50n");
    List rain = Arrays.asList("09d", "10d", "09n", "10n");
    List storm = Arrays.asList("11d", "11n");
    List snow = Arrays.asList("13d", "13n");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        Toolbar weatherToolbar = findViewById(R.id.weather_toolbar);
        weatherToolbar.setTitle("Weather Now");
        weatherToolbar.setTitleTextColor(Color.WHITE);
        weatherToolbar.setNavigationIcon(R.drawable.ic_arrow_white_24dp);

        weatherToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        queue = Volley.newRequestQueue(this);
        requestQueue = Volley.newRequestQueue(WeatherActivity.this);

        datehead = findViewById(R.id.Date);
        Temperature = findViewById(R.id.TempDeg);
        Humid = findViewById(R.id.HumidDeg);
        Wind = findViewById(R.id.WindDeg);
        Descrip = findViewById(R.id.Descrip);
        weathericon = findViewById(R.id.WeatherPhoto);
        calendar = Calendar.getInstance();

        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        String currentDay = daysArray[calendar.get(Calendar.DAY_OF_WEEK)];

        String DateHead = currentDay + ", " + currentDate;

        datehead.setText(DateHead);

        layout = findViewById(R.id.back);

        fetchResponse();
    }

    public void updatePage(JSONObject data){
        try{
            int temp = (int) data.getJSONObject("main").getDouble("temp");
            temp -= 273;

            String tempstr = String.valueOf(temp);

            String icon = data.getJSONArray("weather").getJSONObject(0).getString("icon");
            String describ = data.getJSONArray("weather").getJSONObject(0).getString("description");

            String windspeed = data.getJSONObject("wind").getString("speed");
            String humid = data.getJSONObject("main").getString("humidity");

            windspeed += " m/s";
            humid += " %";

            Temperature.setText(tempstr);
            Humid.setText(humid);
            Wind.setText(windspeed);
            Descrip.setText(describ);
            changeIcon(icon);

        }
        catch(org.json.JSONException exception){
            System.out.print("Error");
            System.out.println(exception);
        }
    }

    public void changeIcon(String icon){
        if (sunny.contains(icon)){
            weathericon.setImageResource(R.drawable.sunnycolor);
        }
        else if (night.contains(icon)){
            weathericon.setImageResource(R.drawable.nightcolor);
        }
        else if (rain.contains(icon)){
            weathericon.setImageResource(R.drawable.rainycolor);
        }
        else if (snow.contains(icon)){
            weathericon.setImageResource(R.drawable.rainycolor);
        }
        else if (storm.contains(icon)){
            weathericon.setImageResource(R.drawable.stormcolor);
        }
        else{
            weathericon.setImageResource(R.drawable.nightcolor);
        }

        if(icon.contains("n")){
            layout.setBackgroundResource(R.color.NightBackGround);
        }
        else{
            layout.setBackgroundResource(R.color.mainBackground);
        }
    }

    private void fetchResponse(){
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, weatherapi, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        updatePage(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error: ", error.getMessage());
                    }
                }
        );

        requestQueue.add(req);
    }

}
