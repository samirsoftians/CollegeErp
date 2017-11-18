package com.karyotype.collegeerp.principal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karyotype.collegeerp.R;
import com.karyotype.collegeerp.links.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 11/1/2017.
 */

public class Login extends AppCompatActivity{

    Button login_submit;
    TextView login_signup1,login_signup2;
    EditText p_user_name,p_login_password;
    int valid=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_login);

        login_submit= (Button) findViewById(R.id.login_submit);
        login_signup1= (TextView) findViewById(R.id.login_signup1);
        login_signup2= (TextView) findViewById(R.id.login_signup2);

        p_user_name= (EditText) findViewById(R.id.p_login_user_name);
        p_login_password= (EditText) findViewById(R.id.p_login_password);

        login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                valid=0;

                validation();


                if(valid>0)
                {
                    Toast.makeText(Login.this, "Please check the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    checkLogin();

                }



            }
        });

        login_signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=new Intent(Login.this,PrincipalRegistration.class);
               startActivity(intent);
            }
        });


        login_signup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,PrincipalRegistration.class);
                startActivity(intent);
            }
        });


    }
    public void validation()
    {
        if(p_user_name.getText().toString().trim().equals(""))
        {
            p_user_name.setError("please enter email");
            valid++;
        }

        if(p_login_password.getText().toString().trim().equals(""))
        {
            p_login_password.setError("please enter password");
            valid++;
        }


    }

    public void checkLogin()
    {
        StringRequest request = new StringRequest(Request.Method.POST, Urls.principalLogin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.trim().equals("Login Successful"))
                {
                    Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
                    Intent sc=new Intent(com.karyotype.collegeerp.principal.Login.this,com.karyotype.collegeerp.principal.MainActivity.class);
                    startActivity(sc);
                }
                else
                {
                    Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                                myPd_ring.dismiss();
                if (error instanceof NetworkError) {
                    Toast.makeText(Login.this, "Cannot connect to Internet...Please check your connection!", Toast.LENGTH_LONG).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(Login.this, "The server could not be found. Please try again after some time!!", Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(Login.this, "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(Login.this, "Parsing error! Please try again after some time !!", Toast.LENGTH_LONG).show();

                } else if (error instanceof NoConnectionError) {
                    Toast.makeText(Login.this, "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(Login.this, "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
                }
            }

            {

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();




                parameters.put("p_user_name", p_user_name.getText().toString());

                parameters.put("p_password", p_login_password.getText().toString());


                return parameters;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        requestQueue.add(request);

    }


}
