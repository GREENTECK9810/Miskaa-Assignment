package com.example.assignment.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.assignment.Adapter.RegionAdapter;
import com.example.assignment.Api.RegionApi;
import com.example.assignment.Model.Country;
import com.example.assignment.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private RegionApi regionApi;
    List<Country> mCountryList;
    RegionAdapter mRegionAdapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        init();

        // Initialize retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.eu/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        regionApi = retrofit.create(RegionApi.class);

        fetchData();

    }

    private void init() {
        mCountryList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.recycler_view);
        mRegionAdapter = new RegionAdapter(mCountryList, this);
        mRecyclerView.setAdapter(mRegionAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = findViewById(R.id.progress_circular);
    }

    private void fetchData() {
        progressBar.setVisibility(View.VISIBLE);
        Call<List<Country>> call = regionApi.region();

        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                progressBar.setVisibility(View.GONE);
                if (!response.isSuccessful()){
                    Log.e("ERROR", "not found");
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Country> countries = response.body();

                for (Country country : countries){
                    mCountryList.add(country);
                    mRegionAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}