package com.example.mnnvocab;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView randomWord,meanTxt;
    private Button randomGenbtn,sentBtn;
    public String DispWord;
    private List<XML_Data> Words = new ArrayList<>();
    private Map<String,String > words = new HashMap<String, String>();
    public List<String> w1 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        randomGenbtn = (Button) findViewById(R.id.wordBtn);
        randomWord = (TextView) findViewById(R.id.wordTxt);
        sentBtn = (Button) findViewById(R.id.sentBtn);
        meanTxt = (TextView) findViewById(R.id.meanTxt);
        meanTxt.setVisibility(View.INVISIBLE);
        InputStream is = getResources().openRawResource(R.raw.mnnvocab);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );
        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                XML_Data sample = new XML_Data();
                sample.setWORD(tokens[1]);
                sample.setDEFINITION(tokens[2]);

                Words.add(sample);

                for(XML_Data element : Words) {
                    words.put(element.getWORD(),element.getDEFINITION());
                }

            }
            for(XML_Data element : Words) {
                w1.add(element.getWORD());
            }
            Log.d(TAG, "the w1 array is : " + w1.get(0));
            Log.d(TAG, "the w1 array size is : " + w1.size());

        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading data file on line" + line, e);
            e.printStackTrace();
        }

        randomGenbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DispWord = w1.get(new Random().nextInt(971));
                randomWord.setText(DispWord);
                meanTxt.setVisibility(View.INVISIBLE);
            }
        });

        sentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meanTxt.setVisibility(View.VISIBLE);
                String meaning = words.get(DispWord);
                Log.d(TAG, "meaning: "+ meaning);
                meanTxt.setText(meaning);
            }
        });


    }
}
