package com.example.user.smartvillage.Activity.dashboard_user.list;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.smartvillage.Adapter.DataPembangunanAdapter;
import com.example.user.smartvillage.Controller.AppConfig;
import com.example.user.smartvillage.Controller.AppController;
import com.example.user.smartvillage.Controller.SessionManager;
import com.example.user.smartvillage.Model.PembangunanModel;
import com.example.user.smartvillage.R;
import com.example.user.smartvillage.service.ApiService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    RecyclerView rvDataPembangunan;
    PembangunanModel listdataPembangunan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        rvDataPembangunan = (RecyclerView) rootView.findViewById(R.id.rv_pembangunan);
        LinearLayoutManager llmData = new LinearLayoutManager(getActivity().getApplicationContext());
        rvDataPembangunan.setLayoutManager(llmData);

        ApiService.service_get.getPembangunan("Bearer bmFuZGE=").enqueue(new Callback<PembangunanModel>() {
            @Override
            public void onResponse(Call<PembangunanModel> call, Response<PembangunanModel> response) {
                listdataPembangunan = response.body();
                DataPembangunanAdapter adapter = new DataPembangunanAdapter(getContext(), listdataPembangunan);
                rvDataPembangunan.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<PembangunanModel> call, Throwable t) {

            }
        });


        return rootView;
    }
}
