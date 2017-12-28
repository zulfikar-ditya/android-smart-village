package com.example.user.smartvillage.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.smartvillage.Controller.AppConfig;
import com.example.user.smartvillage.Controller.AppController;
import com.example.user.smartvillage.Model.DefaultModel;
import com.example.user.smartvillage.Model.User;
import com.example.user.smartvillage.R;
import com.example.user.smartvillage.service.ApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

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
                toSignIn();
            }
        });

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snik = et_nik.getText().toString();
                susername = et_username.getText().toString();
                spassword = et_password.getText().toString();
                sconfirmpass = et_confirmpass.getText().toString();
                doSignUp(snik, susername, spassword, sconfirmpass);
            }
        });
    }

    private void doSignUp(String snik_data, String susername_data, String spassword_data, String sconfirmpass_data){
        final ProgressDialog pDialog = new ProgressDialog(SignUpActivity.this, R.style.Theme_AppCompat_DayNight_Dialog);
        pDialog.setMessage("Registering..");
        pDialog.show();
        ApiService.service_post.postSignUp(snik_data, susername_data, spassword_data, sconfirmpass_data).enqueue(new Callback<DefaultModel>() {
            @Override
            public void onResponse(Call<DefaultModel> call, retrofit2.Response<DefaultModel> response) {
                if(response.body().isStatus()){
                    pDialog.dismiss();
                    toSignIn();
                }else{
                    Toast.makeText(SignUpActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    pDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<DefaultModel> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void toSignIn(){
        //TODO PINDAH INTENT KE SIGNIN
        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(intent);
    }
}
