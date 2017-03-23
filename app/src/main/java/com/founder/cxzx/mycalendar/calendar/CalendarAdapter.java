package com.founder.cxzx.mycalendar.calendar;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.founder.cxzx.mycalendar.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by hyyx on 16/9/5.
 */
public class CalendarAdapter extends BaseAdapter {


    private final LayoutInflater systemService;
    private Context context;
    private List<String> data;
    private String currentYear;
    private String currentMouth;
    private int dayOfWeek = 0;
    private GridView gv;
    private int currentMouthDay = 0;
    private int k = 0;


    public CalendarAdapter(Context context) {
        this.context = context;
        systemService = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        data = new ArrayList<>();

    }

    public void setCurrentYearAndMounth(String year, String mouth) {
        data.clear();
        currentYear = year;
        currentMouth = mouth;
        dealCurrentMouthToOneDay(currentYear, currentMouth);
        int j = 1;

        if (currentMouthDay + dayOfWeek <= 35) {
            k = 35 - (currentMouthDay + dayOfWeek);
        } else if (currentMouthDay + dayOfWeek > 35 && currentMouthDay + dayOfWeek <= 42) {
            k = 42 - (currentMouthDay + dayOfWeek);
        }
        for (int i = 0; i < currentMouthDay + k + dayOfWeek; i++) {
            if (i >= dayOfWeek && i < dayOfWeek + currentMouthDay) {
                data.add(j + "");
                j++;
            } else if (i < dayOfWeek) {
                data.add("0");
            } else if (i >= dayOfWeek + currentMouthDay) {
                data.add("0");
            }
        }
        notifyDataSetChanged();

    }

    public int setDayOfWork() {
        return dayOfWeek;
    }

    public int setCell() {
        return k;
    }


    public void dealCurrentMouthToOneDay(String currentYear, String currentMouth) {
        Calendar calendar = Calendar.getInstance();
        String dateStr = currentYear + "-" + currentMouth + "-01";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            calendar.setTime(format.parse(dateStr));

            switch (calendar.get(Calendar.DAY_OF_WEEK)) {
                case 1: //周日

                    dayOfWeek = 0;

                    break;
                case 2: //周一

                    dayOfWeek = 1;

                    break;
                case 3:

                    dayOfWeek = 2;

                    break;
                case 4:

                    dayOfWeek = 3;

                    break;
                case 5:

                    dayOfWeek = 4;

                    break;
                case 6:

                    dayOfWeek = 5;

                    break;
                case 7:

                    dayOfWeek = 6;

                    break;
                default:
                    break;
            }


        } catch (Exception e) {
            // TODO: handle exception
        }


        switch (Integer.parseInt(currentMouth)) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                currentMouthDay = 31;
                break;
            //对于2月份需要判断是否为闰年
            case 2:
                if ((Integer.parseInt(currentYear) % 4 == 0 && (Integer.parseInt(currentYear) % 100 != 0) || ((Integer.parseInt(currentYear) % 400 == 0)))) {
                    currentMouthDay = 29;
                    break;
                } else {
                    currentMouthDay = 28;
                    break;
                }
            case 4:
            case 6:
            case 9:
            case 11:
                currentMouthDay = 30;
                break;

        }


    }


    @Override
    public int getCount() {
        return data != null ? (currentMouthDay + dayOfWeek + k) : 0;
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = systemService.inflate(R.layout.item_date, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
            //  holder.update();
        } else {
            holder = ((ViewHolder) view.getTag());
        }

        if (!"0".equals(data.get(i))) {
            holder.cell.setVisibility(View.VISIBLE);
            holder.tvCurrentDay.setText(data.get(i));
        } else {
            holder.cell.setVisibility(View.GONE);
        }

        // TODO: 16/9/19 对日历上面做具体判断的逻辑 


        return view;
    }


    static class ViewHolder {
        private RelativeLayout cell;
        private TextView tvCurrentDay;

        public ViewHolder(View convertView) {
            tvCurrentDay = ((TextView) convertView.findViewById(R.id.current_day));
            cell = ((RelativeLayout) convertView.findViewById(R.id.cell));
        }

    }


}
