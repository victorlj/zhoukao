package com.example.zk01.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zk01.R;

import java.util.List;

public class lsbj extends LinearLayout {

    private float textSize;
    private int color;
    private Context content;
//    屏幕的宽
    private int widthPixels;

    public lsbj(Context context, AttributeSet attrs) {
        super(context, attrs);
        content = context;
//        找到资源文件中设置的内容
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Mystyle);
        if(ta!=null){
            textSize =ta.getDimension(R.styleable.Mystyle_textSize, 20);
            color = ta.getColor(R.styleable.Mystyle_circleColor, 0);
        }
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        widthPixels = metrics.widthPixels;
        setOrientation(VERTICAL);
    }
    public void getData(List<String>data){
        LinearLayout linearLayout = getLinearLayout();
        for (int i = 0; i <data.size(); i++) {
            String temp = data.get(i);
            int Linwid = 0;
            int count = linearLayout.getChildCount();
            for (int j = 0; j <count; j++) {
                TextView textView = (TextView) linearLayout.getChildAt(j);
                LayoutParams params = (LayoutParams) textView.getLayoutParams();
                int leftMargin = params.leftMargin;
                textView.measure(getMeasuredWidth(),getMeasuredHeight());
                Linwid+=textView.getLeft()+textView.getRight()+textView.getMeasuredWidth()+leftMargin;
            }
            final TextView textView = gettextView();
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
            params.leftMargin=10;
            params.topMargin=2;
            textView.setLayoutParams(params);
            textView.setText(temp);
            textView.measure(getMeasuredWidth(),getMeasuredHeight());
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(content,textView.getText(), Toast.LENGTH_SHORT).show();
                }
            });
            int textWid = textView.getMeasuredWidth()+textView.getPaddingLeft()+textView.getPaddingRight();
            if(widthPixels>=Linwid+textWid){
                linearLayout.addView(textView);
            }else{
                linearLayout = getLinearLayout();
                linearLayout.addView(textView);
            }
        }
    }


//   创建一个布局
    public LinearLayout getLinearLayout(){
        LinearLayout linearLayout = new LinearLayout(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        this.addView(linearLayout);
        return linearLayout;
    }
    public TextView gettextView(){
        TextView view =  new TextView(getContext());
        view.setTextColor(color);
        view.setTextSize(textSize);
        view.setPadding(10,10,10,10);
        view.setBackgroundColor(Color.parseColor("#FFA2ECCA"));
        return view;
    }

}
