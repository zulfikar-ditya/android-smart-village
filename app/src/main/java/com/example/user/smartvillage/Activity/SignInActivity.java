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
import com.example.user.smartvillage.Activity.dashboard_user.MainActivity;
import com.example.user.smartvillage.Controller.AppConfig;
import com.example.user.smartvillage.Controller.AppController;
import com.example.user.smartvillage.Controller.SessionManager;
import com.example.user.smartvillage.Modal.User;
import com.example.user.smartvillage.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignInActivity extends AppCompatActivity {

    Button button_signin, button_linksignup;
    EditText et_nik, et_password;
    String snik, spassword;
    Intent afterlogin;
    ProgressDialog pDialog;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        et_nik = (EditText) findViewById(R.id.nik);
        et_password = (EditText) findViewById(R.id.password);
        button_signin = (Button) findViewById(R.id.button_signin);
        button_linksignup = (Button) findViewById(R.id.button_link_signup);
        sessionManager = new SessionManager(getApplicationContext());
        button_linksignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO PINDAH INTENT KE SINGUP
                Intent activityselanjutnya = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(activityselanjutnya);
            }
        });

        button_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO LOGIN
                snik = et_nik.getText().toString().trim();
                spassword = et_password.getText().toString().trim();
                if (!snik.isEmpty() && !spassword.isEmpty())
                {
                    doSignIn(snik,spassword);
                }else {
                    Toast.makeText(SignInActivity.this, "data kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void doSignIn(final String nik, final String password) {
        pDialog = new ProgressDialog(SignInActivity.this, R.style.Theme_AppCompat_DayNight_Dialog);
        pDialog.setMessage("Authenticating..");
        pDialog.show();
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        pDialog.hide();

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);
                            pDialog.setMessage("Loading...");
                            //if no error in response
                            if (obj.getInt("code") == 1) {
                                Toast.makeText(getApplicationContext(), obj.getString("msg"), Toast.LENGTH_SHORT).show();

//                                JSONObject userJson = obj.getJSONObject("data");

                                User user = new User(

                                        obj.getInt("id"),
                                        obj.getString("fullname"),
                                        obj.getString("nik")
                                );


                                // TODO ambil data ke page selanjutnya
                                sessionManager.createLoginSession(
                                        user.getId(),
                                        user.getFullname(),
                                        user.getNik()

                                );

                                //TODO pindah Intent
                                pDialog.dismiss();
                                afterlogin = new Intent(SignInActivity.this, MainActivity.class);
                                startActivity(afterlogin);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nik", nik);
                params.put("password", password);
                return params;
            }
        };
        AppController.getInstance(this).addToRequestQueue(stringRequest);
    }
}
