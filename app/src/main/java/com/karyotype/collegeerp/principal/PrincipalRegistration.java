package com.karyotype.collegeerp.principal;

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

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * Created by Lenovo on 10/7/2017.
 */

public class PrincipalRegistration extends AppCompatActivity {



    int valid=0;

    EditText p_name,p_email,p_mobile,p_user_name,p_password,p_confirm_password;
    Button p_submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_registration);

        p_name= (EditText) findViewById(R.id.p_name);
        p_email= (EditText)findViewById(R.id.p_email);
        p_user_name= (EditText)findViewById(R.id.p_user_name);
        p_password= (EditText)findViewById(R.id.p_password);
        p_confirm_password= (EditText)findViewById(R.id.p_confirm_password);

        p_mobile= (EditText)findViewById(R.id.p_mobile);

        p_submit= (Button) findViewById(R.id.p_submit);





       p_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valid=0;
                validation();

                if (valid>0)
                {
                    Toast.makeText(PrincipalRegistration.this, "Please Fill all the fields", Toast.LENGTH_LONG).show();
                }
                else {

                    register();
                }

            }
        });



    }

    //***************************************Validation starts here*****************************************
    public void validation()
    {
        if(p_name.getText().toString().trim().equals(""))
        {
            p_name.setError("please enter name");
            valid++;
        }

        if(p_email.getText().toString().trim().equals(""))
        {
            p_email.setError("please enter Email");
            valid++;
        }

        if (p_mobile.getText().toString().length() < 10 || p_mobile.getText().toString().length() >= 12) {

            p_mobile.setError("Please enter valid phone number");
            // Toast.makeText(SignUpActivity.this,"Please enter valid phone number",Toast.LENGTH_SHORT).show();
            valid++;
        }

        if(p_user_name.getText().toString().trim().equals(""))
        {
            p_user_name.setError("please enter user name");
            valid++;
        }

        if (!checkEmail(p_email.getText().toString())) {
            p_email.setError("Invalid Email");

            // Toast.makeText(SignUpActivity.this, "Paswword lenght should be greater than 6 char", Toast.LENGTH_SHORT).show();
            valid++;

        }


        if(p_password.getText().toString().trim().equals(""))
        {
            p_password.setError("please enter your password");
            valid++;
        }

        if (!isValidPassword(p_password.getText().toString())) {
            p_password.setError("Password length should be greater than 4 character");

            // Toast.makeText(SignUpActivity.this, "Paswword lenght should be greater than 6 char", Toast.LENGTH_SHORT).show();
            valid++;

        }

        if(!p_confirm_password.getText().toString().equals(p_password.getText().toString()))
        {

            p_confirm_password.setError("Password did not matched");
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

    private boolean isValidPassword(String pass)
    {
        if (pass != null && pass.length() > 4) {
            return true;
        }
        return false;

    }

    //**************************************************************Validation Ends Here********************************






    public void register()
    {

        StringRequest request = new StringRequest(Request.Method.POST, Urls.principalRegistration, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {




                Toast.makeText(PrincipalRegistration.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(PrincipalRegistration.this,Login.class);
                startActivity(intent);
                finish();




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                    Toast.makeText(PrincipalRegistration.this, "Cannot connect to Internet...Please check your connection!", Toast.LENGTH_LONG).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(PrincipalRegistration.this, "The server could not be found. Please try again after some time!!", Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(PrincipalRegistration.this, "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(PrincipalRegistration.this, "Parsing error! Please try again after some time !!", Toast.LENGTH_LONG).show();

                } else if (error instanceof NoConnectionError) {
                    Toast.makeText(PrincipalRegistration.this, "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(PrincipalRegistration.this, "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
                }

            }

            {

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();



                parameters.put("p_name", p_name.getText().toString());
                parameters.put("p_user_name", p_user_name.getText().toString());
                parameters.put("p_contact", p_mobile.getText().toString());
                parameters.put("p_email", p_email.getText().toString());
                parameters.put("p_password", p_password.getText().toString());


                return parameters;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }
}
