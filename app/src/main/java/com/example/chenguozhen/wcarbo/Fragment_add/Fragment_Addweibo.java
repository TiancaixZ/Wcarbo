package com.example.chenguozhen.wcarbo.Fragment_add;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.chenguozhen.wcarbo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenguozhen on 2018/1/26.
 */

public class Fragment_Addweibo extends Fragment{

    @BindView(R.id.edittext) EditText editText;
    @BindView(R.id.photo) ImageButton photo;
    @BindView(R.id.aite) ImageButton aite;
    @BindView(R.id.mention) ImageButton mention;
    @BindView(R.id.send) ImageButton send;

    public static final int Addweibo = 100;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_addweibo,container,false);
        ButterKnife.bind(this, view);

        return view;
    }
}
