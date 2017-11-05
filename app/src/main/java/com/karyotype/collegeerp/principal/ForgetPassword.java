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

    GridView gridview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.notice, container, false);


//
// =============================================================================================================


         gridview = (GridView) v.findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(getContext()));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id){

                Toast.makeText(getContext(), "you clicked", Toast.LENGTH_SHORT).show();
                // Send intent to SingleViewActivity
//                Intent i = new Intent(getContext(), SingleViewActivity.class);
//                // Pass image index
//                i.putExtra("id", position);
//                startActivity(i);
            }
        });
        return v;


    }

}
