package com.ityun.jigong;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/26 0026.
 */

public class PersonDetailActivity extends AppCompatActivity {

    @BindView(R.id.back)
    RelativeLayout back;

    @BindView(R.id.user_name)
    public TextView user_name;

    @BindView(R.id.user_job_time)
    public TextView user_job_time;

    @BindView(R.id.jigong_list)
    public RecyclerView jigong_list;

    @BindView(R.id.qiandao)
    public Button qiandao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back, R.id.qiandao})
    public void setonClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.qiandao:
                break;
            default:
                break;
        }
    }

}
