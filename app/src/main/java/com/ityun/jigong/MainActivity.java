package com.ityun.jigong;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.activity_home_frame)
    public FrameLayout activity_home_frame;


    @BindView(R.id.home_phoneBook)
    public RadioButton home_phoneBook;


    @BindView(R.id.home_me)
    public RadioButton home_me;


    private MeFragment meFragment;

    private MessageFragment messageFragment;

    private Fragment nowFragment;

    //a8f0742b2e0fb87662607ed6388f9bb2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Bmob.initialize(this, "a8f0742b2e0fb87662607ed6388f9bb2");
        initFragment();
    }


    /**
     * 初始化fragment
     */
    private void initFragment() {
        messageFragment = new MessageFragment();
        meFragment = new MeFragment();
        nowFragment = messageFragment;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.activity_home_frame, messageFragment).commit();
    }

    private void changeFragment(Fragment fromFragment, Fragment toFragment) {
        if (nowFragment == null || nowFragment != toFragment) {
            nowFragment = toFragment;
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (toFragment.isAdded() == false) {
            ft.hide(fromFragment).add(R.id.activity_home_frame, toFragment).commit();
        } else {
            ft.hide(fromFragment).show(toFragment).commit();
        }
    }

    @OnClick({R.id.home_phoneBook, R.id.home_me})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_phoneBook:
                changeFragment(nowFragment, messageFragment);
                nowFragment = messageFragment;
                break;
            case R.id.home_me:
                changeFragment(nowFragment, meFragment);
                nowFragment = meFragment;
                break;
            default:
                break;
        }
    }

}
