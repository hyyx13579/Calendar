package com.founder.cxzx.mycalendar;

import android.annotation.TargetApi;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.founder.cxzx.mycalendar.calendar.CalendarDialog;
import com.founder.cxzx.mycalendar.calendar.MyDatePickerDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyDatePickerDialog myDatePickerDialog;
    private List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        ((Button) findViewById(R.id.btn_calendar_one)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalendarOne();

            }


        });
        ((Button) findViewById(R.id.btn_calendar_two)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalendarTwo();

            }
        });



    }

    private void showCalendarTwo() {
        CalendarDialog dialog = new CalendarDialog(this);
        dialog.show();

    }

    private void showCalendarOne() {

        myDatePickerDialog = new MyDatePickerDialog(this, data);
        myDatePickerDialog.show();
        setDialogListener();
    }


    private void setDialogListener() {
        myDatePickerDialog.setOnDateClickListener(new MyDatePickerDialog.OnDateClickListener() {
            @Override
            public void OnDateClick(int year, int month, int day) {
                String str = year + "-" + month + "-" + day;
                Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });

    }

    //展现ppw
    private void showPpw(final View anchorView) {

        final View contentView = LayoutInflater.from(anchorView.getContext()).inflate(R.layout.popuw_content_top_arrow_layout, null);
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, contentView.getMeasuredHeight(), false);
        //lvPpw = ((ListView) contentView.findViewById(R.id.lv_ppw));
        List<String> data = new ArrayList<String>();
        for (int j = 0; j < 15; j++) {
            data.add(j + "");
        }
       // lvPpw.setAdapter(new ListViewAdapter(context, data));
        contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                autoAdjustArrowPos(popupWindow, contentView, anchorView);
                contentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });


        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(false);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;   // 这里面拦截不到返回键
            }
        });

        popupWindow.showAsDropDown(anchorView);


    }


    private void autoAdjustArrowPos(PopupWindow popupWindow, View contentView, View anchorView) {
        View upArrow = contentView.findViewById(R.id.up_arrow);
        View downArrow = contentView.findViewById(R.id.down_arrow);

        int pos[] = new int[2];
        contentView.getLocationOnScreen(pos);
        int popLeftPos = pos[0];
        anchorView.getLocationOnScreen(pos);
        int anchorLeftPos = pos[0];
        //目标view的x坐标－ppw的x坐标＋view的宽度／2－箭头的宽度／2
        int arrowLeftMargin = anchorLeftPos - popLeftPos + anchorView.getWidth() / 2 - upArrow.getWidth() / 2;
        //判断popupwindow是否超出父控件的高度，true向上箭头，false向下箭头
        upArrow.setVisibility(popupWindow.isAboveAnchor() ? View.INVISIBLE : View.VISIBLE);
        downArrow.setVisibility(popupWindow.isAboveAnchor() ? View.VISIBLE : View.INVISIBLE);

        RelativeLayout.LayoutParams upArrowParams = (RelativeLayout.LayoutParams) upArrow.getLayoutParams();
        upArrowParams.leftMargin = arrowLeftMargin;
        RelativeLayout.LayoutParams downArrowParams = (RelativeLayout.LayoutParams) downArrow.getLayoutParams();
        downArrowParams.leftMargin = arrowLeftMargin;
    }



}
