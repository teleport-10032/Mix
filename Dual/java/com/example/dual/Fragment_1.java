package com.example.dual;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_1 extends Fragment {

    ImageView scan;
    TextView t111 , t112 , t113 , t114 , t121 , t122 , t123 , t124 , t131 , t132 , t133 , t134;
    TextView t141 , t142 , t143 , t144 , t151 , t152 , t153 , t154 , t161 , t162;

    ImageView p111,p112,p113,p114,p121,p122,p123,p124,p131,p132,p133,p134,p141,p142,p143,p144;
    ImageView p151,p152,p153,p154,p161,p162;
    ImageView p11,p12,p13,yijianshangwang;



    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.f1, null);


        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        scan = getActivity().findViewById(R.id.scan);
        scan.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });

        p111 = getActivity().findViewById(R.id.p111);
        p111.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });

        p112 = getActivity().findViewById(R.id.p112);
        p112.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });

        p113 = getActivity().findViewById(R.id.p113);
        p113.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });

        p114 = getActivity().findViewById(R.id.p114);
        p114.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });

        p121 = getActivity().findViewById(R.id.p121);
        p121.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });

        p122 = getActivity().findViewById(R.id.p122);
        p122.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });

        p123 = getActivity().findViewById(R.id.p123);
        p123.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });

        p124 = getActivity().findViewById(R.id.p124);
        p124.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });



        p131 = getActivity().findViewById(R.id.p131);
        p131.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });

        p132 = getActivity().findViewById(R.id.p132);
        p132.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });


        p133 = getActivity().findViewById(R.id.p133);
        p133.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });


        p134 = getActivity().findViewById(R.id.p134);
        p134.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });

        p141 = getActivity().findViewById(R.id.p141);
        p141.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });

        p142 = getActivity().findViewById(R.id.p142);
        p142.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });

        p143 = getActivity().findViewById(R.id.p143);
        p143.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });



        p144 = getActivity().findViewById(R.id.p144);
        p144.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });



        p151 = getActivity().findViewById(R.id.p151);
        p151.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), card.class);
                startActivity(intent);
            }
        });

        p152 = getActivity().findViewById(R.id.p152);
        p152.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });


        p153 = getActivity().findViewById(R.id.p153);
        p153.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });


        p154 = getActivity().findViewById(R.id.p154);
        p154.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });


        p161 = getActivity().findViewById(R.id.p161);
        p161.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });
        p162 = getActivity().findViewById(R.id.p162);
        p162.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });


        p11 = getActivity().findViewById(R.id.p11);
        p11.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });


        p12 = getActivity().findViewById(R.id.p12);
        p12.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });


        p13 = getActivity().findViewById(R.id.p13);
        p13.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });

        yijianshangwang = getActivity().findViewById(R.id.yijianshangwang);
        yijianshangwang.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_0.class);
                startActivity(intent);
            }
        });

    }
}