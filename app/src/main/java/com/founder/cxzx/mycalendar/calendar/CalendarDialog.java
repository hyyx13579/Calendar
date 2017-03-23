package com.founder.cxzx.mycalendar.calendar;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.GridView;
import android.widget.TextView;

import com.founder.cxzx.mycalendar.R;

import java.util.Calendar;

/**
 * Created by hyyx on 2017/3/23.
 */

public class CalendarDialog extends Dialog {
    private TextView tvLastMonth;
    private TextView tvNextMonth;
    private TextView tvMonth;
    private GridView gv;
    private Context context;
    private CalendarAdapter adapter;
    private int mCurYear;
    private int mCurMonth;
    private int mCurDate;

    public CalendarDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.calendar_dialog_from_gv);
        initView();
        dealView();
    }

    private void dealView() {

        tvLastMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLastMonth();

            }
        });
        tvNextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNextMonth();

            }
        });
    }

    private void setNextMonth() {
        mCurMonth++;
        if (mCurMonth > 12) {
            mCurMonth = 1;
            mCurYear++;
        }
        adapter.setCurrentYearAndMounth(mCurYear + "", mCurMonth + "");
        tvMonth.setText(mCurYear + "-" + mCurMonth);


    }

    private void setLastMonth() {
        mCurMonth--;
        if (mCurMonth == 0) {
            mCurMonth = 12;
            mCurYear--;
        }
        adapter.setCurrentYearAndMounth(mCurYear + "", mCurMonth + "");
        tvMonth.setText(mCurYear + "-" + mCurMonth);

    }

    private void initView() {

        tvLastMonth = ((TextView) findViewById(R.id.dialog_calendar_fromGv_last_month_tv));
        tvNextMonth = ((TextView) findViewById(R.id.dialog_calendar_fromGv_next_month_tv));
        tvMonth = ((TextView) findViewById(R.id.dialog_calendar_fromGv_month_tv));
        gv = ((GridView) findViewById(R.id.dialog_calendar_fromGv_gv));
        adapter = new CalendarAdapter(context);
        gv.setAdapter(adapter);
        Calendar calendar = Calendar.getInstance();
        mCurYear = calendar.get(Calendar.YEAR);
        mCurMonth = calendar.get(Calendar.MONTH) + 1;
        mCurDate = calendar.get(Calendar.DATE);
        adapter.setCurrentYearAndMounth(mCurYear + "", mCurMonth + "");
        tvMonth.setText(mCurYear + "-" + mCurMonth);
    }

}
