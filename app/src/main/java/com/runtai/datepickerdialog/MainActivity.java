package com.runtai.datepickerdialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.runtai.slidedatetimepickerlibrary.SlideDateTimeListener;
import com.runtai.slidedatetimepickerlibrary.SlideDateTimePicker;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public Button bt_1, bt_2, bt_3, bt_4;
    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        bt_1 = (Button) findViewById(R.id.bt_1);
        bt_2 = (Button) findViewById(R.id.bt_2);
        bt_3 = (Button) findViewById(R.id.bt_3);
        bt_4 = (Button) findViewById(R.id.bt_4);
        bt_1.setOnClickListener(this);
        bt_2.setOnClickListener(this);
        bt_3.setOnClickListener(this);
        bt_4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_1:
                intent = new Intent(MainActivity.this, OneStyleActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_2:
                intent = new Intent(MainActivity.this, TwoStyleActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_3:
                intent = new Intent(MainActivity.this, ThreeStyleActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_4://日期时间选择器组合控件
                new SlideDateTimePicker.Builder(getSupportFragmentManager())
                        .setListener(listener)
                        .setInitialDate(new Date())//设置初始化时间
                        //.setMinDate(minDate)//设置最小日期显示
                        //.setMaxDate(maxDate)//设置最大日期显示
                        .setIs24HourTime(true)//设置是否是24小时置
                        //.setTheme(SlideDateTimePicker.HOLO_DARK)//设置显示的主题光
                        //.setIndicatorColor(Color.parseColor("#990000"))//设置导航线颜色
                        .build()
                        .show();
                break;
            default:
                break;
        }
    }

    private SimpleDateFormat mFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private SlideDateTimeListener listener = new SlideDateTimeListener() {
        @Override
        public void onDateTimeSet(Date date) {
            Toast.makeText(MainActivity.this, mFormatter.format(date), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDateTimeCancel() {
            Toast.makeText(MainActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
        }
    };
}
