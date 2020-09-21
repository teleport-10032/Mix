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

public class MainActivity extends FragmentActivity {


    private TextView main_title;

    private LinearLayout main_body;
    private LinearLayout main_bottom_bar;

    private TextView bottom_bar_text_1;
    private ImageView bottom_bar_image_1;
    private RelativeLayout bottom_bar_1_btn;

    private TextView bottom_bar_text_2;
    private ImageView bottom_bar_image_2;
    private RelativeLayout bottom_bar_2_btn;

    private TextView bottom_bar_text_3;
    private ImageView bottom_bar_image_3;
    private RelativeLayout bottom_bar_3_btn;

    private TextView bottom_bar_text_4;
    private ImageView bottom_bar_image_4;
    private RelativeLayout bottom_bar_4_btn;

    private TextView bottom_bar_text_5;
    private ImageView bottom_bar_image_5;
    private RelativeLayout bottom_bar_5_btn;
    private Object Fragment_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setMain();
        //title
//        main_title=findViewById(R.id.main_title);
        //main_body
        main_body=findViewById(R.id.main_body);
        //bottom
        main_bottom_bar=findViewById(R.id.main_bottom_bar);
        //text image
        bottom_bar_text_1=findViewById(R.id.bottom_bar_text_1);
        bottom_bar_image_1=findViewById(R.id.bottom_bar_image_1);
        bottom_bar_text_2=findViewById(R.id.bottom_bar_text_2);
        bottom_bar_image_2=findViewById(R.id.bottom_bar_image_2);
        bottom_bar_text_3=findViewById(R.id.bottom_bar_text_3);
        bottom_bar_image_3=findViewById(R.id.bottom_bar_image_3);
        bottom_bar_text_4=findViewById(R.id.bottom_bar_text_4);
        bottom_bar_image_4=findViewById(R.id.bottom_bar_image_4);
        bottom_bar_text_5=findViewById(R.id.bottom_bar_text_5);
        bottom_bar_image_5=findViewById(R.id.bottom_bar_image_5);

        bottom_bar_1_btn=findViewById(R.id.bottom_bar_1_btn);
        bottom_bar_2_btn=findViewById(R.id.bottom_bar_2_btn);
        bottom_bar_3_btn=findViewById(R.id.bottom_bar_3_btn);
        bottom_bar_4_btn=findViewById(R.id.bottom_bar_4_btn);
        bottom_bar_5_btn=findViewById(R.id.bottom_bar_5_btn);



    }


    private void setSelectStatus(int index) {
        switch (index){
            case 0:
                bottom_bar_image_1 .setImageResource(R.drawable.main_button_1_selected);
                bottom_bar_image_2.setImageResource(R.drawable.main_button_2);
                bottom_bar_image_3.setImageResource(R.drawable.main_button_3);
                bottom_bar_image_4.setImageResource(R.drawable.main_button_4);
                bottom_bar_image_5.setImageResource(R.drawable.main_button_5);
                bottom_bar_text_1.setTextColor(Color.parseColor("#EF8400"));
                bottom_bar_text_2.setTextColor(Color.parseColor("#838383"));
                bottom_bar_text_3.setTextColor(Color.parseColor("#838383"));
                bottom_bar_text_4.setTextColor(Color.parseColor("#838383"));
                bottom_bar_text_5.setTextColor(Color.parseColor("#838383"));
                break;
            case 1:
                bottom_bar_image_1 .setImageResource(R.drawable.main_button_1);
                bottom_bar_image_2.setImageResource(R.drawable.main_button_2_selected);
                bottom_bar_image_3.setImageResource(R.drawable.main_button_3);
                bottom_bar_image_4.setImageResource(R.drawable.main_button_4);
                bottom_bar_image_5.setImageResource(R.drawable.main_button_5);
                bottom_bar_text_1.setTextColor(Color.parseColor("#838383"));
                bottom_bar_text_2.setTextColor(Color.parseColor("#EF8400"));
                bottom_bar_text_3.setTextColor(Color.parseColor("#838383"));
                bottom_bar_text_4.setTextColor(Color.parseColor("#838383"));
                bottom_bar_text_5.setTextColor(Color.parseColor("#838383"));
                break;
            case 2:
                bottom_bar_image_1 .setImageResource(R.drawable.main_button_1);
                bottom_bar_image_2.setImageResource(R.drawable.main_button_2);
                bottom_bar_image_3.setImageResource(R.drawable.main_button_3_selected);
                bottom_bar_image_4.setImageResource(R.drawable.main_button_4);
                bottom_bar_image_5.setImageResource(R.drawable.main_button_5);
                bottom_bar_text_1.setTextColor(Color.parseColor("#838383"));
                bottom_bar_text_2.setTextColor(Color.parseColor("#838383"));
                bottom_bar_text_3.setTextColor(Color.parseColor("#EF8400"));
                bottom_bar_text_4.setTextColor(Color.parseColor("#838383"));
                bottom_bar_text_5.setTextColor(Color.parseColor("#838383"));
                break;
            case 3:
                bottom_bar_image_1 .setImageResource(R.drawable.main_button_1);
                bottom_bar_image_2.setImageResource(R.drawable.main_button_2);
                bottom_bar_image_3.setImageResource(R.drawable.main_button_3);
                bottom_bar_image_4.setImageResource(R.drawable.main_button_4_selected);
                bottom_bar_image_5.setImageResource(R.drawable.main_button_5);
                bottom_bar_text_1.setTextColor(Color.parseColor("#838383"));
                bottom_bar_text_2.setTextColor(Color.parseColor("#838383"));
                bottom_bar_text_3.setTextColor(Color.parseColor("#838383"));
                bottom_bar_text_4.setTextColor(Color.parseColor("#EF8400"));
                bottom_bar_text_5.setTextColor(Color.parseColor("#838383"));
                break;
            case 4:
                bottom_bar_image_1 .setImageResource(R.drawable.main_button_1);
                bottom_bar_image_2.setImageResource(R.drawable.main_button_2);
                bottom_bar_image_3.setImageResource(R.drawable.main_button_3);
                bottom_bar_image_4.setImageResource(R.drawable.main_button_4);
                bottom_bar_image_5.setImageResource(R.drawable.main_button_5_selected);
                bottom_bar_text_1.setTextColor(Color.parseColor("#838383"));
                bottom_bar_text_2.setTextColor(Color.parseColor("#838383"));
                bottom_bar_text_3.setTextColor(Color.parseColor("#838383"));
                bottom_bar_text_4.setTextColor(Color.parseColor("#838383"));
                bottom_bar_text_5.setTextColor(Color.parseColor("#EF8400"));
                break;
        }
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bottom_bar_1_btn:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new Fragment_1()).commit();
                setSelectStatus(0);
                break;
            case R.id.bottom_bar_2_btn:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new Fragment_2()).commit();
                setSelectStatus(1);
                break;
            case R.id.bottom_bar_3_btn:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new Fragment_3()).commit();
                setSelectStatus(2);
                break;
            case R.id.bottom_bar_4_btn:
                setSelectStatus(3);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new Fragment_4()).commit();
                break;
            case R.id.bottom_bar_5_btn:
                setSelectStatus(4);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new Fragment_5()).commit();
                break;
        }
    }

    //用于打开初始页面
    private void setMain() {
        //getSupportFragmentManager() -> beginTransaction() -> add -> (R.id.main_boy,显示课程 new CourseFragment()
        this.getSupportFragmentManager().beginTransaction().add(R.id.main_body,new Fragment_1()).commit();
    }


}