package kitkat.com.subjectmanager.home;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import kitkat.com.subjectmanager.R;

public class AddSubjectActivity extends AppCompatActivity {
    EditText editText_coursename;
    EditText editText_coursenum;
    EditText editText_startweek;
    EditText editText_endweek;
    EditText editText_room;
    EditText editText_weekday;
    EditText editText_lesson;


    SubjectDatabaseHelper dpHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        final Intent intent=getIntent();
        final String account=intent.getStringExtra("ACCOUNTNAME");
        String coursename;
        String coursenum;
        String startweek;
        String endweek;
        String room;
        String weekday;
        String lesson;
//        Log.d("6666",account);
        editText_coursename=findViewById(R.id.subject_name);
        editText_coursenum=findViewById(R.id.subject_num);
        editText_startweek=findViewById(R.id.subject_startweek);
        editText_endweek=findViewById(R.id.subject_endweek);
        editText_room=findViewById(R.id.subject_room);
        editText_weekday=findViewById(R.id.subject_courseweek);
        editText_lesson=findViewById(R.id.subject_lessonnum);
        Button commit=findViewById(R.id.subject_add);
        dpHelper=new SubjectDatabaseHelper(this,"BookStore.db",null,1);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String coursename=editText_coursename.getText().toString();
                String coursenum=editText_coursenum.getText().toString();
                String startweek=editText_startweek.getText().toString();
                String endweek=editText_endweek.getText().toString();
                String room=editText_room.getText().toString();
                String weekday=editText_weekday.getText().toString();
                String lesson=editText_lesson.getText().toString();
                if(TextUtils.isEmpty(coursename)||TextUtils.isEmpty(coursenum)||TextUtils.isEmpty(startweek)||TextUtils.isEmpty(endweek)||TextUtils.isEmpty(room)||TextUtils.isEmpty(weekday)||TextUtils.isEmpty(lesson)){
                    Toast.makeText(AddSubjectActivity.this,"输入不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    Date date =new Date();
                    Calendar calendar=Calendar.getInstance();
                    calendar.setTime(date);
                    int add_year=calendar.get(Calendar.YEAR);
                    int add_month=calendar.get(Calendar.MONTH)+1;
                    int add_day=calendar.get(Calendar.DATE);
                    SQLiteDatabase db=dpHelper.getWritableDatabase();
                    Cursor cursor=db.rawQuery("select * from "+account+" where course_num=?",new String[]{coursenum});
                    if(cursor.getCount()==0){
                        ContentValues values=new ContentValues();
                        values.put("course_name",coursename);
                        values.put("course_num",coursenum);
                        db.insert(account,null,values);
                        values.clear();
                        String sql="create table "+account+"with"+coursenum+"(coursename text ,coursenum text,startweek integer ,endweek integer,room text ,weekday integer,lesson integer,year integer,month integer,day integer)";
                        db.execSQL(sql);
                    }
                        ContentValues values=new ContentValues();
                        values.put("coursename",coursename);
                        values.put("coursenum",coursenum);
                        values.put("startweek",startweek);
                        values.put("endweek",endweek);
                        values.put("room",room);
                        values.put("weekday",weekday);
                        values.put("lesson",lesson);
                        values.put("year",add_year);
                        values.put("month",add_month);
                        values.put("day",add_day);
                        db.insert(account+"with"+coursenum,null,values);
                    Intent intent1=new Intent(AddSubjectActivity.this,SubjectActivity.class);
                    intent1.putExtra("ACCOUNTNAME",account);
                    startActivity(intent1);
                }
            }
        });
    }
}
