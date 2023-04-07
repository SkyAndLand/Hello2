package com.example.plant.fragment;

import android.view.View;
import android.widget.TextView;

import com.example.plant.R;

public class NewsFragment extends BaseFragment {

    private TextView tv;

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_news,null);
        tv = (TextView) view.findViewById(R.id.tv);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }

}

