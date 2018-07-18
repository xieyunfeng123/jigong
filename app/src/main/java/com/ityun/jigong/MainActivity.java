package com.ityun.jigong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity {


    //a8f0742b2e0fb87662607ed6388f9bb2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "a8f0742b2e0fb87662607ed6388f9bb2");
    }
}
