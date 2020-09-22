package com.example.dual;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Fragment_5 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.f5, null);
        return fragment;

    }

    ImageView p51;
    LinearLayout l51 , l52 , l53 , l54 , l55;

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        p51 = getActivity().findViewById(R.id.p51);
        p51.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });

        l51 = getActivity().findViewById(R.id.l51);
        l51.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });

        l52 = getActivity().findViewById(R.id.l52);
        l52.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });

        l53 = getActivity().findViewById(R.id.l53);
        l53.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });

        l54 = getActivity().findViewById(R.id.l54);
        l54.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });

        l55 = getActivity().findViewById(R.id.l55);
        l55.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {

            }
        });


    }
}