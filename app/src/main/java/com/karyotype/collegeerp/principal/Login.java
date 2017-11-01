package com.karyotype.collegeerp.principal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.karyotype.collegeerp.R;

/**
 * Created by Lenovo on 11/1/2017.
 */

public class Login extends AppCompatActivity{

    Button login_submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_login);

        login_submit= (Button) findViewById(R.id.login_submit);

        login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sc=new Intent(com.karyotype.collegeerp.principal.Login.this,com.karyotype.collegeerp.principal.MainActivity.class);
                startActivity(sc);
            }
        });
    }
}
