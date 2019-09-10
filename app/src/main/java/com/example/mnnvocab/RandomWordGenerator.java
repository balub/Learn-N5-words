package com.example.mnnvocab;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RandomWordGenerator {
    private static final String TAG = "RandomSentenceGenerator";
    private Context context;

    public RandomWordGenerator(Context current){
        this.context = current;
    }
    private List<XML_Data> Words = new ArrayList<>();
    private Map<String,String > words = new HashMap<String, String>();
    public List<String> w1 = new ArrayList<>();


    public void readWeatherData() {
        // Read the raw csv file
        InputStream is = context.getResources().openRawResource(R.raw.mnnvocab);

        // Reads text from character-input stream, buffering characters for efficient reading
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        // Initialization
        String line = "";

        // Initialization
        try {
            // Step over headers
            reader.readLine();

            // If buffer is not empty
            while ((line = reader.readLine()) != null) {
                // use comma as separator columns of CSV
                String[] tokens = line.split(",");
                // Read the data
                XML_Data sample = new XML_Data();

                // Setters
                sample.setWORD(tokens[0]);
                sample.setDEFINITION(tokens[1]);

                // Adding object to a class
                Words.add(sample);
                // add the word object to a new map
//                for (Map.Entry<String, String> entry : words.entrySet()) {
                    //System.out.println(entry.getKey() + "/" + entry.getValue());}
                for(XML_Data element : Words) {
                   words.put(element.getWORD(),element.getDEFINITION());
                }

            }
            for(XML_Data element : Words) {
                w1.add(element.getWORD());
            }
            Log.d(TAG, "the w1 array is : " + w1.get(0));
            Log.d(TAG, "the w1 array size is : " + w1.size());



//            Log.d(TAG, "the words map is : " + words);
//            Log.d(TAG, "the words map size is : " + words.size());

        } catch (IOException e) {
            // Logs error with priority level
            Log.wtf("MyActivity", "Error reading data file on line" + line, e);

            // Prints throwable details
            e.printStackTrace();
        }
    }

    public String sentenceReturn(int position){

        return w1.get(32);

    }
    public  String getMeaning(String w1){

        String meaning = words.get(w1);
        Log.d(TAG, "getMeaning: " + meaning );
        return words.get(meaning);
    }

}
