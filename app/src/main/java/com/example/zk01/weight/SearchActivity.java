package com.example.zk01.weight;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.zk01.R;

public class SearchActivity extends RelativeLayout {
    private EditText bar_ed;
    private Button bar_btn;

    public SearchActivity(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.bar, this);
        bar_ed = findViewById(R.id.bar_ed);
        bar_btn = findViewById(R.id.bar_btn);
    }
}
