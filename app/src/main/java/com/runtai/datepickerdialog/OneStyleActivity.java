package com.runtai.datepickerdialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * @作者：高炎鹏
 * @日期：2016/12/20时间08:48
 * @描述：系统自带日期时间选择器
 */
public class OneStyleActivity extends Activity implements View.OnClickListener {

    private EditText one_et_date, one_et_time;
    private Button one_bt_date, one_bt_time;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        initView();
    }

    private void initView() {
        one_et_date = (EditText) findViewById(R.id.one_et_date);
        one_et_time = (EditText) findViewById(R.id.one_et_time);
        one_bt_date = (Button) findViewById(R.id.one_bt_date);
        one_bt_time = (Button) findViewById(R.id.one_bt_time);
        one_bt_date.setOnClickListener(this);
        one_bt_time.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.one_bt_date:
                calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int monthOfYear = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(OneStyleActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        one_et_date.setText(year + "-" + (month + 1) + "-" + day);
                    }
                }, year, monthOfYear, dayOfMonth);
                dpd.show();
                break;
            case R.id.one_bt_time:
                calendar = Calendar.getInstance();
                int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog tpd = new TimePickerDialog(OneStyleActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker arg0, int hourOfDay, int minute) {
                        one_et_time.setText(hourOfDay + ":" + minute);
                    }
                }, hourOfDay, minute, true);
                tpd.show();
                break;
            default:
                break;
        }
    }
}
