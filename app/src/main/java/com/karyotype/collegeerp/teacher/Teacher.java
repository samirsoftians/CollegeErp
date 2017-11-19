package com.karyotype.collegeerp.teacher;

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

public class Teacher extends AppCompatActivity {



    int valid=0;

    EditText t_name,t_email,t_mobile,t_user_name,t_password,t_confirm_password;
    Button t_submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_registration);

        t_name= (EditText) findViewById(R.id.t_name);
        t_email= (EditText)findViewById(R.id.t_email);
        t_user_name= (EditText)findViewById(R.id.t_user_name);
        t_password= (EditText)findViewById(R.id.t_password);
        t_confirm_password= (EditText)findViewById(R.id.t_confirm_password);

        t_mobile= (EditText)findViewById(R.id.t_mobile);

        t_submit= (Button) findViewById(R.id.t_submit);





        t_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valid=0;
                validation();

                if (valid>0)
                {
                    Toast.makeText(Teacher.this, "Please Fill all the fields", Toast.LENGTH_LONG).show();
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
        if(t_name.getText().toString().trim().equals(""))
        {
            t_name.setError("please enter name");
            valid++;
        }

        if(t_email.getText().toString().trim().equals(""))
        {
            t_email.setError("please enter Email");
            valid++;
        }

        if (t_mobile.getText().toString().length() < 10 || t_mobile.getText().toString().length() >= 12) {

            t_mobile.setError("Please enter valid phone number");
            // Toast.makeText(SignUpActivity.this,"Please enter valid phone number",Toast.LENGTH_SHORT).show();
            valid++;
        }

        if(t_user_name.getText().toString().trim().equals(""))
        {
            t_user_name.setError("please enter user name");
            valid++;
        }

        if (!checkEmail(t_email.getText().toString())) {
            t_email.setError("Invalid Email");

            // Toast.makeText(SignUpActivity.this, "Paswword lenght should be greater than 6 char", Toast.LENGTH_SHORT).show();
            valid++;

        }


        if(t_password.getText().toString().trim().equals(""))
        {
            t_password.setError("please enter your password");
            valid++;
        }

        if (!isValidPassword(t_password.getText().toString())) {
            t_password.setError("Password length should be greater than 4 character");
            // Toast.makeText(SignUpActivity.this, "Paswword lenght should be greater than 6 char", Toast.LENGTH_SHORT).show();
            valid++;

        }

        if(!t_confirm_password.getText().toString().equals(t_password.getText().toString()))
        {

            t_confirm_password.setError("Password did not matched");
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

        StringRequest request = new StringRequest(Request.Method.POST, Urls.teacherRegister, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {




                Toast.makeText(Teacher.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(Teacher.this,Login.class);
                startActivity(intent);
                finish();




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                    Toast.makeText(Teacher.this, "Cannot connect to Internet...Please check your connection!", Toast.LENGTH_LONG).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(Teacher.this, "The server could not be found. Please try again after some time!!", Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(Teacher.this, "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(Teacher.this, "Parsing error! Please try again after some time !!", Toast.LENGTH_LONG).show();

                } else if (error instanceof NoConnectionError) {
                    Toast.makeText(Teacher.this, "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(Teacher.this, "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
                }

            }

            {

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();



                parameters.put("t_name", t_name.getText().toString());
                parameters.put("t_user_name", t_user_name.getText().toString());
                parameters.put("t_contact", t_mobile.getText().toString());
                parameters.put("t_email", t_email.getText().toString());
                parameters.put("t_password", t_password.getText().toString());


                return parameters;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }
}
