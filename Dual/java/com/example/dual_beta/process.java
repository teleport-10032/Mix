package com.example.dual_beta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class process extends FragmentActivity {

    ImageView back;
    TextView t1 , t2;
    LinearLayout main_1,l1,l2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);

        l1 = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);

        t1.setTextColor(Color.parseColor("#EF8400"));
        t2.setTextColor(Color.parseColor("#7F7F7F"));

        setmain();

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_1,new process_fragment_1()).commit();
                t1.setTextColor(Color.parseColor("#EF8400"));
                t2.setTextColor(Color.parseColor("#7F7F7F"));
            }
        });


        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_1,new process_fragment_2()).commit();
                t2.setTextColor(Color.parseColor("#EF8400"));
                t1.setTextColor(Color.parseColor("#7F7F7F"));
            }
        });


    }



    //返回
    private void back() {
        this.getSupportFragmentManager().beginTransaction().add(R.id.main_body,new Fragment_1()).commit();
    }
//
    //设置默认fragment
    private void setmain() {
        this.getSupportFragmentManager().beginTransaction().add(R.id.main_1,new process_fragment_1()).commit();
    }

}