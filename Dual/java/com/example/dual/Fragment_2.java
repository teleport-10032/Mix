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

public class Fragment_2 extends Fragment {
    @Nullable
    ImageView p211,p212,p213,p214,p221,p222,p223,p224,p231,p232,p233,p234,p241;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragment2 = inflater.inflate(R.layout.f2, null);
        return fragment2;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        p211 = getActivity().findViewById(R.id.p211);
        p211.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), process.class);
                startActivity(intent);
            }
        });


        p212 = getActivity().findViewById(R.id.p212);
        p212.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), process.class);
                startActivity(intent);
            }
        });




        p213 = getActivity().findViewById(R.id.p213);
        p213.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), process.class);
                startActivity(intent);
            }
        });




        p214 = getActivity().findViewById(R.id.p214);
        p214.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });


        p221 = getActivity().findViewById(R.id.p221);
        p221.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });


        p222 = getActivity().findViewById(R.id.p222);
        p222.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });


        p223 = getActivity().findViewById(R.id.p223);
        p223.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });



        p224 = getActivity().findViewById(R.id.p224);
        p224.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });



        p231 = getActivity().findViewById(R.id.p231);
        p231.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });



        p232 = getActivity().findViewById(R.id.p232);
        p232.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });



        p233 = getActivity().findViewById(R.id.p233);
        p233.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });



        p234 = getActivity().findViewById(R.id.p234);
        p234.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });

        p241 = getActivity().findViewById(R.id.p241);
        p241.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });
    }

}