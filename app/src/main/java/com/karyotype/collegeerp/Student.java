package com.karyotype.collegeerp;

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
import com.karyotype.collegeerp.links.Urls;
import com.karyotype.collegeerp.principal.Login;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Lenovo on 11/19/2017.
 */

public class Student extends AppCompatActivity {



    int valid=0;




    EditText s_name,s_roll,s_email,s_mobile,s_user_name,s_password,s_confirm_password,s_address,s_division,s_year,s_branch,s_shift;
    Button s_submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_registration);

        s_name= (EditText) findViewById(R.id.s_name);
        s_year= (EditText) findViewById(R.id.s_year);
        s_branch= (EditText) findViewById(R.id.s_branch);
        s_shift= (EditText) findViewById(R.id.s_shift);
        s_division= (EditText) findViewById(R.id.s_division);
        s_address= (EditText) findViewById(R.id.s_address);
        s_roll= (EditText) findViewById(R.id.s_roll);
        s_email= (EditText)findViewById(R.id.s_email);
        s_user_name= (EditText)findViewById(R.id.s_user_name);
        s_password= (EditText)findViewById(R.id.s_password);
        s_confirm_password= (EditText)findViewById(R.id.s_confirm_password);
        s_mobile= (EditText)findViewById(R.id.s_mobile);
        s_submit= (Button) findViewById(R.id.s_submit);





        s_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valid=0;
                validation();

                if (valid>0)
                {
                    Toast.makeText(Student.this, "Please Fill all the fields", Toast.LENGTH_LONG).show();
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
        if(s_name.getText().toString().trim().equals(""))
        {
            s_name.setError("please enter name");
            valid++;
        }

        if(s_email.getText().toString().trim().equals(""))
        {
            s_email.setError("please enter Email");
            valid++;
        }

        if (s_mobile.getText().toString().length() < 10 || s_mobile.getText().toString().length() >= 12) {

            s_mobile.setError("Please enter valid phone number");
            // Toast.makeText(SignUpActivity.this,"Please enter valid phone number",Toast.LENGTH_SHORT).show();
            valid++;
        }

        if(s_user_name.getText().toString().trim().equals(""))
        {
            s_user_name.setError("please enter user name");
            valid++;
        }

        if (!checkEmail(s_email.getText().toString())) {
            s_email.setError("Invalid Email");

            // Toast.makeText(SignUpActivity.this, "Paswword lenght should be greater than 6 char", Toast.LENGTH_SHORT).show();
            valid++;

        }


        if(s_password.getText().toString().trim().equals(""))
        {
            s_password.setError("please enter your password");
            valid++;
        }

        if (!isValidPassword(s_password.getText().toString())) {
            s_password.setError("Password length should be greater than 4 character");

            // Toast.makeText(SignUpActivity.this, "Paswword lenght should be greater than 6 char", Toast.LENGTH_SHORT).show();
            valid++;

        }

        if(!s_confirm_password.getText().toString().equals(s_password.getText().toString()))
        {

            s_confirm_password.setError("Password did not matched");
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

        StringRequest request = new StringRequest(Request.Method.POST, Urls.studentRegister, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {




                Toast.makeText(Student.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(Student.this,Login.class);
                startActivity(intent);
                finish();




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                    Toast.makeText(Student.this, "Cannot connect to Internet...Please check your connection!", Toast.LENGTH_LONG).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(Student.this, "The server could not be found. Please try again after some time!!", Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(Student.this, "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(Student.this, "Parsing error! Please try again after some time !!", Toast.LENGTH_LONG).show();

                } else if (error instanceof NoConnectionError) {
                    Toast.makeText(Student.this, "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(Student.this, "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
                }

            }

            {

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();


                parameters.put("s_name", s_name.getText().toString());
                parameters.put("s_roll", s_roll.getText().toString());
                parameters.put("s_email", s_email.getText().toString());
                parameters.put("s_contact", s_mobile.getText().toString());
                parameters.put("s_user_name", s_user_name.getText().toString());

                parameters.put("s_password", s_password.getText().toString());
                parameters.put("s_confirm_password", s_confirm_password.getText().toString());

                parameters.put("s_address", s_address.getText().toString());

                parameters.put("s_division", s_division.getText().toString());

                parameters.put("s_year", s_year.getText().toString());
                parameters.put("s_branch", s_branch.getText().toString());
                parameters.put("s_picture", "samir");
                parameters.put("s_shift", s_shift.getText().toString());




                return parameters;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }
}
