package com.founder.cxzx.mycalendar.calendar;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.founder.cxzx.mycalendar.R;

import java.util.List;



/**
 * Created by QunCheung on 2017/1/13.
 */

public class MyDatePickerDialog extends Dialog {

    public CalendarView calendarView;
    public RelativeLayout backImg;
    public TextView titleName;
    public ImageView more;
    public TextView monthContentTv;
    public TextView lastMonthTv;
    public TextView nextMonthTv;
    private OnDateClickListener listener;
    private List<String> dates;

    public MyDatePickerDialog(Context context, List<String> strings) {
        super(context);
        this.dates = strings;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.calendar_view_dialog);
        initView();
        createParamater();
    }

    /**
     * 设置日期点击事件
     * @param listener
     */
    public void setOnDateClickListener(OnDateClickListener listener) {
        this.listener = listener;
    }

    /**
     * 设置可点击的日期
     * @param dates
     */
    private void setCanClickDate(List<String> dates){
       // calendarView.setOptionalDate(dates);
    }

    /**
     * dialog配置,可以忽略
     */
    private void createParamater() {
        monthContentTv.setText(calendarView.getDate());
        lastMonthTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.setLastMonth();
                monthContentTv.setText(calendarView.getDate());
            }
        });
        nextMonthTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.setNextMonth();
                monthContentTv.setText(calendarView.getDate());
            }
        });
        calendarView.setOnClickDate(new CalendarView.OnClickListener() {
            @Override
            public void onClickDateListener(int year, int month, int day) {
                if (listener != null){
                    listener.OnDateClick(year,month,day);
                    //dismiss();
                }
            }
        });
    }

    private void initView() {
        calendarView = ((CalendarView) findViewById(R.id.dialog_calendar));
        monthContentTv = ((TextView) findViewById(R.id.dialog_calendar_month_tv));
        lastMonthTv = ((TextView) findViewById(R.id.dialog_calendar_last_month_tv));
        nextMonthTv = ((TextView) findViewById(R.id.dialog_calendar_next_month_tv));
        //setCanClickDate(dates);
    }
    public interface OnDateClickListener{
        void OnDateClick(int year, int month, int day);
    }

}
