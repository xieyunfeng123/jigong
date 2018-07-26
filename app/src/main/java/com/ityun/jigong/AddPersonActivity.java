package com.ityun.jigong;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2018/7/23 0023.
 */

public class AddPersonActivity extends AppCompatActivity {

    @BindView(R.id.input_add)
    public EditText input_add;

    @BindView(R.id.sure_add)
    public Button sure_add;

    private Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        dialog = ProgressDialog.create(this, "");
    }

    @OnClick(R.id.sure_add)
    public void onClick() {
        dialog.show();
        if (TextUtils.isEmpty(input_add.getText().toString().trim())) {
            toast("名字不能为空!");
            dialog.dismiss();
            return;
        }
        BmobQuery<Person> query = new BmobQuery<>();
        query.addWhereEqualTo("name", input_add.getText().toString().trim());
        query.setLimit(100);
        query.findObjects(new FindListener<Person>() {
            @Override
            public void done(List<Person> object, BmobException e) {
                if (e == null) {
                    if (object.size() == 0) {
                        addPerson();
                    } else {
                        dialog.dismiss();
                        toast("该工友已存在!");
                    }

                } else {
                    dialog.dismiss();
                    toast("添加失败!");
                }
            }
        });
    }


    public void toast(String message) {
        runOnUiThread(() -> Toast.makeText(AddPersonActivity.this, message, Toast.LENGTH_SHORT).show());
    }


    private void addPerson() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Person person = new Person();
                person.setName(input_add.getText().toString().trim());
                person.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        dialog.dismiss();
                        if (e == null) {
                            toast("添加成功!");
                            person.setObjectId(s);
                            EventBus.getDefault().postSticky(person);
                            finish();
                        } else {
                            toast("添加失败!");
                        }
                    }
                });
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void emptyEvent(MyEvent event) {

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
