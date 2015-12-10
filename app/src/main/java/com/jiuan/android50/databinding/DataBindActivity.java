package com.jiuan.android50.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jiuan.android50.R;

/**
 * Created by wu on 2015/12/10.
 */
public class DataBindActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.jiuan.android50.databinding.ActivityDatabindBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_databind);
        Person person=new Person();
        person.name="wu";
        person.phone="110";
        person.isVisible=false;
        binding.setPerson(person);
    }
}
