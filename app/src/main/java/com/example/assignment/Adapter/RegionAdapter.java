package com.example.assignment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.Activities.CountryActivity;
import com.example.assignment.Model.Country;
import com.example.assignment.Model.Language;
import com.example.assignment.R;

import java.util.ArrayList;
import java.util.List;

public class RegionAdapter extends RecyclerView.Adapter<RegionAdapter.ViewHolder>{

    List<Country> mCountryList;
    Context mContext;

    public RegionAdapter(List<Country> mCountryList, Context applicationContext) {
        this.mCountryList = mCountryList;
        mContext = applicationContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false );
        ViewHolder holder = new ViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Country country = mCountryList.get(position);
        ArrayList<String> languages = new ArrayList<>();
        holder.mCountryName.setText(country.getName());
        holder.mCountryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CountryActivity.class);
                intent.putExtra("Name", country.getName());
                intent.putExtra("Capital", country.getCapital());
                intent.putExtra("Flag", country.getFlag());
                intent.putExtra("Region", country.getRegion());
                intent.putExtra("Sub Region", country.getSubregion());
                intent.putExtra("Population", country.getPopulation());
                intent.putStringArrayListExtra("Borders", country.getBorders());

                for (Language language : country.getLanguages()){
                    languages.add(language.getName());
                }
                intent.putStringArrayListExtra("Languages", languages);

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCountryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mCountryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mCountryName = itemView.findViewById(R.id.country);

        }
    }

}
