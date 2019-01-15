package kitkat.com.subjectmanager.database.subjectdatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import kitkat.com.subjectmanager.database.table.SubjectEntity;
import kitkat.com.subjectmanager.home.SubjectDatabaseHelper;

public class SubjectList {

    private static final String TAG = SubjectList.class.getSimpleName();
    SubjectDatabaseHelper dpHelper;
    Context mcontext;
    SubjectEntity dayMassage = new SubjectEntity();

    public void setContext(Context context) {
        mcontext = context;
    }

    public List<SubjectEntity> getDateList1(String account) {
        List<SubjectEntity> list = new ArrayList<SubjectEntity>();
        dpHelper = new SubjectDatabaseHelper(mcontext, "BookStore.db", null, 1);
        SQLiteDatabase db = dpHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + account, null);
        if (cursor.moveToFirst()) {
            do {
                String coursename = cursor.getString(cursor.getColumnIndex("course_name"));
                String coursenum = cursor.getString(cursor.getColumnIndex("course_num"));
                Cursor cursor1 = db.rawQuery("select * from " + account + "with" + coursenum, null);
                if (cursor1.moveToFirst()) {
                    do {
                        String list_coursename = cursor1.getString(cursor1.getColumnIndex("coursename"));
                        String list_coursenum = cursor1.getString(cursor1.getColumnIndex("coursenum"));
                        int list_startweek = cursor1.getInt(cursor1.getColumnIndex("startweek"));
                        int list_endweek = cursor1.getInt(cursor1.getColumnIndex("endweek"));
                        String list_room = cursor1.getString(cursor1.getColumnIndex("room"));
                        int list_weekday = cursor1.getInt(cursor1.getColumnIndex("weekday"));
                        int list_lesson = cursor1.getInt(cursor1.getColumnIndex("lesson"));
                        int list_year = cursor1.getInt(cursor1.getColumnIndex("year"));
                        int list_month = cursor1.getInt(cursor1.getColumnIndex("month"));
                        int list_day = cursor1.getInt(cursor1.getColumnIndex("day"));
                        Date date = StringToDate(list_year, list_month, list_day);
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        calendar.add(calendar.DAY_OF_MONTH, (list_startweek -2) * 7 );
                        Date datestart = calendar.getTime();
                        calendar.add(calendar.DAY_OF_MONTH, (list_endweek - list_startweek+1) * 7 );
                        Date dateend = calendar.getTime();
                        SubjectEntity dayMassage = new SubjectEntity();
                        dayMassage.setCoursenum(list_coursenum);
                        dayMassage.setCourse(list_coursename);
                        dayMassage.setStartweek(datestart);
                        dayMassage.setEndweek(dateend);
                        dayMassage.setWeekday(list_weekday);
                        dayMassage.setRoom(list_room);
                        dayMassage.setLesson(list_lesson);
                        list.add(dayMassage);
                    } while (cursor1.moveToNext());
                }
            } while (cursor.moveToNext());
        }
        return list;
    }

    public Date StringToDate(int year, int month, int day) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        String str = year + "-" + month + "-" + day;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date = java.sql.Date.valueOf(str);
        return date;
    }

    public List<SubjectEntity> getDateList(String account) {
        List<SubjectEntity> list = getDateList1(account);
        Calendar calendar = Calendar.getInstance();
        Date today = new Date();
        calendar.setTime(today);
        calendar.add(calendar.YEAR, 1);
        Date endday = calendar.getTime();
        List<SubjectEntity> list1 = new ArrayList<SubjectEntity>();
        while (today.compareTo(endday) <= 0) {
            calendar.setTime(today);
            //业务处理...
            for (int b = 0; b < list.size(); b++) {
                SubjectEntity dayMassage = new SubjectEntity();
                dayMassage = list.get(b);
                Date datestart = dayMassage.getStartweek();
                //Date dateend=dayMassage.getEndweek();
                //if(calendar.getTime().compareTo(datestart)>=0){
                //if(calendar.getTime().compareTo(dateend)<=0){
                //if(dayMassage.getWeekday()==getWeekOfDate(today)){
                dayMassage.setCourse(dayMassage.getCoursename());
                dayMassage.setYear1(calendar.get(calendar.YEAR));
                dayMassage.setMonth1(calendar.get(calendar.MONTH) + 1);
                dayMassage.setDay1(calendar.get(calendar.DATE));
                if (dayMassage.getWeekday() == 1) {
                    dayMassage.setWEEK("星期一");
                }
                if (dayMassage.getWeekday() == 2) {
                    dayMassage.setWEEK("星期二");
                }
                if (dayMassage.getWeekday() == 3) {
                    dayMassage.setWEEK("星期三");
                }
                if (dayMassage.getWeekday() == 4) {
                    dayMassage.setWEEK("星期四");
                }
                if (dayMassage.getWeekday() == 5) {
                    dayMassage.setWEEK("星期五");
                }
                if (dayMassage.getWeekday() == 6) {
                    dayMassage.setWEEK("星期六");
                }
                if (dayMassage.getWeekday() == 7) {
                    dayMassage.setWEEK("星期天");
                }
                list1.add(dayMassage);
                // }
                //}
                // }
            }
            //循环
            calendar.add(calendar.DATE, 1);
            today = calendar.getTime();
        }
        return list1;
    }

    public int getWeekOfDate(Date date) {
        int[] weekDays = {3, 4, 5, 6, 7, 1, 2};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public List<List<SubjectEntity>> getDateList3(String account) {


        List<SubjectEntity> list1 = getDateList1(account);

        List<List<SubjectEntity>> list3 = new ArrayList<>();

        List<SubjectEntity> list2;

        Calendar calendar = Calendar.getInstance();

        List<SubjectEntity> list4 = getDateList1(account);


        calendar.setTime(new Date());Date today = new Date();
        long todayMills = today.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 180);
        long enddayMills = calendar.getTime().getTime();
       // Log.e(TAG, "today = " + todayMills + ", enday = " + enddayMills);
        while (todayMills <= enddayMills) {
            Log.e(TAG, String.valueOf(todayMills));
            calendar.setTime(new Date(todayMills));
            list2 = new ArrayList<>();
            //do
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year=calendar.get(Calendar.YEAR);
            String U = null;
            if (month < 10 && day > 9) {
                U = year + "-" + "0" + month + "-" + day + " ";
            }
            if (month > 9 && day > 9) {
                U = year + "-" + month + "-" + day + " ";
            }
            if (month < 10 && day < 10) {
                U = year + "-" + "0" + month + "-" + "0" + day + " ";
            }
            if (month > 9 && day < 10) {
                U = year + "-" + month + "-" + "0" + day + " ";
            }
            Date a = null;
            try {
                DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd ");
                a = format1.parse(U);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Log.d("buxiaode",a+"");
            for (int i = 0; i < list1.size(); i++) {
                dayMassage = new SubjectEntity();
                dayMassage.setStartweek(list4.get(i).getStartweek());
                dayMassage.setEndweek(list4.get(i).getEndweek());
                //getWeekOfDate(a)
                dayMassage.setWeekday(list4.get(i).getWeekday());
                if (a.compareTo(dayMassage.getStartweek()) >= 0) {
                    if (a.compareTo(dayMassage.getEndweek()) <= 0) {
                        if (dayMassage.getWeekday() == getWeekOfDate(a)) {
                            dayMassage.setCourse(list4.get(i).getCoursename());
                            dayMassage.setLesson(list4.get(i).getLesson());
                            dayMassage.setRoom(list4.get(i).getRoom());
                            dayMassage.setDay1(day);
                            dayMassage.setMonth1(month + 1);
                            if (dayMassage.getWeekday() == 1) {
                                dayMassage.setWEEK("星期一");
                            }
                            if (dayMassage.getWeekday() == 2) {
                                dayMassage.setWEEK("星期二");
                            }
                            if (dayMassage.getWeekday() == 3) {
                                dayMassage.setWEEK("星期三");
                            }
                            if (dayMassage.getWeekday() == 4) {
                                dayMassage.setWEEK("星期四");
                            }
                            if (dayMassage.getWeekday() == 5) {
                                dayMassage.setWEEK("星期五");
                            }
                            if (dayMassage.getWeekday() == 6) {
                                dayMassage.setWEEK("星期六");
                            }
                            if (dayMassage.getWeekday() == 7) {
                                dayMassage.setWEEK("星期天");
                            }
                            list2.add(dayMassage);
                        }
                    }

                }
            }
            list3.add(list2);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            todayMills = calendar.getTime().getTime();
        }
        return list3;
    }

    public List<SubjectEntity> getDateList4(List<List<SubjectEntity>> list) {
        List<SubjectEntity> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            list1.addAll(list.get(i));
        }
        return list1;
    }
}