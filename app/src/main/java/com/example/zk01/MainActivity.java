package com.example.zk01;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zk01.weight.lsbj;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText bar_ed;
    private Button bar_btn;
    private lsbj main_found;
    private List<String> list = new ArrayList<>();
    private String[] data = new String[]{"考拉三周年人气热销榜", "电动牙刷", "尤妮佳", "豆豆鞋", "沐浴露"
            , "日东红茶", "榴莲", "电动牙刷", "尤妮佳", "豆豆鞋", "沐浴露", "日东红茶", "榴莲", "考拉三周年人气热销榜", "电动牙刷", "尤妮佳", "豆豆鞋", "沐浴露"
            , "日东红茶", "榴莲", "电动牙刷", "尤妮佳", "豆豆鞋", "沐浴露", "日东红茶"};
    private Button main_delete;
    private lsbj main_zuijin;
    private Uri uri = Uri.parse("content://.SQ.provider.SQprovoider/user");
    private ContentResolver resolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < data.length; i++) {
            list.add(data[i]);
        }
        resolver = getContentResolver();
        initView();
    }

    private void initView() {
        bar_ed = (EditText) findViewById(R.id.bar_ed);
        bar_btn = (Button) findViewById(R.id.bar_btn);

        bar_btn.setOnClickListener(this);
        main_found = (lsbj) findViewById(R.id.main_found);
        main_found.getData(list);
        main_delete = (Button) findViewById(R.id.main_delete);
        main_delete.setOnClickListener(this);
        main_zuijin = (lsbj) findViewById(R.id.main_zuijin);
//        查数据库
        List<String> getdata = getdata();
//        将数据库的内容显示到最近搜索中
        main_zuijin.getData(getdata);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bar_btn:
                submit();
                break;
            case R.id.main_delete:
                List<String> getdata = getdata();
                for (int i = 0; i < getdata.size(); i++) {
                    resolver.delete(uri,"context = ?",new String[]{getdata.get(i)});
                }
                main_zuijin.removeAllViews();
                break;
        }
    }

    private void submit() {
        // validate
//       判空
        String ed = bar_ed.getText().toString().trim();
        if (TextUtils.isEmpty(ed)) {
            Toast.makeText(this, "请输入要搜索的内容", Toast.LENGTH_SHORT).show();
            return;
        }else{
            ContentValues values = new ContentValues();
            values.put("context",ed);
            resolver.insert(uri,values);
//            删除所有的控件
            main_zuijin.removeAllViews();
            List<String> getdata = getdata();
//            重新赋值
            main_zuijin.getData(getdata);
        }
    }

//查数据库
    public List<String> getdata(){
        List<String> list = new ArrayList<>();
        Cursor cursor = resolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()){
            String s = cursor.getString(cursor.getColumnIndex("context"));
            list.add(s);
        }
//        将最近输入的显示在最前面
        List<String> str = new ArrayList<>();
        for (int i = list.size()-1; i>=0; i--) {
            str.add(list.get(i));
        }
        return str;
    }
}
