package com.karyotype.collegeerp.hod;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.karyotype.collegeerp.principal.Login;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Lenovo on 11/19/2017.
 */

public class HodRegistration extends AppCompatActivity {


    int valid = 0;

    EditText h_name, h_email, h_mobile, h_user_name, h_password, h_confirm_password;
    Button h_submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hod_registration);

        h_name = (EditText) findViewById(R.id.h_name);
        h_email = (EditText) findViewById(R.id.h_email);
        h_user_name = (EditText) findViewById(R.id.h_user_name);
        h_password = (EditText) findViewById(R.id.h_password);
        h_confirm_password = (EditText) findViewById(R.id.h_confirm_password);

        h_mobile = (EditText) findViewById(R.id.h_mobile);

        h_submit = (Button) findViewById(R.id.h_submit);


        h_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valid = 0;
                validation();

                if (valid > 0) {
                    Toast.makeText(HodRegistration.this, "Please Fill all the fields", Toast.LENGTH_LONG).show();
                } else {

                    register();
                }

            }
        });


    }

    //***************************************Validation starts here*****************************************
    public void validation() {
        if (h_name.getText().toString().trim().equals("")) {
            h_name.setError("please enter name");
            valid++;
        }

        if (h_email.getText().toString().trim().equals("")) {
            h_email.setError("please enter Email");
            valid++;
        }

        if (h_mobile.getText().toString().length() < 10 || h_mobile.getText().toString().length() >= 12) {

            h_mobile.setError("Please enter valid phone number");
            // Toast.makeText(SignUpActivity.this,"Please enter valid phone number",Toast.LENGTH_SHORT).show();
            valid++;
        }

        if (h_user_name.getText().toString().trim().equals("")) {
            h_user_name.setError("please enter user name");
            valid++;
        }

        if (!checkEmail(h_email.getText().toString())) {
            h_email.setError("Invalid Email");

            // Toast.makeText(SignUpActivity.this, "Paswword lenght should be greater than 6 char", Toast.LENGTH_SHORT).show();
            valid++;

        }


        if (h_password.getText().toString().trim().equals("")) {
            h_password.setError("please enter your password");
            valid++;
        }

        if (!isValidPassword(h_password.getText().toString())) {
            h_password.setError("Password length should be greater than 4 character");
            // Toast.makeText(SignUpActivity.this, "Paswword lenght should be greater than 6 char", Toast.LENGTH_SHORT).show();
            valid++;

        }

        if (!h_confirm_password.getText().toString().equals(h_password.getText().toString())) {

            h_confirm_password.setError("Password did not matched");
            valid++;
        }


    }

    private boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                    "(" +
                    "." +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                    ")+"
    );

    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 4) {
            return true;
        }
        return false;

    }

    //**************************************************************Validation Ends Here********************************


    public void register() {

        StringRequest request = new StringRequest(Request.Method.POST, Urls.hodRegister, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Toast.makeText(HodRegistration.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(HodRegistration.this, Login.class);
                startActivity(intent);
                finish();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                    Toast.makeText(HodRegistration.this, "Cannot connect to Internet...Please check your connection!", Toast.LENGTH_LONG).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(HodRegistration.this, "The server could not be found. Please try again after some time!!", Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(HodRegistration.this, "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(HodRegistration.this, "Parsing error! Please try again after some time !!", Toast.LENGTH_LONG).show();

                } else if (error instanceof NoConnectionError) {
                    Toast.makeText(HodRegistration.this, "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(HodRegistration.this, "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
                }

            }

            {

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();


                parameters.put("h_name", h_name.getText().toString());
                parameters.put("h_user_name", h_user_name.getText().toString());
                parameters.put("h_contact", h_mobile.getText().toString());
                parameters.put("h_email", h_email.getText().toString());
                parameters.put("h_password", h_password.getText().toString());


                return parameters;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }
}