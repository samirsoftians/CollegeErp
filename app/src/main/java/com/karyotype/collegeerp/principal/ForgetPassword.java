package com.karyotype.collegeerp.principal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.karyotype.collegeerp.R;


/**
 * Created by Lenovo on 6/6/2017.
 */

public class ForgetPassword extends Fragment {



    GridView grid;
    String[] web = {
            "Google",
            "Github",
            "Instagram",
            "Facebook",
            "Flickr",
            "Pinterest",


    } ;
    int[] imageId = {
            R.drawable.i3,
            R.drawable.i2,
            R.drawable.i1,
            R.drawable.i1,
            R.drawable.i1,
            R.drawable.i2,


    };

    //GridView gridview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.notice, container, false);






        CustomGrid adapter = new CustomGrid(getContext(), web, imageId);
        grid=(GridView)v.findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getContext(), "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });




        return v;


    }

}
