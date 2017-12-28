package com.example.user.smartvillage.Activity.dashboard_user.request;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.smartvillage.Model.DefaultModel;
import com.example.user.smartvillage.R;
import com.example.user.smartvillage.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestFragment extends Fragment {
    DefaultModel request_pembangunan;

    public RequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View request = inflater.inflate(R.layout.fragment_request, container, false);
        Button bt_request = (Button) request.findViewById(R.id.button_request);
        final EditText et_judul = (EditText) request.findViewById(R.id.judul_request);
        final EditText et_deskripsi = (EditText) request.findViewById(R.id.deskripsi_request);

        bt_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String set_judul = et_judul.getText().toString();
                String set_deskripsi = et_deskripsi.getText().toString();

                ApiService.service_post.postRequest("Bearer bmFuZGE=", set_judul, set_deskripsi, "1").enqueue(new Callback<DefaultModel>() {
                    @Override
                    public void onResponse(Call<DefaultModel> call, Response<DefaultModel> response) {
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<DefaultModel> call, Throwable t) {

                    }
                });
            }
        });
        return request;
    }

}
