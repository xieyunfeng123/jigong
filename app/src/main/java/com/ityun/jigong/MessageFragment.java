package com.ityun.jigong;


import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2018/5/22 0022.
 * 消息
 */

public class MessageFragment extends Fragment {

    @BindView(R.id.person_list)
    public RecyclerView person_list;


    @BindView(R.id.setting)
    public FloatingActionButton setting;

    @BindView(R.id.load_ui)
    public RelativeLayout load_ui;

    private List<Person> mlist = new ArrayList<>();

    private HomeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        adapter = new HomeAdapter(getActivity());
        adapter.setData(mlist);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        person_list.setAdapter(adapter);
        person_list.setLayoutManager(manager);
        person_list.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        adapter.notifyDataSetChanged();
        searchPerson();
        return view;
    }

    @OnClick(R.id.setting)
    public void addIntentClick() {
        Intent intent = new Intent(getActivity(), AddPersonActivity.class);
        startActivity(intent);
    }


    private void searchPerson() {
        BmobQuery<Person> query = new BmobQuery<>();
        query.setLimit(100);
        query.findObjects(new FindListener<Person>() {
            @Override
            public void done(List<Person> object, BmobException e) {
                load_ui.setVisibility(View.GONE);
                if (e == null) {
                    for (Person person : object) {
                        mlist.add(person);
                    }
                    getActivity().runOnUiThread(() -> {
                        adapter.notifyDataSetChanged();
                    });
                } else {
                    toast("请检测网络！");
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void addPersonEvent(Person person) {
        mlist.add(person);
        adapter.notifyDataSetChanged();
    }


    public void toast(String message) {
        getActivity().runOnUiThread(() -> Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show());
    }


    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
