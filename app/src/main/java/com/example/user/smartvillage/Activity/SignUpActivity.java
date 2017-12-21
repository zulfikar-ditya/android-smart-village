package com.example.user.smartvillage.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.user.smartvillage.Controller.AppConfig;
import com.example.user.smartvillage.Controller.AppController;
import com.example.user.smartvillage.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    Button button_signup , button_link_signin;
    EditText et_nik, et_username,et_confirmpass, et_password;
    String snik, susername, sconfirmpass, spassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        button_signup = (Button) findViewById(R.id.button_signup);
        button_link_signin = (Button) findViewById(R.id.Button_link_signin);

        et_nik = (EditText) findViewById(R.id.Signup_NIK);
        et_username = (EditText) findViewById(R.id.Signup_username);
        et_confirmpass = (EditText) findViewById(R.id.Signup_Konfimasi_password);
        et_password = (EditText) findViewById(R.id.Signup_password);

        button_link_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO PINDAH INTENT KE SIGNIN
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO SIGNUP
                snik = et_nik.getText().toString();
                susername = et_username.getText().toString();
                spassword = et_password.getText().toString();
                sconfirmpass = et_confirmpass.getText().toString();

                if (spassword.length() > 7) {
                    if (spassword.equals(sconfirmpass)) {
                        doSignUp(snik, susername, spassword);
                    } else {
                        Toast.makeText(getApplicationContext(), "confirmasi password tidak sama", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(SignUpActivity.this, "password harus lebih dari 8", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void doSignUp(final String snik,final String susername,final String spassword){
        final ProgressDialog pDialog = new ProgressDialog(SignUpActivity.this, R.style.Theme_AppCompat_DayNight_Dialog);
        pDialog.setMessage("Registering..");
        pDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_REGISTER,
                //TODO LISTENER BUKA JEMBATAN KOMUNIKASI
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //TODO SETELAH BERHASIL NGIRIM PARAMETER
                            JSONObject obj = new JSONObject(response);
                            //if no error in response
                            if (obj.getBoolean("status")) {
                                pDialog.dismiss();
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                //TODO JIKA JEMBATAN KOMUNIKASI ERROR ATAU ENGGA
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            // TODO KODE DIBAWAH INI NGRIM PARAMETER KE URL WEB
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nik", snik);
                params.put("username", susername);
                params.put("password", spassword);
                return params;
            }
        };
        AppController.getInstance(this).addToRequestQueue(stringRequest);
    }
}
