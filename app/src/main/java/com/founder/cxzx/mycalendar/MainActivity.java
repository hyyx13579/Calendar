package com.founder.cxzx.mycalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

            }
        });

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

}
