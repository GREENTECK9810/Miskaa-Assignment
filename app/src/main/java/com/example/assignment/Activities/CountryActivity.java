package com.example.assignment.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment.R;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CountryActivity extends AppCompatActivity {

    TextView mName, mCapital, mFlag, mRegion, mSubRegion, mBorders, mPopulation, mLanguages;
    ImageView mFlagImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        init();
        displayData();
    }

    private void displayData() {

        mName.setText("Name : " + getIntent().getStringExtra("Name"));
        mCapital.setText("Capital : " + getIntent().getStringExtra("Capital"));
        GlideToVectorYou.init().with(this).load(Uri.parse(getIntent().getStringExtra("Flag")), mFlagImage    );
        mRegion.setText("Region : " + getIntent().getStringExtra("Region"));
        mSubRegion.setText("Sub Region : " + getIntent().getStringExtra("Sub Region"));
        mPopulation.setText("Population : " + getIntent().getStringExtra("Population"));

        ArrayList<String> borders = getIntent().getStringArrayListExtra("Borders");
        String bordersString = "Borders : ";

        for (String border : borders){
            bordersString = bordersString + border + " ";
        }

        mBorders.setText(bordersString);

        ArrayList<String> languages = getIntent().getStringArrayListExtra("Languages");
        String languagesString = "Languages : ";

        for (String language : languages){
            languagesString = languagesString + language + " ";
        }

        mLanguages.setText(languagesString);


    }

    private void init() {

        mName = findViewById(R.id.name);
        mCapital = findViewById(R.id.capital);
        mFlag = findViewById(R.id.flag);
        mRegion = findViewById(R.id.region);
        mSubRegion = findViewById(R.id.sub_region);
        mBorders = findViewById(R.id.borders);
        mPopulation = findViewById(R.id.population);
        mLanguages = findViewById(R.id.languages);
        mFlagImage = findViewById(R.id.flag_image);


    }
}