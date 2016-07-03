package com.recycler.sachin.hashmapsharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    static TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text);
        storeMap(this);
        getMap(this);
    }


    public static void storeMap(Context context){

        HashMap<String,String> map = new HashMap<>();
        map.put("First","Sachin");
        map.put("Last","Kumar");
        map.put("Phone","911");

        SharedPreferences preferences =  context.getSharedPreferences("MAP_insertion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

       // for ( String s: map.keySet()) {

            editor.putString("User :", map.toString());

        //}

        editor.commit();

    }

    public static void getMap(Context context)  {

        SharedPreferences preferences =  context.getSharedPreferences("MAP_insertion", Context.MODE_PRIVATE);

        String strMap = preferences.getString("User :","");

        // use properties to restore the map
        Properties props = new Properties();
        try {
            props.load(new StringReader(strMap.substring(1, strMap.length() - 1).replace(", ", "\n")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> map2 = new HashMap<String, String>();
        for (Map.Entry<Object, Object> e : props.entrySet()) {
            map2.put((String)e.getKey(), (String)e.getValue());
        }

        for(Map.Entry<String, String> entry : map2.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            textView.setText(key+":"+value);

        }

    }


}
