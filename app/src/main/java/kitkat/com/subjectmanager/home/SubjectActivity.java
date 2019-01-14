package kitkat.com.subjectmanager.home;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import kitkat.com.subjectmanager.R;
import kitkat.com.subjectmanager.database.subjectdatabase.SubjectList;
import kitkat.com.subjectmanager.database.table.SubjectEntity;

public class SubjectActivity extends AppCompatActivity implements View.OnClickListener{

    private Switch switch_1;
    Button add;
    String account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_layout);
//        Intent intent=new Intent(SubjectActivity.this,DetialActivity.class);
//        startActivity(intent);
        add=findViewById(R.id.add);
        switch_1=findViewById(R.id.switch_1);
        switch_1.setOnClickListener(this);
        add.setOnClickListener(this);
        Intent intent=getIntent();
        account=intent.getStringExtra("accountcoursetable");
        SubjectList dateList=new SubjectList();
        dateList.setContext(this);
        List<SubjectEntity> list=dateList.getDateList(account);
        //Toast.makeText(CourseListActivity.this,list.get(0).getCoursename()+"0000"+list.get(0).getYear(),Toast.LENGTH_SHORT).show();
        ListView listView=findViewById(R.id.course_list);
        kitkat.com.subjectmanager.home.SubjectAdapter dateAdapter=new kitkat.com.subjectmanager.home.SubjectAdapter(CourseListActivity.this,list);
        listView.setAdapter(dateAdapter);
    }
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.switch_1:
                Alarmanager();
                Intent intent4=new Intent(SubjectActivity.this,ClockchooseActivity.class);
                startActivity(intent4);
                Toast.makeText(SubjectActivity.this,"你已开启闹钟，请选择提醒模式",Toast.LENGTH_SHORT).show();
                break;
            case R.id.add:
                Intent intent5=new Intent(SubjectActivity.this,AddSubjectActivity.class);
                startActivity(intent5);
                default:
                    break;
        }
    }
    public void Alarmanager()
    {
        AlarmManager alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);
        long current =System.currentTimeMillis();
        Intent intent2=new Intent();
        intent2.setAction("kitkat.com.subjectmanager.action.alarm");
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,intent2,0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,current+10000,60000,pendingIntent);
    }

}
