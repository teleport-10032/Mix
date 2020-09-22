package com.example.dual;

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

public class card extends FragmentActivity {


    ImageView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card);

        t = findViewById(R.id.back);

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMain();
            }
        });
    }

    //用于打开初始页面
    private void setMain() {
        //getSupportFragmentManager() -> beginTransaction() -> add -> (R.id.main_boy,显示课程 new CourseFragment()
        this.getSupportFragmentManager().beginTransaction().add(R.id.main_body,new Fragment_1()).commit();
    }

}
