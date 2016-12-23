package com.runtai.datepickerdialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.runtai.datetimepickerlib.OptionsPopupWindow;
import com.runtai.datetimepickerlib.TimePopupWindow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @作者：高炎鹏
 * @日期：2016/12/20时间08:48
 * @描述：多级联动时间选择器
 */
public class TwoStyleActivity extends Activity implements View.OnClickListener {

    private EditText two_et_city, two_et_time;
    private Button two_bt_city, two_bt_time;
    TimePopupWindow pwTime;
    OptionsPopupWindow pwOptions;

    private ArrayList<String> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        initView();
        initSetCity();
        initSetTime();
    }

    private void initSetCity() {
        // 选项选择器
        pwOptions = new OptionsPopupWindow(this);

        // 选项1
        options1Items.add("广东");
        options1Items.add("湖南");

        // 选项2
        ArrayList<String> options2Items_01 = new ArrayList<>();
        options2Items_01.add("广州");
        options2Items_01.add("佛山");
        options2Items_01.add("东莞");
        ArrayList<String> options2Items_02 = new ArrayList<>();
        options2Items_02.add("长沙");
        options2Items_02.add("岳阳");
        options2Items.add(options2Items_01);
        options2Items.add(options2Items_02);

        // 选项3
        ArrayList<ArrayList<String>> options3Items_01 = new ArrayList<>();
        ArrayList<ArrayList<String>> options3Items_02 = new ArrayList<>();
        ArrayList<String> options3Items_01_01 = new ArrayList<>();
        options3Items_01_01.add("白云");
        options3Items_01_01.add("天河");
        options3Items_01_01.add("海珠");
        options3Items_01_01.add("越秀");
        options3Items_01.add(options3Items_01_01);
        ArrayList<String> options3Items_01_02 = new ArrayList<>();
        options3Items_01_02.add("南海");
        options3Items_01.add(options3Items_01_02);
        ArrayList<String> options3Items_01_03 = new ArrayList<>();
        options3Items_01_03.add("常平");
        options3Items_01_03.add("虎门");
        options3Items_01.add(options3Items_01_03);

        ArrayList<String> options3Items_02_01 = new ArrayList<>();
        options3Items_02_01.add("长沙1");
        options3Items_02.add(options3Items_02_01);
        ArrayList<String> options3Items_02_02 = new ArrayList<>();
        options3Items_02_02.add("岳1");
        options3Items_02.add(options3Items_02_02);

        options3Items.add(options3Items_01);
        options3Items.add(options3Items_02);

        // 三级联动效果
        pwOptions.setPicker(options1Items, options2Items, options3Items, true);
        // 设置选择的三级单位
        pwOptions.setLabels("省", "市", "区");
        // 设置默认选中的三级项目
        pwOptions.setSelectOptions(0, 0, 0);
        // 监听确定选择按钮
        pwOptions.setOnoptionsSelectListener(new OptionsPopupWindow.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                // 返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1)
                        + options2Items.get(options1).get(option2)
                        + options3Items.get(options1).get(option2)
                        .get(options3);
                two_et_city.setText(tx);
            }
        });
    }

    private void initSetTime() {
        // 时间选择器
        pwTime = new TimePopupWindow(this, TimePopupWindow.Type.ALL);
        // 时间选择后回调
        pwTime.setOnTimeSelectListener(new TimePopupWindow.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                two_et_time.setText(getTime(date));
            }
        });
    }

    private void initView() {
        two_et_city = (EditText) findViewById(R.id.two_et_city);
        two_et_time = (EditText) findViewById(R.id.two_et_time);
        two_bt_city = (Button) findViewById(R.id.two_bt_city);
        two_bt_time = (Button) findViewById(R.id.two_bt_time);
        two_bt_city.setOnClickListener(this);
        two_bt_time.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.two_bt_city:
                pwOptions.showAtLocation(two_bt_city, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.two_bt_time:
                pwTime.showAtLocation(two_bt_time, Gravity.BOTTOM, 0, 0, new Date());
                break;
            default:
                break;
        }
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }
}
