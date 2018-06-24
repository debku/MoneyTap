package com.example.aa.moneytap;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //private EditText edit_box;
    private Button btn_search;
    private AutoCompleteTextView auto_text1;
    TextView tv_heading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_search = (Button) findViewById(R.id.btn_search);
        auto_text1 = (AutoCompleteTextView) findViewById(R.id.auto_text1);
        tv_heading = (TextView)findViewById(R.id.tv_heading);
        final Animation animBounce = AnimationUtils.loadAnimation(this, R.anim.bounce2);
        tv_heading.startAnimation(animBounce);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, COUNTRIES);

        //set the adapter for the Autocomplete TextView
        auto_text1.setAdapter(adapter);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str = auto_text1.getText().toString();
                System.out.println("Item is" + str);
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("itemname", str);
                i.putExtras(bundle);
                startActivity(i);

            }
        });
    }

    private static final String[] COUNTRIES = new String[]{
            "Sachin Tendulkar", "Sachin Pilot", "Sachin (actor)", "Sachin Bhowmick", "Sachin Shroff", "Sachin Bansal", "Sachin! Tendulkar Alla", "Sachin: A Billion Dreams", "Sachintha Peiris",
    };


}
