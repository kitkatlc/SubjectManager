package kitkat.com.subjectmanager.home;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import kitkat.com.subjectmanager.R;
import kitkat.com.subjectmanager.database.subjectdatabase.SubjectAdapter;
import kitkat.com.subjectmanager.database.subjectdatabase.SubjectList;
import kitkat.com.subjectmanager.database.table.SubjectEntity;


public class SubjectActivity extends AppCompatActivity implements View.OnClickListener{


    Button add;
    String account;
    //private String[] a=new String[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_layout);

//        LayoutInflater layoutInflater = LayoutInflater.from(SubjectActivity.this);                            //先获取当前布局的填充器
//        View whichYouWantToUse_findViewById = layoutInflater.inflate(R.layout.item_layout, null);   //通过填充器获取另外一个布局的对象
//        Switch switch6 =whichYouWantToUse_findViewById.findViewById(R.id.switch6);
////        switch6.setOnClickListener(this);
//        switch6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("dwds","switch6");
//                Intent intent1 =new Intent(SubjectActivity.this,ClockchooseActivity.class);
//                startActivity(intent1);
//            }
//        });
        add=findViewById(R.id.add);
        add.setOnClickListener(this);
        Intent intent=getIntent();
        account=intent.getStringExtra("ACCOUNTNAME");
        SubjectList dateList=new SubjectList();
        dateList.setContext(this);
        List<SubjectEntity> list=dateList.getDateList4(dateList.getDateList3(account));//subjectList.getDateList4(subjectList.getDateList3(account));
//        //Toast.makeText(CourseListActivity.this,list.get(0).getCoursename()+"0000"+list.get(0).getYear(),Toast.LENGTH_SHORT).show();
        ListView listView=(ListView) findViewById(R.id.courses_list);
        SubjectAdapter dateAdapter=new SubjectAdapter(SubjectActivity.this,list);
        listView.setAdapter(dateAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SubjectEntity data = list.get(position);
//                Toast.makeText(SubjectActivity.this,"我已点击"+data.getCoursename(),Toast.LENGTH_SHORT).show();
                Switch switch6 = view.findViewById(R.id.switch6);
                switch6.setOnClickListener(SubjectActivity.this::onClick);
                Intent intent5=new Intent(SubjectActivity.this,DetialActivity.class);
                Log.d("baijiedede",account);
                Log.d("baijiedede",data.getCoursename());
                intent5.putExtra("COURSENAME",data.getCoursename());
                intent5.putExtra("ACCOUNTNAME",account);
                startActivity(intent5);
            }
        });
    }

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.add:
                Intent intent=new Intent(SubjectActivity.this,AddSubjectActivity.class);
                intent.putExtra("ACCOUNTNAME",account);
                startActivity(intent);
                break;
            case R.id.switch6:
                Log.e("dwds","switch6");
                Intent intent1 =new Intent(SubjectActivity.this,ClockchooseActivity.class);
                startActivity(intent1);
                break;
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
